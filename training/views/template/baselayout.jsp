<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Struts 2 MVC</title>
<link href="<s:url value="/assets/css/default.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<s:url value="/assets/css/jquery-ui.css"/>" rel="stylesheet"
	type="text/css" />

<script type="text/javascript" src="<s:url value="/assets/js/jquery-1.7.1.min.js"/>"></script>
<script type="text/javascript" src="<s:url value="/assets/js/jquery-ui.min.js"/>"></script>



<script>
$(function() {
  var dates = $( "#date_from, #date_to" ).datepicker({
   dateFormat: 'yy/mm/dd',
   defaultDate: "+1w",
   changeMonth: true,
   changeYear: true,
   numberOfMonths: 1,
  });
 });
</script>
</head>

<body>

<!-- HEADER -->
<div id="header">
  <tiles:insertAttribute name="master_header"/>
</div>



<!-- WRAPPER-->
<div id="wrapper">

<div class="menu">
	<tiles:insertAttribute name="master_menu_main"/>
</div>

  <!-- <div id="page_title">
    <p>決済管理</p>
</div> -->
  
  <!-- contents -->
  <div id="contents">
   	<tiles:insertAttribute name="master_content"/>
  </div>
  <!-- /contents --> 
</div>
<!-- /WRAPPER--> 

<!-- FOOTER -->
<div id="footer">
  <tiles:insertAttribute name="master_footer"/>
</div>
<!-- FOOTER -->

</body>
</html>
