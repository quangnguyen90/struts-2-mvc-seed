<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html lang="" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
	<!-- <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Struts 2 MVC</title>
<!-- Bootstrap CSS -->
<link href="<s:url value="/assets/css/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css">
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script type="text/javascript"
		src="<s:url value="/assets/js/jquery-1.11.2.min.js"/>"></script>
	<script type="text/javascript"
		src="<s:url value="/assets/js/jquery-ui.min.js"/>"></script>
	<!-- Bootstrap JavaScript -->
	<script type="text/javascript"
		src="<s:url value="/assets/js/bootstrap.min.js"/>"></script>
	<script>
		$(function() {
			var dates = $("#date_from, #date_to").datepicker({
				dateFormat : 'yy/mm/dd',
				defaultDate : "+1w",
				changeMonth : true,
				changeYear : true,
				numberOfMonths : 1,
			});
		});
	</script>
</head>

<body>

	<!-- HEADER -->
	<div id="header">
		<tiles:insertAttribute name="master_header" />
	</div>

	<!-- WRAPPER-->
	<div id="wrapper">

		<div class="menu">
			<tiles:insertAttribute name="master_menu_main" />
		</div>

		<!-- contents -->
		<div id="contents">
			<tiles:insertAttribute name="master_content" />
		</div>
		<!-- /contents -->
	</div>
	<!-- /WRAPPER-->
	
	<!-- /FACEBOOOK COMMENT PLUGIN -->
	<div id="fb-root"></div>
	    <script>(function(d, s, id) {
		        var js, fjs = d.getElementsByTagName(s)[0];
	        if (d.getElementById(id)) return;
	        js = d.createElement(s); js.id = id;
	        js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.0";
	        fjs.parentNode.insertBefore(js, fjs);
	    }(document, 'script', 'facebook-jssdk'));
	</script>
	<!-- /FACEBOOOK COMMENT PLUGIN -->
	
	
	<!-- FOOTER -->
	<div id="footer">
		<tiles:insertAttribute name="master_footer" />
	</div>
	<!-- FOOTER -->
</body>
</html>
