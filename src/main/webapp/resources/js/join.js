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
	//XMLHttpRequest 객체는 웹서버와의 데이터교환을 비동기식으로 가능하게 해줌
    //해서 페이지 전체를 다시 로딩하지 않고 일부분만 수정이 가능
	const xhr = new XMLHttpRequest();
	//open은 Request를 구현
	xhr.open("GET", "idDoubleCheck.do?id="+id, true);
	//send로 서버에 Request를 보냄
    xhr.send();
    const isIdCheck = document.getElementById('isIdCheck');
    const idErrorMsg = document.getElementById('idErrorMsg');
    //onreadystatechange는 readystate에 변화가 있으면 불려질 함수를 정의
    //readyState 속성값의 종류
    // 0: 요청이 아직 초기화되지 않음
    // 1: 서버와 연결이 완료됨
    // 2: 요청을 서버가 받음
    // 3: 요청에 대한 처리중
    // 4: 요청이 완료되었고 응답이 대기중
    xhr.onreadystatechange = function (){
        // 200은 서버가 요청을 잘 처리했다는 의미
        // DONE이 4임
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
