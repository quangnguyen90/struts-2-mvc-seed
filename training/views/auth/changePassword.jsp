<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="page_title">
	<p>Change Password</p>
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
	
	<!--change password form-->
	<s:form id="change-password-form" action="change-password-action">
		<div class="changepassword_box box2">
			<p align="left">
				<input type="hidden" name = "username" value ='<s:property value="%{user.username}"/>'/>
				Current Password
			</p>
			<div class="mb10">
				<s:password name="password" size="24" id="password"
					cssClass="textbox_full" />
			</div>
			<p align="left">
				New Password
			</p>
			<div class="mb10">
				<s:password name="newPassword" size="24" id="newPassword"
					cssClass="textbox_full" />
			</div>
			<p align="left">
				Confirm New Password
			</p>
			<div class="mb10">
				<s:password name="confirmNewPassword" size="24" id="confirmNewPassword"
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