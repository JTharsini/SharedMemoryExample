package com.jeya.sharedmemory;

import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.StandardOpenOption;

public class Client {

	public static void main(String[] args) {
		String FILE_NAME = "../JayJay.txt";
		File file = new File(FILE_NAME);
		
		try {
			FileChannel channel = FileChannel.open(file.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
			
			MappedByteBuffer b = channel.map(MapMode.READ_WRITE, 0, 4096);
			CharBuffer charBuf = b.asCharBuffer();
			
			System.out.println("Hello Server...");
			
			char c;
			while((c = charBuf.get()) != 0)
			{
				System.out.println(c);
			}
			System.out.println();
			
			charBuf.put(0, '\0');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}