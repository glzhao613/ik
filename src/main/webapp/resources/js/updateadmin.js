/**
 * 
 */
$(function() {
	var adminupdateurl = '/ik/adminad/adminupdate';
	$('#update-btn').click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : adminupdateurl,
			data : {
				adminaccount : $('input[name=adminaccount]').val(),
				adminpwd : $('input[name=password]').val(),
				moduleid : $('input[name=moduleid]').val(),
				adminname : $('input[name=adminname]').val(),
			},
			success : function(data) {
				switch (data.success){
				case 1:
					alert('管理员更新成功');
					window.location.href ='/ik/admin/showadmin';
					break;
				case -1:
					alert(data.errMsg);
					window.location.href ='/ik/admin/showadmin';
					break;
				}
			}
		});
	});

});
