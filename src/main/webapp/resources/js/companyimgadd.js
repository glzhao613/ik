$(function(){
	var companyimgaddurl = "/ik/companyimgadm/companyimgadd";
	
	$('#add-btn').click(function(){
		var companyimg = $('input[name=companyimg]')[0].files[0];
		var companyimgdes = $('input[name=companyimgdes]').val();
		
		var formData = new FormData();
		formData.append('companyimg',companyimg);
		formData.append('companyimgdes',companyimgdes);
		
		$.ajax({
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			type : 'post',
			dataType : 'json',
			url : companyimgaddurl,
			data : formData,
			success : function(data){
				if(data.success){
					alert("添加成功！");
					window.location.href ="/ik/companyimg/companyimgadd ";
				}else{
					alert(data.errMsg);
					window.location.href ="/ik/companyimg/companyimgadd ";
				}
			}
		});
	});
});