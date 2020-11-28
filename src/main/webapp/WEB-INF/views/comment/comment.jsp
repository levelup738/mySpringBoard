<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/comment.js?ver.${System.currentTimeMillis()}"></script>
    <ul class="bbs_comment_list">
        <div class="div_comment_list">
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
    const boardseq = '${boardVO.seq}';
</script>