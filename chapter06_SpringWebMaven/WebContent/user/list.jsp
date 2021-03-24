<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <div id="display"></div> -->
	<table id="table" border="1" cellpadding="3" cellspacing="0"><!-- table에 동적으로 데이터값을 주기위해 id 부여함 -->
		<tr>
			<th width="100">이름</th>
			<th width="100">아이디</th>
			<th width="100">비밀번호</th>
		</tr>
	</table>
	<br><br>
<div class="search">
   <select name="searchOption" id="searchOption">
      <option value="">선택
      <option value="name">이름
      <option value="id">아이디
   </select> 
   <input type="text" id="searchText" name="searchText">
   <button id="searchBtn">검색</button>
</div>
	
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script><!-- 제이쿼리 끌어오기 -->
<script type="text/javascript">

/* window.onload=function(){} */ //같은 코드
/* $(document).ready(function(){}); */
$(document).ready(function(){ //$. = jQuery. 이다
	$('#searchBtn').click(function(){//검색버튼 눌렀을때
		if($('#searchOption').val()==''){//검색 옵션이 비어있다면
			alert('검색 옵션을 선택하세요');
			return false;
		}else{
			$('#table tr:gt(0)').remove(); //table 의 안에(후손) tr 의 값이 gt(>) 0이면 지워라
			
			$.ajax({
				type: 'post',
				url: '/chapter06_SpringWebMaven/user/search',   //  ex searchOption=id&searchText='h'
				
				//data:'searchOption='+$('#searchOption').val()+"&searchText="+$('#searchText').val(), //searchOption 벨류와(&) searchText벨류 데이터를 넘긴다
				
				data: JSON.stringify({'searchOption': $('searchOption').val(),
									  'searchText': $('#searchText').val()}), //문자열이 아닌 제이슨 형태로 데이터를 보낸다// stringify 해주면 제이슨의 형식 자체를 문자 그대로 넘어가게하는것이다 
			  /* 
			  JSON의 형식 그대로 문자열로 바꾸어서 제이슨의 형식이 자체를 문자그대로 이렇게 넘긴다
			  '{"searchOption": $("searchOption").val(),"searchText": $("#searchText").val()}' 
			  */
									  
									  
				cotentType: 'application/json;charset=UTF-8', // 컨텐트타입은 문자열을 제이슨 타입으로 넘기겟다고 알려주는 것이다 안하면 데이터가 넘어가지 못한다. /json 이라고 써줘서 제이슨 형태로 보낸다고 알려줘야한다 
				dataType: 'json',							  
				success: function(data){
					//alert(JSON.stringify(data));
					
			         $.each(data.list, function(index, items){
			             $('<tr/>').append($('<td/>',{ // tr태그에 아래 내용을 갖다 붙이라는 뜻
			                align: 'center',
			                text: items.name
			             })).append($('<td/>',{
			                align: 'center',
			                text: items.id
			             })).append($('<td/>',{
			                align: 'center',
			                text: items.pwd
			             })).appendTo('#table'); //appendTo(캡쳐참조)뒤에나중에 table의 자식, 하위에 갖다 붙이는 명령어, for문으로 여러개 연달아 붙이고 있다 여긴 table 객체를 붙여주는거라 $가필요없다 ??
			          });
				}
			});
		}
		
	});
	
	$.ajax({
		type: 'post',
		url: '/chapter06_SpringWebMaven/user/getUserList', // UserController의 userService.getUserList로 가고 거기의 return 값이 여기의 data로 온다
		dataType: 'json', // UserController에서 온 데이터가  json 객체로 바뀌엇다
		success: function(data){ //chapter06_SpringWebMaven 출력 흐름도(캡처) 최종 종착지가 여기 data 이다
			//alert(JSON.stringify(data));// 제이슨 데이터를 문자로 보내주는기능 이걸로 찍어보면 {}밖의 data 안의 {} list 를 꺼내온다는 뜻 -> data.list
			
			//------------1번째 방법-------------
			/* var table = '<table border=1>';
			table += '<tr>';
			table += '<th width="100">이름</th>';
			table += '<th width="100">아이디</th>';
			table += '<th width="100">비밀번호</th>';
			table += '</tr>';
			
			$.each(data.list, function(index, items){ // jQuery.each(data의 배열의 list, 그 안에 fucntion(index들의, items) 0번째, 1번째 list들
				table += '<tr>';
				table += '<td align=center>'+items.name+'</td>';
				table += '<td align=center>'+items.id+'</td>';
				table += '<td align=center>'+items.pwd+'</td>';
				table += '</tr>';
				//alert(items);//자바스크립트는 16진수 주소값을 인식하지못해서 object 형태로 띄워준다
				//alert(items.name); // 이렇게하면 data의 index items들을 하나씩 찍어낸다
			});
			table += '</table>';
			$('#display').append(table);*/ //id display 에다가 append(붙여주세요)(table)을 append는 계속 누적해서 붙여주는거라 붙어야하는데 왜....
			
			
			//-------------2번째 방법--------------
	         $.each(data.list, function(index, items){
	             $('<tr/>').append($('<td/>',{ // tr태그에 아래 내용을 갖다 붙이라는 뜻
	                align: 'center',
	                text: items.name
	             })).append($('<td/>',{
	                align: 'center',
	                text: items.id
	             })).append($('<td/>',{
	                align: 'center',
	                text: items.pwd
	             })).appendTo('#table'); //appendTo(캡쳐참조)뒤에나중에 table의 자식, 하위에 갖다 붙이는 명령어, for문으로 여러개 연달아 붙이고 있다 여긴 table 객체를 붙여주는거라 $가필요없다 ??
	          });

		
		},
		error: function(err){
			console.log(err);
		}
		
	});
});
</script>
</body>
</html>