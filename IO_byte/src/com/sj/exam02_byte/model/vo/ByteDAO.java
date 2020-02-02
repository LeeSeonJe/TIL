package com.sj.exam02_byte.model.vo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteDAO {
	public void fileOpen() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("exam02_byte.txt");
			// byte 단위 하나를 읽어옴
			int value = 0;
			while ((value = fis.read()) != -1) {
				System.out.print((char) value + " ");
			}

//			
//			byte[] b1 = new byte[10];
//			fis.read(b1);
//			fis.read(b1, 0, 4);
//			
//			for(int a : b1) {
//				System.out.println(a);
//			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void fileSave() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("exam02_byte.txt");
			// int 값 저장
			fos.write(97);

			// byte 배열 생성
			byte[] b = new byte[] { 'a', 'b', 'c', 'd', 'e' };
			// 배열 전체 저장
			fos.write(b);

			// 배열 인덱스 범위 저장
			fos.write(b, 1, 3);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
