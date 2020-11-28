<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/resources/js/board_view.js?ver.${System.currentTimeMillis()}"></script>
<div class="bodytext_area box_inner">
	<input type="hidden" id="b_seq" value="${boardVO.seq}"/>
	<ul class="bbsview_list">
		<li class="bbs_title">${boardVO.title}</li>
		<li class="bbs_username">작성자 : <span>${boardVO.writer}</span></li>
		<li class="bbs_date">작성일 : <span>
			<fmt:formatDate value="${boardVO.regdate}" pattern="yy.MM.dd"/>
		</span></li>
		<li class="bbs_hit">조회수 : <span>${boardVO.hit}</span></li>
		<li class="bbs_content">
			<div class="editer_content">
				${boardVO.content}
			</div>
		</li>
	</ul>
<%@include file="../comment/comment.jsp"%>
	<p class="btn_line txt_right">
		<button type="button" class="btn_bbs btn_radius5" onclick="location.href='/myboard/board/list.do'">목록</button>
		<c:if test="${boardVO.writer == sessionUser.name}">
			<button type="button" class="btn_bbs btn_radius5"
					onclick="location.href='/myboard/board/move_modify.do?seq=${boardVO.seq}'">수정</button>
			<button type="button" class="btn_bbs btn_radius5" 
			onclick="onDelete()" style="background-color: #FE2E2E;">삭제</button>
		</c:if>
	</p>
	<ul class="near_list mt20">
		<c:if test="${not empty nextSeq}">
			<li><h4 class="next">다음글</h4> <a href="javascript:goView(${nextSeq})">${nextTitle}</a></li>
		</c:if>
		<c:if test="${not empty prevSeq}">
			<li><h4 class="prev">이전글</h4> <a href="javascript:goView(${prevSeq})">${prevTitle}</a></li>
		</c:if>
	</ul>
</div>