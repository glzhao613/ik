/**
 * 
 */
$(function(){
	initNewsDetails();
});

function initNewsDetails(){
	var updatenewsurl = "/ik/newsadm/setupdateid";
	$.getJSON(updatenewsurl,function(data){
		/*$("#newsedit").html(data.updatenews.Admin.adminId);*/
		$("#newsdate").html(getDate(data.updatenews.newsDate));
		$("#newstitle").html(data.updatenews.newsTitle);
		$("#newsarticle").html(data.updatenews.newsArticle);
	});
}

function getDate(tm){
	var time = new Date(parseInt(tm)).toLocaleString().substr(0,17);
	return time;
}