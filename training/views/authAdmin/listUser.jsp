<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<legend>
		<h2>List Users</h2>
	</legend>
</div>
<!-- contents -->
<div id="list-user-contents" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<!-- place message display -->
	<div style="color: red">
		<s:iterator value="arrErrorMessage" status="stt">
		<s:property value="arrErrorMessage.get(#stt.index)" />
		<br />
	</s:iterator>
</div>
<s:property value="successMessage" />

<!--list user form-->
<div class="rows">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<a href="create-user.html">Create New User</a>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Username</th>
						<th>FullName</th>
						<th>Email</th>
						<th>Address</th>
						<th>Tel</th>
						<th>Birthday</th>
						<th>Gender</th>
						<th>City</th>
						<th>District</th>
						<th>User Type</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="userResults" var="user">
						<tr>
							<td><s:property value="username" /></td>
							<td><s:property value="fullname" /></td>
							<td><s:property value="email" /></td>
							<td><s:property value="address" /></td>
							<td><s:property value="tel" /></td>
							<td><s:property value="dob" /></td>
							<td><s:property value="gender" /></td>
							<td><s:property value="cityId" /></td>
							<td><s:property value="districtId" /></td>
							<td><s:property value="userType" /></td>
							<td><s:property value="status" /></td>
							<td>
								<a href="edit-user.html?userId=<s:property value="%{username}"/>">Edit</a>
								<a href="reset-user-password.html?userId=<s:property value="%{username}"/>">Reset</a>
								<a href="remove-user.html?userId=<s:property value="%{username}"/>">Remove</a>
								<a href="enable-user.html?userId=<s:property value="%{username}"/>">Enable</a>
								<a href="disable-user.html?userId=<s:property value="%{username}"/>">Disable</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<div id="DividePage">
			<b>Go To Page</b>
			
			<ul class="pagination">
				<s:iterator value="lstPage" id="lstPage" var="p" status="ps">
					<li>
						<s:if test="#p!=trang">
							<strong><s:a onclick="CngClass(this);" href="list-user.html?numberPage=%{#ps.count}"><s:property value="#p" />
								</s:a></strong>
						</s:if>
						<s:else>
							<b><s:property />
							</b>
						</s:else>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
</div>
<!--end the form-->
</div>
<!-- /contents -->
<script>
	function CngClass(obj) {
		if (obj.className==""){
			obj.className = "iselected";
		}else{
			obj.className = "";
		}
	}
</script>