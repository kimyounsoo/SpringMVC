<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form name="modifyForm" id="modifyForm">
<h3>회원정보수정</h3>
<hr>
<table border="1" cellpadding="3" cellspacing="0">
<tr>
	<td width="100" align="center">이름</td>
	<td>
		<input type="text" id="name" name="name" value="${memberDTO.name }" placeholder="이름입력">
		<div id="nameDiv"></div>
	</td>
</tr>

<tr>
	<td align="center">아이디</td>
	<td>
		<input type="text" id="id" name="id" value="${memberDTO.id }" size="25" readonly>
	</td>
</tr>

<tr>
	<td align="center">비밀번호</td>
	<td>
		<input type="password" name="pwd" id="pwd" size="30">
		<div id="pwdDiv"></div>
	</td>
</tr>

<tr>
	<td align="center">재확인</td>
	<td>
		<input type="password" name="repwd" id="repwd" size="30">
		<div id="repwdDiv"></div>
	</td>
</tr>

<tr>
	<td align="center">성별</td>
	<td>
		<input type="radio" name="gender" value="0">남
		<input type="radio" name="gender" value="1">여
	</td>
</tr>

<tr>
	<td align="center">이메일</td>
	<td>
		<input type="text" name="email1" value="${memberDTO.email1 }" size="15">
		@
		<input type="email" name="email2" list="email2" placeholder="직접입력">
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
	<select name="tel1" style="width: 60px;">
		<option value="010">010
		<option value="011">011
		<option value="019">019
	</select>
	 -
	 <input type="text" name="tel2" value="${memberDTO.tel2 }" size="5">
	 -
	 <input type="text" name="tel3" value="${memberDTO.tel3 }" size="5"></td>
</tr>

<tr>
	<td align="center">주소</td>
	<td>
		<input type="text" id="postcode" name="zipcode" value="${memberDTO.zipcode }" size="5" readonly>
		<input type="button" value="우편번호검색" id="checkPostBtn"><br>
		<input type="text" id="address" name="addr1" value="${memberDTO.addr1 }" size="50"  readonly placeholder="주소"><br>
		<input type="text" id="detailAddress" name="addr2" value="${memberDTO.addr2 }" size="50" placeholder="상세주소">
	</td>
</tr>

<tr>
	<td colspan="2" align="center">
	<input type="button" value="회원정보수정" id="modifyBtn">
	<input type="reset" value="다시작성">
</tr>
</table>
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/member.js"></script>
<script type="text/javascript">
window.onload=function(){
	document.modifyForm.gender['${memberDTO.gender }'].checked = true;
	document.modifyForm.email2.value = "${memberDTO.email2 }";
	document.modifyForm.tel1.value = "${memberDTO.tel1 }";
}
</script>






















