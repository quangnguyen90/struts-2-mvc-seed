<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="models.actions.auth.recaptcha.ReCaptcha"%>
<%@ page import="models.actions.auth.recaptcha.ReCaptchaFactory"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
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
			<br />
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
				<s:textfield name="username" size="24" id="username"
					cssClass="textbox_full" />
			</div>
			<p align="left">
				<s:property value="getText('item.user_pass')" />
			</p>
			<div class="mb10">
				<s:password name="password" size="24" id="password"
					cssClass="textbox_full" />
			</div>
			<p align="left">
				<a href="forgot-password.html">Forgot password</a>
			</p>
			<p align="left">
				<a href="register.html">Register</a>
			</p>
			<p align="left">
				<s:set name="failCount" value="%{failCount}" />
				<s:if test="%{#failCount > 4}">
					<div>
						<label class="captcha" for="captcha">Captcha</label>
					</div>
				</s:if>
			</p>
			<div class="mb10">
				<s:set name="failCount" value="%{failCount}" />
				<s:if test="%{#failCount > 4}">
					<!-- CAPTCHA-->
					<div id="recaptcha" class="captch">
						<%
							ReCaptcha c = ReCaptchaFactory.newReCaptcha(
											"6Lf4W98SAAAAAID1nMaf25I9NoXBP1Uf7VfahP1M",
											"6Lf4W98SAAAAAHfcJzLW6bf3oINtbE12J1jwIpXD", false);
									out.print(c.createRecaptchaHtml(null, null));
						%>
					</div>
				</s:if>
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
