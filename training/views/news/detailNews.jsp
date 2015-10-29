<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<legend>
		<h2><s:property value="%{news.title}"/></h2>
		<s:property value="%{news.brief}"/>
	</legend>
</div>
<!-- contents -->
<div id="edit-news-contents" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<!-- place message display -->
	<div style="color: red">
		<s:iterator value="arrErrorMessage" status="stt">
		<s:property value="arrErrorMessage.get(#stt.index)" />
		<br />
	</s:iterator>
</div>
<s:property value="successMessage" />
<!--detail news form-->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<input type="hidden" class="form-control" id="newsId" name="newsId" value='<s:property value="%{news.id}"/>'>
		<p>Created: <s:property value="%{news.created}"/> | Updated: <s:property value="%{news.updated}"/></p>
		<p class="help-block">
			<img src='<s:property value="%{contextPath + imgAURL}"/>' id="news_image" alt="Responsive image" class="img-rounded" style="max-width: 400px; max-height: 350px">
		</p>
		<p><s:property value="%{news.content}"/></p>
	</div>
</div>
<!--end the form-->
</div>
<!-- /contents -->

<!-- Facebook comment plugin  -->
<hr />
<div id="comments" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
  <h3 class="section-title">Comments</h3>
  <div class="fb-like" data-href="news-detail.html" data-layout="standard" data-action="like" data-show-faces="true" data-share="true"></div>
  <div class="fb-comments" data-href="news-detail.html" data-numposts="5" data-colorscheme="light"></div>
</div>
<!-- /Facebook comment plugin  -->