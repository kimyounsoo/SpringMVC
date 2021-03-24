<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">
.contents {
    white-space: pre-line;
    word-break: break-all;
    width: 450px;
}
</style>

<form name="imageboardViewForm">
<table cellpadding="5" frame="hsides" rules="rows">
    <tr>
        <td rowspan="4">
            <img id="image1" width="200" height="200">
        </td>
        <td width = "250">
                        상품명: <span id="imageNameSpan"></span>
        </td>
    <tr> 
        <td width="250">
                        단가 : <span id="imagePriceSpan"></span>
        </td>
    </tr>
    <tr>
        <td width="250">
                         개수 : <span id="imageQtySpan"></span> 
        </td>
    </tr>
    <tr>
        <td width="250">
                         합계 : <span id="totalSpan"></span>
        </td>
    </tr>
    <tr>
        <td colspan="3" height="200" valign="top">
            <pre class="contents"><span id="imageContentSpan"></span></pre>
        </td>
    </tr>
</table>
<input type="button" value="목록" onclick="location.href='imageboardList?pg=${pg }'">
</form>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		type: 'post',
		url: '/spring/imageboard/getImageboardView',
		data: 'seq=${seq}',/* pg는 가지고갈필요없다 seq만 가지고 가면된다 위에 hidden에 id값을 잡아놧기 때문에id로 빼와도 되고 컨트롤러에서 뺴와도 되고 지금은 컨트롤러에서빼오는 방식(el태그) 로잡아놧다 */
		dataType: 'json',
		success : function(data){
			console.log(data);
		
			let total = data.imageboardDTO.imagePrice * data.imageboardDTO.imageQty; /* success 안의 data 안에  imageboardDTO 안에 imagePrice 가 있다 */
			
			$('#image1').attr('src', '../storage/'+data.imageboardDTO.image1);// 속성넣어주는 attr prop해도 된다고한다
			$('#imageNameSpan').text(data.imageboardDTO.imageName); // span에 값을넣어줄때 text쓰면된다
			$('#imageQtySpan').text(data.imageboardDTO.imageQty);
			$('#totalSpan').text(total.toLocaleString());// .toLocaleString() 3자리마다 쉼표 나오게해주는 메소드 // total은 위에 let 변수 잡아서 계산한다음 오기때문에 imageboardDTO 에서 빼는것이 아니다
			$('#imageContentSpan').text(data.imageboardDTO.imageContent); 
			
		},
		error: function(err){
			console.log(err);
		}
	});
});
</script>










