/**
 * 
 */
package cn.com.et.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Tcall implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		System.out.println("0--------------");
		return 123;
	}
}

/**
 * @author Think
 *
 */
public class CallTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FutureTask<Integer> task = new FutureTask<>(new Tcall());
		new Thread(task).start();
		try {
			System.out.println(task.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
