<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form name="writeForm" method="post" >
<h3>회원가입</h3>
<hr>
<table border="1" cellpadding="3" cellspacing="0">
<tr>
	<td width="100" align="center">이름</td>
	<td>
		<input type="text" id="name" name="name" placeholder="이름입력">
		<div id="nameDiv" style="color: red; font-size: 8pt; font-weight: bold;"></div>
	</td>
</tr>

<tr>
	<td align="center">아이디</td>
	<td>
		<input type="text" id="id" name="id" size="25" placeholder="아이디 입력">
		<!-- <input type="button" id="checkIdBtn" value="중복체크"> --> <!-- 네이버처럼 중복체크 아예 없앴다 -->
		<input type="hidden" id="check" value=""><!-- 넘어갈 값은 아니기떄문에 id 속성만 부여해준다 -->
		<div id="idDiv" style="color: red; font-size: 8pt; font-weight: bold;"></div>
	</td>
</tr>

<tr>
	<td align="center">비밀번호</td>
	<td>
	<input type="password" id="pwd" name="pwd" size="30">
	<div id="pwdDiv" style="color: red; font-size: 8pt; font-weight: bold;"></div>
	</td>
</tr>

<tr>
	<td align="center">재확인</td>
	<td>
	<input type="password" name="repwd" size="30">
	<div id="repwdDiv" style="color: red; font-size: 8pt; font-weight: bold;"></div>
	</td>
</tr>

<tr>
	<td align="center">성별</td>
	<td>
		<input type="radio" id ="gender" name="gender" value="0" checked>남
		<input type="radio" name="gender" value="1">여
	</td>
</tr>

<tr>
	<td align="center">이메일</td>
	<td>
		<input type="text" id="email1" name="email1" size="15">
		@
		<input type="email" id="email2" name="email2" list="email2" placeholder="직접입력">
		<datalist id="email2">
			<option value="gmail.com">
			<option value="naver.com">
			<option value="hanmail.net">
		</datalist>
	</td>
</tr>

<tr>
	<td align="center">핸드폰</td>
	<td>
	<select name="tel1" id="tel1" style="width: 60px;">
		<option value="010">010
		<option value="011">011
		<option value="019">019
	</select>
	 -
	 <input type="text" id="tel2" name="tel2" size="5">
	 -
	 <input type="text" id="tel3" name="tel3" size="5"></td>
</tr>

<tr>
	<td align="center">주소</td>
	<td>
		<input type="text" id="zipcode" name="zipcode" size="5" readonly>
		<input type="button" id="checkPostBtn" value="우편번호검색" ><br>
		<input type="text" id="addr1" name="addr1" size="50"  readonly placeholder="주소"><br>
		<input type="text" id="addr2" name="addr2" size="50" placeholder="상세주소">
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
	<input type="button" value="회원가입" id="writeBtn">
	<input type="reset" value="다시작성"></td>
</tr>
</table>
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>















