<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<duv class="container-fluid">
<div class="row">
	<div class="col-sm-8 col-md-8">
		<h2>Struts 2 MVC Seed</h2>
		<s:property value="message"/>
	</div>
	<div class="col-sm-4 col-md-4">
		<br>
		<div class="container-fluid">
			<div id="language" class="row">
				<form action="" id="languageForm"  style="text-align:right">
					<script type="text/javascript">
						var urlTemp = document.URL;
						var urlLang = urlTemp;
						var position1 = urlTemp.indexOf("?request_locale");
						var position2 = urlTemp.indexOf("&request_locale");

						if(position1 == -1 && position2 == -1) {
							position1 = urlTemp.indexOf("?");
							if(position1 == -1){
								urlLang = urlTemp+"?request_locale";
							}
							else{
								urlLang = urlTemp+"&request_locale";
							}
						}
						else {
							if(position1 > -1){
								urlLang = urlTemp.substr(0,position1)+"?request_locale";
							}
							if(position2 > -1){
								urlLang = urlTemp.substr(0,position2)+"&request_locale";
							}
						}
						document.write('<span>');
						document.write('<a href="'+urlLang+'=vi" title="Vietnam">');			
						document.write('<img src="/training/assets/img/icon_flag_vn.png" alt="...">');
						document.write('</a>');
						document.write('</span>');

						document.write('<span>');
						document.write('<a href="'+urlLang+'=en" title="English">');
						document.write('<img src="/training/assets/img/icon_flag_uk.jpg" alt="...">');
						document.write('</a>');
						document.write('</span>');
					</script>
				</form>
			</div>
		</div>
	</div>
</div>
</duv>

