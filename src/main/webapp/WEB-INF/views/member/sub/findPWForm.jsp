<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var phoneid='';
		var email='';
		var email_chk='';
		$("#phoneid").on("keyup",function(){
			$.ajax({
				type : "post",
				url : "../PhoneIdCheckServlet",
				data :{
					phoneid:$("#phoneid").val()
				},
				dataType : "json",
				success : function(data,status,xhr){
					phoneid = data.phoneid;
					email = data.email;
					email_chk = data.email_chk;
					
					if(phoneid == $("#phoneid").val()){
						$("#idchk").text("정보확인");
						$("#email_chkYN").val(email_chk);
					}else{
						$("#idchk").text(phoneid);
					}
					
				},
				error : function(xhr,status,error){
					console.log(error);
					console.log(status);
				}
			});
		});
		
		$("#email").on("keyup",function(){
			if(email == $("#email").val()){
				$("#emailchk").text("정보확인");
			}else{
				$("#emailchk").text("정보없음");
			}
		});
		
		$("#find").on("click",function(event){
			event.preventDefault();
			if(phoneid == $("#phoneid").val() && email == $("#email").val() && email_chk == 'Y'){
				myForm.submit();
			}else{
				if($("#phoneid").val().length==0 || $("#email").val().length==0){
					if($("#phoneid").val().length==0 && $("#email").val().length==0){
						alert('입력란이 비었습니다');
					}else if($("#phoneid").val().length==0){
						alert('아이디를 입력하시오');
					}else if($("#email").val().length==0){
						alert('이메일 입력하시오');
					}
				}else{
					if($("#phoneid").val()!=phoneid){
						alert('정보가 없는 아이디 입니다');
					}else{
						if($("#emailchk").text() == '정보없음'){
							alert('정보가 없는 이메일 입니다');
						}else{
							if($("#email_chkYN").val() == 'N'){
								alert('인증되지 않은 이메일 입니다');
							}
						}
					}
				}
			}
		});
	});
	
		
		
</script>

<form action="../SendPWMailServlet" method="post" name="myForm" class="form_login">
<input type="hidden" id="email_chkYN" name="email_chkYN">
<table>
<tr>
<th> 아이디: </th>
<td><input type="text" class="login" id="phoneid" name="phoneid" placeholder="핸드폰번호 일껄요?" maxlength="11"></td>
<td><span id="idchk"></span></td>
</tr>
<tr>
<th> 이메일: </th>
<td><input type="email" class="login" id="email" name="email"></td>
<td><span id="emailchk"></span></td>
</tr>
<tr>
<td colspan="2" align="right"><input type="submit" value="찾기" id="find" style="width: 50px"></td>
</tr>
</table>
</form>