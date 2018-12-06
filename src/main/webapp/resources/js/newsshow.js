var data

$(function(){
	getData(1);
	$("#currentPage").html(data.currentPage);
	$("#totalPage").html(Math.ceil(data.totalNews / data.pageCount));
	$("#count").html(data.pageCount);
	showData();
	
	$('#tbody').on('click','.delbtn',function() {
		var newsdeleteurl = "/ik/newsadm/deletenews";
		var newsid=$(this).attr('id');
		var formData = new FormData();
		formData.append('newsid', newsid);
		$.ajax({
			url : newsdeleteurl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert('删除成功');
					window.location.href ='/ik/news/newspaging';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/news/newspaging';
				}
			}
		});

	});
	
	$('#tbody').on('click','.edit',function() {
		var setidurl = "/ik/newsadm/setid";
		var newsid=$(this).attr('id');
		var formData = new FormData();
		formData.append('newsid', newsid);
		$.ajax({
			url : setidurl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ='/ik/news/newsupdate';	
				}
			}
		});

	});
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
	var htemp = "<tr><th>资讯ID</th><th>资讯标题</th><th>资讯内容</th><th>编辑日期</th><th>资讯操作</th></tr>";
	var t = "</td><td>";
	$('#thead').html(htemp);
	var btemp = '';
	data.newsList.map(function(item,index){
		btemp += "<tr><td>" + item.newsId + t + item.newsTitle +  t + item.newsArticle 
		+  t + getDate(item.newsDate) + t + '<input type="button" value="编辑资讯" class="edit" id="' + item.newsId 
		+ '">&nbsp;<input type="button" value="删除资讯" class="delbtn" id="'+item.newsId+'"></td></tr>';
	});
	$("#tbody").html(btemp);	
}


function getDate(tm){
	var time = new Date(parseInt(tm)).toLocaleString().substr(0,10);
	return time;
}