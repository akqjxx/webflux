/**
 * 
 */
package cn.com.et.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
	private volatile int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void increment() throws Exception {
		lock.lock();
		try {
			while (number != 0) {
				condition.await();
			}
			number++;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void decrement() throws Exception {
		lock.lock();
		try {
			while (number == 0) {
				condition.await();
			}
			number--;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

/**
 * @author Think
 *
 */
public class ComsummerDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ShareData shareData = new ShareData();
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					shareData.increment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "A").start();
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					shareData.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "B").start();
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					shareData.increment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "C").start();
		new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					shareData.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "D").start();
	}

}
