'use strict';

window.onload = function(){
	const form_updates = document.querySelectorAll('.form_update');
	form_updates.forEach(el => {
		el.style.display = 'none';
	});
	// 수정 버튼
	const comment_modis = document.querySelectorAll('.comment_modi');
	comment_modis.forEach(el => {
		el.addEventListener('click', function() { showUpdateComment(this); });
	});
	
	// 삭제 버튼
	const comment_dels = document.querySelectorAll('.comment_del');
	comment_dels.forEach(el => {
		el.addEventListener('click', function() { commentDelete(this); });
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

const commentSubmit = function(){
	 const content = document.querySelector('.form_comment #content');
	 if(!content.value){
	 	alert('댓글을 작성해주세요.');
	 	return false;
	 }
	 document.querySelector('.form_comment').submit();
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
		const b_seq = document.getElementById('b_seq').value;
		const c_seq = commentBox.id.split('-')[1];
		const writer = document.getElementById('writer').value;
		location.href="/myboard/comment/delete.do?b_seq="+b_seq+"&c_seq="+c_seq+"&writer="+writer;
		//console.log(b_seq +", "+c_seq);
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

const goView = function(b_seq){
	location.href = "/myboard/board/view.do?b_seq="+b_seq;
}

const goPage = function(b_seq, curPage){
	location.href = "/myboard/board/view.do?b_seq="+b_seq+"&curPage="+curPage;
}