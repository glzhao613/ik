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
				adminaccount : $('input[name=adminname]').val(),
				adminpwd : $('input[name=password]').val()
			},
			success : function(data) {
				if(data.success){
					alert("管理员登陆成功");
					var manageurl=data.loginadmin
					window.location.href =manageurl;
				}else {
					alert(data.errMsg);
					window.location.href ='/ik/admin/login';
				}				
			}
		});
	});

});
