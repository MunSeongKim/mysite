<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action="/mysite/comment" method="post">
	<input type="hidden" name="a" value="write" />
	<input type="hidden" name="bno" value="${ board.no }" />
	<input type="hidden" name="p" value="${ param.p }" />
	
	<table class="tbl-ex">
		<tr>
			<th colspan="4" style='text-align: left'>댓글</th>
		</tr>
		<c:if test="${ not empty authUser }">
		<tr>
			<td class="label">내용</td>
			<td colspan='2'><input type="text" name="content" style="width:100%" maxlength="99" placeholder="최대 100자 입니다." /></td>
			<td align='right'><input type="submit" VALUE=" 쓰기 "></td>
		</tr>
		</c:if>
		<c:forEach items='${ commentList }' var="comment" varStatus="status" >
		<tr align='right'>
			<td>${ comment.userName }</td>
			<td><p style="width:100%">${ comment.vo.content }</p></td>
			<td align='right'>
				${ comment.vo.regDate }
			</td>
			<td align='right'>
				<c:if test='${ not empty authUser && comment.vo.userNo eq authUser.no }'>
				<a href="/mysite/comment?a=delete&no=${ comment.vo.no }&bno=${ board.no }&p=${ param.p }" class="del" style="text-align:right">삭제</a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>
</form>