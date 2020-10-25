<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath }/resources/js/board_write.js?ver.${System.currentTimeMillis()}"></script>
<c:if test="${empty boardVO.seq }">
	<c:set var="action" value="/myboard/board/create.do"/>
	<c:set var="title" value="게시글 작성"/>
</c:if>
<c:if test="${not empty boardVO.seq }">
	<c:set var="action" value="/myboard/board/modify.do"/>
	<c:set var="title" value="수정"/>
</c:if>
 
<form:form class="app_form" action="${action}" method="post" commandName="boardVO">
	<fieldset>
	<legend>글쓰기 입력 양식</legend>
		<p class="page_tit">${title}</p>
		<c:if test="${not empty boardVO.seq}">
			<form:hidden path="seq" value="${boardVO.seq}"/>
		</c:if>
		<form:hidden path="writer" value="${sessionUser.name}"/>
		<form:hidden path="hit" value="0"/>
		<ul class="write_container">
			<li class="clear">
				<div class="post_name">
					<form:input path="title" placeholder="제목을 입력하세요."/>
				</div>
	
			</li>
			<li class="clear">
				<div class="post_content">
					<form:textarea path="content" placeholder="게시글을 입력하세요."/>
				</div>
			</li>
		</ul>
	</fieldset>
		<p class="btn_write_line">
			<button type="button" class="btn_radius5" onclick="posting()">등록</button>
			<button type="button" class="btn_exit btn_radius5" onclick="location.href='/myboard/board/list.do'">나가기</button>
		</p>
</form:form>