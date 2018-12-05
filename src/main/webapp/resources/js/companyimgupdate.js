$(function(){
	var updatecompanyimgurl = "/ik/companyimgadm/companyimgupdate";
	initCompanyimgDes();
	
	$('#update-btn').click(function() {
		var companyimgdes = $('input[name=companyimgdes]').val();
		var formData = new FormData();
		formData.append('companyimgdes',companyimgdes);
		$.ajax({
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			type : 'post',
			dataType : 'json',
			url : updatecompanyimgurl,
			data : formData,
			success : function(data){
				if(data.success){
					alert("修改成功！");
					window.location.href = "/ik/companyimg/companyimgman";
				}else{
					alert(data.errMsg);
					window.location.href = "/ik/companyimg/companyimgman";
				}
			}
		});
	});
});

function initCompanyimgDes(){
	var setcompanyimgupdateurl = "/ik/companyimgadm/setupdateid";
	$.getJSON(setcompanyimgupdateurl,function(data){
		$("#companyimgdes").attr("value",data.updatecompanyimg.companyImgDes);
	});
}