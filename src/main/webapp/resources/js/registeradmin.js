/**
 * 
 */
$(function() {
	var adminregisterurl = '/ik/adminad/adminregister';
	$('#register-btn').click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : adminregisterurl,
			data : {
				adminaccount : $('input[name=adminaccount]').val(),
				adminpwd : $('input[name=password]').val(),
				moduleid : $('input[name=moduleid]').val()
			},
			success : function(data) {
				switch (data.success){
				case 1:
					alert('管理员注册成功');
					window.location.href ='/ik/admin/showadmin';
					break;
				case -1:
					alert(data.errMsg);
					break;
				case -2:
					alert(data.errMsg);
					break;
				}
			}
		});
	});

});
