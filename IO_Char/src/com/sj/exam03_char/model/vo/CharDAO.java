package com.sj.exam03_char.model.vo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharDAO {
	public void fileSave() {
		try (FileWriter fr = new FileWriter("exam03.txt");) {
			char[] ch = { 'a', 'b', 'c', 'd', 'e' };
			// write(char[] cbuf)
			fr.write(ch);
			// write(char[] cbuf, int off, int len)
			fr.write(ch, 1, 3);
			// write(int c)
			fr.write(97);
			String str = "Test";
			// write(String str)
			fr.write(str);
			// write(String str, int off, int len)
			fr.write(str, 0, 2);
			// write(String str)
			fr.write("FileReader:::");
			// write(String str)
			fr.write("한글을 사랑하자:::");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void fileOpen() {
		try (FileReader fr = new FileReader("exam03.txt");) {
			int result = 0;
			while ((result = fr.read()) != -1) {
				System.out.print((char) result);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
