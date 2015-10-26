<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<div id="page_title">
	<p>Profile Information</p>
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

	<!--profile form-->
	<s:form id="profile-account-form" action="profile-action.html" method="POST" enctype="multipart/form-data">
		<div class="login_box box2">
			<p align="left">
				<s:property value="getText('item.userid')" />
			</p>
			<div class="mb10">
				<s:textfield name="username" size="24" value="%{user.username}" id="username" cssClass="textbox_full" disabled="true"/>
			</div>
			
			<p align="left">
				Full Name
			</p>
			<div class="mb10">
				<s:textfield name="fullname" size="24" value="%{user.fullname}" id="fullname"
					cssClass="textbox_full" />
			</div>
			<p align="left">
				Avatar
			</p>
			<div class="mb10">
				<img id="image_viewaccount" class="regist-text" style="height:150p;width:200px" src='<s:property value="%{contextPath + imgAURL}"/>' />
				<input accept="image/*" type="file" value="Choose image" id="user-avatar" name="myAvatar"  />
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
				<s:textfield name="email" size="24" value="%{user.email}" id="email"
					cssClass="textbox_full" />
			</div>
			
			<p align="left">
				Telephone
			</p>
			<div class="mb10">
				<s:textfield name="tel" type="text" value="%{user.tel}" size="24" id="telephone"
					cssClass="textbox_full" />
			</div>
			
			<p align="left">
				Birthday
			</p>
			<div class="mb10">
				<s:textfield name="birthday" size="24" type="text" value="%{birthday}" id="dob" cssClass="textbox_full" />
			</div>
			
			<p align="left">
				City
			</p>
			<div class="mb10">
				<s:select id="city" style="height: 22px" cssClass="textbox_full" class="city"
					headerKey="0" headerValue="--" 
					list="city"
					name="citySelected"
					onchange="loadDistricts(this.value);"
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
				<s:textfield name="address" value="%{user.address}" size="24" id="address"
					cssClass="textbox_full" />
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
<!-- Javascript -->
<script type="text/javascript"
	src="<s:url value="/assets/js/auth/auth.js"/>"></script>
<script type="text/javascript"
	src="<s:url value="/assets/js/auth/authEvent.js"/>"></script>