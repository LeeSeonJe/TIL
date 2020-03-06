
type : 태그종류 지정(ex, text password, submit, checkbox, radio, reset)
name : input 태그 이름
value : name에 해당하는 값

```
input
<input type = "text" name = "name" size = "10">

password
<input type = "password" name = "name" size = "10">

submit = 전송버튼
<input tpye = "submit" value = "전송">

reset = 초기화버튼
<input type = "rest" value = "초기화">

checkbox = type과 name이 동일하다. value 값만 다르게 사용, 중복가능
<input type="checkbox" name = "hobby" value = "read" > 독서
<input type="checkbox" name = "hobby" value = "cook" > 요리
<input type="checkbox" name = "hobby" value = "run" > 조깅
<input type="checkbox" name = "hobby" value = "swim" > 수영
<input type="checkbox" name = "hobby" value = "sleep" > 취침

radio = type과 name이 동일하다. value 값만 다르게 사용, 중복불가, checked는 default 값
<input type = "radio" name = "major" value = "kor"> 국어
<input type = "radio" name = "major" value = "eng" checked = "checked" > 영어
<input type = "radio" name = "major" value = "mat"> 수학
<input type = "radio" name = "major" value = "des"> 디자인

select = 리스트 형태의 데이터를 사용
<select name = "protocol">
	<option value = "http"> http </option>
	<option value = "ftp" selected = "selected > ftp </option>
	<option value = "smtp"> smtp </option>
	<option value = "pop"> pop </option>
</select>
```

### 
form
<form action = "FormEx" method = "post">
