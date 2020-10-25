'use strict';

window.onload = function(){       
	document.getElementById('keyword').value = document.getElementById('savedKeyword').value;
	const opt = document.getElementById('savedSearchOpt').value;
	if(opt == 'all'){
		document.getElementById('searchOpt').value = 'titAndContent';
	}else{
		document.getElementById('searchOpt').value = opt;
	}
}

const goPage = function(curPage){
	const searchOpt = document.getElementById('searchOpt').value;
	const keyword = document.getElementById('keyword').value;
	location.href = "/myboard/board/list.do"+
		"?curPage="+curPage+"&searchOpt="+searchOpt+"&keyword="+keyword;
}
const goSearch = function(){
	const searchOpt = document.getElementById('searchOpt').value;
	const keyword = document.getElementById('keyword').value;
	location.href = "/myboard/board/list.do?searchOpt="+searchOpt+"&keyword="+keyword;
}
const goView = function(pageNum){
	location.href = "/myboard/board/view.do?b_seq="+pageNum;
}