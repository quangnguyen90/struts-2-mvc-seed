//Constructor
function News(){
	
}
//function
News.prototype = {
		getDetailNews : function(title, imgAURL, brief, id){
			$("#lastestNewsTitle").text(title);
			$("#lastestNewsBrief").text(brief);
			document.getElementById("lastestNewsImg").setAttribute("src", imgAURL);
			document.getElementById("lastestNewsUrl").setAttribute("href", "news-detail.html?newsId="+id);
		},
}

//Instance
newsIs = new News();