$(function(){
	var updatecompanyurl = "/ik/companyadm/companyupdate";
	initCompanyImg();
	initCompany();
	
	$('#update-btn').click(function() {
		var companyimg = $('#companyimg option:selected').val();
		var companyname = $('input[name=companyname]').val();
		var companytel = $('input[name=companytel]').val();
		var companyemail = $('input[name=companyemail]').val();
		var companyaddress = $('input[name=companyaddress]').val();
		var companydes = $('input[name=companydes]').val();
		
		var formData = new FormData();
		formData.append('companyimg',companyimg);
		formData.append('companyname',companyname);
		formData.append('companytel',companytel);
		formData.append('companyemail',companyemail);
		formData.append('companyaddress',companyaddress);
		formData.append('companydes',companydes);
		
		$.ajax({
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			type : 'post',
			dataType : 'json',
			url : updatecompanyurl,
			data : formData,
			success : function(data){
				if(data.success){
					alert("添加成功！");
					window.location.href ="/ik/company/companyman ";
				}else{
					alert(data.errMsg);
					window.location.href ="/ik/company/companyaddman ";
				}
			}
		});
	});
	
});

function initCompanyImg(){
	var companyimg = "/ik/companyimgadm/companyimg";
	$.getJSON(companyimg,function(data){
		var temp = "";
		data.companyimglist.map(function(item,index){
			temp += "<option value='" + item.companyImgId + "'>"
			+ item.companyImgDes + "</option>";
		});
		$("#companyimg").html(temp);
	});
}

function initCompany(){
	var setcompanyupdateurl = "/ik/companyadm/setupdateid";
	$.getJSON(setcompanyupdateurl,function(data){
		$("#companyname").attr("value",data.updatecompany.companyName);
		$("#companytel").attr("value",data.updatecompany.companyTel);
		$("#companyemail").attr("value",data.updatecompany.companyEmail);
		$("#companyaddress").attr("value",data.updatecompany.companyAddress);
		$("#companydes").attr("value",data.updatecompany.companyDes);
	});
}