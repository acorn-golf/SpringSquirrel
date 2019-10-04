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
		$("#password").on("keyup",function(){
			if($("#password").val().length < 7){
				$("#confirmpw").text("사용 불가").css("color","red");
			}else{
				$("#confirmpw").text("사용 가능").css("color","green");
			}
		});
		
		$("#repassword").on("keyup",function(){
			if($("#password").val()==($("#repassword").val())){			
				$("#matchedpw").text("비밀번호 일치").css("color","green");
			}else{
				$("#matchedpw").text("비밀번호 불일치").css("color","red");
			};
		});
		$("[type='button']").on("click",function(event){

				location.href="/golfhi/member/adminModified.jsp";
			
		});
		$("#adminModified").click(function(){
			
				this.action="adminModified";
		});

	});

</script>

<form id="adminModified" method="get" class="form_main">
<input class="adminupdate" type="hidden" id="user_no" name="user_no" value="${userinfo.user_no}">
<table>
<tr>
<th>아이디:</th>
<td><input class="adminupdate" type="text" id="phone_id" name="phone_id" value="${userinfo.phone_id}" readonly></td>
<td class="confirm"><span id="idchk"></span></td>
</tr>
<tr>
<th>이름:</th>
<td><input class="adminupdate" type="text" id="username" name="username" value="${userinfo.username}"></td>
</tr>
<tr>
<th>닉네임:</th>
<td><input class="adminupdate" type="text" id="nickname" name="nickname" value="${userinfo.nickname}"></td>
</tr>
<tr>
<th>등급:</th>
<td><input class="adminupdate" type="text" id="rating" name="rating" value="${userinfo.rating}"></td>
</tr>
<tr>
<th>등급 시작일:</th>
<td><input class="adminupdate" type="text" id="rstartdate" name="rstartdate" value="${userinfo.rstartdate}"></td>
</tr>
<tr>
<th>추가 :</th> 
<td>
<select class="text_center" id="sRenddate" name="sRenddate">
<c:forEach var="month" begin="0" end="12">
<option>${month} 개월</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<th>등급 종료일:</th>
<td><input class="adminupdate" type="text" id="renddate" name="renddate" value="${userinfo.renddate}"></td>
</tr>
<tr>
<th>e-mail:</th>
<td><input class="adminupdate" type="email" id="email" name="email" value="${userinfo.email}"></td>
<td class="confirm"><span id="emailchk"></span></td>
</tr>
<tr>
<td colspan="2" class="m_space"></td>
</tr>
<tr>
<td colspan="2" class="text_center"><input class="m_sub_re" type="submit" value="수정 하기">&nbsp;&nbsp;<input class="m_sub_re" type="button" value="취소"></td>
</tr>
</table>
</form>