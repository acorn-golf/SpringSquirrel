<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
<script type="text/javascript">

	
 var multichk = true;

	$(document).ready(function(){
		
		$("input").keydown(function(event){
			if(event.keyCode === 13){
				event.preventDefault();
			}
		});
		
 		$("#email").on("keyup",function(){
			$.ajax({
				type:"post",
				url:"MultiCheckServlet",
				data: { email : $("#email").val()},				
				dataType:"text",
				success : function(data,status,xhr){	
					if(data == 0){
						$("#emailchk").text("사용 가능").css("color","green");
						multichk = true;
					}else{
						multichk = false;
						$("#emailchk").text("사용 불가").css("color","red");
							$("form").on("submit",function(event){							
								if(!multichk){
									alert("email이 중복입니다.");
									event.preventDefault();
									$("#email").val("");
									$("#email").focus();
									$("#emailchk").text("<--");
								}					
							});
						}					
					},
				error : function(xhr,status,error){
					console.log(xhr);
					console.log(status);
					console.log(error);					
				}										
			});				
		});	 
		$("#userpw").on("keyup",function(){
			if($("#userpw").val().length < 7){
				$("#confirmpw").text("사용 불가").css("color","red");
			}else{
				$("#confirmpw").text("사용 가능").css("color","green");
			}
		});
		
		$("#repassword").on("keyup",function(){
			if($("#userpw").val()==($("#repassword").val())){			
				$("#matchedpw").text("비밀번호 일치").css("color","green");
			}else{
				$("#matchedpw").text("비밀번호 불일치").css("color","red");
			};
		});

		if(${login.gender} == 1){
			$("#male").attr("checked","checked");
		}else{
			$("#female").attr("checked","checked");
		}		
		$("[type='radio']").attr("disabled","true");
		
		$("input[type='button']").on("click",function(){
			location.href="SendMailServlet";
		});
		
	});

</script>

<form method="post" class="form_main" action="myPageUpdate">
<input type="hidden" id="user_no" name="user_no" value="${login.user_no}">
<input type="hidden" id="rating" name="rating" value="${login.rating}">
<input type="hidden" id="rstartdate" name="rstartdate" value="${login.rstartdate}">
<input type="hidden" id="renddate" name="renddate" value="${login.renddate}">
<input type="hidden" id="email_check" name="email_check" value="${login.email_chk}">
<input type="hidden" id="gender" name="gender" value="${login.gender}">
<table>
<tr>
<th>아이디:</th>
<td><input class="myupdate" type="text" id="phone_id" name="phone_id" value="${login.phone_id}" readonly></td>
<td class="confirm"><span id="idchk"></span></td>
</tr>
<tr>
<th><font class="red">*</font>비밀번호:</th>
<td><input class="myupdate" type="password" id="userpw" name="userpw" required></td>
<td class="confirm"><span id="confirmpw"></span></td>
</tr>
<tr>
<th><font class="red">*</font>비밀번호확인:</th>
<td><input class="myupdate" type="password" id="repassword" name="repassword" required></td>
<td class="confirm"><span id="matchedpw"></span></td>
</tr>
<tr>
<td></td><td class="text_left"><font class="red">7자리이상 입력하셔야합니다.</font></td><td></td>
</tr>
<tr>
<td colspan="3" class="m_space"></td>
</tr>
<tr>
<th>이름:</th>
<td><input class="myupdate" type="text" id="username" name="username" value="${login.username}" readonly></td>
</tr>
<tr>
<th>닉네임:</th>
<td><input class="myupdate" type="text" id="nickname" name="nickname" value="${login.nickname}" readonly></td>
</tr>
<tr>
<th>생년월일:</th>
<td><input class="myupdate" type="text" id="userssn" name="userssn" value="${login.userssn}" readonly></td>
</tr>
<tr>
<th style="text-align: right;">남<input type="radio" id="male" value="male" name="gender"></th>
<th style="text-align: left;">여<input type="radio" id="female" value="female" name="gender"></th>
</tr>
<tr>
<th><font class="red">*</font>e-mail:</th>
<td><input class="myupdate" type="email" id="email" name="email" value="${login.email}"></td>
<td class="confirm"><span id="emailchk"></span>
<c:if test="${login.email_chk eq 'N'}">
	<input type="button" value="인증하기" style="width: 80px" id="emailchk">
</c:if>
</td>
</tr>
<tr>
<th colspan="2" class="m_space"><font class="red">*</font> 은 수정가능</th>
</tr>
<tr>
<td colspan="2" class="text_center"><input class="m_sub_re" type="submit" value="수정 하기">&nbsp;&nbsp;<input class="m_sub_re" type="reset" value="다시 작성"></td>
</tr>
</table>
</form>