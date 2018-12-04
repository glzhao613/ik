var data

$(function(){
	getData(1);
	$("#currentPage").html(data.currentPage);
	$("#totalPage").html(Math.ceil(data.newstypetotal / data.pageCount));
	$("#count").html(data.pageCount);
	showData();
	
	$('#tbody').on('click','.delbtn',function() {
		var newstypedeleteurl = "/ik/newstypeadm/newstypedelete";
		var newstypeid=$(this).attr('id');
		var formData = new FormData();
		formData.append('newstypeid', newstypeid);
		$.ajax({
			url : newstypedeleteurl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert('删除成功');
					window.location.href ='/ik/newstype/newstypeman';	
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/newstype/newstypeman';
				}
			}
		});

	});
	
	$('#tbody').on('click','.edit',function() {
		var setidurl = "/ik/newstypeadm/setid";
		var newstypeid=$(this).attr('id');
		var formData = new FormData();
		formData.append('newstypeid', newstypeid);
		$.ajax({
			url : setidurl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ='/ik/newstype/newstypeupdate';	
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
	if(data.currentPage >= (Math.ceil(data.newstypetotal / data.pageCount))){
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
	if(jumpPage > (Math.ceil(data.newstypetotal / data.pageCount))){
		alert("超出页面总数！");
		return ;
	}
	getData(jumpPage);
	$("#currentPage").html(data.currentPage);
	showData();
}

function getData(currentPage){
	var quernewstypeurl = "/ik/newstypeadm/newstypeman/";
	if(currentPage === null){
		currentPage = 1;}
	$.ajax({
		type:"get",
		url:quernewstypeurl + currentPage,
		async:false,
		dataType:"json",
		success:function(da){
			data = da
		}
	})
}

function showData(){
	var htemp = "<tr><th>资讯类型ID</th><th>资讯类型名称</th><th>资讯类型操作</th></tr>";
	var t = "</td><td>";
	$('#thead').html(htemp);
	var btemp = '';
	data.newstypelist.map(function(item,index){
		btemp += "<tr><td>" + item.newsTypeId + t + item.newsTypeName 
		+ t + '<input type="button" value="编辑资讯类型" class="edit" id="' + item.newsTypeId 
		+ '">&nbsp;<input type="button" value="删除资讯" class="delbtn" id="'+item.newsTypeId+'"></td></tr>';
	});
	$("#tbody").html(btemp);	
}