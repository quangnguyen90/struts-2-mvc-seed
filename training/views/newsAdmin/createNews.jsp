<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ckeditor" uri="http://ckeditor.com" %> 
<%@ taglib prefix="ckfinder" uri="http://cksource.com/ckfinder"%>
<%@ page import="com.ckeditor.CKEditorConfig"%>  


<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<legend>
		<h2>Create News</h2>
	</legend>
</div>
<!-- contents -->
<div id="create-news-contents" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<!-- place message display -->
	<div style="color: red">
		<s:iterator value="arrErrorMessage" status="stt">
		<s:property value="arrErrorMessage.get(#stt.index)" />
		<br />
	</s:iterator>
</div>
<s:property value="successMessage" />

<!--create news form-->
<div class="row">
	<s:form id="create-news-form" class="form-horizontal" action="create-news-action.html" nethod="POST" enctype="multipart/form-data">
	<div class="form-group">
		<label for="news-title" class="col-sm-2 control-label">Title</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="news-title" name="title" placeholder="Input title">
		</div>
	</div>
	
	<div class="form-group">
		<label for="news-brief" class="col-sm-2 control-label">Brief</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="news-brief" name="brief" placeholder="Input brief to describe about news">
		</div>
	</div>
	
	<div class="form-group">
		<label for="news-content" class="col-sm-2 control-label">Content</label>
		<div class="col-sm-10">
			<textarea class="form-control" rows="5" name="content" id="news-content"></textarea>
		</div>
	</div>
	
	<div class="form-group">
		<label for="news-avatar" class="col-sm-2 control-label">Avatar</label>
		<div class="col-sm-10">
			<input type="file" id="news-avatar" name="myNewsAvatar" accept="image/*" value="Choose image">
			<p class="help-block">
				<img src="/training/assets/img/news-img/awsome.jpg" alt="Responsive image" id="news_image" class="img-rounded" style="max-width: 200px; max-height: 150px;">
			</p>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">Submit</button>
		</div>
	</div>

	<% 
		/*====================================*/
		/* Ex 1: integrate CKEDITOR with *.jar */
		/* About Edit News: will integrate CKEDITOR with js*/
		/*====================================*/
		
	    CKEditorConfig settings = new CKEditorConfig();
	    settings.addConfigValue("filebrowserBrowseUrl","/training/assets/addons/ckfinder/ckfinder.html");
	    settings.addConfigValue("filebrowserImageBrowseUrl","/training/assets/addons/ckfinder/ckfinder.html?type=Images");
	    settings.addConfigValue("filebrowserFlashBrowseUrl","/training/assets/addons/ckfinder/ckfinder.html?type=Flash");
	    settings.addConfigValue("filebrowserUploadUrl","/training/assets/addons/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files");
	    settings.addConfigValue("filebrowserImageUploadUrl","/training/assets/addons/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images");
	    settings.addConfigValue("filebrowserFlashUploadUrl","/training/assets/addons/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash");

	    //settings.addConfigValue("width", "500");
    	//settings.addConfigValue("height", "200");  
    	//settings.addConfigValue("toolbar", "Basic");  
    	settings.addConfigValue("allowedContent", "h1 h2 h3 p blockquote strong em;a[!href];img(left,right)[!src,alt,width,height];table tr th td caption;span{!font-family};span{!color};span(!marker);del ins");  
    	settings.addConfigValue("basicEntities",false);
	%>

	<ckeditor:replace replace="news-content" basePath="/training/assets/addons/ckeditor/" config="<%=settings %>" />
</s:form>
</div>
<!--end the form-->
</div>
<!-- /contents -->
<!-- Javascript -->
<script type="text/javascript" src='<s:url value="/assets/js/news/news.js"/>'></script>
<script type="text/javascript" src='<s:url value="/assets/js/news/newsEvent.js"/>'></script>
<!-- EDTIOR CONFIGURATION -->
<!-- <script type="text/javascript" src='<s:url value="/assets/addons/ckeditor/ckeditor.js"/>'></script>
<script type="text/javascript" src='<s:url value="/assets/addons/ckfinder/ckfinder.js"/>'></script> -->
<!--
<script type="text/javascript">
  /*window.onload = function()
  {
    // Used for click submit again
    if(CKEDITOR.instances['news-content']) { 
        CKEDITOR.remove(CKEDITOR.instances['news-content']); 
    } 

    CKEDITOR.config.width = 1024; 
    CKEDITOR.config.height = 450; 
     //Whether to convert all remaining characters not included in the ASCII character table to their relative 
    //decimal numeric representation of HTML entity. When set to force, it will convert all entities into this format. 
    //For example : &#27721;&#35821;."
    CKEDITOR.config.entities_processNumerical = 'force'; // used for UTF-8
    var editor = CKEDITOR.replace('news-content',
      { uiColor: '#C2D6FF',
        // for save data, Allow everything (disable ACF)
        allowedContent: 
          'h1 h2 h3 p blockquote strong em;' +
          'a[!href];' +
          'img(left,right)[!src,alt,width,height];' +
          'table tr th td caption;' +
          'span{!font-family};' +
          'span{!color};' +
          'span(!marker);' + 'del ins', 
        basicEntities: false
      }); 

    CKFinder.setupCKEditor( editor, '/training/assets/addons/ckfinder/' );
  }*/
</script>
-->