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

```JAVA
public void fileSave() {
		FileWriter fr = new FileWriter("exam03.txt");	
	}
```

+
	+ 매개변수의 파일에 값을 저장(출력)한다. **단, 파일이 없을 시 생성시킨다.**
	+ IO를 사용하기 위해서는 **예외처리**를 꼭 해줘야한다.
	+ FileWriter 코드를 살펴보자.
	+ (Ctrl + 클릭)을 사용해서 확인해준다.

```JAVA
public FileWriter(String fileName) throws IOException
```
+	
	+ throws IOException 예외처리를 던지고 있다.
	+ try~catch문을 사용하여 예외처리를 해주자.
	+ 지금부터는 try~catch문을 자동으로 만들어 주고 try-with-resource문을 사용할 것이다.
	+ try-with-resource문 try**()** 괄호안에 스트림을 선언해 주는 것으로 우리가 항상 마지막에 finally에 스트림을 close() 해주는 부분을 자동으로 처리해준다.
	+ 밑에 소스를 통해서 확인하자.
	
```JAVA
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
	+ **FileOutputStream** 과 같이 자동으로 덮어쓰는 것을 하지 않고 이어서 쓸 수 있게 해주는 Constructors이다.

>

+
	+ 출력하기 위헤서는 FileOutputStream과 같은 write()를 사용해준다.
	+ write()메소드도 IOException을 예외처리 해줘야하는데 이미 FileWriter 때문에 처리해줬기 때문에 예외처리 에러가 뜨지 않는다.

>

+
	+ write()를 사용해보자.
		+ write(char[] cbuf) : char 배열 전체를 저장한다.
		+ write(char[] cbuf, int off, int len) : char 배열 인덱스 범위만큼 저장한다. **마지막 범위는 항상 포함하지 않는다.**
		+ write(int c) : 정수 값 하나를 저장한다.
		+ write(String str) : 문자열을 저장한다.
		+ write(String str, int off, int len) : 문자열의 인덱스 범위만큼 저장한다. **마지막 범위는 항상 포함하지 않는다.**
	
```JAVA
public void fileSave() {
		try (FileWriter fr = new FileWriter("exam03.txt");) {
			char[] ch = { 'a', 'b', 'c', 'd', 'e' };
			// write(char[] cbuf)
			fr.write(ch);
			// write(char[] cbuf, int off, int len)
			fr.write(ch, 2, 4);
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
			while (fr.read() != -1) {
				System.out.println((char) fr.read());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
```

+
	+ 실행해보면 **exam03.txt** 파일이 만들어지는 것을 알 수 있다.
	+ 파일이 존재하지 않기 때문에 자동으로 만들어진다.

>

+
	+ **exam03.txt** 파일을 열어보자.
	+ abcdebcdaTestTeFileReader:::한글을 사랑하자::: 이런게 결과값이 생긴걸 볼 수 있다.
		+ abcde 부분 : write(char[] cbuf)
		+ bcd 부분 : // write(char[] cbuf, int off, int len)
		+ a 부분 : write(int c)
		+ Test 부분 : // write(String str)
		+ Te 부분 : // write(String str, int off, int len)
		+ FileReader::: 부분 : write(String str)
		+ 한글을 사랑하자::: 부분 : write(String str)
	+ 아주 잘 생성이 되었다. **다음은 파일을 읽어오자**
	
	
### FileWriter // // 파일을 받아(읽어)오자.
	
```JAVA
public void fileOpen() {
		FileReader fr = new FileReader("exam03.txt");
	}
```

+
	+ 매개변수의 파일을 읽어온다.
	+ IO를 사용하기 위해서 **예외처리**를 해준다
	+ FileReader 코드를 살펴보고 어떤 예외처리를 던지는지 확인해보자.
	
```JAVA
public FileReader(String fileName) throws FileNotFoundException
```

+	
	+ throws FileNotFoundException 예외처리를 해주자
	+ 또한 try-with-resource문으로 작성해보자.
	
```JAVA
public void fileOpen() {
		try (FileReader fr = new FileReader("exam03.txt");){

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ fr에 오류가 생길 것이다.
	+ 오류를 확인해 보자.
		+ Unhandled exception type IOException thrown by automatic close() invocation on fr 이라는 오류가 생겼다.
		+ close()를 하기 위해서는 IOException을 해줘야하는데 우리는 FileNotFoundException만 catch해줘서 나오는 오류이다.
		+ IOException을 catch문에 추가해주자.
		
```JAVA
public void fileOpen() {
		try (FileReader fr = new FileReader("exam03.txt");){

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ 모든 예외처리를 잡아주었다
	+ 다음 파일의 값을 읽어와보자.
	+ 읽어오기 위해서는 read()를 사용한다.
>

+
	+ read() 메소드를 API에서 확인해보자.
	+ read() 메소드는 int 값을 반환하는 것을 알 수 있다.
	+ 읽어온 값을 담아줄 value 값을 만들어주자
	
```JAVA
public void fileOpen() {
		try (FileReader fr = new FileReader("exam03.txt");){
			int value = 0;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ 값을 읽어와보자

```JAVA
public void fileOpen() {
		try (FileReader fr = new FileReader("exam03.txt");) {
			int value = 0;
			while ((value = fr.read()) != -1) {
				System.out.print((char) value + " ");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ read() 메소드는 **일회성**이기 때문에 값을 저장해줘야 한다.
	+ 그리고 다음 값이 없을 때는 -1을 반환해주므로 while문 조건을 만들어 준다.
	+ read()의 반환값은 int형 이므로 char형으로 **Casting**을 해준다.
>

+	
	+ 결과값을 확인해 보자
	+ a b c d e b c d a T e s t T e F i l e R e a d e r : : : 한 글 을   사 랑 하 자 : : : 이 나오는걸 확인할 수 있다.
	+ FileReader 또한 문자 하나하나씩 읽어오기 때문에 보조스트림을 사용해서 한번에 가져와야한다.
	+ 보조스트림 부분에서 다시 알아보도록 하자...!
	