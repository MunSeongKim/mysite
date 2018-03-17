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
					<c:choose>
						<c:when test='${ action eq "write" }' >
							<input type="hidden" name="a" value="write" />
						</c:when>
						<c:when test='${ action eq "modify" }' >
							<input type="hidden" name="a" value="modify" />
							<input type="hidden" name="no" value="${ result.vo.no }" />
						</c:when>
						<c:when test='${ action eq "reply" }' >
							<input type="hidden" name="a" value="reply" />
							<input type="hidden" name="group-no" value="${ result.vo.groupNo }" />
							<input type="hidden" name="order-no" value="${ result.vo.orderNo }" />
							<input type="hidden" name="depth" value="${ result.vo.depth }" />
						</c:when>
					</c:choose>
					
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글쓰기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td>
								<c:choose>
									<c:when test='${ action ne "modify" }'>
										<input type="text" name="title" value="">
									</c:when>
									<c:when test='${ action eq "modify" }'>
										<input type="text" name="title" value="${ result.vo.title }">
									</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<c:choose>
									<c:when test='${ action ne "modify" }'>
										<textarea id="content" name="content"></textarea>
									</c:when>
									<c:when test='${ action eq "modify" }'>
										<textarea id="content" name="content">${ result.vo.content }</textarea>
									</c:when>
								</c:choose>
								
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="/mysite/board">취소</a>
						<c:choose>
							<c:when test='${ action ne "modify" }' >
								<input type="submit" value="등록">
							</c:when>
							<c:when test='${ action eq "modify" }' >
								<input type="submit" value="수정">
							</c:when>
						</c:choose>
						
					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" >
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
		</div>
	</div>
</body>
</html>