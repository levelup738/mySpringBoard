<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath }/resources/js/board_view.js?ver.${System.currentTimeMillis()}"></script>
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
	<ul class="bbs_comment_list">
		<c:if test="${commentVOs != null}">
			<c:forEach var="vo" items="${commentVOs}">
				<li class="comment_box" id="comment-${vo.seq}">
					<div class="profile_info">
						<span id="writer">${vo.writer}</span><span>
						<fmt:formatDate value="${vo.regdate}" pattern="yy.MM.dd HH:mm"/></span>
						<c:if test="${sessionUser.name == vo.writer}">
							<button type="button" class="btn_st1 btn_radius5 comment_del" style="background-color: #ff7373;">삭제</button>
							<button type="button" class="btn_st1 btn_radius5 comment_modi">수정</button>
						</c:if>
					</div>
					<div class="comment_text">
						<p>${vo.content}</p>
					</div>
				</li>
			</c:forEach>
			<li>
				<div class="pagination">
					<c:if test="${pageInfo.curPage - 1 >= 1}">
						<a href="javascript:goPage(${boardVO.seq}, ${pageInfo.curPage - 1});" class="nextpage pbtn"> <img src="/myboard/resources/images/btn_prevpage.png" alt="다음 페이지로 이동"></a>
					</c:if>
					<c:if test="${pageInfo.startPage != 1}">
						<a href="javascript:goPage(${boardVO.seq}, 1);"><span class="pagenum">1</span></a>
						<div class="pageDot">
							<span> ... </span>
						</div>
					</c:if>
					<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.curPage-1}">
						<a href="javascript:goPage(${boardVO.seq}, ${i});"><span class="pagenum">${i}</span></a>
					</c:forEach>
					<a href="javascript:;"><span class="pagenum currentpage">${pageInfo.curPage}</span></a>
					<c:forEach var="i" begin="${pageInfo.curPage+1}" end="${pageInfo.endPage}">
						<a href="javascript:goPage(${boardVO.seq}, ${i});"><span class="pagenum">${i}</span></a>
					</c:forEach>
					<c:if test="${pageInfo.endPage < pageInfo.totalPage}">
						<div class="pageDot">
							<span> ... </span>
						</div>
						<a href="javascript:goPage(${boardVO.seq}, ${pageInfo.totalPage});"><span class="pagenum">${pageInfo.totalPage}</span></a>
					</c:if>
					<c:if test="${pageInfo.curPage + 1 <= pageInfo.totalPage}">
						<a href="javascript:goPage(${boardVO.seq}, ${pageInfo.curPage + 1});" class="nextpage pbtn"> <img src="/myboard/resources/images/btn_nextpage.png" alt="다음 페이지로 이동"></a>
					</c:if>
				</div>
			</li>
		</c:if>
		<li class="bbs_comment">
			<%--@elvariable id="commentVO" type="com.myway.myboard.model.CommentVO"--%>
			<form:form class="form_comment" action="/myboard/comment/write.do" method="post" modelAttribute="commentVO">
				<fieldset>
					<legend>댓글 입력 양식</legend>
					<form:hidden path="writer" value="${sessionUser.name}"/>
					<form:hidden path="boardseq" value="${boardVO.seq}"/>
					<div class="commnet_area">
						<form:textarea path="content" placeholder="댓글을 적을수 있는 창입니다."/>
						<p class="btn_line txt_right">
							<button type="button" class="btn_st1 btn_radius5" style="background-color: #ff7373;" onclick="commentSubmit();">등록</button>
						</p>
					</div>
				</fieldset>
			</form:form>
		</li>
	</ul>
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
		<c:if test="${pageMax >= boardVO.seq + 1}">
			<li><h4 class="next">다음글</h4> <a href="javascript:goView(${boardVO.seq + 1})">${nextTitle}</a></li>
		</c:if>
		<c:if test="${pageMin <= boardVO.seq - 1}">
			<li><h4 class="prev">이전글</h4> <a href="javascript:goView(${boardVO.seq - 1})">${prevTitle}</a></li>
		</c:if>
	</ul>
</div>
<form:form class="form_update" action="/myboard/comment/update.do"
	method="post" modelAttribute="commentVO">
	<div class="commnet_area">
		<form:hidden path="seq"/>
		<form:hidden path="writer" />
		<form:hidden path="boardseq" value="${boardVO.seq}"/>
		<form:textarea path="content" placeholder="댓글을 적을수 있는 창입니다." />
		<p class="btn_line txt_right">
			<button type="button" class="btn_st1 btn_radius5"
				style="background-color: #ff7373;" onclick="commentUpdate()">등록</button>
		</p>
	</div>
</form:form>