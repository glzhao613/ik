/**
 * 
 */
$(function() {
	var updateurl = '/ik/filetypeadmin/updatefiletype';
	$('#btn').click(function() {
		var name = $('#name').val();
		var formData = new FormData();
		formData.append('filetypename',name);
		$.ajax({
			url : updateurl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert("修改信息成功");
					window.location.href ='/ik/filetype/showfiletype';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/filetype/updatefiletype';
				}
			}
		});
	});

});
