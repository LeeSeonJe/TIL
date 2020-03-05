# Servlet

### Servlet 특징
+ 특정 웹어플리케이션 컴포넌트
+ .java 확장자
+ 클라이언트의 요청에 동적으로 작동하고, 응답은 html 이용
+ java thread 이용하여 동작 (사용하는 이유)
+ MVC패턴에서 Contorller로 이용

### 서블릿 매핑
+ 사용이유 : 주소가 너무 길어지고, 보안에 노출되어 있는 경로를 간단하게 매핑하는 것이다.
+ 1\. web.xml 매핑  <br>

  ![image](https://user-images.githubusercontent.com/59919620/75952854-48d1e300-5ef3-11ea-8f9d-1ee6615042a9.png)
  + \<servlet-name\> : 임의의 이름을 만들어 준다.
  + \<servlet-class\> : 매핑할 클래스 파일명을 패키지명을 포함하여 정확하게 입력
  + \<url-pattern\> : servlet-class의 클래스를 매핑할 임의의 이름을 입력 (**주의할 점은 '/'로 시작해야 한다.**)

+ 2\. Annotation 매핑  <br>

  ![image](https://user-images.githubusercontent.com/59919620/75953221-41f7a000-5ef4-11ea-9a57-660f29a62b6d.png)
  + @WebServlet("HW") : 매핑명(HW)을 java소스에 직접 입력 (**주의할 점은 '/'로 시작해야 한다.**)
### Request GET & POST 방식
+ 1\. doGet
  + Form 태그 method 속성값 = get으로 요청
  + URL 값으로 정보가 전송(URL 값에 정보를 실어서)되어 보안에 약함
+ 2\. doPost
  + Form 태그 method 속성값 = post로 요청
  + header를 이용해 정보가 전송되어 보안에 강함
