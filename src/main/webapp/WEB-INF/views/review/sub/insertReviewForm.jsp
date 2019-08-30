<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="/teamSquirrel/jquery-3.4.1.js"></script> 
<script>
	$(document).ready(function(){
		$("#cancle").on("click",function(){
			/* $("form").attr("action","ReviewListServlet"); // 아마 골프장 자세히보기로 가야되지 싶다 */
			location.href="ReviewListServlet";
		});
		
		$("#loc").on("change",function(){
			console.log($("#loc").val());
			if($("#loc").val()!="지역선택"){
				$.ajax({
					type:"get",
					url:"productSelectGolfCC",
					data:{
						loc_ID:$("#loc").val()
					},
					dataType:"text",
					success:function(data,status,xhr){
						console.log(">>"+data.trim());
						$("#defaultgolf").html(data);
					},
					error:function(xhr,status,error){
						console.log(error);
						console.log(status);
					}
				});
			}else{
				$("#defaultgolf").html("<option>골프장선택</option>");
			}
		});
	});
</script>
<form action="insertReview" method="get" name="insertReviewForm" class="form_main">
<%-- <input type="hidden" name="cc_id" value="${cc_id}"> --%>
	<table class="line_table">
		<tr>
			<td class="line_td"><select id="loc">
					<option selected="selected">지역선택</option>
					<c:forEach var="loc" items="${LocationList}" varStatus="status">
						<option value="${loc.loc_id }">${loc.loc_name }</option>
					</c:forEach>
			</select></td>
			<td class="line_td"> 
				<select name="cc_id" id="defaultgolf"> <!-- 지역을 선택할 때 마다 골프장select박스 내용이나온다, ajax : SelectGolfCCServlet CC_NAME을 받아 CC_ID추출  -->
					<option>골프장선택</option>
				</select></td>
		</tr>
		<tr>
			<th class="line_th">평점</th>
			<td class="line_td"><select name="score">
				<option>0</option>
				<option>1</option>
				<option>2</option>
				<option>3</option>
				<option>4</option>
				<option>5</option>
			</select></td>
		</tr>
		<tr>
			<th class="line_th">후기 제목</th>
			<td class="line_td"><input type="text" name="rv_title" id="rv_title"></td>
		</tr>
		<tr>
			<th class="line_th">후기 내용</th>
			<td class="line_td"><textarea rows="20" cols="30" name="rv_content" id="rv_content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right" class="line_td"><input type="button" id="cancle" value="취소">&nbsp;
			<input type="submit" value="등록"></td>
		</tr>
	</table>
</form>