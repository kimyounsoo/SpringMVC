$('#boardWriteBtn').click(function(){
	$('#subjectDiv').empty();
	$('#contentDiv').empty();

	if($('#subject').val()==''){
		$('#subjectDiv').text('제목을 입력하세요');
		$('#subjectDiv').css('color','red');
		$('#subjectDiv').css('font-size','8pt');
		$('#subjectDiv').css('font-weight','bold');
		
	}else if($('#content').val()==''){
		$('#contentDiv').text('내용을 입력하세요')
		$('#contentDiv').css('color','red')
		$('#contentDiv').css('font-size','8pt')
		$('#contentDiv').css('font-weight','bold');
		
	}else {
		$.ajax({
			type: 'post',
			url: '/spring/board/boardWrite',
			data: {'subject': $('#subject').val(),
				   'content': $('#content').val()},
			success: function(){
				alert('글쓰기 완료');
				location.href='/spring/board/boardList';
			},
			error: function(err){
				console.log(err);
			}
		});
	}
});













