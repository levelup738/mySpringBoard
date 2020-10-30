window.onload = function () {
    getCommentList();
}

const getCommentList = function () {
    const boardSeq = document.getElementById("boardseq").value;
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if(xhr.status === 200 || xhr.status === 201) {
                const log = JSON.parse();
                console.log(log);
            }
        }
    }
    xhr.open("GET", "../comment/list.do?b_seq="+boardSeq, true);
    xhr.send();
}