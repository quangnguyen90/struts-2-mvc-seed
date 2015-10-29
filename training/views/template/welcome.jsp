<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-8 col-md-8">
			<!-- SHOW THE LASTEST NEWS -->
			<div class="jumbotron">
				<h3 id="lastestNewsTitle"><s:property value="%{news.title}"/></h3>
				<div class="thumbnail">
					<img id="lastestNewsImg" src='<s:property value="%{contextPath + imgAURL}"/>' alt="Responsive image" alt="Responsive image" class="img-responsive img-rounded" style="max-width: 190px; max-height: 190px;">
				</div>
				<p id="lastestNewsBrief"><s:property value="%{news.brief}"/></p>
				<p>
					<a id="lastestNewsUrl" class="btn btn-primary btn-lg" href="news-detail.html?newsId=<s:property value="%{news.id}"/>" role="button">Detail</a>
				</p>
			</div>
		</div>
		<div class="col-sm-4 col-md-4">
			<div class="list-group">
			<!-- SHOW TOP 8 NEWS -->
			<a href="javascript:void(0)" class="list-group-item active"> Hot News </a> 
			<%-- <s:set var="rowPrinted" value="0"/> --%>
			<s:iterator value="top8NewsResults" var="topNews">
				<%-- <s:if test="#rowPrinted <8" > --%>
					<a  href="javascript:void(0)" 
						class="top8NewsItem list-group-item" 
						data-newsId='<s:property value="%{id}"/>' 
						data-newsTitle='<s:property value="%{title}"/>'
						data-newsBrief='<s:property value="%{brief}"/>'
						data-newsImg='<s:property value="%{contextPath + avatar}"/>'><s:property value="title" />
					</a>
					<%-- <s:set var="rowPrinted" value="%{#rowPrinted + 1}"/>
				</s:if> --%>
			</s:iterator>
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<legend>
					<h2>News</h2>
				</legend>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<!-- SHOW ALL NEWS WITH STAUS: ON -->
				<s:iterator value="newsResults" var="news">
					<div class="col-sm-6 col-md-4">
						<div class="thumbnail">
							<img src='<s:property value="%{contextPath + avatar}"/>' alt="Responsive image" alt="Responsive image" class="img-responsive img-rounded" style="max-width: 170px; max-height: 170px;">
							<div class="caption">
								<h3><s:property value="title" /></h3>
								<p><s:property value="brief" /></p>
								<p>
									<a href="news-detail.html?newsId=<s:property value="%{id}"/>" class="btn btn-primary" role="button">Detail</a> 
								</p>
							</div>
						</div>
					</div>
				</s:iterator>
			</div>
		</div>
		<div id="DividePage" class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<b>Go To Page</b>
			<ul class="pagination">
				<s:iterator value="lstPage" id="lstPage" var="p" status="ps">
					<li>
						<s:if test="#p!=trang">
							<strong>
								<s:a onclick="CngClass(this);" href="home.html?numberPage=%{#ps.count}"><s:property value="#p" /></s:a>
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
<!-- /content -->

<!-- Javascript -->
<script type="text/javascript" src="<s:url value="/assets/js/news/news.js"/>"></script>
<script type="text/javascript" src="<s:url value="/assets/js/news/newsEvent.js"/>"></script>
<!-- /Javascript -->