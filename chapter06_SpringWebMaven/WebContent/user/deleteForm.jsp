<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
삭제할 아이디 입력 : <input type="text" id="searchId">
<input type="button" id="searchBtn" value="검색">
<br><br>
<div id="resultDiv"></div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){ // onload와 같은기능
	$('#searchBtn').click(function(){
		$('#resultDiv').empty();
		
		  if($('#searchId').val() == ''){ //$ 라는(제이쿼리) 함수가 searchId 가져온다
		         $('#resultDiv').text('먼저 아이디를 입력하세요');
		         $('#resultDiv').css('color', 'red');
		         $('#resultDiv').css('font-size', '10pt');
		         $('#resultDiv').css('font-weight', 'bold');
		} else {
			$.ajax({ //제이쿼리에서.ajax를 연동한다
				type: 'post',
				url: '/chapter06_SpringWebMaven/user/getUser', // 먼저 아이디를 끌고 온 후에 삭제 해야하므로 modifyForm의 수정전 아이디 끌고온것과 같은방식이다
				data: {'id': $('#searchId').val()},
				dataType: 'json', //리턴 될 타입 설정
				success: function(data){
					//alert(JSON.stringify(data));
					//if(JSON.stringify(data) == JSON.stringify({})){ // == "" 이렇게하면 제이슨 오브젝트 객체는 오지만 그 안의 값이 오지않는다 제이슨의 값을 문자열로 바꿔서 받은것이기때문에 이렇게써줘야한다
					//if(JSON.stringify(data) == "{}"){ 
						if($.isEmptyObject(data)){ // 바로위에것 아니면 이렇게 해준다 이게 더 편하긴하다
						  $('#resultDiv').text('삭제할 아이디가 없습니다');
		                  $('#resultDiv').css('color','red')
		                  $('#resultDiv').css('font-size','10pt')
		                  $('#resultDiv').css('font-weight','bold')

					}else{
						if(confirm("정말로 삭제하시겠습니까?")){
							$.ajax({
								type: 'post',
								url: '/chapter06_SpringWebMaven/user/delete',
								data: {'id': $('#searchId').val()},
								success: function(){
									alert("회원정보 삭제 완료");
									location.href='/chapter06_SpringWebMaven/index.jsp';
								}
							});
						}
						
					}
				},
				error: function(err){
					console.log(err);
				}
			});
		}

	});
});
</script>
</body>
</html>