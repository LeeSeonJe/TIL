# byteDAO
## - IO_byte Summary

* 바이트 기반 스트림
	- 입력 스트림 
		- 최상위 클래스 : InputStream
		- 하위 클래스 : XXXInputStream
	- 출력 스트림
		- 최상위 클래스 : OutputStream
		- 하위 클래스 : XXXOutputStream


#### FileOutputStream // 파일을 저장 및 생성하자.
```
public void fileSave() {
		FileOutputStream fos = new FileOutputStream("exam02_byte.txt");
	}
```


>> - 매개변수의 파일에 값을 저장한다. **단, 파일이 없을 시 생성시킨다.**
>> - IO를 사용하기 위해서는 **예외처리** 를 꼭 해줘야한다.


>> - FileOutputStream 코드를 살펴보자
>> - (Ctrl + 클릭)을 사용해서 확인해준다.

```
public FileOutputStream(String name) throws FileNotFoundException
```


>> - throws FileNotFoundException 예외처리를 던지고 있다.
>> - try~catch문을 사용하여 예외처리를 해주자.

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


>> - 마우스를 호버하면 자동으로 try~catch문을 작성해준다.
>> - API - FileOutputStream - Constructors 부분을 보면 **FileOutputStream(File file, boolean append)** 뒤에 boolean append가 붙는 Constructors이 존재한다.
>> - 이는 파일을 저장하거나 만들때 **자동으로 덮어쓰기**가 되는 것을 덮어쓰지 않고 이어쓸 수 있게 해주는 Constructors이다.


>> - 파일을 쓰기 위해서는 write()메소드를 사용할 수 있다.
>> - API를 살펴보면 


>> - **write(byte[] b)** : 바이트 배열을 만들어 값을 저장한다.
>> - **write(byte[] b, int off, int len)** : 바이트 배열의 인덱스 범위이다. **마지막 범위는 항상 포함하지 않는다**
>> - **write(int b)** : 정수 값 하나를 저장한다.
>> - 하나씩 저장해보자.

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


>> - write(int b) : 값을 넣었을 때 Unhandled exception type IOException 예외처리를 해주라는 메세지가 나온다.
>> - write() 안에 들어가서 소스를 살펴보자

```
public void write(int b) throws IOException {
```


>> - IOException 예외처리를 던지는 것을 볼 수 있다.
>> - 마우스를 호버하여 try~catch문을 처리하지 말고 FileNotFoundException 밑에다 직접 써서 해주자. 중첩 try문이 되므로 그렇다.

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

>> - 다음은 byte 배열을 하나 생성해서 저장해보도록 하자.

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


>> - 저장된 값을 확인해 보자. 
>> - 프로젝트 우클릭 > refresh를 해주면 파일이 생성되어 있을 것이다.
>> - 여러번 실행하면 덮어쓰기때문에 계속해서 값이 같을 것이며 *파일명 뒤에 true를 붙여주면 이어서 붙여질 것*이다.

>> - 생성된 파일을 보자.
>> - aabcdebcd 이렇게 저장이 되어 있을 것이다.
>>> - a : 첫번째 int 97값
>>> - abcde : 배열 전체의 저장 값
>>> - bcd : 배열 인덱스 범위의 저장 값
>> - 잘 저장되는 것을 확인했다. 이제 파일을 열어보자.
