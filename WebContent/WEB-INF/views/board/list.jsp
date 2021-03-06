<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board" method="post">
					<!-- <input type="hidden" name="a" value="list" /> -->
					<input type="text" id="kwd" name="kwd" value="" />
					<input type="submit" value="찾기" />
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					
					<c:forEach items="${ list }" var="result" varStatus="status">
					<tr>
						<td>${ pager.startPostNumber - status.index }</td>
						<td style="text-align:left; padding-left: ${ result.vo.depth * 20 }px">
							<c:if test='${ result.vo.depth gt 0 }'>
								<img src="/mysite/assets/images/reply.png" />
							</c:if>
							<a href="/mysite/board?a=view&kwd=${ param.kwd }&no=${ result.vo.no }&p=${ pager.currentPageNumber }">${ result.vo.title }</a>
						</td>
						<td>${ result.userName }</td>
						<td>${ result.vo.hitCount }</td>
						<td>${ result.vo.regDate }</td>
						<td>
						<c:if test="${ not empty authUser && result.vo.userNo eq authUser.no }">
							<a href="/mysite/board?a=delete&no=${ result.vo.no }" class="del">삭제</a>
						</c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<c:if test='${ pager.leftNavigator }'>
						<li><a href="/mysite/board?kwd=${ param.kwd }&p=${ pager.startPageNumber -1 } ">◀</a></li>
						</c:if>
						<c:forEach begin='0' end='${ pager.pageCount -1 }' var='i' step='1'>
						<c:choose>
							<c:when test='${ (pager.startPageNumber + i) gt pager.totalPageCount }'>
								<li>${ pager.startPageNumber + i }</li>
							</c:when>
							<c:when test='${ (pager.startPageNumber + i) eq pager.currentPageNumber }'>
								<li class="selected"><a href="/mysite/board?kwd=${ param.kwd }&p=${ pager.startPageNumber + i }">${ pager.startPageNumber + i }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/mysite/board?kwd=${ param.kwd }&p=${ pager.startPageNumber + i }">${ pager.startPageNumber + i }</a></li>
							</c:otherwise>
						</c:choose>
						</c:forEach>
						<c:if test='${ pager.rightNavigator }'>
						<li><a href="/mysite/board?kwd=${ param.kwd }&p=${ pager.endPageNumber +1 } ">▶</a></li>
						</c:if>
					</ul>
				</div>
				<c:if test="${ not empty sessionScope.authUser }">
				<div class="bottom">
					<a href="/mysite/board?a=writeform&kwd=${ param.kwd }&p=${ pager.currentPageNumber }" id="new-book">글쓰기</a>
				</div>
				</c:if>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" >
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>