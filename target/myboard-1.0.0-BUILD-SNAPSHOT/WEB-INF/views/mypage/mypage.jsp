<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/resources/js/mypage.js?ver.${System.currentTimeMillis()}"></script>
<form:form class="app_form" action="/myboard/userUpdate.do" method="post" modelAttribute="userVO">
	<fieldset>
		<legend>회원정보 수정 입력 양식</legend>
		<p class="page_tit">회원정보</p>
		<form:hidden path="seq" value="${sessionUser.seq}"/>
		<ul>
			<li class="clear">
				<form:label path="name" class="tit_lbl">이름</form:label>
				<div class="app_content">
					<form:input path="name" placeholder="이름을 입력하세요." value="${sessionUser.name}"/>
				</div>
				<div class="errorMsg">
					<form:errors path="name"/>
				</div>
			</li>
 			<li class="clear">
				<form:label path="id" class="tit_lbl">아이디</form:label>
				<div class="app_content">
					<form:input path="id" value="${sessionUser.id}" readonly="true"/>
				</div>
				<div class="errorMsg">
				</div>
			</li>
			<li class="clear">
				<form:label path="pw" class="tit_lbl">비밀번호</form:label>
				<div class="app_content">
					<form:password path="pw" maxlength="20" placeholder="비밀번호를 입력하세요."/>
				</div>
				<div class="errorMsg">
					<form:errors path="pw"/>
				</div>
			</li>
			<li class="clear">
				<form:label path="pwre" class="tit_lbl">비밀번호 확인</form:label>
				<div class="app_content">
					<form:password path="pwre" maxlength="20"/>
				</div>
				<div class="errorMsg">
				</div>
			</li>
 			<li class="clear">
				<span class="tit_lbl">성별</span>
            	<div class="app_content radio_area">
            		<form:label class="radio_gen" path="gender">
    					<form:radiobuttons items="${joinOpt.opt_genders}" path="gender" />
					</form:label>
                </div>
                <div class="errorMsg">
					<form:errors path="gender"/>
				</div>
			</li>
			<li class="clear">
				<form:label path="hp" class="tit_lbl">연락처</form:label>
				<div class="app_content">
					<form:input path="hp" placeholder="' - ' 없이 숫자만 입력하세요." value="${sessionUser.hp}"/>
				</div>
				<div class="errorMsg">
					<form:errors path="hp"/>
				</div>
            </li>
 			<li class="clear">
				<span class="tit_lbl">생년월일</span>
				<div class="app_content sel_birth">
					<form:select class="sel_date" path="birth_year">
    					<option value="">년도</option>
    					<form:options items="${joinOpt.opt_years}"/>
					</form:select>
					<form:select class="sel_date" path="birth_month">
    					<option value="">월</option>
    					<form:options items="${joinOpt.opt_months}"/>
					</form:select>
					<form:select class="sel_date" path="birth_day">
    					<option value="">일</option>
    					<form:options items="${joinOpt.opt_days}"/>
					</form:select>
					<form:hidden path="birth"/>
				</div>
				<div class="errorMsg">
					<form:errors path="birth"/>
				</div>
			</li>
			<li class="clear">
				<span class="tit_lbl">이메일</span>
				<div class="app_content">
					<form:input path="email" placeholder="이메일을 입력하세요." value="${sessionUser.email}"/>	
				</div>
				<div class="errorMsg">
					<form:errors path="email"/>
				</div>
			</li>
		</ul>
	</fieldset>
		<p class="btn_line">
			<button type="button" class="btn_radius5" onclick="update()">회원 정보 수정</button>
			<button type="button" class="btn_radius5" onclick="delUser()" style="background-color: #ff0000;">회원 탈퇴</button>
		</p>
</form:form>
<script type="text/javascript">
	const birth = '${sessionUser.birth}';
	const gender = '${sessionUser.gender}';
</script>