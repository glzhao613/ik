/**
 * 
 */
var data

$(function(){
	initTopDisplayNews()
	initNewsByDate();

	getData(1);
	$("#currentPage").html(data.currentPage);
	$("#totalPage").html(Math.ceil(data.totalNews / data.pageCount));
	$("#count").html(data.pageCount);
	initBottomNews();
	
	$('#topdisplay').on('click','.topdisplayimg',function() {
		var setidurl = "/ik/newsadm/setid";
		var newsid = $(this).attr("id");
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
					window.location.href = "/ik/news/dnews";
				}
			}
		});
	});
	
	$('#newsbydate').on('click','.newsdetail',function() {
		var setidurl = "/ik/newsadm/setid";
		var newsid = $(this).attr("id");
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
					window.location.href = "/ik/news/dnews";
				}
			}
		});
	});
	
	$('#bottomnewsdis').on('click','.bottomnews',function() {
		var setidurl = "/ik/newsadm/setid";
		var newsid = $(this).attr("id");
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
					window.location.href = "/ik/news/dnews";
				}
			}
		});
	});
});

function initTopDisplayNews(){
	var getnewsbydate = "/ik/newsadm/getnewsbydate";	
	$.getJSON(getnewsbydate,function(data){
		var h_temp = '<li><a href="/ik/news/dnews" class="topdisplayimg" id="';
		var b_temp = '" ><img src="';
		var t_temp = '" width="500" height="280" alt="展示图片" /></a></li>';
		var temp = '';
		
		data.newslist.map(function(item,index){
			temp += h_temp + item.newsId + b_temp + item.newsImg + t_temp;
		});
		$("#topdisplay").html(temp);
	});
}

function initNewsByDate(){
	var getnewsbydate = "/ik/newsadm/getnewsbydate";	
	$.getJSON(getnewsbydate,function(data){
		var h_temp = '<a  class="newsdetail"  id="';
		var b_temp = '"><li>';
		var t_temp = '</li></a>';
		var temp = '';
		
		data.newslist.map(function(item,index){
			temp += h_temp + item.newsId + b_temp + item.newsTitle + t_temp;
		});
		$("#newsbydate").html(temp);
	});
}

function previousPage(){
	if(data.currentPage == 1){
		alert("已经在第一页，无法翻页！");
		return ;
	}
	data.currentPage -= 1;
	getData(data.currentPage);
	$("#currentPage").html(data.currentPage);
	initBottomNews();
}

function nextPage(){
	if(data.currentPage >= (data.totalNews / data.pageCount)){
		alert("已经在最后一页，无法翻页！");
		return ;
	}
	data.currentPage += 1;
	getData(data.currentPage);
	$("#currentPage").html(data.currentPage);
	initBottomNews();
}

function jumpPage(){
	var jumpPage = $('#paging input[name=jumoPage]').val();
	if(jumpPage > (data.totalNews / data.pageCount)){
		alert("超出页面总数！");
		return ;
	}
	getData(jumpPage);
	$("#currentPage").html(data.currentPage);
	initBottomNews();
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

function initBottomNews(){
	var img_h_temp = '<td><img src="';
	var img_t_temp = '"></td>';
	
	var title_h_temp = '<td><a  class="bottomnews"  id="';
	var title_m_temp = '">';
	var title_t_temp = '</a></td>';
	
	var img_temp = '';
	var title_temp = '';
	
	data.newsList.map(function(item,index){
		img_temp += img_h_temp + item.newsImg + img_t_temp;
		title_temp += title_h_temp + item.newsId + title_m_temp + item.newsTitle + title_t_temp;
	});
	
	$("#bottomnewsimg").html(img_temp);	
	$("#bottomnewstitle").html(title_temp);	
}
