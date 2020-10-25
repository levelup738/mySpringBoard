'use strict';
const join = function(){
	// 날짜 합치기
    let year = document.getElementById("birth_year").value;
    let month = document.getElementById("birth_month").value;
    let day = document.getElementById("birth_day").value;
    
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
    	return false;
    }
    // 아이디 중복체크 확인
    const isIdCheck = document.getElementById('isIdCheck');
    if(isIdCheck.value == "no"){
        alert('아이디 중복체크를 해주세요.');
        return false;
    }

    document.getElementById("userVO").submit();
}

// 아이디 중복체크
const idDoublecheck = function(){
	const id = document.getElementById('id').value;
	if(!id){
		alert('아이디를 먼저 입력하세요.');
		document.getElementById('id').focus();
		return false;
	}
	const xhr = new XMLHttpRequest();
	xhr.open("GET", "idDoubleCheck.do?id="+id, true);
    xhr.send();
    const isIdCheck = document.getElementById('isIdCheck');
    const idErrorMsg = document.getElementById('idErrorMsg');
    xhr.onreadystatechange = function (){
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200){
            const result = xhr.responseText.split('-');
            if(result[0] == "yes"){
                idErrorMsg.style.color = "green";
                idErrorMsg.innerHTML = result[1];
                isIdCheck.value = "yes";
            }else{
                idErrorMsg.style.color = "red";
                idErrorMsg.innerHTML = result[1];
            }
        }
    }
    isIdCheck.onkeyup = function (){
        isIdCheck.value = "no";
    }
}
