<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/common.jsp" %>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>민원상담관리</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0"/>
<!-- font-awesome -->
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
<!-- css -->
<link rel="stylesheet" type="text/css" href="${CssPath}/reset.css" />
<link rel="stylesheet" type="text/css" href="${CssPath}/contents.css" />

<!-- jquery -->
<script type="text/javascript" src="${JsPath}/jquery-3.2.1.min.js"></script>
<script src="${JsPath}/jquery.simple-sidebar.min.js"></script>
<script src="${JsPath}/slight.js"></script>
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
	<!-- content -->
	<!-- Login -->
	<section id="content">
		<form id="loginForm" action="${contextPath}/login/loginOk.do" method="post">
	    <h2 class="hide">로그인</h2>
			<div id="login">
				<p><a href="#" class="login_logo">로그인로고</a></p>
				<div id="login_tbox">
					<p><INPUT TYPE="text" NAME="adminId" id="adminId" class="loginbox" placeholder="아이디"></p>
					<p><INPUT TYPE="text" NAME="password" id="password" class="loginbox" placeholder="비밀번호"  onkeyup="enterkey();"></p>	
					<p><a href="#" class="btn_login">로그인</a></p>	
					<p><a href="javascript:loginOk();" class="btn_home">홈으로</a></p>	
				</div>
					
			</div>
		</form>
	</section>
	<!-- //  Login -->
	<!-- //content -->
</body>
</html>
