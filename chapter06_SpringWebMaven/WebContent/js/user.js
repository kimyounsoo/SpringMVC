/* 		function checkwrite(){
			if(document.getElementById("name").value=="") 
				document.getElementById("nameDiv").innerText = "이름을 입력하세요";
		} */


	/*제이쿼리 문법*/
	/*id가 #writeBtn인 제이쿼리객체($)가 클릭 했을 때*/
	/*div영역에 데이터가 들어가면 지워줘야한다*/
$('#writeBtn').click(function(){
	$('#nameDiv').empty();
	$('#idDiv').empty();
	$('#pwdDiv').empty();
	
		
	if($('#name').val()=='')
		$('#nameDiv').text('이름을 입력하세요');
	else if($('#id').val()=='')
		$('#idDiv').text('아이디를 입력하세요');
	else if($('#pwd').val()=='')
		$('#pwdDiv').text('비밀번호를 입력하세요');
	else
			//document.writeForm.submit(); // 네임속성
			//$('#writeForm').submit();  // 아이디 속성
			//$('form[name=writeForm]').submit(); // 폼태그의 라이트폼의 네임속성
			//$. 해도 되고 jquery. 해도 된다 위의 $()와는 다른 개념이다 $(~~) 는 함수 (~~를 jquery 객체로 리턴)
			// 제이슨 표기법, 변수명 : 값 여기도 url 주소 "write" 로 생략 가능하다
			//ajax() 괄호안에 코드 넣을때 {} 중괄호 넣어줘야한다 "" 더블따옴표도 되고 '' 싱글따옴표도 된다
		$.ajax({
			type: 'post',
			url: '/chapter06_SpringWebMaven/user/write', 
			data: {'name': $('#name').val(), 
			       'id': $('#id').val(),
			       'pwd': $('#pwd').val()},
			success: function(){
				alert("회원 등록 완료");
				location.href='/chapter06_SpringWebMaven/index.jsp';
			},
			error: function(){
				alert("회원 등록 실패");
			}
		});
	}); /* --> 함수가 제이슨객체로 리턴 해준다 제이쿼리는 주로 싱글따옴표를 쓴다 이거 한줄로 위에 주석들을 다 생략할수 잇다 */
	/* input type의 객체의 id의 값을 리턴해준다*/

$('#id').focusout(function(){
	$('#idDiv').empty();
	
	if($('#id').val()==''){
		$('#idDiv').text('필수정보 입력');
		$('#idDiv').css('color', 'magenta');
	}else{
			// 포커싱을 빠져 나왓을 때 중복체크(네이버와동일) 하고싶으면 65번째 줄 ajax 코드 여기에 넣으면 된다
		}
	});
	
	//checkIdBtn 버튼을 click(이벤트발생)했을때 function 기능 작동
	$('#checkIdBtn').click(function(){
		$('#idDiv').empty();
		
		if($('#id').val()==''){
			$('#idDiv').text('필수정보 입력');
			$('#idDiv').css('color', 'magenta')
		}else{
			$.ajax({
				type: 'post', // 중복인지아닌지만 확인하기 때문에 post 든 get이든 상관없다 
				url: '/chapter06_SpringWebMaven/user/checkId', // jsp 파일로 가라는것이 아니고 UserController에(서버) value="/checkId" 로 보내는것이다
				data: 'id='+$('#id').val(),
				dataType: 'text', // 자바가 아니라 자바스크립이라  String, int 아니다 자바스크립 자료형은 'xml', 'json', 'html', 'text' 있다.
								// 자바스크립은 객체는 받을수 없기때문에 객체는 'json' 으로 변경해야한다
				success: function(result){ // result 는 임의로 내가 정할 수 있는 이름  UserController의 checkId의 리턴 값을 받아서 성공, 실패 뜬다
					if(result == '사용 가능'){
						$('#idDiv').text(result);
						$('#idDiv').css('color', 'blue'); 
						$('#idDiv').css('font-size','8pt')
						$('#idDiv').css('font-weight','bold');
					}else if(result == '사용 불가능'){
						$('#idDiv').text(result);
						$('#idDiv').css('color', 'red');
						$('#idDiv').css('font-size','8pt')
						$('#idDiv').css('font-weight','bold');
					}

					},error: function(){ // 실패 조건은 UserController에서 데이터가 클라이언트로 넘어가지 않은 모든 경우이다
						alert("실패");
					}
			});
	}
});
	
	
	
	
	