// window.onload = function () {
//     getCommentList();
// }

// const getCommentList = function () {
//     const boardSeq = document.getElementById("boardseq").value;
//     const xhr = new XMLHttpRequest();
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState == XMLHttpRequest.DONE) {
//             if(xhr.status === 200 || xhr.status === 201) {
//                 const log = JSON.parse();
//                 console.log(log);
//             }
//         }
//     }
//     xhr.open("GET", "../comment/list.do?b_seq="+boardSeq, true);
//     xhr.send();
// }

$(function(){
    getCommentList();
});

// <c:if test="${commentVOs != null}">
//     <li>
//         <div class="pagination">
//             <c:if test="${pageInfo.curPage - 1 >= 1}">
//                 <a href="javascript:goPage(${boardVO.seq}, ${pageInfo.curPage - 1});" class="nextpage pbtn"> <img
//                     src="/myboard/resources/images/btn_prevpage.png" alt="다음 페이지로 이동"></a>
//             </c:if>
//             <c:if test="${pageInfo.startPage != 1}">
//                 <a href="javascript:goPage(${boardVO.seq}, 1);"><span class="pagenum">1</span></a>
//                 <div class="pageDot">
//                     <span> ... </span>
//                 </div>
//             </c:if>
//             <c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.curPage-1}">
//                 <a href="javascript:goPage(${boardVO.seq}, ${i});"><span class="pagenum">${i}</span></a>
//             </c:forEach>
//             <a href="javascript:;"><span class="pagenum currentpage">${pageInfo.curPage}</span></a>
//             <c:forEach var="i" begin="${pageInfo.curPage+1}" end="${pageInfo.endPage}">
//                 <a href="javascript:goPage(${boardVO.seq}, ${i});"><span class="pagenum">${i}</span></a>
//             </c:forEach>
//             <c:if test="${pageInfo.endPage < pageInfo.totalPage}">
//                 <div class="pageDot">
//                     <span> ... </span>
//                 </div>
//                 <a href="javascript:goPage(${boardVO.seq}, ${pageInfo.totalPage});"><span
//                     class="pagenum">${pageInfo.totalPage}</span></a>
//             </c:if>
//             <c:if test="${pageInfo.curPage + 1 <= pageInfo.totalPage}">
//                 <a href="javascript:goPage(${boardVO.seq}, ${pageInfo.curPage + 1});" class="nextpage pbtn"> <img
//                     src="/myboard/resources/images/btn_nextpage.png" alt="다음 페이지로 이동"></a>
//             </c:if>
//         </div>
//     </li>
// </c:if>
const getCommentList = function (b_seq, curPage) {
    // 현재페이지 게시글번호 설정, 없으면 미리정해둔값으로 설정
    let mySeq = b_seq;
    let myCurpage = curPage;
    if(mySeq === undefined || mySeq === null || mySeq === ''){mySeq = boardseq;}
    if(myCurpage === undefined || myCurpage === null || myCurpage === ''){myCurpage = 1;}
    let url = '../comment/list.do?b_seq='+mySeq+'&curPage='+myCurpage;
    $.ajax({
        // ajax 속성들 정의
        type : "GET",
        // 동기식으로 설정
        async : false,
        url : url,
        success: function (result){
            let html = "";
            // 댓글목록 html
            if(result.length > 0){
                for(let i = 0; i < result.length - 1; i++){
                    html += "<li class='comment_box' id='comment-"+result[i].c_seq+"'>";
                    html += "<div class='profile_info'>";
                    html += "<span id=writer'>"+result[i].c_writer+"</span><span>" + result[i].c_regdate+"</span>";
                    if(sessionUserName == result[i].c_writer){
                        let c_seq = "'"+result[i].c_seq+"'";
                        let c_writer = "'"+result[i].c_writer+"'";
                        //console.log(c_seq+', '+c_writer);
                        html += "<button type='button' class='btn_st1 btn_radius5 comment_del' style='background-color: #ff7373;' onclick='commentDelete(this)'>삭제</button>";
                        html += "<button type='button' id='btn_modi' class='btn_st1 btn_radius5 comment_modi' " +
                                'onclick="showUpdateComment('+c_seq+','+c_writer+')">수정</button>';
                    }
                    html += "</div>";
                    html += "<div class='comment_text'>"
                    html += "<p>"+result[i].c_content+"</p>";
                    html += "</div>";
                    html += "</li>";
                }
                // 페이징 정보들
                const totalPage = result[result.length - 1].totalPage;
                const totalPost = result[result.length - 1].totalPost;
                const curPage = result[result.length - 1].curPage;
                const startPage = result[result.length - 1].startPage;
                const endPage = result[result.length - 1].endPage;
                // pagination html
                html += "<li>";
                html += "<div class='pagination'>";
                if(curPage - 1 >= 1){
                    html += '<a href="javascript:goPage('+b_seq+', '+(curPage-1)+');" class="nextpage pbtn"> ' +
                        "<img src=\"/myboard/resources/images/btn_prevpage.png\" alt=\"다음 페이지로 이동\"></a>";
                }
                if(startPage != 1){
                    html += '<a href="javascript:goPage('+b_seq+', 1);"><span class="pagenum">1</span></a>' +
                        "<div class=\"pageDot\"><span> ... </span></div>";
                }
                for(let i = startPage; i < curPage; i++){
                    html += '<a href="javascript:goPage('+b_seq+', '+i+');"><span class="pagenum">'+i+'</span></a>';
                }
                html += "<a href='javascript:;'><span class='pagenum currentpage'>"+curPage+"</span></a>";
                for(let i = curPage+1; i <= endPage; i++){
                    html += '<a href="javascript:goPage('+b_seq+', '+i+');"><span class="pagenum">'+i+'</span></a>';
                }
                if(endPage < totalPage){
                    html += "<div class=\"pageDot\"><span> ... </span></div>" +
                        '<a href="javascript:goPage('+b_seq+', '+totalPage+');"><span class="pagenum">'+totalPage+'</span></a>';
                }
                if(curPage + 1 <= totalPage){
                    html += '<a href="javascript:goPage('+b_seq+', '+(curPage+1)+');" class="nextpage pbtn">' +
                        " <img src=\"/myboard/resources/images/btn_nextpage.png\" alt=\"다음 페이지로 이동\"></a>";
                }
                html += "</div>";
                html += "</li>";
            }else{
                html += "<p>게시글이 없습니다.</p>";
            }
            $('.div_comment_list').html(html);
        },
        error:function(request,status,error){
            console.log('comment list error');
        }
    })
}
const commentSubmit = function(){
    // 댓글을 작성하지 않았다면 false
    if(!$('.form_comment #content').val()){
        alert('댓글을 작성해주세요.');
        return false;
    }
    $.ajax({
        // ajax 속성들 정의
        type : "POST",
        url : "../comment/write.do",
        data : $('.form_comment').serialize(),
        success : function (data){
            if(data == "success"){
                // 목록을 업데이트
                getCommentList();
                // 입력창을 초기화함
                $('#form_comment #content').val("");
            }else{
                console.log('comment write fail!');
            }
        },
        error:function(request,status,error){
            console.log('comment write error');
        }
    })
}

const commentDelete = function(el){
    const bDel = confirm('정말로 삭제하시겠습니까?');
    if(bDel){
        // id 와 작성자정보를 가져옵니다.
        const commentBox = el.closest('.comment_box');
        const c_seq = commentBox.id.split('-')[1];
        const c_writer = document.getElementById('writer').value;
        // get방식으로
        $.get("../comment/delete.do?c_seq=" + c_seq + "&writer=" + c_writer,
            function (data) {
                if (data == "success") {
                    // 삭제가된 목록 업데이트
                    getCommentList();
                } else {
                    console.log('comment delete fail!!!');
                }
            }
        ).fail(function () {
                console.log('comment delete error');
            }
        )
    }
}

const showUpdateComment = function(c_seq){
    // 여러개 눌러도 댓글창 1개만 보이기 위해서 목록 초기화
    getCommentList();
    // 댓글 입력창 생성
    let html = "";
    html += "<form class='form_comment' id='form_comment' action='../comment/update.do' method='post'>";
    html += "<fieldset>";
    html += "<div class='commnet_area'>";
    html += "<input type='hidden' name='seq' value="+c_seq+">"
    html += "<input type='hidden' name='writer' value="+sessionUserName+">";
    html += "<input type='hidden' name='boardseq' value="+boardseq+">";
    html += "<textarea id='content' name='content' placeholder='댓글을 적을수 있는 창입니다.'></textarea>";
    html += "<p class=\"btn_line txt_right\">";
    const commentId = "'#comment-"+c_seq+"'";
    html += "<button type='button' class='btn_st1 btn_radius5' style='background-color: #ff7373;' " +
        'onclick="commentUpdate('+commentId+')">등록</button>'
    html += "</p>";
    html += "</div>";
    html += "</fieldset>";
    html += "</form>";
    $('#comment-'+c_seq).append(html);

    // 정보 셋팅
    const text = $('#comment-'+c_seq+' .comment_text p').text();
    //console.log(text);
    $('#comment-'+c_seq+' #form_comment #content').val(text);
}
const commentUpdate = function(commentId){
    // 입력 텍스트 가져오기
    const content = $(commentId+' #form_comment #content').val();
    if(content == null || content == ""){
        alert('댓글을 작성해주세요.');
        return false;
    }

    $.ajax({
        type : "POST",
        url : "../comment/update.do",
        data : $(commentId+' #form_comment').serialize(),
        success : function (data){
            if (data == "success") {
                getCommentList();
            } else {
                console.log('comment update fail!!!');
            }
        },
        error : function (){
            console.log('comment update error');
        }
    })
}