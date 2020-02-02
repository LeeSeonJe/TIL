package com.sj.exam02_byte.run;

import com.sj.exam02_byte.model.vo.ByteDAO;

public class Run {
	public static void main(String[] args) {
		ByteDAO bd = new ByteDAO();
		bd.fileSave();
	}
}
