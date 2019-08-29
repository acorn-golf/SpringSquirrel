<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">

var idchk = true;
var nickchk = true;
var emailchk = true;
var captchachk = false;

function changeCaptcha() {

	  $('#catpcha').html('<img src="/teamSquirrel/CaptCha?rand='+ Math.random() + '"/>');
	 }

function winPlayer(objUrl) {
	  $('#audiocatpch').html(' <bgsound src="' + objUrl + '">');
	 }
	 
function audioCaptcha() {

	   var uAgent = navigator.userAgent;
	   var soundUrl = '/teamSquirrel/AudioCaptCha';
	   if (uAgent.indexOf('Trident') > -1 || uAgent.indexOf('MSIE') > -1) {
	       //IE일 경우 호출
	       winPlayer(soundUrl+'?agent=msie&rand='+ Math.random());
	   } else if (!!document.createElement('audio').canPlayType) {
	       //Chrome일 경우 호출
	       try { new Audio(soundUrl).play(); } catch(e) { winPlayer(soundUrl); }
	   } else window.open(soundUrl, '', 'width=1,height=1');
	 }




	$(document).ready(function(){
		
		  changeCaptcha(); //Captcha Image 요청
		  
		  $('#reLoad').click(function(){ changeCaptcha(); }); //'새로고침'버튼의 Click 이벤트 발생시 'changeCaptcha()'호출
		  $('#soundOn').click(function(){ audioCaptcha(); }); //'음성듣기'버튼의 Click 이벤트 발생시 'audioCaptcha()'호출
			
		//'확인' 버튼 클릭시
		  $('#captchaSubmit').click(function(){
		      if ( !$('#captchaAnswer').val() ) {
		           alert('이미지에 보이는 숫자 또는 스피커를 통해 들리는 숫자를 입력해 주세요.');
		      } else {
		           $.ajax({
		               url: '/teamSquirrel/CaptchaConfirm',
		               type: 'POST',
		               dataType: 'text',
		               data: 'captchaAnswer=' + $('#captchaAnswer').val(),
		               async: false,  
		               success: function(data) {
		            	   console.log(data);
		            	   if( data == 0 ){
		            		   alert("입력값이 일치합니다.");  
		            		   captchachk = true;
		            	   }else{          
		            		   captchachk = false;
		                       alert("입력값이 일치하지않습니다. 다시 입력하셔야합니다.");
		                       $('#reLoad').click();
		                       $('#captchaAnswer').val('');
		                       
		            	   }
		            	   
		              }
		         });
		      }
		 
		 });
		var RegexEmail = /\w+\@\w+\.[a-zA-Z\.]{2,5}$/; 
		var RegexName = /^(가|간|갈|감|강|견|경|계|고|곡|공|곽|관|교|구|국|궉|권|근|금|기|길|김|나|난|남|남궁|낭|내|노|뇌|다|단|담|당|대|도|독|독고|돈|동|동방|두|등|등정|라|란|랑|려|로|뢰|류|리|림|마|만|망절|매|맹|명|모|목|묘|무|무본|묵|문|미|민|박|반|방|배|백|번|범|변|보|복|봉|부|비|빈|빙|사|사공|산|삼|상|서|서문|석|선우|설|섭|성|소|손|송|수|순|승|시|신|심|아|안|애|야|양|어|어금|엄|여|연|염|엽|영|예|오|옥|온|옹|완|왕|요|용|우|운|원|위|유|육|윤|은|음|이|인|임|자|장|전|점|정|제|제갈|조|종|좌|주|증|지|진|차|창|채|천|초|총|최|추|탁|탄|탕|태|판|팽|편|평|포|표|풍|피|필|하|학|한|함|해|허|현|형|호|홍|화|황|황목|황보|후)[가-힣]{1,4}$/; 
		var RegexNick = /^[a-zA-Z0-9가-힣_-]{2,8}$/; 
		var RegexPhone = /^(010|011|016|017|018|019|070)[0-9]{7,8}$/;
		var RegexPassword = /[a-zA-Z0-9\!\@\#\$\%\^\&\*\(\)\<\>\?\.]{7,20}/;
		var RegexBirth = /^(19|20)[0-9]{2}(0[0-9]|1[0-2])(0[0-9]|[12][0-9]|3[01])$/;
		
		$("input:text, input:password").keydown(function(event){
			if(event.keyCode === 13){
				event.preventDefault();
			}
		});
		
		$("form").on("submit",function(event){			

				if ( $("#phoneid").val() == ""){		
					
					alert("아이디를 입력해주세요.");
					$("#phoneid").focus();
					return false;
					
				}else if( !RegexPhone.test($.trim($("#phoneid").val())) ){
					
					alert("아이디형식 오류");					
					$("#phoneid").val("");
					$("#phoneid").focus();
					return false;
					
				}else if ( $("#password").val() == "" ){	
					
					alert("비밀번호를 입력해주세요.");
					$("#password").focus();
					return false;
					
				}else if ( !RegexPassword.test($.trim($("#password").val())) ){
					
					alert("비밀번호형식 오류");
					$("#password").val("");
					$("#password").focus();
					return false;
					
				}else if ( $("#phoneid").val() == $("#password").val() ){
					
					alert("ID와 비밀번호가 동일합니다.");
					$("#password").val("");
					$("#password").focus();
					return false;					
				
				}else if ( $("#repassword").val() == ""){
					
					alert("비밀번호 확인하셔야합니다.");
					$("#repassword").focus();
					return false;
					
					
				}else if ( $("#username").val() == ""){
					
					alert("이름을 입력해주세요.");
					$("#username").focus();
					return false;
					
				}else if ( !RegexName.test($.trim($("#username").val())) ){
					
					alert("이름형식 오류");
					$("#username").focus();
					return false;
					
				}else if ( $("#nickname").val() == ""){
					
					alert("별명을 입력해주세요.");
					$("#nickname").focus();
					return false;

				}else if ( !RegexNick.test($.trim($("#nickname").val())) ){

					alert("별명형식 오류");
					$("#nickname").focus();
					return false;
				}else if ( $("#userssn").val() == ""){
					
					alert("생년월일을 입력해주세요.")
					$("#userssn").focus();
					return false;
					
				}else if ( !RegexBirth.test($.trim($("#userssn").val())) ){
					
					alert("생년월일형식 오류");					
					$("#userssn").val("");
					$("#userssn").focus();
					return false;	
					
				}else if ( $(":radio[name='gender']:checked").length < 1 ){
					
					alert("성별을을 입력해주세요");
					return false;	
				
					
				}else if ( $("#email").val() == ""){
					
					alert("Email을 입력해주세요.");
					$("#email").focus();
					return false;

				}else if ( !RegexEmail.test($.trim($("#email").val())) ){
					
					alert("email형식 오류");					
					$("#email").val("");
					$("#email").focus();
					return false;
					
				}else if ( $("#captchaAnswer").val() == "" ){
					
					alert("자동가입 인증 오류.");
					$('#reLoad').click();
					$("#captchaAnswer").focus();
					return false;
					
				}else if ( captchachk == false ){
					
					alert("자동가입 인증 오류.");
					$('#reLoad').click();
					$("#captchaAnswer").focus();
					return false;
				}
							
					this.action="/teamSquirrel/MemberAddServlet";
							 
		});
		
		$("#password").on("keyup",function(){
			if($("#password").val().length < 7 || $("#phoneid").val() == $("#password").val()){
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
		
		$("#phoneid").on("keyup",function(){
			$.ajax({
				type:"post",
				url:"/multiCheck",
				data: { phoneid : $("#phoneid").val()},				
				dataType:"text",
				success : function(data,status,xhr){	
					if(data == 0){
						if ( !RegexPhone.test($.trim($("#phoneid").val())) ){
							$("#idchk").text("사용 불가").css("color","red");
						}else{
							$("#idchk").text("사용 가능").css("color","green");
							idchk = true;
						}												
					}else{
						idchk = false;
						$("#idchk").text("사용 불가").css("color","red");
							$("form").on("submit",function(event){							
								if(!idchk){
									alert("아이디가 중복입니다.");
									event.preventDefault();
									$("#phoneid").val("");
									$("#phoneid").focus();
									$("#idchk").text("<---");
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

		$("#nickname").on("keyup",function(event){
			$.ajax({
				type:"post",
				url:"MultiCheckServlet",
				data: {nickname : $("#nickname").val()},
				dataType:"text",
				success : function(data,status,xhr){	
					if(data == 0){						
						$("#nickchk").text("사용 가능").css("color","green");
						nickchk = true;
					}else{
						nickchk = false;
						$("#nickchk").text("사용 불가").css("color","red");
							$("form").on("submit",function(event){							
								if(!nickchk){
									alert("별명이 중복입니다.");
									event.preventDefault();
									$("#nickname").val("");
									$("#nickname").focus();
									$("#nickchk").text("<--");
									
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
		
		$("#email").on("keyup",function(){
			$.ajax({
				type:"post",
				url:"MultiCheckServlet",
				data: { email : $("#email").val()},				
				dataType:"text",
				success : function(data,status,xhr){	
					if(data == 0){
						$("#emailchk").text("사용 가능").css("color","green");
						emailchk = true;
					}else{
						emailchk = false;
						$("#emailchk").text("사용 불가").css("color","red");
							$("form").on("submit",function(event){							
								if(!emailchk){
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
				
	});

</script>

<form method="post" class="form_main">
<input type="hidden" id="rating" name="rating" value="U">
<table>
<tr>
<th>아이디:</th>
<td><input class="inputmadd" type="text" id="phoneid" name="phoneid" maxlength="11" required></td>
<td class="confirm"><span id="idchk"></span></td>
</tr>
<tr>
<td></td><td class="text_left"><font class="red">핸드폰 번호를 입력하세요 ( - ) 없이</font></td><td></td>
</tr>
<tr>
<td colspan="3" class="m_space"></td>
</tr>
<tr>
<th>비밀번호:</th>
<td><input class="inputmadd" type="password" id="password" name="password" maxlength="20"></td>
<td class="confirm"><span id="confirmpw"></span></td>
</tr>
<tr>
<th>비밀번호확인:</th>
<td><input class="inputmadd" type="password" id="repassword" name="repassword" maxlength="20"></td>
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
<td><input class="inputmadd" type="text" id="username" name="username"></td>
</tr>
<tr>
<th>닉네임:</th>
<td><input class="inputmadd" type="text" id="nickname" name="nickname"></td>
<td class="confirm"><span id="nickchk"></span></td>
</tr>
<tr>
<td></td><td class="text_left"><font class="red">앱에서 사용하실 닉네임</font></td><td></td>
</tr>
<tr>
<td colspan="3" class="m_space"></td>
</tr>
<tr>
<th>생년월일:</th>
<td><input class="inputmadd" type="text" id="userssn" name="userssn" placeholder="예) 20190101"></td>
</tr>
<tr>
<th class="text_right">남<input type="radio" id="male" value="male" name="gender"></th>
<th class="text_left">여<input type="radio" id="female" value="female" name="gender"></th>
</tr>
<tr>
<th>e-mail:</th>
<td><input class="inputmadd" type="email" id="email" name="email" placeholder="인증시 필요"></td>
<td class="confirm"><span id="emailchk"></span></td>
</tr>
<tr>
<td colspan="2" class="m_space"></td>
</tr>
<tr>
  <td colspan="2" class="text_center"><div id="catpcha">Wait...</div></td>
</tr>
<tr>
  <td colspan="2" class="text_center"><div id="audiocatpch" style="display: none;"></div></td>
</tr>
<tr>
<td colspan="2" class="text_center">
  <input style="width:80px" id="reLoad" type="button" value="새로고침" />
  <input style="width:80px" id="soundOn" type="button" value="음성듣기" /></td>
</tr>
<tr>
<td colspan="2" class="text_center">
  <input style="width:110px" type="text" id="captchaAnswer" name="answer" value="" required/>
  <input style="width:50px" type="button" id="captchaSubmit" value="확인" /></td>
</tr>
<tr>
<td colspan="2" class="m_space"></td>
</tr>
<tr>
<td colspan="2" class="text_center"><input class="m_sub_re" type="submit" value="가입하기">&nbsp;&nbsp;<input class="m_sub_re"  type="reset" value="다시 작성"></td>
</tr>
</table>
</form>