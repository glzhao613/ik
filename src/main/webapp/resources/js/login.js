/**
 * 
 */
$(function() {
	var userloginurl = '/ik/user/userlogin';
	$('#login-btn').click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : userloginurl,
			data : {
				userAccount : $('input[name=account]').val(),
				userPwd : $('input[name=pwd]').val()
			},
			success : function(data) {
				if (data.success) {
					alert('登录成功');
					window.location.href ='';
				} else {
					alert(data.errMsg);
				}
			}
		});
	});

});
