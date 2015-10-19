<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="page_title">
	<p>Forgot Password</p>
</div>
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

	<!--forgot password form-->
	<s:form id="forgot-password-form" action="forgot-password-action">
		<div class="login_box box2">
			<p align="left">
				<s:property value="getText('item.userid')" />
			</p>
			<div class="mb10">
				<s:textfield name="username" size="24" id="username"
					cssClass="textbox_full" />
			</div>
			<p align="left">
				Email
			</p>
			<div class="mb10">
				<s:textfield name="email" size="24" id="email"
					cssClass="textbox_full" />
			</div>
			<p align="left"><a href="home.html">Home</a></p>
			<br />
			<div class="mt10" align="right">
				<s:submit cssClass="button3" value="Submit" />
			</div>
		</div>
	</s:form>
	<!--end the form-->
</div>
<!-- /contents -->