'use strict';

window.onload = function(){
	//console.log(birth);
	// 생년월일 설정
	const births = birth.split('-');
	document.getElementById('birth_year').value = Number(births[0]);
	document.getElementById('birth_month').value = Number(births[1]);
	document.getElementById('birth_day').value = Number(births[2]);
	// 성별 설정
	const radio_btns = document.getElementsByName('gender');
    if(gender == '남'){
		radio_btns[0].checked = 'true';
	}
	if(gender == '여'){
		radio_btns[1].checked = 'true';
	}

}

const update = function(){	
	// 날짜 합치기
    let year = document.getElementById('birth_year').value;
    let month = document.getElementById('birth_month').value;
    let day = document.getElementById('birth_day').value;
    
    if(Number(month) < 10){
    	month = "0" + month;
    }
    if(Number(day) < 10){
    	day = "0" + day;
    }
    
    document.getElementById("birth").value = year + "-" + month + "-" + day;
    
    // 비밀번호 확인
    const pw = document.getElementById("pw").value;
    const pwre = document.getElementById("pwre").value;
    if(pw !== pwre){
    	alert('비밀번호가 일치하지 않습니다.');    	
    }else{
    	document.getElementById("userVO").submit();
    }
}

const delUser = function (){
	if(confirm('정말로 탈퇴하시겠습니까?')){
		location.href = "/myboard/delete/user.do?seq="+document.getElementById('seq').value
		+"&id="+document.getElementById('id').value;
	}
}