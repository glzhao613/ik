/**
 * 
 */
$(function(){
	initNewsDetails();
});

function initNewsDetails(){
	var updatenewsurl = "/ik/newsadm/setupdateid";
	$.getJSON(updatenewsurl,function(data){
		/*$("#newsedit").html(data.admin.adminName);*/
		$("#newsdate").html(getDate(data.updatenews.newsDate));
		$("#newstitle").html(data.updatenews.newsTitle);
		$("#newsarticle").html(data.updatenews.newsArticle);
		$("#newsimg").attr("src",data.updatenews.newsImg);
	});
	
}

function getDate(tm){
	var time = new Date(parseInt(tm)).toLocaleString().substr(0,10);
	return time;
}