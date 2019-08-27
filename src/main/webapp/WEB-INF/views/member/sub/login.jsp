<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
		
	$(document).ready(function(){
		
		$("#phoneid").click(function(){
			$("#idchk").text("");			
		});

		$("#password").click(function(){
			$("#pwchk").text("");			
		});
		
		$("form").on("submit",function(event){
			event.preventDefault();
			if(  $("#phoneid").val() != ""  ){
				$.ajax({
					type : "post",
					url : "/teamSquirrel/MultiCheckServlet",
					data : 'phoneid=' + $('#phoneid').val(),
					datatype : "text",
					success : function(data){
						if(data == 0){
							$("#idchk").text("아이디가 틀렸네요.").css("color","red");
							$("#phoneid").val("");
							$("#phoneid").focus();
							$("#password").val("");
							$("#pwchk").text("")
						}else if(  $("#password").val() != ""  ){
							$.ajax({
								type : "post",
								url : "/teamSquirrel/MultiCheckServlet",
								data : 'password=' + $('#password').val(),
								datatype : "text",
								success : function(data){
									if(data == 0){
										$("#pwchk").text("비밀번호가 틀렸네요.").css("color","red");
										$("#password").val("");
									}else{
										location.href="/teamSquirrel/LoginServlet?phoneid="+$('#phoneid').val()+"&password="+$('#password').val();
									}
								}						
							});
						}
					}						
				});					
			}
			
			if( $("#phoneid").val() == "" || $("#password").val() == ""){
				
				if( $("#phoneid").val() == "" ){
					$("#phoneid").focus();
					$("#idchk").text("아이디를 입력하세요").css("color","red");
				}else if( $("#password").val() == "" ){
					$("#password").focus();
					$("#pwchk").text("비밀번호를 입력하세요").css("color","red");										
				}
			}
		});
		
		$("#findPW").on("click",function(){
			location.href='FindPWFormServlet';
		});
		
	});
</script>
<form method="post" class="form_login">
<table>
<tr>
<th> 아이디: </th>
<td><input type="text" class="login" id="phoneid" name="phoneid" placeholder="핸드폰번호 일껄요?" maxlength="11"></td>
<td class="confirm"><span id="idchk"></span></td>
</tr>
<tr>
<th> 비밀번호: </th>
<td><input type="password" class="login" id="password" name="password"></td>
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