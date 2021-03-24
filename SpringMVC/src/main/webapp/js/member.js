//로그인
$('#loginBtn').click(function(){
	$('#loginIdDiv').empty();
	$('#loginPwdDiv').empty();
	
	if($('#loginId').val()==''){
		$('#loginIdDiv').text('아이디를 입력')
						.css('color','red')
						.css('font-size','8pt')
						.css('font-weight','bold');
		
	}else if($('#loginPwd').val()==''){
		$('#loginPwdDiv').text('비밀번호를 입력')
						 .css('color','red')
						 .css('font-size','8pt')
						 .css('font-weight','bold');
	}else{
		$.ajax({
			type : 'post',
			url: '/spring/member/login',
			data: {'id': $('#loginId').val(), 'pwd': $('#loginPwd').val()},
			dataType: 'text',
			success: function(data){
				if(data == 'success'){
					location.href = '/spring/index.jsp';
					
				}else if(data == 'fail'){
					alert('로그인 실패');
				}
			},
			error: function(err){
				console.log(err);
			}
			
		});
	}
});

//회원가입
$('#writeBtn').click(function(){
	$('#nameDiv').empty();
	$('#idDiv').empty();
	$('#pwdDiv').empty();
	$('#repwdDiv').empty();
	
	
	
	//if($('#name').val()==''){ //네임속성
	if($('input[id=name]').val()==''){ //form태그에 네임속성의 writeForm(의 엑션 타고) 으로 가라, 아이디속성 이게 더 정확하다
		$('#nameDiv').text('이름을 입력하세요');
		$('#nameDiv').css('color','red');
		$('#nameDiv').css('font-size','8pt');
		$('#nameDiv').css('font-weight','bold');
	
	}else if($('#id').val()==''){
		$('#idDiv').text('아이디를 입력하세요')
		$('#idDiv').css('color','red')
		$('#idDiv').css('font-size','8pt')
		$('#idDiv').css('font-weight','bold');
		
	}else if($('input[name=pwd]').val()==''){
		$('#pwdDiv').text('비밀번호를 입력하세요')
		$('#pwdDiv').css('color','red')
		$('#pwdDiv').css('font-size','8pt')
		$('#pwdDiv').css('font-weight','bold');
		
	}else if($('input[name=repwd]').val() != $('input[name=pwd]').val()){ // input태그에 네임속성의 repwd 가라
		$('#repwdDiv').text('비밀번호가 맞지 않습니다')//비밀번호 재확인은 = '' 이면 안된다가 아니라 비밀번호와 != 이면 안된다로 띄운다
		$('#repwdDiv').css('color','red')
		$('#repwdDiv').css('font-size','8pt')
		$('#repwdDiv').css('font-weight','bold');
		
	}else if($('#id').val() != $('#check').val()){ //#check 안의 값이 #id 와 맞지 않다면 메시지 띄운다
		$('#idDiv').text('중복체크 하세요')
		$('#idDiv').css('color','red')
		$('#idDiv').css('font-size','8pt')
		$('#idDiv').css('font-weight','bold');
	
	}else{
		$('form[name=writeForm]').submit(); //form의 name 과 writeForm이맞아야 submit 된다 중복체크안하면 가입못한다
	}
});



//중복 아이디 체크
$('#id').focusout(function(){
	if($('#id').val()==''){
		$('#idDiv').text('먼저 아이디를 입력하세요')
		$('#idDiv').css('color','magenta')
		$('#idDiv').css('font-size','8pt')
		$('#idDiv').css('font-weight','bold');
	
	}else{
	
		$.ajax({
			type: 'post',
			url: '/spring/member/checkId',
			data: 'id='+$('#id').val(),
			dataType: 'text',
			success: function(data){
				if(data == 'exist'){
					$('#idDiv').text('사용 불가능')
					$('#idDiv').css('color','magenta')
					$('#idDiv').css('font-size','8pt')
					$('#idDiv').css('font-weight','bold');
					
				}else if(data == 'non_exist'){
					$('#check').val($('#id').val());//사용 가능 시 체크변수에 체크 후 사용 가능한 값을 넣어주는 코드 
													// #check 는 히든타입의 텍스트 상자 이다 비어있는 value(val()) 에 값을 넣어주는것이다 text() 아니다			
					$('#idDiv').text('사용 가능')
					$('#idDiv').css('color','blue')
					$('#idDiv').css('font-size','8pt')
					$('#idDiv').css('font-weight','bold');
				}
			}
		});
	}
});

//우편번호
$('#checkPostBtn').click(function(){
	//checkPost.jsp 해주면 servlet-context.xml에 작성해줘야하지만 .jsp 안해주면 컨트롤러로간다
	//그런줄알았는데 바로 작동 된다고한다 servlet-context.xml 작성안해줬는데 바로가내?? 일단은 살려놓는다
	//zipcode는 아무거나 써준거다 그래야 우편번호 창이 여러개 안뜬다
	window.open("/spring/member/checkPost", "zipcode", "width=700 height=500 scrollbars=yes");
});

$('#checkPostSearchBtn').click(function(){
	//유효성 검사 생략
	$.ajax({
		type: 'post',
		url: '/spring/member/checkPostSearch',
		data: $('#checkPostForm').serialize(), // 시도 시군구 로드네임 3개의 데이터 실어가야한다 //serialize() 해주면 폼안에있는 네임속성의 값만 전부 넘어간다 
		dataType: 'json',
		success: function(data){
				//alert(JSON.stringify(data));
				
				$('#checkPostTable tr:gt(2)').remove(); // 0행 1행 2행 밑으로 삭제 tr>2행 삭제
				
				$.each(data.list, function(index, items){ // item이 돌면서 0번째 index 1번째 index 차례대로 들어간다
					var address = items.sido+' '
								+ items.sigungu+' '
								+ items.yubmyundong+' '
								+ items.ri+' '
								+ items.roadname+' '
								+ items.buildingname;
					
					//우편번호에 보기싫은 null 삭제 
					address = address.replace(/null/g, ''); // g는 전체를 뜻한다 null이란 글자 g 전체를 '' 으로 바꿔서 address 에넣어주세요
					
					$('<tr/>').append($('<td/>',{
						align: 'center',
						text: items.zipcode
						
					})).append($('<td/>',{
						colspan: '3',
						//<a/> td의 자손
						}).append($('<a/>',{ // a태그 걸어줌
							href: '#',
							id: 'addressA',
							text: address
						}))
					).appendTo($('#checkPostTable'));
			});//each /*data를 가지고있는 list를 each문(for문) 돌아라*/
				
			$('a').click(function(){ //'a' 는 위의 <a/> 의 a 이다
				//alert($(this).prop('tagName')); // a태그 중에서 클릭 된 애를 this, alert 하면 객체가뜨는데 tag의 이름이 궁금하기때문에.prop('tagName') 해주면 A(태그) 라고 나온다
				//alert($(this).parent().prev().text());
				/*열려있는 애들 중에 아이디가 #인 애들*/
				$('#postcode', opener.document).val($(this).parent().prev().text());
				$('#address', opener.document).val($(this).text());
				$('#detailAddress', opener.document).focus();
				window.close();
			});
		},
		error: function(err){
			console.log(err);
		}
	});	
});


//회원정보수정
$('#modifyBtn').click(function(){
	$('#nameDiv').empty();
	$('#pwdDiv').empty();
	$('#repwdDiv').empty();
	
	if($('#name').val()==''){
		$('#nameDiv').text('이름을 입력하세요');
		$('#nameDiv').css('color','red');
		$('#nameDiv').css('font-size','8pt');
		$('#nameDiv').css('font-weight','bold');
		
	}else if($('input[name=pwd]').val()==''){
		$('#pwdDiv').text('비밀번호를 입력하세요')
		$('#pwdDiv').css('color','red')
		$('#pwdDiv').css('font-size','8pt')
		$('#pwdDiv').css('font-weight','bold');
		
	}else if($('input[name=repwd]').val() != $('input[name=pwd]').val()){
		$('#repwdDiv').text('비밀번호가 맞지 않습니다')
		$('#repwdDiv').css('color','red')
		$('#repwdDiv').css('font-size','8pt')
		$('#repwdDiv').css('font-weight','bold');
		
	}else{
		$.ajax({
			type: 'post',
			url: '/spring/member/modify',
			data: $('#modifyForm').serialize(), //serialize() 해주면 폼안에있는 네임속성의 값만 전부 넘어간다 
			success: function(){
				alert('회원정보수정 완료');
				location.href='/spring/index.jsp';
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});






