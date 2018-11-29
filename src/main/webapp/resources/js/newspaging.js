var data

$(function(){
	getData(1);
	$("#currentPage").html(data.currentPage);
	$("#totalPage").html(data.totalNews / data.pageCount);
	$("#count").html(data.pageCount);
	showData();
});

function previousPage(){
	if(data.currentPage == 1){
		alert("已经在第一页，无法翻页！");
		return ;
	}
	data.currentPage -= 1;
	getData(data.currentPage);
	$("#currentPage").html(data.currentPage);
	showData();
}

function nextPage(){
	if(data.currentPage >= (data.totalNews / data.pageCount)){
		alert("已经在最后一页，无法翻页！");
		return ;
	}
	data.currentPage += 1;
	getData(data.currentPage);
	$("#currentPage").html(data.currentPage);
	showData();
}

function jumpPage(){
	var jumpPage = $('#paging input[name=jumoPage]').val();
	if(jumpPage > (data.totalNews / data.pageCount)){
		alert("超出页面总数！");
		return ;
	}
	getData(jumpPage);
	$("#currentPage").html(data.currentPage);
	showData();
	
}

function getData(currentPage){
	var querNewsurl = "/ik/newsadm/newspaging/";
	if(currentPage === null){
		currentPage = 1;}
	$.ajax({
		type:"get",
		url:querNewsurl + currentPage,
		async:false,
		dataType:"json",
		success:function(da){
			data = da
		}
	})
}

function showData(){
	var temp = "<tr><th>资讯ID</th><th>资讯标题</th><th>资讯内容</th><th>编辑日期</th><th>资讯图片</th></tr>";
	var t = "</td><td>";
	data.newsList.map(function(item,index){
		temp += "<tr><td>" + item.newsId + t + item.newsTitle +  t + item.newsArticle 
		+  t + getDate(item.newsDate) + t + item.newsImg + "</td></tr>";
	});
	$("#display").html(temp);	
}
function getDate(tm){
	var time = new Date(parseInt(tm)).toLocaleString();
	return time;
}
