<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 만들기</title>
<link href="${pageContext.request.contextPath}/resources/css/common.css?ver.${System.currentTimeMillis()}" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<header>
		<a href="${pageContext.request.contextPath}/home.do">HOME</a> |
		<c:choose>
			<c:when test="${sessionUser.name != null}">
				${sessionUser.name}님 | 
				<a href="${pageContext.request.contextPath}/mypage.do">MY PAGE</a> |
				<a href="${pageContext.request.contextPath}/logoutLogic.do">LOGOUT</a> |
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/login.do">LOGIN</a> |
				<a href="${pageContext.request.contextPath}/join.do">JOIN</a> |
			</c:otherwise>
		</c:choose>
		<a href="${pageContext.request.contextPath}/board/list.do">BOARD</a>
		</header>
		<div class="main" align="center">