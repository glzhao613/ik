/**
 * 
 */
$(function() {
	var userloginurl = '/ik/useradmin/userlogin';
	$('#login-btn').click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : userloginurl,
			data : {
				userAccount : $('input[name=account]').val(),
				userPwd : $('input[name=pwd]').val(),
				kaptcha:$('input[name=kaptcha]').val()
			},
			success : function(data) {
				if (data.success) {
					alert('登录成功');
					window.location.href ='/ik/index.html';
				} else {
					alert(data.errMsg);
					$('#kaptcha_img').click();
				}
			}
		});
	});

});
