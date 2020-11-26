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
    // // 수정 버튼
    // const comment_modis = document.querySelectorAll('.comment_modi');
    // comment_modis.forEach(el => {
    //     el.addEventListener('click', function() { showUpdateComment(this); });
    // });

    // 삭제 버튼
    const comment_dels = document.querySelectorAll('.comment_del');
    comment_dels.forEach(el => {
        el.addEventListener('click', function() { commentDelete(this); });
    });
});


const getCommentList = function () {
    const boardSeq = $('#boardseq').val();
    let data = {};
    $.ajax({
        type : "GET",
        url : "../comment/list.do?b_seq="+boardSeq,
        success: function (result){
            console.log(result);
            let html = "";
            if(result.length > 0){
                for(let i = 0; i < result.length - 1; i++){
                    html += "<li class='comment_box' id='comment-"+result[i].c_seq+"'>";
                    html += "<div class='profile_info'>"
                    html += "<span id=writer'>"+result[i].c_writer+"</span><span>" + result[i].c_regdate+"</span>";
                    if(sessionUserName == result[i].c_writer){
                        html += "<button type='button' class='btn_st1 btn_radius5 comment_del' style='background-color: #ff7373;' onclick='commentDelete(this)'>삭제</button>"
                        html += "<button type='button' class='btn_st1 btn_radius5 comment_modi'>수정</button>"
                    }
                    html += "</div>";
                    html += "<div class='comment_text'>"
                    html += "<p>"+result[i].c_content+"</p>";
                    html += "</div>";
                    html += "</li>";
                }
            }else{

            }
            $('.div_comment_list').html(html);
        },
        error:function(request,status,error){
            console.log('comment list fail');
        }
    })

}
const commentSubmit = function(){
    if(!$('.form_comment #content').val()){
        alert('댓글을 작성해주세요.');
        return false;
    }
    $.ajax({
        type : "POST",
        url : "../comment/write.do",
        data : $('.form_comment').serialize(),
        success : function (data){
            if(data == "success"){
                getCommentList();
                $('#form_comment #content').val("");
            }else{
                console.log('comment write fail!');
            }
        },
        error:function(request,status,error){

        }
    })
}
const commentUpdate = function(el){
    const content = document.querySelector('.form_update #content');
    if(!content.value){
        alert('댓글을 작성해주세요.');
        return false;
    }
    document.querySelector('.form_update').submit();
}

const commentDelete = function(el){
    const bDel = confirm('정말로 삭제하시겠습니까?');
    if(bDel){
        const commentBox = el.closest('.comment_box');
        const c_seq = commentBox.id.split('-')[1];
        const c_writer = document.getElementById('writer').value;
        console.log(c_seq, c_writer);
        $.get("../comment/delete.do?c_seq=" + c_seq + "&writer=" + c_writer,
            function (data) {
                if (data == "success") {
                    getCommentList();
                } else {
                    console.log('comment delete fail!');
                }
            }
        )
    }
}

const showUpdateComment = function(el){
    const commentArea = document.querySelector('.form_update');
    commentArea.style.display = 'block';
    const boxPoint = el.closest('.profile_info').nextSibling.nextSibling;
    boxPoint.appendChild(commentArea);

    // 정보 셋팅
    const commentBox = el.closest('.comment_box');
    const text = document.querySelector(`#${commentBox.id} .comment_text p`);
    const content = document.querySelector('.form_update #content');
    content.value = text.textContent;

    const c_seq = document.querySelector('.form_update #seq');
    c_seq.value = commentBox.id.split('-')[1];

    const writer = document.querySelector('.form_update #writer');
    writer.value = document.querySelector(`#${commentBox.id} #writer`).textContent;

}