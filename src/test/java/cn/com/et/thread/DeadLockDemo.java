package cn.com.et.thread;

import java.util.concurrent.TimeUnit;

class LockHandle implements Runnable{
	
	private String lockA ;
	private String lockB;
	public LockHandle(String lockA ,String lockB) {
		this.lockA=lockA;
		this.lockB=lockB;
	}
	public void run() {
		synchronized (lockA) {
			System.out.println(Thread.currentThread().getName()+"\t "+lockA+"等待"+lockB);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (lockB) {
				System.out.println(Thread.currentThread().getName()+"\t "+lockB+"等待"+lockA);
			}
		}
	}
}

public class DeadLockDemo {

	public static void main(String[] args) {
		
		
		String lockA ="lockA";
		String lockB = "lockB";
		new Thread(new LockHandle(lockA,lockB),"AAA" ).start();
		new Thread(new LockHandle(lockB,lockA),"BBB" ).start();
	}

}
