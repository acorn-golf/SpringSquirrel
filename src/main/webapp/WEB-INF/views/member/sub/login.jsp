<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<a id="kakao-login-btn"></a>
	<a href="http://developers.kakao.com/logout"></a>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<script type='text/javascript'>
		//<![CDATA[
		// 사용할 앱의 JavaScript 키를 설정해 주세요.
		Kakao.init('d6e5cf48ae116f09fd297be33a5bc535');
		// 카카오 로그인 버튼을 생성합니다.
		Kakao.Auth.createLoginButton({
			container : '#kakao-login-btn',
			success : function(authObj) {
				$.ajax({
					type : "post",
					url : "<c:url value="/Oauth/kakao"/>",
					data : {
						access_token : authObj.access_token
					},
					datatype : "json",
					success : function(data) {
						console.dir(data);
						console.log(data);
						var loginInfo = data;
						
						if (loginInfo.errer_code == 0) {
							alert("정상적으로 로그인 되었습니다.");
							location.href = "<c:url value="/"/>";
						}
						else //-401일 경우 재발급 진행해야되니 나중에 유의할것.
							{
							alert("에러 발생 :"+loginInfo.errer_code+"\n"
									+loginInfo.err_mesg);
							}
					}
				});
			},
			fail : function(err) {
				alert(JSON.stringify(err));
			}
		});
		//]]>

		function alertClose(){
			$("#alert").html('');
		
		}
		
	$(document).ready(function(){
		
		$("#phone_id").click(function(){
			$("#idchk").text("");
			$("#pwchk").text("");
		});

		$("#userpw").click(function(){
			$("#idchk").text("");
			$("#pwchk").text("");			
		});
		
		$("#close").click(function() {
			$("#alert").html("");
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
							$("#userpw").focus();
							$("#userpw").val("");
							$("#alert").html("<div class='alert'>아이디<br>또는<br>비밀번호가 틀립니다.<br><br><input type='button' value='확인' onclick='alertClose()'></div>");
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
<style>
.login_box {	
	border-radius: 4px;
	position: absolute;
	top: 65%;
	transform: translate(-50%, -50%);
}
.login_box input[type='button'] {
	border-bottom: none;
	cursor: pointer;
	background: #808000;
	color: SeaShell;
}
#kakao-login-btn {
	position: absolute;
	top: 88%;
	left: 70%;
	transform: translate(-50%, -50%);
}
.alert {
	background-color: #F5F5F5;
	font-size: 18px;
	border-radius: 4px;
	position: absolute;
	top: 65%;
	transform: translate(-50%, -50%);
}
</style>


	<div id="alert">		
	</div>

<div class="login_box">
	<h1>Login</h1>
	<form method="POST" action="login">
		<div><input type="text" name="phone_id" id="phone_id" maxlength="11" required /><label>전화번호</label></div>
		<div><input type="password" name="userpw" id="userpw" maxlength="30" required /><label>비밀번호</label></div>
		<input type="submit" value="로그인"><br><br>
		<input type="button" value="비밀번호찾기" id="findPW">	
	</form>			
</div>

	
