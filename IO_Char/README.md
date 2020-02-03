# charDAO
### - IO_Char Summary

+ 문자 기반 스트림
	+ 입력 스트림
		+ 최상위 클래스 : Reader
		+ 하위 클래스 : XXXReader
	+ 출력 스트림
		+ 최상위 클래스 : Writer
		+ 하위 클래스 : XXXWriter
		
### FileReader // 파일을 저장 및 생성하자.

```
public void fileSave() {
		FileWriter fr = new FileWriter("exam03.txt");	
	}
```

+
	+ 매개변수의 파일에 값을 저장(출력)한다. **단, 파일이 없을 시 생성시킨다.**
	+ IO를 사용하기 위해서는 **예외처리**를 꼭 해줘야한다.
	+ FileWriter 코드를 살펴보자.
	+ (Ctrl + 클릭)을 사용해서 확인해준다.

```
public FileWriter(String fileName) throws IOException
```
+	
	+ throws IOException 예외처리를 던지고 있다.
	+ try~catch문을 사용하여 예외처리를 해주자.
	+ 지금부터는 try~catch문을 자동으로 만들어 주고 try-with-resource문을 사용할 것이다.
	+ try-with-resource문 try**()** 괄호안에 스트림을 선언해 주는 것으로 우리가 항상 마지막에 finally에 스트림을 close() 해주는 부분을 자동으로 처리해준다.
	+ 밑에 소스를 통해서 확인하자.
	
```
public void fileSave() {
		try (FileWriter fr = new FileWriter("exam03.txt");) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ API - FileWriter - Constructors 부분을 보면 **FileWriter(File file, boolean append)** 뒤에 boolean append가 붙은 Constructors를 확인할 수 있다.
	+ **FileOutputStream 과 같이 자동으로 덮어쓰는 것을 하지 않고 이어서 쓸 수 있게 해주는 Constructors이다.

>

+
	+ 출력하기 위헤서는 FileOutputStream과 같은 write()를 사용해준다.
	+ write()메소드도 IOException을 예외처리 해줘야하는데 이미 FileWriter 때문에 처리해줬기 때문에 예외처리 에러가 뜨지 않는다.
	+ 
	