package com.sj.exam01_flie.run;

import com.sj.exam01_flie.model.dao.FileController;

public class Run {
	public static void main(String[] args) {
		FileController fc = new FileController();
		fc.method();
		// 실행 시키고 프로젝트를 refresh해주면 파일이 생성된걸 볼 수 있다.
	}
}
