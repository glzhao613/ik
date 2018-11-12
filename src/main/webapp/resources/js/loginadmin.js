/**
 * 
 */
$(function() {
	var adminloginurl = '/ik/adminad/adminlogin';
	$('#login-btn').click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : adminloginurl,
			data : {
				adminAccount : $('input[name=adminname]').val(),
				adminPwd : $('input[name=password]').val()
			},
			success : function(data) {
				switch (data.success){
				case 0:
					alert('超级管理员登录成功');
					window.location.href ='';
				case 1:
					alert('管理员登录成功');
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
