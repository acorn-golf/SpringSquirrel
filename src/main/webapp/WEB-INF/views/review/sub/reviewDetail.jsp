<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${message != null}">
	<script>
		alert('${message}');
	</script>
</c:if>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		updateEvent();
		deleteEvent();

		$("#updateReviewDeatil").on("click", function() {
			$("form").attr({
				"action" : "updateReviewform",
				"method" : "post"
			});
		});
		$("#deleteReviewDeatil").on("click", function() {
			$("form").attr({
				"action" : "deleteReviewDeatil",
				"method" : "post"
			});
		});
		$("#ReviewList").on("click", function() {
			location.href = "reviewList";
		});

		$("#insert").on("click", function() {
			if ($("#re_content").val() == "") {
				alert('댓글을 입력하세요');
			} else {
				$.ajax({
					type : "post",
					url : "insertComment",
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
				/* location.href = "reviewDetail?score_no=" + $
				{
					reviewdetail.score_no
				}
				+"&user_no=" + $
				{
					reviewdetail.user_no
				}
				; */
				window.location.reload();
			}
		});

	});

	var tmp = new Array();
	function updateEvent() {
		$(".update").on("click",function(event) {
			var re_no = $(this).parent().children("input[type='hidden']").val();
			tmp[tmp.length] = {"re_no" : re_no, "text" : $("#content" + re_no).text()};
			$("#content" + re_no).html("<input type='text' name='content" + re_no+ "' value='"+ $("#content" + re_no).text()+ "'>");
			//$(this).html('<input type="submit" class="update" value="등록">');
			$("#upordel" + re_no).html("<input type='button' class='update' id='update"+re_no+"' value='등록'> <input type='button' id='cancel"+re_no+"' value='취소'><input type='hidden' name='re_no' value='"+re_no+"'>");
			$("#update" + re_no).on("click",function() {
				console.log($("input[name='content"+ re_no + "']").val());
				$.ajax({
					type : "post",
					url : "updateComment",
					data : {
						re_no : $(this).parent().children("input[type='hidden']").val(),
						re_content : $("input[name='content"+ re_no+ "']").val()
					},
					dataType : "text",
					success : function(data,status, xhr) {
						alert(data);
					},
					error : function(xhr,status,error) {
						console.log(error);
						console.log(status);
					}
				});
				//location.href = "reviewDetail?score_no="+ ${reviewdetail.score_no}+"&user_no=" + ${reviewdetail.user_no};
				window.location.reload();
			});
			$("#cancel" + re_no).on("click",function() {
				var re_no = $(this).parent().children("input[type='hidden']").val();
				tmp.forEach(function(ele) {
					if (ele.re_no == re_no) {
						$("#content"+ re_no).html(ele.text);
					}
				});
				//$("#content"+re_no).html($("input[name='content"+re_no+"']").val());
				$("#upordel" + re_no).html("<input type='button' class='update' value='수정'> <input type='button' class='delete' value='삭제'><input type='hidden' name='hidden_re_no' value='"+re_no+"' id='re_no'>");
				updateEvent();
				deleteEvent();
			});
		});
	}

	function deleteEvent() {
		$(".delete").on("click",function() {
			var re_no = $(this).parent().children("input[type='hidden']").val();
			console.log(re_no);
			$.ajax({
				type : "post",
				url : "deleteComment",
				data : {
					re_no : $(this).parent().children("input[type='hidden']").val()
				},
				dataType : "text",
				success : function(data, status, xhr) {
					alert(data);
				},
				error : function(xhr, status, error) {
					console.log(error);
					console.log(status);
				}
			});
			/* location.href = "reviewDetail?score_no=" + $
			{
				reviewdetail.score_no
			}
			+"&user_no=" + $
			{
				reviewdetail.user_no
			}; */
			window.location.reload();
		});
	}
</script>

<%-- ${user_no eq sessionScope.login.user_no} --%>
<form class="form_main">
	<input type="hidden" name="score_no" value="${reviewdetail.score_no}"
		id="score_no"> <input type="hidden" name="login_user_no"
		value="${login.user_no}" id="login_user_no"> <input
		type="hidden" name="review_user_no" value="${reviewdetail.user_no}"
		id="review_user_no">
	<table class="line_table">
		<tr>
			<th class="line_th">작성자</th>
			<td class="line_td">${nickname}</td>
			<th class="line_th">평점</th>
			<td class="line_td">${reviewdetail.score}</td>
		</tr>
		<tr>
			<th class="line_th">작성일</th>
			<td class="line_td">${reviewdetail.score_date}</td>
			<th class="line_th">조회수</th>
			<td class="line_td">${reviewdetail.rv_vcount}</td>
		</tr>
		<tr>
			<th class="line_th">제목</th>
			<td colspan="3" class="line_td">${reviewdetail.rv_title}</td>
		</tr>
		<tr>
			<th class="line_th">내용</th>
			<td colspan="3" class="line_td">${reviewdetail.rv_content}</td>
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
	
	
</form>
<div class="form_main">
<!-- <form action="#" method="post" name="dmlForm"> -->
	<table id="recommentTable">
	<!-- 댓글등록 -->
		<tr>
			<td class="line_td" style="background-color: #CCFFCC" align="right">댓글</td>
			<td class="line_td" style="background-color: #CCFFCC"><textarea name="re_content" cols="30"
					rows="1" style="height: 30px" id="re_content"></textarea></td>
			<td align="left"><button id="insert">등록</button></td>
		</tr>
		<!-- 댓글보기 -->
		<c:forEach var="rlist" items="${recommentList}">

			<tr>
				<td class="line_td" style="background-color: #CCFFCC"><font>작성자 : <b>${rlist.nickname}</b></font><br>
					<font size="2" color="#4374D9">작성일 : ${rlist.re_date}</font></td>
				<td class="line_td" style="background-color: #CCFFCC"><div class="user_content"
						id="content${rlist.re_no}">${rlist.re_content}</div></td>
				<c:if
					test="${rlist.user_no == login.user_no || login.rating eq 'A'}">

					<td><div class="upordel"
							id="upordel${rlist.re_no}">
							<input type="button" class="update" value="수정">
							<input type="button" class="delete" value="삭제">
							<input type="hidden" name="hidden_re_no" value="${rlist.re_no}"
								id="re_no">
						</div></td>

				</c:if>
			</tr>

		</c:forEach>

	</table>
<!-- </form> -->
</div>