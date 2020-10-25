'use strict';

const posting = function(){
	const title = document.getElementById('title').value;
	if(!title){
		alert('제목을 입력해주세요.');
		return;
	}
	const content = document.getElementById('content').value;
	if(!content){
		alert('본문 내용을 입력해주세요.');
		return;
	}
	document.getElementById('boardVO').submit();
}