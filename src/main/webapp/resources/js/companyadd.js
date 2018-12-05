$(function(){
	var companyaddurl = "/ik/companyadm/companyadd";
	initCompanyImg();
	
	$('#add-btn').click(function(){
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
			url : companyaddurl,
			data : formData,
			success : function(data){
				if(data.success){
					alert("添加成功！");
					window.location.href ="/ik/company/companyadd ";
				}else{
					alert(data.errMsg);
					window.location.href ="/ik/company/companyadd ";
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