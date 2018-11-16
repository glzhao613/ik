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
				adminAccount : $('input[name=adminname]').val(),
				adminPwd : $('input[name=password]').val(),
				adminType : $('input[name=admintype]').val()
			},
			success : function(data) {
				switch (data.success){
				case 1:
					alert('管理员注册成功');
					window.location.href ='';
				case -1:
					alert(data.errMsg);
				case -2:
					alert(data.errMsg);
				}
			}
		});
	});

});
