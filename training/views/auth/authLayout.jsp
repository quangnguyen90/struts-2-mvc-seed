<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Auth</title>
<link href="<s:url value="/assets/css/default.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<s:url value="/assets/css/jquery-ui.css"/>" rel="stylesheet"
	type="text/css" />

<script type="text/javascript"
	src="<s:url value="/assets/js/jquery-1.11.2.min.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/assets/js/jquery-ui.min.js"/>"></script>
	
</head>
<body>
	<div id="header">
		<tiles:insertAttribute name="auth_header" />
	</div>
	<!-- WRAPPER-->
	<div id="wrapper">
		<tiles:insertAttribute name="auth_content" />
	</div>
	<!-- /WRAPPER-->
	<!-- FOOTER -->
	<div id="footer">
		<tiles:insertAttribute name="auth_footer" />
	</div>
	<!-- /FOOTER -->
</body>
</html>