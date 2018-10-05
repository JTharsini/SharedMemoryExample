package com.jeya.sharedmemory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Server {
	public static void main(String[] args) {
		String FILE_NAME = "../JayJay.txt";
		//File tempFile = new File(System.getProperty("java.io.tmpdir", "lock-test.dat"));
		System.setProperty("file.encoding", "ANSI");
		File tempFile = new File(FILE_NAME);
		
		try {
			//FileChannel channel = FileChannel.open(tempFile.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
			FileChannel channel = new RandomAccessFile(tempFile, "rw").getChannel();
			
			long RECORDS = 512;
			int RECORD_SIZE = 8;
			MappedByteBuffer b = channel.map(FileChannel.MapMode.READ_WRITE, 0, RECORDS * RECORD_SIZE);
			
			CharBuffer charBuf = b.asCharBuffer();
			
			char[]string = "Hello client\0".toCharArray();
			charBuf.put(string); // "UTF8"
			
			System.out.println("Waiting for client...");
			
			while(charBuf.get(0) != '\0');
			System.out.println("Finished waiting.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}