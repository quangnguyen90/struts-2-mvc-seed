<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:if test="#session != null && (#session.master_user != null)">	
	<META HTTP-EQUIV="Refresh" CONTENT="1;URL=home.html">
</s:if>

<div id="page_title">
	<p>Register</p>
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

	<!--register form-->
	<s:form id="register-form" action="register-action.html" method="POST" enctype="multipart/form-data">
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
				
			</p>
			<div class="mb10">
				<s:password name="password" size="24" id="password"
					cssClass="textbox_full" />
			</div>
			<p align="left">
				Confirm password
			</p>
			<div class="mb10">
				<s:password name="confirmPassword" size="24" id="confirmPassword"
					cssClass="textbox_full" />
			</div>
			
			<p align="left">
				Full Name
			</p>
			<div class="mb10">
				<s:textfield name="fullname" size="24" id="fullname"
					cssClass="textbox_full" />
			</div>
			
			<p align="left">
				Gender
			</p>
			<div class="mb10">
				<s:select id="gender" style="height: 22px" cssClass="textbox_full" class="gender"
					headerKey="0" headerValue="--" 
					list="genders"
					name="genderSelected"
					listKey="genderId"
					listValue="genderName" 
					>
				</s:select>
			</div>
			
			<p align="left">
				Email
			</p>
			<div class="mb10">
				<s:textfield name="email" size="24" id="email"
					cssClass="textbox_full" />
			</div>
			
			<p align="left">
				Telephone
			</p>
			<div class="mb10">
				<s:textfield name="tel" size="24" id="telephone"
					cssClass="textbox_full" />
			</div>
			
			<p align="left">
				Birthday
			</p>
			<div class="mb10">
				<s:textfield id="dob" size="24" name="birthday" cssClass="textbox_full" />
			</div>
			
			<p align="left">
				City
			</p>
			<div class="mb10">
				<!-- <s:textfield name="city" size="24" id="city"
					cssClass="textbox_full" /> -->
				<%-- <s:select id="city" style="height: 22px" cssClass="textbox_full" class="city"
					headerKey="0" headerValue="--" 
					list="city"
					name="citySelected"
					onchange="loadDistricts(this.value);"
					listKey="cityId"
					listValue="cityName" 
					>
				</s:select> --%>
				<s:select id="city" style="height: 22px" cssClass="textbox_full" class="city"
					headerKey="0" headerValue="--" 
					list="city"
					name="citySelected"
					listKey="cityId"
					listValue="cityName" 
					>
				</s:select>
			</div>
			
			<p align="left">
				District
			</p>
			<div class="mb10">
				<s:select id="district" style="height: 22px" class="district"
					headerKey="0" headerValue="--" 
					list="district"
					name="districtSelected"
					listKey="districtId"
					listValue="districtName" 
					>
				</s:select>
			</div>
			<p align="left">
				Address
			</p>
			<div class="mb10">
				<s:textfield name="address" size="24" id="address"
					cssClass="textbox_full" />
			</div>
			<p align="left">
				Avatar
			</p>
			<div class="mb10">
				<input id="user-avatar" accept="image/*" type="file" value="Choose image" name="myAvatar" >
				<img id="image_viewaccount" class="regist-text" style="height:150p;width:200px" src='/training/assets/img/users-img/images.png' />
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

<!-- CHOOSE BIRTHDAY -->

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
</script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

<!-- Javascript -->
<script type="text/javascript"
	src="<s:url value="/assets/js/auth/auth.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/assets/js/auth/authEvent.js"/>"></script>