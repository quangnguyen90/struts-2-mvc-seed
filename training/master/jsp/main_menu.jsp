<%-- $Id: main_menu.jsp 1162 2010-06-07 09:11:18Z kenji.kodaka $ --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="slatenav">
	<ul>	
	<li><a href="<s:url action="moveto_a3"></s:url>"><s:property value="getText('a3.page_title')"/></a></li>
	<li><a href="<s:url action="a7Load"></s:url>"><s:property value="getText('item.a7.page_title')"/></a></li>
	<li><a href="<s:url action="link-to-A10"></s:url>"><s:property value="getText('A10.items.title')"/></a></li>
	<li><a href="<s:url action="moveto_a13"></s:url>" ><s:property value="getText('A13.page.title')"/></a></li>
	<li><a href="<s:url action="a34Load.html"></s:url>" ><s:property value="getText('item.a34.page_title')"/></a></li>
	<li><a href="<s:url action="link-to-A16"></s:url>" ><s:property value="getText('A16.page.title')"/></a></li>
    <li><a href="<s:url action="system_managed"></s:url>" ><s:property value="getText('A19.page.title')"/></a></li>
    <li><a href="<s:url action="link-to-A28"></s:url>" ><s:property value="getText('A28.page.title.main_menu')"/></a></li>
    <li><a href="<s:url action="link-to-A31"></s:url>" ><s:property value="getText('A31.page.title')"/></a></li>
    <li><a href="<s:url action="logout-action"></s:url>" ><s:property value="getText('btn.logout')"/></a></li>
	</ul>
	</div>