$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/spring/board/getBoardList',
		data: {'pg': $('#pg').val()},
		dataType: 'json',
		success: function(data){
			//alert(JSON.stringfy(data)); //boardList.js 의 sucees의(data) 많은 데이터가 간다(5개의 글, 세션, 페이지, 페이징 처리) 콘솔에 찍어서 확인함
			
			$.each(data.list, function(index, items){ //아이템 하나씩 넣어주는 방식 캡처확인
				$('<tr/>').append($('<td/>',{
					align: 'center',
					text: items.seq
	            })).append($('<td/>',{ //td의 짝한테 a태그 걸어줬다 캡처 확인
	                
					}).append($('<a/>',{
						href: '#',
						text: items.subject,
						id: 'subjectA',
						class: items.seq+'' // 아이디의 속성이 너무많아서 클래스속성을각각 준다  $(subjectA).click( 하면 subjectA 5개에 다 이벤트를 주기때문에 5번뜬다 그래서 $('.'items.seq).click 하면 40번 seq 38번 seq는 하나씩밖에 없기때문에 하나에만 적용된다 . 은 class 속성을 뜻함 
	                }))
	             
				).append($('<td/>',{
					align: 'center',
					text: items.id
				})).append($('<td/>',{
					align: 'center',
					text: items.hit
				})).append($('<td/>',{
					align: 'center',
					text: items.logtime
				})).appendTo($('#boardListTable'));
	            
	            //답글
				for(i=0; i<items.lev; i++){
					$('.'+items.seq).before('&emsp;'); // prev는 앞 td 앞칸 이고 before는 앞 글자를 emp 로채운다
	            }
				if(items.pseq!=0){ //pseq가 0번이 아닌건 답글
					$('.'+items.seq).before($('<img/>',{
						src: '../image/reply.gif'
					}));
				}
			
				//로그인 여부
				//각각 다른 이름의 class 속성을 지정하면 이벤트가 따로 불러진다
				/*$('.'+items.seq).click(function(){
					//alert($(this).prop('tagName'));
					
					if(data.memId == null){
						alert('먼저 로그인하세요');
					}else{
						//alert($(this).parent().prev().text());
						let seq = $(this).parent().prev().text();
					}
				});*/
				
			});//each
			
			//로그인 여부
			// a태그에 이벤트를 걸면 왼쪽메뉴에서도 이벤트가 걸려서 왼쪽메뉴를 클릭하면 "먼저 로그인하세요"가 뜬다
			//$('a').click(function(){
			
			//비동적 처리 - 목록 5줄이 id가모두 'subjectA'라서 첫번째 줄에만 이벤트가 걸린다
			//$('#subjectA').click(function(){
			
			//동적 처리 - 부모 or 조상.on(이벤트, 자식, 함수(){}); #subjectA 의 조상
			$('#boardListTable').on('click', '#subjectA', function(){ // click이벤트 발생하면 비동적되어서 다음것은 처리가 안된다(비동적 방식) on 사용하면된다
				//alert($(this).prop('tagName')); // a태그 중에서 클릭 된 애를 this
				
				if(data.memId == null){
					alert('먼저 로그인하세요');
				}else{
					//alert($(this).parent().prev().text());
					let seq = $(this).parent().prev().text();
					let pg = data.pg;
					location.href = '/spring/board/boardView?seq='+seq+"&pg="+pg;
				}
			});
			//------------
			
			//페이징 처리			success의 data의 boardPaging의 pagingHTML
			$('#boardPagingDiv').html(data.boardPaging.pagingHTML); //boardList.jsp의 div영역으로 넣는것인데 html형식으로 넣어야한다
			//이전 다음 BoardPaging.java에 스크립트 잡아놧다 포문으로 그 페이지로 갈수있게도 잡아놨다 = boardPaging이라는 메소드가 필요하다
		},
		error: function(err){
			console.log(err);
		}
	});
});

//검색
$('#boardSearchBtn').click(function(event, str){// trigger event
	if(str != 'research') $('input[name=pg]').val(1); // research 가 왔다는건 trigger를 눌렀다는것 // 직접검새 버튼을 눌렀을때
	
	if($('#keyword').val() == ''){
		alert('검색어를 입력하세요');
	}else{
		$.ajax({
			type: 'post',
			url: '/spring/board/getBoardSearch',
			data: $('#boardSearchForm').serialize(), //pg, searchType, keyword
			dataType: 'json',
			success: function(data){
				//alert(JSON.stringify(data));
				
				$('#boardListTable tr:gt(0)').remove();// 검색하면 append 되어서 계속 들러붙기때문에 검색되서 불러오는값을 제외하고 0번째 보다 큰 테이블을 전부삭제한다
				
				$.each(data.list, function(index, items){ //아이템 하나씩 넣어주는 방식 캡처확인
					$('<tr/>').append($('<td/>',{
						align: 'center',
						text: items.seq
					})).append($('<td/>',{ //td의 짝한테 a태그 걸어줬다 캡처 확인
		                
						}).append($('<a/>',{
							href: '#',
							text: items.subject,
							id: 'subjectA',
							class: items.seq+'' // 아이디의 속성이 너무많아서 클래스속성을각각 준다  $(subjectA).click( 하면 subjectA 5개에 다 이벤트를 주기때문에 5번뜬다 그래서 $('.'items.seq).click 하면 40번 seq 38번 seq는 하나씩밖에 없기때문에 하나에만 적용된다 . 은 class 속성을 뜻함 
						}))
						
					).append($('<td/>',{
						align: 'center',
						text: items.id
					})).append($('<td/>',{
						align: 'center',
						text: items.hit
					})).append($('<td/>',{
						align: 'center',
						text: items.logtime
					})).appendTo($('#boardListTable'));
					
					//답글
					for(i=0; i<items.lev; i++){
						$('.'+items.seq).before('&emsp;'); // prev는 앞 td 앞칸 이고 before는 앞 글자를 emp 로채운다
		            }
					if(items.pseq!=0){ //pseq가 0번이 아닌건 답글
						$('.'+items.seq).before($('<img/>',{
							src: '../image/reply.gif'
						}));
					}
				});//each
				
				//페이징 처리
				$('#boardPagingDiv').html(data.boardPaging.pagingHTML);
			},
			error: function(err){
				console.log(err);
			}			
		});
	}
});