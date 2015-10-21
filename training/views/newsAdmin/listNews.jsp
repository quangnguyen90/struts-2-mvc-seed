<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<legend>
		<h2>List News</h2>
	</legend>
</div>
<!-- contents -->
<div id="list-news-contents" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<!-- place message display -->
	<div style="color: red">
		<s:iterator value="arrErrorMessage" status="stt">
		<s:property value="arrErrorMessage.get(#stt.index)" />
		<br />
	</s:iterator>
</div>
<s:property value="successMessage" />

<!--list news form-->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<a href="create-news.html">Create News</a>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>Title</th>
						<th>Brief</th>
						<th>Created</th>
						<th>Updated</th>
						<th>Avatar</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="newsResults" var="news">
					<tr>
							<td><s:property value="id" /></td>
							<td><s:property value="title" /></td>
							<td><s:property value="brief" /></td>
							<td><s:property value="created" /></td>
							<td><s:property value="updated" /></td>
							<td><img src='<s:property value="%{contextPath + avatar}"/>' alt="Responsive image" class="img-responsive img-rounded" style="max-width: 140px; max-height: 140px;"></td>
							<s:if test='#news.status == 1'>
								<td>On</td>
							</s:if>
							<s:if test='#news.status == 0'>
								<td>Off</td>
							</s:if>
							<td>
								<a href="edit-news.html?newsId=<s:property value="%{id}"/>">Edit</a>
								<a href="remove-news.html?newsId=<s:property value="%{id}"/>">Remove</a>
								<a href="enable-news.html?newsId=<s:property value="%{id}"/>">Enable</a>
								<a href="disable-news.html?newsId=<s:property value="%{id}"/>">Disable</a>
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
							<strong>
								<s:a onclick="CngClass(this);" href="list-news.html?numberPage=%{#ps.count}"><s:property value="#p" /></s:a>
							</strong>
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