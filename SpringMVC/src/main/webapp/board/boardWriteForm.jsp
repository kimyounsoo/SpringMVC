<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form id="boardWriteForm">
<h3>글쓰기</h3>
<hr>
<table border="1" cellpadding="3" cellspacing="0">
<tr>
<td align="center" width="60px">제   목</td>
<td>
 <input size="70" type="text" name="subject" id="subject" placeholder="제목을 입력하세요">
 <div id="subjectDiv"></div>
</td>
</tr>
<tr>
<td align="center">내   용</td>
<td>
 <textArea name="content" id="content" placeholder="내용을 입력하세요" rows="20" cols="70"></textArea>
 <div id="contentDiv"></div>
</td>
</tr>
<tr>
<td align="center" colspan="2">
 <input type="button" value="글쓰기" id="boardWriteBtn">
 <input type="reset" value="다시작성">
</td>
</tr>
</table>
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/board.js"></script>











