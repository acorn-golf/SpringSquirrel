<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="/teamSquirrel/jquery-3.4.1.js"></script>
<script>



	$(document).ready(function() {
		
		updateEvent();
		deleteEvent();
		
		$("#updateReviewDeatil").on("click", function() {
			$("form").attr({
				"action" : "UpdateReviewformServlet",
				"method" : "post"
			});
		});
		$("#deleteReviewDeatil").on("click", function() {
			$("form").attr({
				"action" : "DeleteReviewDeatilServlet",
				"method" : "post"
			});
		});
		$("#ReviewList").on("click", function() {
			location.href = "ReviewListServlet";
		});

		$("#insert").on("click", function() {
			$.ajax({
				type : "post",
				url : "InsertCommentServlet",
				data : {
					score_no : $("#score_no").val(),
					user_no : $("#login_user_no").val(),
					re_content : $("#re_content").val()
				},
				dataType : "text",
				success : function(data, status, xhr) {
					alert(data);
					$("#re_content").val("");
				},
				error : function(xhr, status, error) {
					console.log(error);
					console.log(status);
				}
			});
			location.href = "ReviewDetailServlet?score_no=" + $
			{
				reviewdetail.score_no
			}
			+"&user_no=" + $
			{
				reviewdetail.user_no
			}
			;
		});



		
	});
	
	var tmp = new Array();
	function updateEvent() {
		$(".update").on("click", function(event) {
			var re_no = $(this).parent().children("input[type='hidden']").val();
			tmp[tmp.length] = {"re_no":re_no , "text":$("#content"+re_no).text()};
			$("#content"+re_no).html("<input type='text' name='content"+re_no+"' value='"+$("#content"+re_no).text()+"'>");
			//$(this).html('<input type="submit" class="update" value="등록">');
			$("#upordel"+re_no).html("<input type='button' class='update' id='update"+re_no+"' value='등록'> <input type='button' id='cancel"+re_no+"' value='취소'><input type='hidden' name='re_no' value='"+re_no+"'>");
			
			
			$("#update"+re_no).on("click",function(){
				console.log($("input[name='content"+re_no+"']").val());
				$.ajax({
					type:"post",
					url:"UpdateCommentServlet",
					data:{
						re_no : $(this).parent().children("input[type='hidden']").val(),
						re_content : $("input[name='content"+re_no+"']").val()
					},
					dataType:"text",
					success:function(data,status,xhr){
						alert(data);
					},
					error:function(xhr,status,error){
						console.log(error);
						console.log(status);
					}
				});
				location.href="ReviewDetailServlet?score_no="+${reviewdetail.score_no}+"&user_no="+${reviewdetail.user_no};
			});
			$("#cancel"+re_no).on("click",function(){
				var re_no = $(this).parent().children("input[type='hidden']").val();
				tmp.forEach(function(ele){
					if(ele.re_no == re_no){
						$("#content"+re_no).html(ele.text);
					}
				});
				//$("#content"+re_no).html($("input[name='content"+re_no+"']").val());
				$("#upordel"+re_no).html("<input type='button' class='update' value='수정'> <input type='button' class='delete' value='삭제'><input type='hidden' name='hidden_re_no' value='"+re_no+"' id='re_no'>");
				updateEvent();
				deleteEvent();
			});
		});
	}
	
	
	function deleteEvent() {
		 $(".delete").on("click",function(){
			 var re_no = $(this).parent().children("input[type='hidden']").val();
			 console.log(re_no);
			 $.ajax({
					type:"post",
					url:"DeleteCommentServlet",
					data:{
						re_no : $(this).parent().children("input[type='hidden']").val()
					},
					dataType:"text",
					success:function(data,status,xhr){
						alert(data);
					},
					error:function(xhr,status,error){
						console.log(error);
						console.log(status);
					}
				});
				location.href="ReviewDetailServlet?score_no="+${reviewdetail.score_no}+"&user_no="+${reviewdetail.user_no};
		 });
	}
</script>

<%-- ${user_no eq sessionScope.login.user_no} --%>
<form>
	<input type="hidden" name="score_no" value="${reviewdetail.score_no}"
		id="score_no"> <input type="hidden" name="login_user_no"
		value="${login.user_no}" id="login_user_no"> <input
		type="hidden" name="review_user_no" value="${reviewdetail.user_no}"
		id="review_user_no">
	<table border="1">
		<tr>
			<th>작성자</th>
			<td>${nickname}</td>
			<th>평점</th>
			<td>${reviewdetail.score}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${reviewdetail.score_date}</td>
			<th>조회수</th>
			<td>${reviewdetail.rv_vcount}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">${reviewdetail.rv_title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3">${reviewdetail.rv_content}</td>
		</tr>
	</table>
	<c:choose>
		<c:when
			test="${reviewdetail.user_no eq login.user_no || login.rating eq 'A'}">
			<%-- 3이아니라 로그인 세션의 유저pk로 비교해야함, 세션의 등급확인(관리자)  --%>
			<button id="updateReviewDeatil">수정</button>&nbsp;<button
				id="deleteReviewDeatil">삭제</button>
		</c:when>
	</c:choose>
	<input type="button" value="목록" id="ReviewList"> <br> <br>
	<!-- 댓글등록 -->
	<table>
		<tr>
			<td>댓글<textarea name="re_content" cols="30" rows="2"
					id="re_content"></textarea>
				<button id="insert">등록</button></td>
		</tr>
	</table>
</form>
<!-- 댓글보기 -->
<form action="#" method="post" name="dmlForm">
<table id="recommentTable">

	<c:forEach var="rlist" items="${recommentList}">
		
		<tr>
			<td>${rlist.re_date}</td>
			<td><div class="user_content" id="content${rlist.re_no}">${rlist.re_content}</div></td>
			<td>${rlist.nickname}</td>
			<c:if test="${rlist.user_no == login.user_no || login.rating eq 'A'}">

				<td><div class="upordel" id="upordel${rlist.re_no}">
						<input type="button" class="update" value="수정">
						<input type="button" class="delete" value="삭제">
						<input type="hidden" name="hidden_re_no" value="${rlist.re_no}"
							id="re_no">
					</div></td>

			</c:if>
		</tr>
		
	</c:forEach>

</table>
</form>