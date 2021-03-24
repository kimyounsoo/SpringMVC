<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form name="boardViewForm">

<input type="hidden" name="seq" id="seq" value="${seq }"> <!-- name, id 둘다 잡은 이유는 post방식의 submit을 넘겨야하기때문에 name 도잡고 -->
<input type="hidden" name="pg" id="pg" value="${pg }">		<!-- id 는 맨아래 seq를 보내기위해 잡았다 -->>

<div class="main">
	<table border="1" width="500" cellpadding="3" cellspacing="0" frame="hsides" rules="rows">
		<tr>
			<td colspan="3"><h3><span id="subjectSpan"></span></h3></td>
		</tr>

		<tr>
			<td width="150">글번호 : <span id="seqSpan"></span></td>
			<td width="190">작성자 : <span id="idSpan"></span></td>
			<td width="150">조회수 : <span id="hitSpan"></span></td>
		</tr>

		<tr>
			<td colspan="3" height="350" valign="top" 
			style="white-space: pre-wrap; word-break: break-all;"><span id="contentSpan"></span>
			</td>
		</tr>
	</table>
	<div class="button">
		<input type="button" value="목록" onclick="location.href='../board/boardList?pg=${pg }'">
		
		<span id="boardViewSpan">
			<input type="button" value="글수정" onclick="mode(1)">
			<input type="button" value="글삭제" onclick="mode(2)">
		</span>
		
		<input type="button" value="답글" onclick="mode(3)">
	</div>
</div>
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
function mode(num){
	if(num==1){//글수정
		document.boardViewForm.method="post";
		document.boardViewForm.action="boardModifyForm";
		document.boardViewForm.submit();
		
	}else if(num==2){//글삭제
		if(confirm("정말로 삭제하시겠습니까?")){
			document.boardViewForm.method="post";
			document.boardViewForm.action="boardDeleteForm";
			document.boardViewForm.submit();
		}		
	}else if(num==3){//답글
		document.boardViewForm.method="post";
		document.boardViewForm.action="boardReplyForm";
		document.boardViewForm.submit();
	}
}
</script>
<script>
$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/spring/board/getBoard',
		data: 'seq='+$('#seq').val(),
		dataType : 'json',
		success : function(data){
			//alert(JSON.stringify(data));
			console.log(data);
			
			$('#subjectSpan').text(data.boardDTO.subject);//data 에 boardDTO가 제이슨 형식으로 들어있기때문에 data 안의 boardDTO안에 subject 를 text로 작성하다 라는 뜻
			$('#seqSpan').text(data.boardDTO.seq);
			$('#idSpan').text(data.boardDTO.id);
			$('#hitSpan').text(data.boardDTO.hit);
			$('#contentSpan').text(data.boardDTO.content);
			
			if(data.memId == data.boardDTO.id) //(글수정)data의세션아이디가 DTO의 id 와 같으면 show 다르면 hide
				$('#boardViewSpan').show();
			else
				$('#boardViewSpan').hide();
		},
		error: function(err){
			console.log(err);
		}
	});
});
</script>























