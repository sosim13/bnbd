<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <spring:message code=""/> --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="CssPath" value="${contextPath}/resources/css"/>
<c:set var="ImagePath" value="${contextPath}/resources/images"/>
<c:set var="JsPath" value="${contextPath}/resources/js"/>
<c:set var="HomePage" value="테스트"/>
<fmt:formatDate var="CurDate" value="<%=new java.util.Date()%>" pattern="yyyyMMdd"/>