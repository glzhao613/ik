var data

$(function(){
	getData(1);
	$("#currentPage").html(data.currentPage);
	$("#totalPage").html(Math.ceil(data.companytotal / data.pageCount));
	$("#count").html(data.pageCount);
	showData();
	
	$('#tbody').on('click','.delbtn',function() {
		var companydeleteurl = "/ik/companyadm/companydelete";
		var companyid=$(this).attr('id');
		var formData = new FormData();
		formData.append('companyid', companyid);
		$.ajax({
			url : companydeleteurl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert('删除成功');
					window.location.href ='/ik/company/companyman';	
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/company/companyman';
				}
			}
		});

	});
	
	$('#tbody').on('click','.edit',function() {
		var setidurl = "/ik/companyadm/setid";
		var companyid=$(this).attr('id');
		var formData = new FormData();
		formData.append('companyid', companyid);
		$.ajax({
			url : setidurl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ='/ik/company/companyupdate';	
				}else{
					alert("出现错误！");
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
	if(data.currentPage >= (Math.ceil(data.companytotal / data.pageCount))){
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
	if(jumpPage > (Math.ceil(data.companytotal / data.pageCount))){
		alert("超出页面总数！");
		return ;
	}
	getData(jumpPage);
	$("#currentPage").html(data.currentPage);
	showData();
}

function getData(currentPage){
	var quercompanyurl = "/ik/companyadm/companyman/";
	if(currentPage === null){
		currentPage = 1;}
	$.ajax({
		type:"get",
		url:quercompanyurl + currentPage,
		async:false,
		dataType:"json",
		success:function(da){
			data = da
		}
	})
}

function showData(){
	var htemp = "<tr><th>公司ID</th><th>公司名字</th><th>公司电话</th><th>公司邮箱</th><th>公司地址</th><th>公司信息</th><th>信息操作</th></tr>";
	var t = "</td><td>";
	$('#thead').html(htemp);
	var btemp = '';
	data.companylist.map(function(item,index){
		btemp += "<tr><td>" + item.companyId + t + item.companyName 
		+ t + item.companyTel + t + item.companyEmail + t + item.companyAddress + t + item.companyDes + t
		+ '<input type="button" value="编辑" class="edit" id="' + item.companyId 
		+ '">&nbsp;<input type="button" value="删除" class="delbtn" id="'+item.companyId+'"></td></tr>';
	});
	$("#tbody").html(btemp);	
}