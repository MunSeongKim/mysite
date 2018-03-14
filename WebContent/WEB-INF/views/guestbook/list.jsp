<%@page import="com.cafe24.mysite.vo.GuestbookVO"%>
<%@page import="java.util.List"%>
<%@page import="com.cafe24.mysite.dao.GuestbookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<GuestbookVO> list = (List<GuestbookVO>)request.getAttribute( "list" );
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		
		<div id="content">
			<div id="guestbook">
				<form action="/mysite/guestbook" method="post">
					<input type="hidden" name="a" value="add" />
					<table border="1" width="510">
						<tr>
							<td>이름</td><td><input type="text" name="name"></td>
							<td>비밀번호</td><td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" cols=66 rows=5></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<br>
				<%
					int totalCount = list.size();
					int index = 0;
					if (list != null ) {
				    	for ( GuestbookVO vo : list ) { 
				%>
					<table width="510" border="1">
						<tr>
							<td>[<%= totalCount - index %>]</td>
							<td><%= vo.getName() %></td>
							<td><%= vo.getRegDate() %></td>
							<td><a href="/mysite/guestbook?a=deleteform&no=<%= vo.getNo() %>">삭제</a></td>
						</tr>
						<tr>
							<td colspan="4">
							<%= vo.getContent().replace("\n", "<br />") %>
							</td>
						</tr>
					</table>
					<br />
				<%
							index++;
						}
					}
				%>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>