<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
수정할 아이디 입력 : <input type="text" id="searchId">
<input type="button" id="searchBtn" value="검색">
<br><br>
<div id="resultDiv"></div>

<div id="modifyFormDiv" aciton="">
<form id="modifyForm">
			<!-- post방식과 action은 jQuery 문법에 표기해 놨기때문에 빼줘도 된다 -->
<table border="1" cellpadding="3" cellspacing="0">
				<tr>
				   <td width="100">이름</td>
				   <td>
				      <input type="text" name="name" id="name">
				      <div id="nameDiv"></div>
				   </td>
				</tr>

				<tr>
					<td width="100">아이디</td>
					<td><input type="text" name="id" id="id" readonly>
					<!-- jQuery 이용하기위해 id 추가한다 밑에 library 가 있어서 onclick 없어도 이벤트가 발생한다 -->
						<div id="idDiv"></div>
					</td>
				</tr>

				<tr>
					<td width="100">비밀번호</td>
					<td><input type="password" name="pwd" id="pwd">
						<div id="pwdDiv"></div></td>
				</tr>

				<tr>
					<td colspan="2" align="center">
						<input type="button" value="회원정보수정" id="modifyBtn"> 
						<input type="reset" value="다시작성" id="resetBtn"> <!-- 다시작성 눌렀을때  아이디와 이름에 value 값이 없기때문에 전부 사라저버린다 다른건 상관없지만 아이디는 사라저선 안된다 -->
					</td>									<!-- 그래서 readonly 걸린 아이디는 사라져선 안된다 다시작성 버튼과 검색 버튼이 같은 기능을 해야한다 -->
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){ // onload 와 같은기능 화면이뜨자마자 수행
	 $('#modifyFormDiv').hide(); 
	 
	 $('#resetBtn').click(function(){ //바로 아래 function(event) event 넣어준다 스프링에서 해결해주는 콜백메소드 같은역할이다
		 // 아래 디비가는 ajax 코드를 여기에 복붙하면 누를때마다 디비를 갔다와서 없어지지 않지만 코드가 길어질경우 같은코드를 또쓰는건 비효율적이다
		 //강제로 이벤트 발생 나(다시작성 버튼) 가 강제적으로 강압적으로 검색버튼 의 이벤트를 발생시키는것을 트리거 라고한다
		 // $('#searchBtn').click(function(event) 이벤트는 직접 trigger간접 이벤트
		 //내용을 다 지우는 reset 기능이 작동되고 난 후 검색한 아이디를 정보를 끌고오는 순서
		 $('#searchBtn').trigger('click');//강제로 이벤트를 발생
	 });

	  $('#searchBtn').click(function(event){
		  $('#resultDiv').empty();// 지우고 물어봐야 하기때문에

		  if($('#searchId').val() == ''){ //$ 라는(제이쿼리) 함수가 searchId 가져온다
		         $('#resultDiv').text('먼저 아이디를 입력하세요');
		         $('#resultDiv').css('color', 'red');
		         $('#resultDiv').css('font-size', '10pt');
		         $('#resultDiv').css('font-weight', 'bold');
			} else {
				 $.ajax({
					type: 'post',
					url: '/chapter06_SpringWebMaven/user/getUser',
					data: {'id': $('#searchId').val()},//보낼 데이터 = #searchId 검색할 벨류값
					dataType: 'json', //리턴 될 타입 설정
					success: function(data){ // 위에 data 가 이 data(책) 가 아니다 data -> result 변경 가능
						//alert(JSON.stringify(data));

						 if($.isEmptyObject(data)){// is~~~() 는 true false 리턴 // 비어있다면
							  $('#resultDiv').text('수정할 아이디가 없습니다');
			                  $('#resultDiv').css('color','red')
			                  $('#resultDiv').css('font-size','10pt')
			                  $('#resultDiv').css('font-weight','bold')
						} else {
							$('#modifyFormDiv').show(); // 아니라면 보인다 위의 hide 반대

			                  $('#name').val(data.name);
			                  $('#id').val(data.id);
		               }
		            }   
		         });
		      }
		   });
	  
	 // 회원정보 수정버튼
	 $('#modifyBtn').click(function(){ // modifyBtn 버튼을 클릭 했을 때
			$('#nameDiv').empty();
			$('#idDiv').empty();
		
			if($('#name').val()=='')
				$('#nameDiv').text('이름을 입력하세요').css('color', 'red').css('font-size','8pt').css('font-weight','bold');
			else if($('#pwd').val()=='')
				$('#pwdDiv').text('비밀번호를 입력하세요').css('color', 'red').css('font-size','8pt').css('font-weight','bold');
			else
				//$('#modifyForm').submit(); // modifyFormDiv에 action="" 해서 넘겨도 된다 하지만 제이쿼리 이용한다
				$.ajax({ // 에이작스가 디비갔다오는 기능
					type: 'post',
					url: '/chapter06_SpringWebMaven/user/modify',
					data: $('#modifyForm').serialize(), // 객체들 한번에 보내는 기능
					success: function(){
						alert('회원정보를 수정 하였습니다.');
						location.href='/chapter06_SpringWebMaven/index.jsp';
					}
				});
	 });
});
</script>
</body>
</html>



