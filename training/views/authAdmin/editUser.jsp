<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<legend>
		<h2>Edit User</h2>
	</legend>
</div>
<!-- contents -->
<div id="edit-user-contents" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<!-- place message display -->
	<div style="color: red">
		<s:iterator value="arrErrorMessage" status="stt">
		<s:property value="arrErrorMessage.get(#stt.index)" />
		<br />
	</s:iterator>
</div>
<s:property value="successMessage" />

<!--register form-->
<s:form id="edit-user-form" class="form-horizontal" action="edit-user-action" method="POST" enctype="multipart/form-data">
<input type="hidden" class="form-control" id="hidden-username" name="userId" value='<s:property value="%{user.username}"/>'>
<div class="form-group">
	<label for="edit-user-username" class="col-sm-2 control-label">Username</label>
	<div class="col-sm-10">
		<input type="text" class="form-control" id="edit-user-username" value='<s:property value="%{user.username}"/>' disabled="true">
	</div>
</div>

<div class="form-group">
	<label for="edit-user-fullname" class="col-sm-2 control-label">Fullname</label>
	<div class="col-sm-10">
		<input type="text" class="form-control" id="edit-user-fullname" name="fullname" value='<s:property value="%{user.fullname}"/>' placeholder="Input fullname">
	</div>
</div>

<div class="form-group">
	<label for="user-avatar" class="col-sm-2 control-label">Avatar</label>
	<div class="col-sm-10">
		<input type="file" id="user-avatar" name="myAvatar" accept="image/*" value="Choose image">
		<p class="help-block">
			<img id="image_viewaccount" src='<s:property value="%{contextPath + imgAURL}"/>' alt="Responsive image" class="img-rounded" style="max-width: 200px; max-height: 150px">
		</p>
	</div>
</div>

<div class="form-group">
	<label for="edit-user-gender" class="col-sm-2 control-label">Gender</label>
	<div class="col-sm-10">
		<s:select id="edit-user-gender" style="height: 33px" class="form-control"
			headerKey="0" headerValue="--" 
			list="genders"
			name="genderSelected"
			listKey="genderId"
			listValue="genderName" 
			>
		</s:select>
	</div>
</div>

<div class="form-group">
	<label for="edit-user-email" class="col-sm-2 control-label">Email</label>
	<div class="col-sm-10">
		<input type="email" class="form-control" id="edit-user-email" name="email" value='<s:property value="%{user.email}"/>' placeholder="Input email">
	</div>
</div>

<div class="form-group">
	<label for="edit-user-tel" class="col-sm-2 control-label">Telephone</label>
	<div class="col-sm-10">
		<input type="text" class="form-control" id="edit-user-tel" name="tel" value='<s:property value="%{user.tel}"/>' placeholder="Input telephone">
	</div>
</div>

<div class="form-group">
	<label for="edit-user-dob" class="col-sm-2 control-label">Birthday</label>
	<div class="col-sm-10">
		<input type="text" class="form-control" id="dob" name="birthday" value='<s:property value="%{birthday}"/>' placeholder="Input birthday">
	</div>
</div>

<div class="form-group">
	<label for="edit-user-city" class="col-sm-2 control-label">City</label>
	<div class="col-sm-10">
		<s:select id="user-city" style="height: 33px" class="form-control"
			headerKey="0" headerValue="--" 
			list="city"
			name="citySelected"
			listKey="cityId"
			listValue="cityName">
		</s:select>
	</div>
</div>

<div class="form-group">
	<label for="edit-user-district" class="col-sm-2 control-label">District</label>
	<div class="col-sm-10">
		<s:select id="user-district" style="height: 33px" class="form-control"
			headerKey="0" headerValue="--" 
			list="district"
			name="districtSelected"
			listKey="districtId"
			listValue="districtName">
		</s:select>
	</div>
</div>

<div class="form-group">
	<label for="edit-user-address" class="col-sm-2 control-label">Address</label>
	<div class="col-sm-10">
		<input type="text" class="form-control" id="edit-user-address" name="address" value='<s:property value="%{user.address}"/>' placeholder="Input address">
	</div>
</div>

<div class="form-group">
	<div class="col-sm-offset-2 col-sm-10">
		<button type="submit" class="btn btn-default">Submit</button>
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