<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/resources/js/board_list.js?ver.${System.currentTimeMillis()}"></script>
<div class="bodytext_area box_inner">
	<input type="hidden" id="savedSearchOpt" value="${searchOpt}">
	<input type="hidden" id="savedKeyword" value="${keyword}">
	<c:choose>
		<c:when test="${voList == null}">
			<h2>게시글이 없습니다.</h2>
			<c:if test="${sessionUser.name != null }">
				<div class="btn_line" style="text-align: left;">
					<button type="button" class="btn_bbs btn_radius5" onclick="location.href='/myboard/board/write.do'">글쓰기</button>
				</div>
			</c:if>
		</c:when>
		<c:otherwise>
			<div class="listViewOpt">
				<p class="postCnt">* ${pageInfo.totalPost}개의 게시물이 있습니다.</p>
<%--				<span>말머리∨</span>--%>
<%--				<span>목록10개∨</span>--%>
			</div>

			<table class="bbsListTbl">
				<caption class="hdd">게시판 목록</caption>
				<thead>
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">글쓴이</th>
						<th scope="col">조회수</th>
						<th scope="col">작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vo" items="${voList}">
						<tr>
							<td>${vo.seq}</td>
							<td class="tit_notice"><a class="underline" href="javascript:goView(${vo.seq})">${vo.title}</a></td>
							<td>${vo.writer}</td>
							<td>${vo.hit}</td>
							<td>
								<!-- 원하는 날짜형식으로 출력하기 위해서 fmt태그 사용 -->
								<fmt:formatDate value="${vo.regdate}" pattern="yy.MM.dd"/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination">
				<c:if test="${sessionUser.name != null }">
					<p class="btn_line">
						<button type="button" class="btn_bbs btn_radius5" onclick="location.href='/myboard/board/write.do'">글쓰기</button>
					</p>
				</c:if>
				<c:if test="${pageInfo.curPage - 1 >= 1}">
					<a href="javascript:goPage(${pageInfo.curPage - 1});" class="nextpage pbtn">
					<img src="${pageContext.request.contextPath}/resources/images/btn_prevpage.png" alt="다음 페이지로 이동"></a>
				</c:if>
				<c:if test="${pageInfo.startPage != 1}">
					<a href="javascript:goPage(1);"><span class="pagenum">1</span></a>
					<div class="pageDot"><span> ... </span></div>
				</c:if>
				<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.curPage-1}">
					<a href="javascript:goPage(${i});"><span class="pagenum">${i}</span></a>
				</c:forEach>
				<a href="javascript:;"><span class="pagenum currentpage">${pageInfo.curPage}</span></a>
				<c:forEach var="i" begin="${pageInfo.curPage+1}" end="${pageInfo.endPage}">
					<a href="javascript:goPage(${i});"><span class="pagenum">${i}</span></a>
				</c:forEach>
				<c:if test="${pageInfo.endPage < pageInfo.totalPage}">
					<div class="pageDot"><span> ... </span></div>
					<a href="javascript:goPage(${pageInfo.totalPage});"><span class="pagenum">${pageInfo.totalPage}</span></a>
				</c:if>
				<c:if test="${pageInfo.curPage + 1 <= pageInfo.totalPage}">
					<a href="javascript:goPage(${pageInfo.curPage + 1});" class="nextpage pbtn">
					<img src="${pageContext.request.contextPath}/resources/images/btn_nextpage.png" alt="다음 페이지로 이동"></a>
				</c:if>
			</div>
		</c:otherwise>
	</c:choose>
	<div class="minisrch_form">
		<select id="searchOpt" class="sel_srch">
			<option value="titAndContent">제목+내용</option>
			<option value="title">글제목</option>
			<option value="writer">글쓴이</option>
			<option value="content">내용</option>
		</select>
		<input type="text" id="keyword" class="tbox" placeholder="검색어를 입력하세요." />
		<a href="javascript:goSearch()" class="btn_srch btn_radius5">검색</a>
	</div>
</div>
