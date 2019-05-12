/**
 * 
 */
package cn.com.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/**
 * @author Think
 *
 */
public class ChannelTest {

	/**
	 * 字符集
	 * 
	 * @throws Exception
	 */
	@Test
	public void t5() throws Exception {
		Charset.availableCharsets().forEach((x, v) -> {
			System.out.println(x + "->" + v);
		});
		Charset cs = Charset.forName("GBK");
		ByteBuffer bb = ByteBuffer.allocate(1023);
		bb.put(new String("中国人民").getBytes());
		
		cs.decode(bb);
		bb.flip();
	}

	/**
	 * 
	 * 分散读取 将通道《channel》中的数据分散到多个buffer中 聚集写入 将多个bufferz中的数据聚集到通道《channel》中
	 * 
	 * @throws Exception
	 */

	@Test
	public void t4() throws Exception {
		RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");

		FileChannel channel1 = raf1.getChannel();

		ByteBuffer buf1 = ByteBuffer.allocate(100);
		ByteBuffer buf2 = ByteBuffer.allocate(1024);
		ByteBuffer by[] = { buf1, buf2 };
		channel1.read(by);
		for (ByteBuffer b : by) {
			b.flip();
		}

		System.out.println(new String(by[0].array(), 0, by[0].limit()));
		System.out.println(new String(by[1].array(), 0, by[1].limit()));

	}

	// 通道之间的传输
	@Test
	public void t3() throws Exception {
		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ,
				StandardOpenOption.CREATE);

		inChannel.transferTo(0, inChannel.size(), outChannel);

		outChannel.close();
		inChannel.close();
	}

	@Test
	public void t2() throws Exception {
		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ,
				StandardOpenOption.CREATE);
		// 内存映射文件
		MappedByteBuffer inMbb = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		MappedByteBuffer outMbb = outChannel.map(MapMode.READ_WRITE, 0, inChannel.size());
		byte b[] = new byte[inMbb.limit()];
		inMbb.get(b);
		outMbb.put(b);
		outChannel.close();
		inChannel.close();
	}

	@Test
	public void t1() throws Exception {
		try (FileInputStream fis = new FileInputStream("1.jpg");
				FileOutputStream fos = new FileOutputStream("2.jpg");) {
			FileChannel inChannel = fis.getChannel();
			FileChannel outChannel = fos.getChannel();

			ByteBuffer fug = ByteBuffer.allocate(1024);

			while (inChannel.read(fug) != -1) {
				fug.flip();
				outChannel.write(fug);
			}
		}

	}

}
