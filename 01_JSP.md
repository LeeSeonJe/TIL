# 웹 ?
### 웹프로그래밍이란?
+ 웹프로그래밍이란, 웹어플리케이션을 구현하는 행위이다.
+ 웹어플리케이션이란, 웹을 기반으로 작동되는 프로그램이다.
+ 웹이란, 1개 이상의 사이트가 연결되어있는 인터넷 서비스의 한가지 형태를 말한다.
+ 인터넷이란, 1개 이상의 네이트워가 연결되어 있는 형태를 말한다.

``` 
http://wwww.sba.seoul.kr:80/kr/index
----   ----------------- -- ---------
  1           2,3         4    5
```
+ 1\. 프로토콜(Protocol) : 네트워크상에서 약속한 통신규약 (Http, FTP, SMTP, POP, DHCP)
  + (SMTP - 메일을 보내는 통신규약, POP - 메일을 받는 통신규약, DHCP - 동적 호스트 구성 프로토콜)
+ 2\. IP : 네트워크상에서 식별할 수 있는 주소
+ 3\. DNS : IP주소를 인간이 쉽게 외우도록 맵핑한 문자열
+ 4\. Port : 해당 컴퓨터의 구동되고 있는 프로그램을 구분할 수 있는 번호
+ 5\. informatiln path

### 웹프로그램의 동작
+ 웹서버
  + 클라이언트의 요청에 의해 정보를 제공해 주는 서버(Aphach, IIS),  
  별도의 구현이 필요한 로직이 있을 경우 웹어플리케이션 서버에 요청
+ 웹브라우저
  + 웹서버에 정보를 요청하고, 웹서버로부터 정보를 받는 매개체, 이때 HTTP 프로토콜을 사용함.

<br>

```
          > request          > Logic 요청                     > DB 접근
브라우저               웹서버               웹어플리케이션 서버             데이터베이스
          < response         < Logic 수행                     < 데이터
```
<br>

# JSP
+ 동적 웹어플리케이션 컴포넌트
+ .jsp 확장자
+ 클라이언트의 요청에 동적으로 작동하고, 응답은 html을 이용
+ jsp는 서블릿으로 변환되어 실행
+ MVC패턴에서 View로 이용

![JSP동작](https://user-images.githubusercontent.com/59919620/75901661-38355480-5e82-11ea-954e-5f760b37cd0d.JPG)

<br>

### JSP 아키텍쳐
+ jsp file -> java file -> class file  
  (hello.jsp -> hello_jsp.java -> hello_jsp.class)
  
![image](https://user-images.githubusercontent.com/59919620/75904338-5dc45d00-5e86-11ea-8076-c74c0523160f.png)

<br>

# Servlet
+ 특정 웹어플리케이션 컴포넌트
+ .java 확장자
+ 클라이언트의 요청에 동적으로 작동하고, 응답은 html 이용
+ java thread 이용하여 동작 (사용하는 이유)
+ MVC패턴에서 Contorller로 이용


