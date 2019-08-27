<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="/teamSquirrel/jquery-3.4.1.js"></script> 
<script type="text/javascript">
	$(document).ready(function(){
		$("#loc").on("change",function(){
			console.log($("#loc").val());
			if($("#loc").val()!="지역선택"){
				$.ajax({
					type:"get",
					url:"SelectGolfCCServlet",
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
		$("#gomain").on("click",function(){
			location.href="main.jsp";
		});
	});
</script>

<form action="ProductInsertServlet" method="get" name="myForm">
<!-- 각각의 유효성 체크 해야한다 -->
지역 : 
<select id="loc">
	<option selected="selected">지역선택</option>
	<c:forEach var="loc" items="${LocationList}" varStatus="status">
		<option value="${loc.loc_id }">${loc.loc_name }</option>
	</c:forEach>
</select><br>
골프장 : 
<select name="cc_id" id="defaultgolf"> <!-- 지역을 선택할 때 마다 골프장select박스 내용이나온다, ajax : SelectGolfCCServlet CC_NAME을 받아 CC_ID추출  -->
	<option>골프장선택</option>
</select><br>
티업일자 : <input type="text" name="date"><br>
티업시간 : <input type="text" name="time"><br>
<!-- P_UPLOADDATE는 default가 sysdate -->
그린피 : <input type="text" name="p_price">만원<br>
<input type="checkbox" name="p_babyn">식사포함&nbsp;&nbsp;&nbsp;
<input type="checkbox" name="p_cartyn">카트비 포함<br>
홀 선택: <input type="radio" name="p_hole" value="18" checked="checked">18홀&nbsp;&nbsp;
<input type="radio" name="p_hole" value="27">27홀&nbsp;&nbsp;
<input type="radio" name="p_hole" value="36">36홀<br>
캐디: <input type="radio" name="p_caddyyn" value="Y" checked="checked">캐디&nbsp;&nbsp;
<input type="radio" name="p_caddyyn" value="N">노캐디<br>
인원: <input type="text" name="p_maxpeople" readonly="readonly" value="4"><br>
상품설명 :<br>
<textarea role="20" cols="50" name="p_content">
</textarea><br>
<input type="button" value="메인으로" id="gomain">&nbsp;<input type="submit" value="등록">
<!-- 이전버튼 누를 시 전화면으로 돌아간다->자바스크립트로 찝어서 해당갈 곳으로 src 해주면 될듯 -->
<!-- USER_NO는 form에서 보낼 때 해당 서블릿에서 세션으로 받을것 -->
<!-- p_id - 시퀀스자동넘버링
P_PDATE - 서블릿에서 date와 time을 합친다
P_UPLOADDATE - default : sysdate
USER_NO - ProductInsertServlet서블릿에서 세션으로 검색
CC_ID - Ajax로 지역이 change -> 그럼 골프장명이 나오면서 option의 value가 cc_id  해서 등록버튼 누르면 자동으로 넘어감
P_VCOUNT(조회수) - 시퀀스자동넘버링 -->
</form>