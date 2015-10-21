<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<nav class="navbar navbar-default" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="home.html">Home</a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">
			<li class="active"><a href="about.html">About</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<s:if
				test="#session == null || (#session != null && #session.master_user == null)">
				<li><a href="<s:url action="login.html"></s:url>"><s:property
						value="getText('button.login')" /></a></li>
				<li><a href="register.html">Register</a></li>
			</s:if>
			<s:if test="#session != null && (#session.master_user != null)">
				<li class="dropdown"><a href="" class="dropdown-toggle"
					data-toggle="dropdown">Profile<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<s:url action="logout"></s:url>"><s:property
									value="getText('button.logout')" /></a></li>
						<li><a href="change-password.html">Change Password</a></li>
						<li><a href="profile.html">Information - <s:property value="#session.master_user.fullname" /></a></li>
					</ul></li>
				<!-- Only allow admin see modules  -->
				<s:if test='#session.master_user.username == "admins"'>	
					<li class="dropdown"><a href="" class="dropdown-toggle"
						data-toggle="dropdown">Modules<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="list-user.html">User</a></li>
							<li><a href="list-news.html">News</a></li>
						</ul>
					</li>
				</s:if>
			</s:if>
		</ul>
	</div>
	<!-- /.navbar-collapse -->
</nav>
