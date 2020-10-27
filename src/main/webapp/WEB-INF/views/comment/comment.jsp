<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    <ul class="bbs_comment_list">
        <c:if test="${commentVOs != null}">
            <c:forEach var="vo" items="${commentVOs}">
                <li class="comment_box" id="comment-${vo.seq}">
                    <div class="profile_info">
                        <span id="writer">${vo.writer}</span><span>
                            <fmt:formatDate value="${vo.regdate}" pattern="yy.MM.dd HH:mm"/></span>
                        <c:if test="${sessionUser.name == vo.writer}">
                            <button type="button" class="btn_st1 btn_radius5 comment_del"
                                    style="background-color: #ff7373;">삭제
                            </button>
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
                        <a href="javascript:goPage(${boardVO.seq}, ${pageInfo.curPage - 1});" class="nextpage pbtn"> <img
                                src="/myboard/resources/images/btn_prevpage.png" alt="다음 페이지로 이동"></a>
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
                        <a href="javascript:goPage(${boardVO.seq}, ${pageInfo.totalPage});"><span
                                class="pagenum">${pageInfo.totalPage}</span></a>
                    </c:if>
                    <c:if test="${pageInfo.curPage + 1 <= pageInfo.totalPage}">
                        <a href="javascript:goPage(${boardVO.seq}, ${pageInfo.curPage + 1});" class="nextpage pbtn"> <img
                                src="/myboard/resources/images/btn_nextpage.png" alt="다음 페이지로 이동"></a>
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
        window.onload = function () {
            getCommentList();
        }

        const getCommentList = function () {
            const xhr = new XMLHttpRequest();
            xhr.open("GET", "${pageContext.request.contextPath}/comment/list.do?b_seq="+${boardVO.seq}, true);
            xhr.send();
            xhr.onreadystatechange = function () {
                if (xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {

                }
            }
        }
    </script>