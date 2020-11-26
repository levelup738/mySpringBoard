'use strict';

window.onload = function(){
	const form_updates = document.querySelectorAll('.form_update');
	form_updates.forEach(el => {
		el.style.display = 'none';
	});
}

const onDelete = function(){
	const bDel = confirm('정말로 삭제하시겠습니까?');
	if(bDel){
		const b_seq = document.getElementById('b_seq').value;
		const b_writer = document.querySelector('.bbs_username span').value;
		location.href="/myboard/board/delete.do?b_seq="+b_seq+"&b_writer="+b_writer;
	}
}



const goView = function(b_seq){
	location.href = "/myboard/board/view.do?b_seq="+b_seq;
}

const goPage = function(b_seq, curPage){
	location.href = "/myboard/board/view.do?b_seq="+b_seq+"&curPage="+curPage;
}