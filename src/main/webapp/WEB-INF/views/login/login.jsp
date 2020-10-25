<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form:form class="app_form" action="/myboard/loginLogic.do" method="post" modelAttribute="userVO">
	<fieldset>
	<legend>로그인 입력 양식</legend>
		<p class="page_tit">로그인</p>
		<ul>
			<li class="clear">
				<div class="app_content">
					<form:input path="id" placeholder="아이디를 입력하세요."/>
				</div>
			</li>
			<li class="clear">
				<div class="error_login border_none">
					<form:errors path="id"/>
				</div>
			</li>
			<li class="clear">
				<div class="app_content">
					<form:password path="pw" placeholder="비밀번호를 입력하세요."/>
				</div>
			</li>
			<li class="clear">
				<div class="error_login border_none">
					<c:choose>
						<c:when test="${errorMsg != null }">
							<span style="color:red;">${errorMsg }</span>
						</c:when>
						<c:otherwise>
							<form:errors path="pw"/>
						</c:otherwise>
					</c:choose>
				</div>
			</li>
			<li class="clear">
				<div class="app_content border_none">
					<button class="btn_login" type="submit">로그인</button>
				</div>
			</li>
		</ul>
	</fieldset>
</form:form>
