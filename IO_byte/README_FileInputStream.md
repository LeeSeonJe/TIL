# byteDAO
> ## - IO_byte Summary

>> - 바이트 기반 스트림

>>> - 입력 스트림 

>>>> - 최상위 클래스 : InputStream
>>>> - 하위 클래스 : XXXInputStream

>>> - 출력 스트림

>>>> - 최상위 클래스 : OutputStream
>>>> - 하위 클래스 : XXXOutputStream


>> #### FileInputStream // 파일을 받아오자.
>> ```
public void fileOpen() {
		FileInputStream fis = new FileInputStream("exam02_byte.txt");
	}

>> 
- 매개변수의 파일을 읽어온다
- IO를 사용하기 위해서는 **예외처리**를 꼭 해줘야한다.

>>
- FileInputStream 코드를 살펴보자
- (Ctrl + 클릭)을 사용해서 확인해준다.

>> ``` 
public FileInputStream(String name) throws FileNotFoundException

>>
- throws FileNotFoundException 예외처리를 던지고 있다.
- try~catch문을 사용하여 예외처리를 해주자.
 
>> ```
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


>>
- 마우스를 호버하면 자동으로 try~catch문을 작성해준다.





