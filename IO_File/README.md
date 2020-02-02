# IO_project
- IO_file Summary

```
public class FileController {
	public void method() {
//		파일 생성하기 file1
		File file1 = new File("exam01.txt");
//		File(URI uri)
//			Creates a new File instance by converting the given 
//			file: URI into an abstract pathname.
//		매개변수로 URL을 입력하면 경로 이름으로 파일이 생성된다.

//		디렉터리 생성하기 file2
		File file2 = new File("C:\\Users\\JE\\Desktop\\mkdir");
		file2.mkdir();
//		위에 경로에 mkdir이라는 폴더가 생성된걸 볼 수 있다.
//		try ~ catch문이 따로 필요없다.

//		경로에 폴더 및 파일 동시 생성하기
		File file3 = new File("C:\\Users\\JE\\Desktop\\mkdir\\mkdir.txt");
//		경로 and 파일 생성
//		위에 file1처럼 @@.txt 처럼 파일만 생성했을시 경로가 현재 자바프로젝트 안에 생성된다
//		경로를 설정해주고 @@.txt 파일을 생성하면 경로에 파일이 생성된다.
//		잘 만들어졌다. :)

//		파일삭제하기
		File file4 = new File("C:\\Users\\JE\\Desktop\\mkdir\\mkdir.txt");
		try {
//			파일 생성
			file1.createNewFile();
			file3.createNewFile();
//			파일 삭제
			file4.delete();
//			파일이 없어진 것을 확인 할 수 있다.

//			파일 존재여부 확인하기
			System.out.println(file1.exists()); // true
			System.out.println(file2.exists()); // true
			System.out.println(file3.exists()); // false
			System.out.println(file4.exists()); // false
//			[boolean : exists()] 메소드는 매개변수로 지정된 경로에 파일 or 디렉터리가 존재하는지 확인합니다.
			System.out.println(file1.isFile()); // true
			System.out.println(file2.isFile()); // false
			System.out.println(file3.isFile()); // false
			System.out.println(file4.isFile()); // false
//			[boolean : isFile()] 메소드는 매개변수로 지정된 경로의 파일이 일반 파일인지 확인합니다.

			System.out.println(file1.getName()); // exam01.txt
			System.out.println(file2.getName()); // mkdir
//			[String : getName()] 메소드는 파일과 디렉터리의 이름을 가져옵니다.
			System.out.println(file1.getAbsoluteFile()); // E:\SJ_workspace\git\IO_project\exam01.txt
//			[File : getAbsoluteFile()] 메소드는 절대경로를 반환합니다.
			System.out.println(file1.length()); // 0
//			[long : length()] 메소드는 파일의 용량을 가져옵니다.
			System.out.println(file1.getParent());
			System.out.println(file2.getParent());
//			[String : getParent()] 메소드는 file의 상위 폴더를 가져온다. 상위폴더를 지정하지 않을경우에는 null을 반환한다.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		public boolean createNewFile() throws IOException
//		createNEwFile은 IOExceptio을 상위 메소드로 위임하므로 try ~ catch문을 생성해주자.
	}
}