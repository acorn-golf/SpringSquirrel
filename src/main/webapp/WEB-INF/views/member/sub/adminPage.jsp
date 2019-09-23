<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#adminPage").on("click", function() {
			this.action = "/golfhi/adminPage";
		});

	});
</script>
<form id="adminPage" method="post">
	<div style="display: flex; margin-left: 400px; margin-top: 30px">
		<div>
			<select style="height: 31px;" id="adminSelect"
				name="adminSelect">
					<option id="member" value="member">회원</option>
					<option id="product" value="product">상품</option>
					<option id="ccname" value="golfcc">골프장</option>
					<option id="notice" value="notice">공지사항</option>
					<option id="order" value="order">주문</option>
			</select>
		</div>
		<div style="width: 280px;">
			<input type="text" id="adminSearch" name="adminSearch"
				placeholder="검색조건이 없으면 카테고리 전체">
		</div>
		<div>
			<input style="height: 31px;" type="submit" id="adminSubmit" value="검색" >
		</div>
	</div>
</form>
<!-- <form id="adminPage" method="post">
<table border="1" class="form_main">
<tr>
<th>
<select style="size: 40px;" id="adminSelect" name="adminSelect">
<option id="member" value="member">회원</option>
<option id="product" value="product">상품</option>
<option id="ccname" value="golfcc">골프장</option>
<option id="notice" value="notice">공지사항</option>
<option id="order" value="order">주문목록</option>
</select></th>
<td style="width: 280px;"><input type="text" id="adminSearch" name="adminSearch" placeholder="검색조건이 없으면 카테고리 전체"></td>
<td><input type="submit" id="adminSubmit" value="검색"></td>
</tr>
</table>
</form> -->