package com.sj.exam02_byte.model.vo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteDAO {
	public void fileOpen() {
		try {
			FileInputStream fis = new FileInputStream("exam02_byte.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fileSave() {
		try {
			FileOutputStream fos = new FileOutputStream("exam02_byte.txt");
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
		}
	}
}
