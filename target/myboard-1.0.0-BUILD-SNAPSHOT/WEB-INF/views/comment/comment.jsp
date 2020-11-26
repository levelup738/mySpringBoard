<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/comment.js?ver.${System.currentTimeMillis()}"></script>
    <ul class="bbs_comment_list">
        <div class="div_comment_list">
<%--        <c:if test="${commentVOs != null}">--%>
<%--            <li>--%>
<%--                <div class="pagination">--%>
<%--                    <c:if test="${pageInfo.curPage - 1 >= 1}">--%>
<%--                        <a href="javascript:goPage(${boardVO.seq}, ${pageInfo.curPage - 1});" class="nextpage pbtn"> <img--%>
<%--                                src="/myboard/resources/images/btn_prevpage.png" alt="다음 페이지로 이동"></a>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${pageInfo.startPage != 1}">--%>
<%--                        <a href="javascript:goPage(${boardVO.seq}, 1);"><span class="pagenum">1</span></a>--%>
<%--                        <div class="pageDot">--%>
<%--                            <span> ... </span>--%>
<%--                        </div>--%>
<%--                    </c:if>--%>
<%--                    <c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.curPage-1}">--%>
<%--                        <a href="javascript:goPage(${boardVO.seq}, ${i});"><span class="pagenum">${i}</span></a>--%>
<%--                    </c:forEach>--%>
<%--                    <a href="javascript:;"><span class="pagenum currentpage">${pageInfo.curPage}</span></a>--%>
<%--                    <c:forEach var="i" begin="${pageInfo.curPage+1}" end="${pageInfo.endPage}">--%>
<%--                        <a href="javascript:goPage(${boardVO.seq}, ${i});"><span class="pagenum">${i}</span></a>--%>
<%--                    </c:forEach>--%>
<%--                    <c:if test="${pageInfo.endPage < pageInfo.totalPage}">--%>
<%--                        <div class="pageDot">--%>
<%--                            <span> ... </span>--%>
<%--                        </div>--%>
<%--                        <a href="javascript:goPage(${boardVO.seq}, ${pageInfo.totalPage});"><span--%>
<%--                                class="pagenum">${pageInfo.totalPage}</span></a>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${pageInfo.curPage + 1 <= pageInfo.totalPage}">--%>
<%--                        <a href="javascript:goPage(${boardVO.seq}, ${pageInfo.curPage + 1});" class="nextpage pbtn"> <img--%>
<%--                                src="/myboard/resources/images/btn_nextpage.png" alt="다음 페이지로 이동"></a>--%>
<%--                    </c:if>--%>
<%--                </div>--%>
<%--            </li>--%>
<%--        </c:if>--%>
        </div>
        <li class="bbs_comment">
            <%--@elvariable id="commentVO" type="com.myway.myboard.model.CommentVO"--%>
            <form:form class="form_comment" id="form_comment" action="/myboard/comment/write.do" method="post" modelAttribute="commentVO">
                <fieldset>
                    <legend>댓글 입력 양식</legend>
                    <form:hidden path="writer" value="${sessionUser.name}"/>
                    <form:hidden path="boardseq" value="${boardVO.seq}"/>
                    <div class="commnet_area">
                        <form:textarea path="content" placeholder="댓글을 적을수 있는 창입니다."/>
                        <p class="btn_line txt_right">
                            <button type="button" class="btn_st1 btn_radius5" style="background-color: #ff7373;"
                                    onclick="commentSubmit();">등록
                            </button>
                        </p>
                    </div>
                </fieldset>
            </form:form>
        </li>
    </ul>

<script>
    const sessionUserName = '${sessionUser.name}';
</script>