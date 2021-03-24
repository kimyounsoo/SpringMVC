<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
#currentPaging {
	color: red;
	text-decoration: underline;
	cursor: pointer;
}
#paging {
	color: black;
	text-decoration: none;
	cursor: pointer;
}
</style>

<form id="imageboardListForm" method="get" action="imageboardDelete">

<table id="imageboardListTable" border="1" frame="hsides" rules="rows">
 <tr>
  	<th width="100"><input type="checkbox" id="all">번호</th>
 	<th width="150">이미지</th>
 	<th width="150">상품명</th>
 	<th width="100">단가</th>
 	<th width="100">개수</th>
 	<th width="100">합계</th>
 </tr>
</table>

<div style="float: left;">
	<input type="button" value="선택삭제" id="choiceDeleteBtn">
</div>

<div id="imageboardPagingDiv" style="float: left; width: 600px; text-align: center;"></div>
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/spring/imageboard/getImageboardList',
		data: 'pg=${pg}',// $는 $안의 값을 가져오는거라 '' 안에있든 밖에잇든 상관없다/* 컨트롤러 에서 보내온  pg 받기: 아이디속성에서 잡아온것 - $('#id').val() / 컨트롤러에서넘어온것 - el태그 ${pg} */
		dataType: 'json',
		success : function(data){
			//console.log(data);
			
			$.each(data.list, function(index, items){
				$('<tr/>').append($('<td/>',{
					align: 'center',
					text: items.seq
				}).prepend($('<input/>',{ // append가 뒤에붙이는거라면 prepend는 앞에 붙이는것이다 seq앞에 붙인것
					type: 'checkbox',
					name: 'check',
					value: items.seq
				}))
				).append($('<td/>',{ //이미지가 td의 옆에(자손) 으로 붙어야하기때문에 td 의 괄호를 찾아서 img를 넣어준다
					align: 'center',
					
					}).append($('<img/>',{
						src: '/spring/storage/'+items.image1,
						style: 'width: 70px; height: 70px; cursor: pointer;',
						class: items.seq+''
					}))
				
				).append($('<td/>',{
					align: 'center',
					text: items.imageName
					
				})).append($('<td/>',{
					align: 'center',
					text: items.imagePrice.toLocaleString()
					
				})).append($('<td/>',{
					align: 'center',
					text: items.imageQty
					
				})).append($('<td/>',{
					align: 'center',
					text: (items.imagePrice * items.imageQty).toLocaleString()
				})).appendTo($('#imageboardListTable'));   /* imageboardListTable 뒤에 붙여라 */
				
				//이미지마다 클래스속성 부여해줬다
				//이미지 보기 each문 안에 넣어주자
				$('.'+items.seq).click(function(){
							//이건 위에 'pg=${pg}'와는 다르게 $가없어서 $안의 값을 가져오는것이 아니라 값들에는 '' 구분 해줘야한다
							location.href = '/spring/imageboard/imageboardView?seq='+items.seq+'&pg='+data.pg;// seq는 items에 있어서 items.seq pg는 제이슨으로 넘어오기때문에 data에 있어서data.pg 
				});
			});//each
			
			//페이징처리
			$('#imageboardPagingDiv').html(data.imageboardPaging.pagingHTML);
		},
		error: function(err){
			console.log(err);
		}
	});
});


function imageboardPaging(pg){
	location.href="imageboardList?pg="+pg;
}

//전체 선택 또는 해제
$('#all').click(function(){
	//alert($('#all').attr('checked')); - checked 속성이 없어서 undefind으로 나온다
	//alert($('#all').prop('checked')); - true, false
	
	if($('#all').prop('checked')) // 속성을 꺼내오는것 .attr 하면 문자열로 꺼내오기때문에 ture인지 false인지 알수 없다	
		$('input[name=check]').prop('checked', true); //속성을 주는것 56번줄에 id가 아니라 name으로 줬기때문에
	else
		$('input[name=check]').prop('checked', false);	
});

//선택 삭제
$('#choiceDeleteBtn').click(function(){
	let count = $('input[name=check]:checked').length;// 체크가 되어있다면 length를 계산해라
	
	if(count == 0)
		alert('삭제할 항목을 선택하세요');
	else{
		if(confirm('정말로 삭제하시겠습니까?')){
			$('#imageboardListForm').submit(); //submit 은 action 타고 가야하는데 id여야 갈수 있어서 name 에서 id 로 변경 (imageboardListForm)
		}
	}
});




//둘다 태그안에있는 속성을 가져오는것
/* .attr() - HTML 에 작성된 속성값을 문자열 그대로 받아온다
 * 
 

   .prop() - 자바스크립트의 프로퍼티를 가져온다
   		   - 자바스크립트의 프로퍼티 값이 넘어오므로 boolean, date, function 등을 가져올 수 있다
   			[형식]
   			prop(key)			-> 속성값을 가져온다
   			prop(key, value) 	-> 속성값을 추가한다
   		   
   		   [실습] exam04.html
	   
   		attribute
<font color="red" */

</script>










