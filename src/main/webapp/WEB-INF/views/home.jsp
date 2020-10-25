<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<script>
	window.onload = function (){
		const emailcheck = '${param.emailcheck}';
		if(emailcheck == 'no'){
			alert('이메일 인증을 완료해주세요.');
		}
	}
</script>
<h1>
	<c:choose>
		<c:when test="${not empty param.joinConfirmMsg}">
			${param.joinConfirmMsg}
		</c:when>
		<c:when test="${not empty param.unsubscribeMsg}">
			${param.unsubscribeMsg}
		</c:when>
		<c:otherwise>
			Spring 게시판 만들기 페이지입니다.
		</c:otherwise>
	</c:choose>
</h1>
