<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- ${param.x} + ${param.y} = ${param.x + param.y} --%>
${requestScope.x } + ${requestScope.y } = ${sum }<br>
${x } + ${y } = ${sum }<br>
두가지 같은말 아래껄로써도 되지만 위에껄로쓰는게 맞다 

<%-- ${sumDTO.x } +  ~~~ DTO 통으로 보내는방식은 이렇게 바꿔줘야한다--%>
</body>
</html>