<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="/mysite/board">
					<input type="hidden" name="kwd" value="${ param.kwd }" />
					<input type="hidden" name="p" value="${ param.p }" />
					<c:choose>
						<c:when test='${ action eq "replyform" }'>
							<input type="hidden" name="a" value="reply" />
							<input type="hidden" name="group-no" value="${ result.groupNo }" />
							<input type="hidden" name="order-no" value="${ result.orderNo }" />
							<input type="hidden" name="depth" value="${ result.depth }" />
						</c:when>
						<c:otherwise>
							<input type="hidden" name="a" value="write" />
						</c:otherwise>
					</c:choose>

					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td><textarea id="content" name="content"></textarea></td>
						</tr>
					</table>
					<div class="bottom">
						<c:choose>
							<c:when test='${ action eq "replyform" }'>
								<a href="/mysite/board?a=view&kwd=${ param.kwd }&no=${ param.no }&p=${ param.p }">취소</a>
							</c:when>
							<c:otherwise>
								<a href="/mysite/board?kwd=${ param.kwd }&p=${ param.p }">취소</a>
							</c:otherwise>
						</c:choose>

						<input type="submit" value="등록">
					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>