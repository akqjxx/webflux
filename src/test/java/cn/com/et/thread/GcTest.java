/**
 * 
 */
package cn.com.et.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Think
 *
 */
public class GcTest {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		System.out.println("hello");
		TimeUnit.MINUTES.sleep(Integer.MAX_VALUE);
	}

}
