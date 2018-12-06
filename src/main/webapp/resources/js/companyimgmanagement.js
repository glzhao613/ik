var data

$(function(){
	getData(1);
	$("#currentPage").html(data.currentPage);
	$("#totalPage").html(Math.ceil(data.companyimgtotal / data.pageCount));
	$("#count").html(data.pageCount);
	showData();
	
	$('#tbody').on('click','.delbtn',function() {
		var companyimgdeleteurl = "/ik/companyimgadm/companyimgdelete";
		var companyimgid=$(this).attr('id');
		var formData = new FormData();
		formData.append('companyimgid', companyimgid);
		$.ajax({
			url : companyimgdeleteurl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert('删除成功');
					window.location.href ='/ik/companyimg/companyimgman';	
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/companyimg/companyimgman';
				}
			}
		});

	});
	
	$('#tbody').on('click','.edit',function() {
		var setidurl = "/ik/companyimgadm/setid";
		var companyimgid=$(this).attr('id');
		var formData = new FormData();
		formData.append('companyimgid', companyimgid);
		$.ajax({
			url : setidurl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ='/ik/companyimg/companyimgupdate';	
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
	if(data.currentPage >= (Math.ceil(data.companyimgtotal / data.pageCount))){
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
	if(jumpPage > (Math.ceil(data.companyimgtotal / data.pageCount))){
		alert("超出页面总数！");
		return ;
	}
	getData(jumpPage);
	$("#currentPage").html(data.currentPage);
	showData();
}

function getData(currentPage){
	var quercompanyimgurl = "/ik/companyimgadm/companyimgman/";
	if(currentPage === null){
		currentPage = 1;}
	$.ajax({
		type:"get",
		url:quercompanyimgurl + currentPage,
		async:false,
		dataType:"json",
		success:function(da){
			data = da
		}
	})
}

function showData(){
	var htemp = "<tr><th>图片ID</th><th>图片路径</th><th>图片描述</th><th>图片操作</th></tr>";
	var t = "</td><td>";
	$('#thead').html(htemp);
	var btemp = '';
	data.companyimglist.map(function(item,index){
		btemp += "<tr><td>" + item.companyImgId + t + item.companyImgPath 
		+ t + item.companyImgDes + t + '<input type="button" value="编辑图片" class="edit" id="' + item.companyImgId 
		+ '">&nbsp;<input type="button" value="删除图片" class="delbtn" id="'+item.companyImgId+'"></td></tr>';
	});
	$("#tbody").html(btemp);	
}