/**
 * 
 */
package cn.com.et.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Think
 *
 */
public class TestABCPrint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ABCDemo demo = new ABCDemo();
		for (int i = 0; i < 20; i++) {
			new Thread(() -> {
				demo.ptA();
			}, "A").start();
			new Thread(() -> {
				demo.ptB();
			}, "B").start();
			new Thread(() -> {
				demo.ptC();
			}, "C").start();
		}

	}

}

class ABCDemo {
	private int number = 1;
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();

	public void ptA() {
		lock.lock();
		try {
			while (number != 1) {
				condition1.await();
			}
			for (int i = 1; i < 2; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			number = 2;
			condition2.signal();
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
	}

	public void ptB() {
		lock.lock();
		try {
			while (number != 2) {
				condition1.await();
			}
			for (int i = 1; i < 2; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			number = 3;
			condition3.signal();
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
	}

	public void ptC() {
		lock.lock();
		try {
			while (number != 3) {
				condition1.await();
			}
			for (int i = 1; i < 2; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			number = 1;
			condition1.signal();
		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
	}

}