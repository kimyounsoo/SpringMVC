$('#imageboardWriteBtn').click(function(){
	$('#imageIdDiv').empty();
	$('#imageNameDiv').empty();
	$('#imagePriceDiv').empty();
	$('#imageQtyDiv').empty();
	$('#imageContentDiv').empty();
	$('#image1Div').empty();
	
	if($('#imageId').val() == 'img_' || $('#imageId').val() == ''){
		$('#imageIdDiv').text('상품코드를 입력하세요');
		$('#imageIdDiv').css('color','red');
		$('#imageIdDiv').css('font-size','8pt');
		$('#imageIdDiv').css('font-weight','bold');
	
	}else if($('#imageName').val()==''){
		$('#imageNameDiv').text('상품명을 입력하세요');
		$('#imageNameDiv').css('color','red');
		$('#imageNameDiv').css('font-size','8pt');
		$('#imageNameDiv').css('font-weight','bold');
		
	}else if($('#imagePrice').val()==''){
		$('#imagePriceDiv').text('단가를 입력하세요');
		$('#imagePriceDiv').css('color','red');
		$('#imagePriceDiv').css('font-size','8pt');
		$('#imagePriceDiv').css('font-weight','bold');
		
	}else if($('#imageQty').val() == '' ){
		$('#imageQtyDiv').text('개수를 입력하세요');
		$('#imageQtyDiv').css('color','red');
		$('#imageQtyDiv').css('font-size','8pt');
		$('#imageQtyDiv').css('font-weight','bold');
		
	}else if($('#imageContent').val() == ''){
		$('#imageContentDiv').text('내용을 입력하세요');
		$('#imageContentDiv').css('color','red');
		$('#imageContentDiv').css('font-size','8pt');
		$('#imageContentDiv').css('font-weight','bold');
	
	}else{
		//1.
		//$('#imageboardWriteForm').submit();
		
		//2. AJax 통신 할 때
		let formData = new FormData($('#imageboardWriteForm')[0]);
		
		//serializable'변수=값&변수=값'
		$.ajax({
			type: 'post',
			enctype: 'multipart/form-data',
			processData: false,  // 데이터를 컨텐트 타입에 맞게 변환 여부 둘다 false 잡아놔야 잘넘어간다
			contentType: false, // 요청 컨텐트 타입
			url: '/spring/imageboard/imageboardWrite',
			data: formData, //$('#imageboardWriteForm').serialize()
			success: function(data){
				alert("이미지 등록 완료");
				location.href = '/spring/imageboard/imageboardList'; //원래는 pg=1있어야함
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});

/*processData
- 기본값은  true
- 기본적으로 Query String으로 변환해서 보내진다('변수=값&변수=값')
- 파일 전송시에는 반드시  false로 해야 한다.(formData를 문자열로 변환하지 않는다)

contentType
- 기본적으로 "application/x-www-form-urlencoded; charset=UTF-8"
- 파일 전송시에는 'multipart/form-data'*/