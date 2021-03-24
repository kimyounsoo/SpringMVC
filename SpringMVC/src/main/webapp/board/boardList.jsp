<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="../css/board.css">

<input type="hidden" id="pg" value="${pg }"><!-- 여기 pg는 컨트롤러로 부터 넘어오는 pg 아래(25줄)는 검색을 위해 무조건 1로 설정되어있는 pg -->

<table id="boardListTable" border="1" cellpadding="3" cellspacing="0" frame="hsides" rules="rows">
<tr>
   <th width = "180">글번호</th> <!-- 테이블 헤더 / 가운데, 굵게 -->
   <th width = "500">제목</th>
   <th width = "180">작성자</th>
   <th width = "100">조회수</th>
   <th width = "180">작성일</th>
</tr>
</table>

<div id="boardPagingDiv" class="paging" align="center"></div>

<br><br>
<form id="boardSearchForm">
<input type="hidden" name="pg" value="1"><!-- 1페이지부터 검색하려고 value 1 설정 -->

<div style="text-align: center;">
	<select name="searchType" style="width: 100px;">
		<option value="subject" selected>제목</option>
		<option value="id">아이디</option>
	</select>
	
	<input type="search" name="keyword" id="keyword">
	<input type="button" value="검 색" id="boardSearchBtn">
</div>
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="../js/boardList.js"></script>
<script type="text/javascript">
function boardPaging(pg){ //BoardPaging.java boardPaging 의 startPage-1+")' 와 i 의 값이 pg로 들어온다 다음 이전 갈수있는 값
	var keyword = document.getElementById("keyword").value;
	if(keyword == ""){
		location.href = 'boardList?pg='+pg;	//값이없으면 boardList의 모든 값을 출력하면된다
	}else{
		$('input[name=pg]').val(pg); // 8번째줄의 pg 이다
		//검색한 글들의 페이지를 눌렀지만 검색을 누른것과 똑같은 이벤트를 발생시켜야만 한다 그러지 않으면 다시 boardList 를 불러와서 모든 글을 다보여주기때문에
		//강제로 이벤트를 발생
		//[2]페이지에서 다시 검색버튼을 누르면 그 페이지값을 기억하고 2페이지부터 찾는다
		//그래서 다시 검색 버튼을 눌렀을때 1페이지만 있는 검색결과는 2페이지로 인식해서 나오질않기때문에
		// 다시 검색버튼을 눌렀을때 1페이지부터 검색을 할 수 있도록 정의한다.
		$('#boardSearchBtn').trigger('click', 'research');
	}
}

</script>















