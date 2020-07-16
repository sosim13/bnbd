<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/common.jsp" %>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>${HomePage}</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0"/>
<link rel="shortcut icon" type="image/x-icon" href="${ImagePath}/nmsfavicon.png"/>
<link type="text/css" rel="stylesheet" href="${CssPath}/font-awesome.min.css"/>
<link type="text/css" rel="stylesheet" href="${CssPath}/common.css"/>
<link type="text/css" rel="stylesheet" href="${CssPath}/nms.css"/>
<script type="text/javascript" src="${JsPath}/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${JsPath}/nms.js"></script>
<!--[if lte IE 9]>
<link rel="stylesheet" type="text/css" href="css/main_ie.css" />
<![endif]-->

<script type="text/javascript">
function loginOk(){
	$("#loginForm").submit();
}

function enterkey() {
    if (window.event.keyCode == 13) {         
    	loginOk();
    }
}
</script>

</head>
<body>
	<div id="loginContainer">
		<header class="group">
			<h1 class="logo"><img src="${ImagePath}/logo.png" alt="scgs로고"><em>MAIN</em></h1>
		</header>		
		<footer class="hide"></footer>
	</div>
</body>
</html>
