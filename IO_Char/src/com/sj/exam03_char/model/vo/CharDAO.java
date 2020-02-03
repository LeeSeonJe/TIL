package com.sj.exam03_char.model.vo;

import java.io.FileWriter;
import java.io.IOException;

public class CharDAO {
	public void fileSave() {
		try (FileWriter fr = new FileWriter("exam03.txt");) {
			char[] ch = { 'a', 'b', 'c', 'd', 'e' };
			fr.write(ch);
			fr.write("FileReader:::");
			fr.write("한글을 사랑하자:::");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
