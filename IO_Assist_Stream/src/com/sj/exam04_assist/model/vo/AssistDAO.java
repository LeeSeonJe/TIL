package com.sj.exam04_assist.model.vo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AssistDAO {
	public void byteStringSave() {
		try (OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream("byteString.txt"), "UTF-8");) {
			osr.write("안녕하세요\n");
			osr.write("반갑습니다\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void byteStringOpen() {
		try (InputStreamReader isr = new InputStreamReader(new FileInputStream("byteString.txt"), "UTF-8");) {
			int value = 0;
			while ((value = isr.read()) != -1) {
				System.out.print((char) value + " ");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void bufferedSave() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("buffered.txt"));) {
			bw.write("안녕하세요\n");
			bw.write("반갑습니다\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void bufferedOpen() {
		try (BufferedReader br = new BufferedReader(new FileReader("buffered.txt"));) {
			String str = null;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void dataStreamSave() {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));) {
			dos.writeUTF("이선제");
			dos.writeDouble(93.8);
			dos.writeBoolean(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void dataStreamOpen() {
		try (DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));) {
			String name = dis.readUTF();
			double score = dis.readDouble();
			boolean check = dis.readBoolean();

			System.out.println(name);
			System.out.println(score);
			if (check) {
				System.out.println("통과!");
			} else {
				System.out.println("탈락!");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void objectStreamSave() {

	}

	public void objectStreamOpen() {

	}
}
