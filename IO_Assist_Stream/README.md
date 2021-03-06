# IO_Assist

### 보조스트림

> 스트림의 기능을 향상시키거나 새로운 기능을 추가하기 위해 사용
보조 스트림은 실제 데이터를 주고 받는 스트림이 아니기 때문에
입출력 처리 불가능
기반 스트림을 먼저 생성한 후 이를 이용하여 보조 스트림 생성

+ 보조 스트림 종류
	+ 문자변환 보조스트림
		+ 소스 스트림이 바이트 기반 스트림이지만 데이터가 문자일 경우 사용
		+ Reader와 Writer는 문자 단위로 입출력을 하기 때문에 데이터가 문자인 경우 바이트 기반 스트림보다 편리하게 사용 가능
		+ 바이트기반 스트림의 데이터를 지정된 인코딩의 문자데이터로 변환해준다.
		+ InputStreamReader / OutputStreamWriter
	+ 성능향상 보조스트림
		+ 느린 속도로 인해 입출력 성능에 영향을 미치는 입출력 소스를 이용하는 경우 사용
		+ 입출력 소스와 직접 작업하지 않고 버퍼에 데이터를 모아 한꺼번에 작업을 하여 실행 성능 향상
		+ 버퍼란? :전송 받은 내용들을 모아서 한꺼번에 전송 할 수 있도록 해주는 임시 저장소
		+ BufferedInputStream / BufferedOutputStream / BufferedReader / BufferedWriter
	+ 기본타입 입출력 보조스트림
		+ 기본 자료형 별 데이터 읽고 쓰기가 가능하도록 기능 제공
		+ 단, 입력된 자료형의 순서와 출력될 자료형의 순서 일치
		+ DataInputStream / DataOutputStream
	+ 객체 입출력
		+ 객체를 파일 또는 네트워크로 입출력 할 수 있는 기능 제공
		+ 단, 객체는 문자가 아니므로 바이트 기반 스트림으로 데이터를 변경해주는 직렬화 필수
		+ ObjectInputStream / ObjectOutputStream
		
		
### 직렬화와 역직렬화
+ 직렬화(Seralization)
	+ Serializable 인터페이스를 implements 하여 구현
	+ 객체 직렬화 시 private 필드를 포함한 모든 필드를 바이트로 변환
	+ transient키워드를 사용한 필드는 직렬화에서 제외
	
+ 역직렬화(Deserialization)
	+ 직렬화된 객체를 역직렬화할 때는 직렬화 했을 때와 같은 클래스 사용
	+ 단, 클래스 이름이 같더라도 클래스 내용이 변경된 경우 역직렬화 실패
	
	
	
# 보조스트림을 사용하자!!

### 문자변환 보조스트림
> ## OutputStreamWriter

+ 
	+ 먼저 보조스트림을 사용하는 방법을 알아보자.
	+ 우선 기반스트림을 생성하여 파일을 받아온다. 
	+ 그리고 기반스트림을 매개변수로 하는 보조스트림을 생성한다.
	+ API를 살펴보면 
		+ Characters written to it are encoded into bytes using a specified charset. ==> 지정된 문자 세트를 사용하여 바이트로 인코딩된다고 한다.
	+ UTF-8로 인코딩시켜서 출력시켜주자.
	
```JAVA
	public void byteStringSave() {
		try {
			FileOutputStream fos = new FileOutputStream("byteString.txt");
			OutputStreamWriter osr = new OutputStreamWriter(fos, "UTF-8");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ 기반 스트림을 생성하고 보조스트림을 생성하였다.
	+ try-with-resource문으로 바꾸고 보조스트림의 매개변수에 기반스트림을 바로 넣어주자.
	
```JAVA
	public void byteStringSave() {
		try (OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream("byteString.txt"), "UTF-8");) {
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ isr이 오류가 난다.
	+ 저번과 마찬가지로 close()할 때의 예외처리를 해주어야한다.
	
```JAVA
	public void byteStringSave() {
		try (OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream("byteString.txt"), "UTF-8");) {
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
```

+
	+ 보조스트림을 이용하여 파일의 내용을 저장(출력) 해보자.
	+ 출력에는 write()메소드를 사용해주면 된다.
	+ write()메소드는 여러가지로 오버로딩이 되어있지만 이번에는 String 값으로만 출력해줄 것이다.
	
```JAVA
	public void byteStringSave() {
		try (OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream("byteString.txt"),"UTF-8");) {
			osr.write("안녕하세요\n");
			osr.write("반갑습니다\n");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
```
+
	+ 실행하고 출력되는 파일을 확인해보자. 
	+ 잘 저장이 되었다. 이제 파일을 읽어와보자
	
> ## InputStreamReader

+
	+ InputStreamReader을 사용해서 파일을 읽어보자.

```JAVA
	public void byteStringOpen() {
		FileInputStream fis = new FileInputStream("byteString.txt");
		InputStreamReader isr = new InputStreamReader(fis);
	}
```
+
	+ try-with-resource문을 사용하고 예외처리를 모두 해주자.
	
```JAVA
	public void byteStringOpen() {
		try (InputStreamReader isr = new InputStreamReader(new FileInputStream("byteString.txt"), "UTF-8");) {
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
```
+
	+ FileInputStream과 마찬가지로 값을 읽어와 보자.
	
```JAVA
	public void byteStringOpen() {
		try (InputStreamReader isr = new InputStreamReader(new FileInputStream("byteString.txt"), "UTF-8");) {
			int value = 0;
			while ((value = isr.read()) != -1) {
				System.out.print((char) value + " ");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
```
	
+
	+ int value를 선언해주는 이유는 read()의 반환 값이 int형이고 **일회성**이기 때문에 한번 읽어온 값을 저장해줘야한다.
	+ char형으로 **Casting** 해주어 읽어온다.
	+ 결과값으로 안 녕 하 세 요 (엔터) 반 갑 습 니 다 
	+ 잘 읽어오는 것을 확인 할 수 있다.

### 성능향상 보조스트림
> ## BufferedWriter

+
	+ 입출력의 성능향상을 위해 보조스트림을 사용해 파일을 출력해보자.
	+ 버퍼안에 문자들을 임시로 저장했다가 한번에 입출력 해온다.
	+ BufferedOutputStream도 사용할 수 있으나 우리는 문자열을 출력하기 위해서 writer을 사용해보자
	+ BufferedOutputStream을 사용하여 파일을 출력해보자.
	
```JAVA
	public void bufferedSave() {
		FileWriter fw = new FileWriter("buffered.txt");
		BufferedWriter bw = new BufferedWriter(fw);
	}

```

+
	+ try-with-resource문을 사용하고 예외처리를 모두 해주자.
	
```JAVA
	public void bufferedSave() {
		FileWriter fw;
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("buffered.txt"));) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```
	
+ 
	+ 파일을 출력하기 위해 write()를 사용한다.
	+ API를 보면 BufferedOutputStream은 String을 매개변수로 하는 write()가 존재하지 않는다.
	+ 문자열을 출력해보자.
	
```JAVA
	public void bufferedSave() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("buffered.txt"));) {
			bw.write("안녕하세요");
			bw.write("반갑습니다");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ 프로젝트를 새로고침하면 buffered.txt가 생성되었다.
	+ 안에 값을 확인해보자. 잘 만들어졌다.
	
> ## BufferedReader

+
	+ 이제 저장을 했으니 값을 읽어와 보자.
	+ **BufferReader**을 사용해서 파일을 읽어보자.
	
```JAVA
	public void bufferedOpen() {
		FileReader fr = new FileReader("buffered.txt");
		BufferedReader br = new BufferedReader(fr);
	}
```

+
	+ try-with-resource문을 사용하고 예외처리를 모두 해주자.

```JAVA
	public void bufferedOpen() {
		try (BufferedReader br = new BufferedReader(new FileReader("buffered.txt"));) {
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
```

+
	+ 이제 파일을 하나하나 읽어보자.
	+ 파일을 읽어오는건 **readLine()**을 사용한다.
	+ BufferedReader는 값을 읽어올때 **한줄씩** 가져올 수 있다.
	+ 항상 read()를 통해서 하나하나 문자나 바이트 단위로 가져왔었는데 성능향상을 받은 것을 알 수 있다.
	
```JAVA
	public void bufferedOpen() {
		try (BufferedReader br = new BufferedReader(new FileReader("buffered.txt"));) {
			String str = null;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
```

+
	+ **readLine()**을 API 살펴보자.
		+ 반환값은 **String**이다.
		+ 파일의 끝에 도달할 경우에는 null을 반환한다.
	+ 읽어오는 것은 항상 **일회성**이므로 저장할 변수를 항상 생성해주자.
	+ 실행시켜보자. 잘 출력이 되었다.
	
### 기본타입 입출력 보조스트림
> ## DataOutputStream

+
	+ 바이트 스트림으로 전송할 수 없는 타입(int, long, float, double, short, String, boolean등)들을 전송하기위한 보조스트림이다.
	+ 자바의 기본타입을 살려서 생성할 수 있다.
	+ 보안상에 유리하다는 장점이 있다.
	+ DataOutputStream을 보조스트림을 사용하여 출력해보도록 하자.
	
```JAVA
	public void dataStreamSave() {
		FileOutputStream fos = new FileOutputStream("data.txt");
		DataOutputStream dos = new DataOutputStream(fos);
	}
```

+
	+ try-with-resource문을 사용하고 예외처리를 모두 해주자.

```JAVA
	public void dataStreamSave() {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));) {

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
```

+
	+ API를 살펴보자. 여러가지 write()메소드들이 존재한다
		+ write(byte[] b, int off, int len)
		+ write(int b) 
		+ writeBoolean(boolean v)
		+ writeByte(int v)
		+ writeBytes(String s)
		+ writeChar(int v)
		+ writeChars(String s)
		+ writeDouble(double v)
		+ writeFloat(float v)
		+ writeInt(int v)
		+ writeLong(long v)
		+ writeShort(int v)
		+ writeUTF(String str)
	+ 다양하게 기본타입으로 출력시킬 수 있다. writeUTF는 처음보는 부분이라 찾아봤다.
	+ Writes a string to the underlying output stream using modified UTF-8 encoding in a machine-independent manner.
	+ UTF-8 인코딩을 사용하여 기본 출력 스트림에 문자열로 사용된다고 한다.
	+ 사용해보자.

```JAVA
	public void dataStreamSave() {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));) {
			dos.writeUTF("이선제");
			dos.writeDouble(93.8);
			dos.writeBoolean(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
```

+
	+ writeXXX()를 사용해서 값을 저장해 주었다.
	+ 파일을 확인하였는데 저장된 값이 조금 이상하다.
	+ 파일을 읽어봐야할거 같다. 이제 파일을 읽어보자.
	

> ## DataIntputStream

+
	+ 파일을 읽어보자. 
	+ **주의할 점**이 있다.
	+ 파일을 읽어올때 **순서대로** 읽어와야 한다.
	+ 우선 **DataInputStream** 보조스트림을 생성해보자
	
```JAVA
	public void dataStreamOpen() {
		FileInputStream fis = new FileInputStream("data.txt");
		DataInputStream dis = new DataInputStream(fis);
	}
```
+
	+ try-with-resource문을 사용하고 예외처리를 모두 해주자.
	
```JAVA
	public void dataStreamOpen() {
		try (DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));) {

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
```

+
	+ 아까 **주의할 점**을 생각하며 파일을 읽어보자.
	
```JAVA
	public void dataStreamOpen() {
		try (DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));) {
			String name = dis.readUTF();
			double score = dis.readDouble();
			boolean check = dis.readBoolean();

			System.out.println(name);
			System.out.println(score);
			if (check) {
				System.out.println("통과!");
			} else {
				System.out.println("탈락!");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
```

+
	+ 순서를 지켜서 값을 가져왔다. 
	+ 프로그램을 실행시켜보면 아주 잘 되는 것을 볼 수 있다.
	+ 순서를 지키지 않으면 원하는 값이 나오지 않을 수 있다.
	
	
### 객체 입출력
> ## ObjectOutputStream

+
	+ 객체를 출력하기 위한 보조스트림이다.
	+ Book이라는 객체를 담을 클래스를 만들어보자.  
		\- title : String  
		\- price : int  
		\+ Book()  
		\+ Book(title:String, price:int)  
		\+ getter() / setter()  
		\+ toString() : public

```JAVA
public class Book {
	private String title;
	private int price;

	public Book() {

	}

	public Book(String title, int price) {
		this.title = title;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return title + "(" + price + ")";
	}
}
```

+
	+ Book 클래스를 생성해주었다.
	+ **객체를 보조스트림을 사용해서 값을 출력하기 위해서는 반드시 직렬화를 해야한다.**
	+ Book 클래스에 **implements Serializable**을 해주자.
	+ import도 꼭 해주자.

```JAVA
public class Book implements Serializable {

		.............생략............
		
}
```

+ 
	+ Book에 노란 경고가 뜨는 것을 확인 할 수 있다.
	+ 마우스를 호버해서 Add generated serial version ID를 눌러준다.
	
```JAVA
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4717924231109932122L;
	
		.............생략............
		
}
```

+
	+ serialVersionUID 필드를 추가해준다.
	+ 직렬화한 클래스와 같은 클래스임을 알려주는 식별자 역할을 한다. 되도록 명시해주도록 하자.
	
```JAVA
import java.io.Serializable;

public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4717924231109932122L;
	private String title;
	private int price;

	public Book() {

	}

	public Book(String title, int price) {
		this.title = title;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return title + "(" + price + ")";
	}
}
```

+
	+ Book 클래스를 만들었다.
	+ 이제 저장을 해보자.
	+ try-with-resource문과 예외처리까지 해주자.
	
```JAVA
	public void objectStreamSave() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.dat"));) {

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
	+ 이제 출력을 해보자.
	+ write()메소드를 사용해준다. 하지만 우리는 object를 출력해줘야 하기 때문에 writeObject()를 사용해서 값을 넘겨준다. 매개변수로는 object를 받아온다.
	
```JAVA
	public void objectStreamSave() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.dat"));) {
			oos.writeObject(new Book("자바를 잡아라", 30000));
			oos.writeObject(new Book("오라클 정복", 35000));
			oos.writeObject(new Book("웹표준 2.0", 27500));
			oos.writeObject(new Book("자바 Servlet/JSP", 28000));
			oos.writeObject(new Book("ajax 사용법", 15000));
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
	+ 실행시켜보자. object.dat파일이 생성되었을 것이다. 안에 내용을 살펴보자. 외계어가 써있다.
	+ 이제 다시 읽어보자!
	
> ## ObjectInputStream

+
	+ 객체를 입력받기 위한 보조스트림을 생성해준다.
	+ ObjectInputStream을 사용해서 만들어주자.
	+ 예외처리까지 해주자. 다 해주자....

```JAVA
	public void objectStreamOpen() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.dat"));) {

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
	+ 이제는 아주 쉽고 빠르게 만들 수 있다. ㅎㅎ
	+ 이제 object.dat파일을 읽어보자
	+ 읽어오는 메소드 readObject()를 사용해보자. 그전에 API를 살펴보자.
	+ 여러가지 read()메소드가 있다. 우리는 객체를 받아올 것이기 때문에 readObject()를 사용하면 될 것 같다. 반환값은 object이다.

>

+ 읽어오는 방법은 여러가지가 있다.
+ 내가 아는 선에서 모두 구현해보자.
	+ 1\. 크기를 알고 있다면 객체배열을 생성해서 저장해서 읽어온다.
		
```JAVA
	public void objectStreamOpen() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.dat"));) {
			Book[] bArr = new Book[5];
			for (int i = 0; i < bArr.length; i++) {
				bArr[i] = (Book) ois.readObject();
			}			
			for (Book b : bArr) {
				System.out.println(b);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ 2\. EOFException을 사용해서 읽어온다.

```JAVA
	public void objectStreamOpen() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.dat"));) {
			while (true) {
				try {
					Book b = (Book) ois.readObject();
					System.out.println(b);
				} catch (EOFException e) {
					// 파일에 다음 값이 없으면 뱉는 오류이다.
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ 3\. 크기를 모를 경우 컬렉션을 사용해서 읽어온다. 이 부분에도 EOFException을 함께 사용해준다.
	
```JAVA
	public void objectStreamOpen() {
		ArrayList<Book> list = new ArrayList<Book>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.dat"));) {
			while (true) {
				list.add((Book) ois.readObject());
			}
		} catch (EOFException e) {
			for (Book b : list) {
				System.out.println(b);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```

+
	+ 이렇게 여러가지 방법을 통해서 파일을 읽어왔다.
	+ 객체를 입출력 해줄때는 객체를 정의한 클래스에 꼭 **직렬화**를 하는 인터페이스를 추가해주어야한다.
	+ **EOFException은 객체 파일을 읽어올때 다음 값이 없으면 뱉는 오류라는 것을 잘 기억해두자.**
	+ 실행해보니 object.dat파일에는 알 수 없는 값들이 저장 되어 있엇는데 읽어오니 아주 잘 출력되는 것을 확인하였다.
	+ 다음에는 컬렉션을 공부해볼까한다. ㅎㅎ

+
	+ update Test
	
	