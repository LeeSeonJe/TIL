package com.sj.exam04_assist.run;

import com.sj.exam04_assist.model.vo.AssistDAO;

public class Run {
	public static void main(String[] args) {
		AssistDAO bd = new AssistDAO();
		bd.byteStringSave();
		bd.byteStringOpen();
	}
}
