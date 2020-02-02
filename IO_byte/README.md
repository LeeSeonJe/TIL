# byteDAO
### - IO_byte Summary

* 바이트 기반 스트림
	- 입력 스트림 
		- 최상위 클래스 : InputStream
		- 하위 클래스 : XXXInputStream
	- 출력 스트림
		- 최상위 클래스 : OutputStream
		- 하위 클래스 : XXXOutputStream


### FileOutputStream // 파일을 저장 및 생성하자.

```
public void fileSave() {
		FileOutputStream fos = new FileOutputStream("exam02_byte.txt");
	}
```

+
	+ 매개변수의 파일에 값을 저장한다. **단, 파일이 없을 시 생성시킨다.**
	+ IO를 사용하기 위해서는 **예외처리** 를 꼭 해줘야한다.


	+ FileOutputStream 코드를 살펴보자
	+ (Ctrl + 클릭)을 사용해서 확인해준다.

```
public FileOutputStream(String name) throws FileNotFoundException
```
+
	+ throws FileNotFoundException 예외처리를 던지고 있다.
	+ try~catch문을 사용하여 예외처리를 해주자.

```
public void fileSave() {
		try {
			FileOutputStream fos = new FileOutputStream("exam02_byte.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```
+
	+ 마우스를 호버하면 자동으로 try~catch문을 작성해준다.
	+ API - FileOutputStream - Constructors 부분을 보면 **FileOutputStream(File file, boolean append)** 뒤에 boolean append가 붙는 Constructors이 존재한다.
	+ 이는 파일을 저장하거나 만들때 **자동으로 덮어쓰기**가 되는 것을 덮어쓰지 않고 이어쓸 수 있게 해주는 Constructors이다.

	+ 파일을 쓰기 위해서는 write()메소드를 사용할 수 있다.
	+ API를 살펴보면 
		+ **write(byte[] b)** : 바이트 배열을 만들어 값을 저장한다.
		+ **write(byte[] b, int off, int len)** : 바이트 배열의 인덱스 범위이다. **마지막 범위는 항상 포함하지 않는다**
		+ **write(int b)** : 정수 값 하나를 저장한다.
	+ 하나씩 저장해보자.

```
public void fileSave() {
		try {
			FileOutputStream fos = new FileOutputStream("exam02_byte.txt");
			// int 값 저장
			fos.write(97);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ write(int b) : 값을 넣었을 때 Unhandled exception type IOException 예외처리를 해주라는 메세지가 나온다.
	+ write() 안에 들어가서 소스를 살펴보자

```
public void write(int b) throws IOException {
```

*
	+ IOException 예외처리를 던지는 것을 볼 수 있다.
	+ 마우스를 호버하여 try~catch문을 처리하지 말고 FileNotFoundException 밑에다 직접 써서 해주자. 중첩 try문이 되므로 그렇다.

```
public void fileSave() {
		try {
			FileOutputStream fos = new FileOutputStream("exam02_byte.txt");
			// int 값 저장
			fos.write(97);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
```
+
	+ 다음은 byte 배열을 하나 생성해서 저장해보도록 하자.

```
public void fileSave() {
		try {
			FileOutputStream fos = new FileOutputStream("exam02_byte.txt");	
			fos.write(97); // int 값 저장	
			byte[] b = new byte[] { 'a', 'b', 'c', 'd', 'e' }; // byte 배열 생성		
			fos.write(b); // 배열 전체 저장
			fos.write(b, 1, 3); // 배열 인덱스 범위 저장	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
```
+
	- 저장된 값을 확인해 보자. 
	- 프로젝트 우클릭 > refresh를 해주면 파일이 생성되어 있을 것이다.
	- 여러번 실행하면 덮어쓰기때문에 계속해서 값이 같을 것이며 *파일명 뒤에 true를 붙여주면 이어서 붙여질 것*이다.

+
	+ 마지막으로 사용한 fos를 스트림을 닫아줘야한다.
	+ 스트림이 많으면 코드가 꼬일 수 있으므로 닫아주는 것을 생활화하자.
	+ **finally** 코드를 작성하여 close해주도록 하자.

```
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
		} finally {
			fos.close();
		}
	}
```

+
	+ fos를 try문에 만들었지때문에 finally 안에 fos는 읽을 수 없으므로 밖으로 빼주도록 하자

```
public void fileSave() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("exam02_byte.txt");
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
		} finally {
			fos.close();
		}
	}
```

+
	+ 빼주고 나면 close() 또한 Unhandled exception type IOException 예외처리를 던진다.
	+ 예외처리 해주도록 하자.

```
public void fileSave() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("exam02_byte.txt");
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
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
```

+	
	+ finally는 try, catch 부분과 상관없이 무조건 실행시키는 문장이므로 close()를 finally 안에 넣어준 것이다.
>>

+
	+ 마지막으로 생성된 파일을 보자.
	+ aabcdebcd 이렇게 저장이 되어 있을 것이다.
		+ a : 첫번째 int 97값
		+ abcde : 배열 전체의 저장 값
		+ bcd : 배열 인덱스 범위의 저장 값
	+ 잘 저장되는 것을 확인했다. 이제 파일을 열어보자.


### FileInputStream // 파일을 받아오자.

```
public void fileOpen() {
		FileInputStream fis = new FileInputStream("exam02_byte.txt");
	}
```

+
	- 매개변수의 파일을 읽어온다
	- IO를 사용하기 위해서는 **예외처리**를 꼭 해줘야한다.
	- FileInputStream 코드를 살펴보자
	- (Ctrl + 클릭)을 사용해서 확인해준다.

``` 
public FileInputStream(String name) throws FileNotFoundException
```

+
	- throws FileNotFoundException 예외처리를 던지고 있다.
	- try~catch문을 사용하여 예외처리를 해주자.
	
```
public class ByteDAO {
	public void fileOpen() {
		try {
			FileInputStream fis = new FileInputStream("exam02_byte");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
```

+
	- 마우스를 호버하면 자동으로 try~catch문을 작성해준다.
	+ 미리 fis를 try밖에 선언해주고 finally를 만들어 주자.

```
public void fileOpen() {
		FileInputStream fis  = null;
		try {
			fis = new FileInputStream("exam02_byte.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
```
+
	+ 이제 값을 받아오자
	+ 값을 받아오는 방법은 read() 메소드를 사용한다.
	+ API를 살펴보자.
		+ read() : byte 하나를 읽는다
		+ read(byte[] b) : byte 배열에 저장한다. for문을 사용해서 출력하면 저장되어있는걸 확인 할 수 있다.
		+ read(byte[] b, int off, int len) : byte 배열에 인덱스 범위를 저장한다. 
	
```
public void fileOpen() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("exam02_byte.txt");
			// byte 단위 하나를 읽어옴
			int value = 0;
			while ((value = fis.read()) != -1) {
				System.out.print((char) value + " ");
			}

//			
//			byte[] b1 = new byte[10];
//			fis.read(b1);
//			fis.read(b1, 0, 4);
//			
//			for(int a : b1) {
//				System.out.println(a);
//			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
```

+
	+ read()를 사용하면 모두 byte단위인 정수로 가져오기 때문에 int value 에 저장하고 형변환을 해주자.
	+ **일회성** 이기 때문에 값을 저장하지 않으면 바로 다음 값으로 넘어가게 된다. 그래서 value 값을 만들어 저장해주자.
	+ **또한 위에서 한번 다 읽고 다시 밑에서 읽을려면 이미 읽었기 때문에 값이 없다는점 유의하자**
	+ 주석 부분인 배열부분을 만들고 아무리 가져와도 값을 이미 위에서 다 출력하여 밑에는 저장되지 않는다.
	
>
+
	+ 콘솔창을 확인하여 출력된 값을 보자.
	+ a a b c d e b c d 이렇게 출력된 것을 볼 수 있다.
	+ 값을 하나하나 가져오기 때문에 한번에 가져오고 싶다.
	+ 이건 보조스트림을 하면서 해보자.
