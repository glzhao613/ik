$(function(){
	var newstypeaddurl = "/ik/newstypeadm/newstypeadd";
	
	$('#add-btn').click(function(){
		var newstypename = $('input[name=newstypename]').val();
		var formData = new FormData();
		formData.append('newstypename',newstypename);
		
		$.ajax({
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			type : 'post',
			dataType : 'json',
			url : newstypeaddurl,
			data : formData,
			success : function(data){
				if(data.success){
					alert("添加成功！");
					window.location.href ="/ik/newstype/newstypeadd";
				}else{
					alert(data.errMsg);
					window.location.href ="/ik/newstype/newstypeadd";
				}
			}
		});
	});
});