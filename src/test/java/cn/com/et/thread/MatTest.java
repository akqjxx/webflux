/**
 * 
 */
package cn.com.et.thread;

import java.util.UUID;

/**
 * @author Think
 *
 */
public class MatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "";
		while (true) {
			str = str+ str+ UUID.randomUUID().toString();
		}
		
	}
	public static void t() {
		t();
	}

}
