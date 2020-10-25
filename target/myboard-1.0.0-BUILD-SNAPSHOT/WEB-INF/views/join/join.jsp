<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath }/resources/js/join.js?ver.${System.currentTimeMillis()}"></script>
<form:form class="app_form" action="/myboard/add/user.do" method="post" modelAttribute="userVO">
	<input type="hidden" name="isIdCheck" id="isIdCheck" value="no"/>
	<fieldset>
		<legend>회원가입 입력 양식</legend>
		<p class="page_tit">회원가입</p>
		<ul>
			<li class="clear">
				<form:label path="name" class="tit_lbl">이름</form:label>
				<div class="app_content">
					<form:input path="name" placeholder="이름을 입력하세요."/>
				</div>
				<div class="errorMsg">
					<form:errors path="name"/>
				</div>
			</li>
 			<li class="clear">
				<form:label path="id" class="tit_lbl">아이디</form:label>
				<div class="app_content id_area">
					<form:input path="id" placeholder="아이디를 입력하세요."/>
					<button class="btn_idcheck" type="button" onclick="idDoublecheck()">중복 체크</button>
				</div>
				<div class="errorMsg">
					<span id="idErrorMsg"></span>
				</div>
			</li>
			<li class="clear">
				<form:label path="pw" class="tit_lbl">비밀번호</form:label>
				<div class="app_content">
					<form:password path="pw" placeholder="비밀번호를 입력하세요."/>
				</div>
				<div class="errorMsg">
					<form:errors path="pw"/>
				</div>
			</li>
			<li class="clear">
				<form:label path="pwre" class="tit_lbl">비밀번호 확인</form:label>
				<div class="app_content">
					<form:password path="pwre"/>
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
					<form:input path="hp" placeholder="' - ' 없이 숫자만 입력하세요."/>
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
					<form:input path="email" placeholder="이메일을 입력하세요."/>	
				</div>
				<div class="errorMsg">
					<form:errors path="email"/>
				</div>
			</li>
		</ul>
	</fieldset>
		<p class="btn_line">
			<button type="button" class="btn_radius5" onclick="join()">회원가입</button>
		</p>
</form:form>
