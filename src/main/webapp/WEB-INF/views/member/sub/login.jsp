<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">	

	$(document).ready(function(){
		
		$("#phone_id").click(function(){
			$("#idchk").text("");
			$("#pwchk").text("");
		});

		$("#userpw").click(function(){
			$("#idchk").text("");
			$("#pwchk").text("");			
		});
		
		$("form").on("submit",function(event){
			event.preventDefault();
			if(  $("#phone_id").val() != ""  ){
				$.ajax({
					type : "POST",
					url : "../multiCheck",
					data : { 
						phone_id: $('#phone_id').val(),
						userpw: $('#userpw').val()
							 },
					datatype : "text",
					success : function(data){
 						if( data == 0){
							$("#idchk").text("아이디 또는").css("color","red");
							$("#userpw").focus();
							$("#userpw").val("");
							$("#pwchk").text("비밀번호가 틀렸네요").css("color","red");
						}else{
							$("form").unbind("submit").submit();					
						} 
					}						
				});					
			}
		});
		
		$("#findPW").on("click",function(){
			location.href='findPWForm';
		});
		
	});
</script>
<form method="POST" class="form_login" action="login">
<table>
<tr>
<th> 아이디: </th>
<td><input type="text" class="login" id="phone_id" name="phone_id" placeholder="핸드폰번호 일껄요?" maxlength="11" required></td>
<td class="confirm"><span id="idchk"></span></td>
</tr>
<tr>
<th> 비밀번호: </th>
<td><input type="password" class="login" id="userpw" name="userpw" required></td>
<td class="confirm"><span id="pwchk"></span></td>
</tr>
<tr>
<td colspan="3" class="m_space"></td>
</tr>
<tr>
<td colspan="3" class="text_center">
<input class="m_sub_re" type="submit" value="로그인">&nbsp;&nbsp;<input class="m_sub_re" type="reset" value="다시입력">&nbsp;
<input type="button" value="비밀번호찾기" style="width: 110px" id="findPW">
</td>
</tr>
</table>
</form>