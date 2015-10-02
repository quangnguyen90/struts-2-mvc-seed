<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:property value="getText('master.title')" /></title>
<link href="<s:url value="/master/css/default.css"/>" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<!-- HEADER -->
	<div id="header">
		<p class="logo">Login Example</p>
	</div>
	<!-- WRAPPER-->
	<div id="wrapper">
		<div id="page_title">
			<p>
				<s:property value="getText('page.title')" />
			</p>
		</div>
		<!-- contents -->
		<div id="contents">
			<!-- place message display -->
			<div style="color: red">
		  		<s:iterator value="arrErrorMessage" status="stt">
		  			<s:property value="arrErrorMessage.get(#stt.index)" />
		  			<br/>
		  		</s:iterator>
  			</div>
			<s:property value="successMessage" />
			
			<!--login form-->
			<s:form id="login-form" action="login_action">
				<div class="login_box box2">
					<p align="left">
						<s:property value="getText('item.userid')" />
					</p>
					<div class="mb10">
						<s:textfield name="username" size="24" id="username" cssClass="textbox_full"/>
					</div>
					<p align="left">
						<s:property value="getText('item.user_pass')" />
					</p>
					<div class="mb10">
						<s:password name="password" size="24" id="password" cssClass="textbox_full"/>
					</div>
					<br />
					<div class="mt10" align="right">
						<s:submit cssClass="button3" value="%{getText('button.login')}" />
					</div>
				</div>
			</s:form>
			<!--end the form-->
		</div>
		<!-- /contents -->
	</div>
	<!-- /WRAPPER-->
	<!-- FOOTER -->
	<div id="footer">
		<p>Copyright Â© 2012, Alij Lab. All Rights Reserved.</p>
	</div>
	<!-- FOOTER -->
</body>
</html>