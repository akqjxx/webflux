package cn.com.nio;

import java.nio.ByteBuffer;

import org.junit.Test;

public class TestBuffer {

	/*
	 * capacity 最大容量，不能改变
	 * limit，界限，表示缓冲区中可以操作的数据的大小，limit后面的数据是不能读写的。
	 * position 位置，表示缓冲区中正在操作数据的位置
	 * position<limit<capacity
	 * 
	 * 
	 * 
	 * */
	@Test
	public void t1()
	{
		
	}
	@Test
	public  void main() {
		
		// Invariants: mark <= position <= limit <= capacity
		ByteBuffer bbf = ByteBuffer.allocate(1024);
		System.out.println(bbf.capacity());
		System.out.println(bbf.limit());
		System.out.println(bbf.position());
		System.out.println(bbf.mark());
		String str="abcdef";
		bbf.put(str.getBytes());
		System.out.println(bbf.getClass().getName());
		System.out.println("--------------1------------");
		System.out.println(bbf.capacity());
		System.out.println(bbf.limit());
		System.out.println(bbf.position());
		System.out.println(bbf.mark());
		bbf.flip();
		System.out.println("------------2--------------");
		System.out.println(bbf.capacity());
		System.out.println(bbf.limit());
		System.out.println(bbf.position());
		System.out.println(bbf.mark());

		byte[] b = new byte[bbf.limit()];
		bbf.get(b);
		System.out.println("--------------3------------");
		System.out.println(bbf.capacity());
		System.out.println(bbf.limit());
		System.out.println(bbf.position());
		System.out.println(bbf.mark());
		
		bbf.rewind();
		System.out.println("--------------4------------");
		System.out.println(bbf.capacity());
		System.out.println(bbf.limit());
		System.out.println(bbf.position());
		System.out.println(bbf.mark());
		bbf.clear();
		System.out.println("--------------5------------");
		System.out.println(bbf.capacity());
		System.out.println(bbf.limit());
		System.out.println(bbf.position());
		System.out.println(bbf.mark());		
		
		

	}

}
