package com.sj.exam03_char.run;

import com.sj.exam03_char.model.vo.CharDAO;

public class Run {
	public static void main(String[] args) {
		CharDAO cd = new CharDAO();
		cd.fileSave();
	}
}
