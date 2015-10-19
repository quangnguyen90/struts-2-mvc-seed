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
		<div id="page_title">
			<p>Reset Your Password</p>
		</div>
	</div>

	<!-- WRAPPER-->
	<div id="wrapper">
		<!-- contents -->
		<div id="contents">
			<!-- place message display -->
			<div style="color: red">
				<s:iterator value="arrErrorMessage" status="stt">
					<s:property value="arrErrorMessage.get(#stt.index)" />
					<br />
				</s:iterator>
			</div>
			<s:property value="successMessage" />

			<!--change password form-->
			<s:form id="reset-password-form" action="reset-password-action">
				<div class="changepassword_box box2">
					<p align="left">
						<input type="hidden" name="username"
							value='<%=request.getParameter("username")%>' /> Your temporary
						Password
					</p>
					<div class="mb10">
						<s:password name="temporaryPassword" size="24"
							id="temporaryPassword" cssClass="textbox_full" />
					</div>
					<p align="left">New Password</p>
					<div class="mb10">
						<s:password name="password" size="24" id="password"
							cssClass="textbox_full" />
					</div>
					<p align="left">Confirm New Password</p>
					<div class="mb10">
						<s:password name="confirmNewPassword" size="24"
							id="confirmNewPassword" cssClass="textbox_full" />
					</div>
					<p align="left">
						<a href="home.html">Home</a>
					</p>
					<br />
					<div class="mt10" align="right">
						<s:submit cssClass="button3" value="Submit" />
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
		<p>Copyright © 2015, Quang Nguyen Phu. All Rights Reserved.</p>
	</div>
	<!-- /FOOTER -->
</body>
</html>