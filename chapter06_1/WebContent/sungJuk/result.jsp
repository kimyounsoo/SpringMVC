<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body> <!-- name이라는 필드를 부르는것이 아니라 getName의 메소드를 부르는것이 가능하다 -->
*** ${sugJukDTO.name } 의 성적 ***<br>
총 점 : ${sungJukDTO.tot }<br>
평 균 : ${sungJukDTO.avg }<br>
</body>
</html>