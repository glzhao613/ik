/**
 * 
 */
$(function() {
	var userloginurl = '/ik/useradmin/userupdatapwd';
	$('#login-btn').click(function() {
		var pwd=$('input[name=pwd]').val();
		var newpwd=$('input[name=newpwd]').val();
		var rnewpwd=$('input[name=rnewpwd]').val();
		
		if(newpwd!=rnewpwd){
			alert("新密码前后不一致");
			return;
		}
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : userloginurl,
			data : {
				userpwd : pwd,
				newpwd:rnewpwd
			},
			success : function(data) {
				if (data.success) {
					alert('修改成功');
					window.location.href ='/ik/user/login';
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/user/updatapwd';
				}
			}
		});
	});

});
