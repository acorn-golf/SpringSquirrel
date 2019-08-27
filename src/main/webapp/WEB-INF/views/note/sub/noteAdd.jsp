<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
</head>
<body>

<iframe name='action' width="0" height="0" frameborder="0" scrolling='yes'></iframe>

<% request.getParameter("USER_NO"); %>

<form name="addForm" method="post" target="action" action="/notice/notice_add_act.jsp">
<table>
 <tr>
  <td>
   <table>
    <tr>
     <td><h1>공지사항</h1></td>
   
    </tr>
   </table>
   <select name="공지">      

                    <option value=1 >공지</option>

                    <option value=2 >중요</option>

                    <option value=3 >이벤트</option>

                   </select>

   <table>
    <tr>
    <td>등급<input name="RATING" size="10" maxlength="10">
     아이디  <input name="USER_NO" size="10" maxlength="10">
    </td>
    <td>
      </tr>
    <tr>
    
     <td>제목<input name="NOTE_TITLE" size="50" maxlength="100"></td>
    </tr>
    <tr>
    
     <td><textarea name="NOTE_CONTENT" cols="50" rows="13"></textarea></td>
    </tr>
   </table>

   <table>
    <tr>
     <td><button>목록</button></td>
     <td><button>수정하기</button></a></td>
     <td><button>저장</button></td>
    </tr>
   
   </table>

  </td>
 </tr>
</table>
</form>


</body>
</html>