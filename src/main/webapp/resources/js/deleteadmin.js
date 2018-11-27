/**
 * 
 */
$(function() {
	var admindeleteurl = '/ik/adminad/admindelete';
	$('#delete-btn').click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : admindeleteurl,
			data : {
				adminaccount : $('input[name=adminaccount]').val()
			},
			success : function(data) {
				switch (data.success){
				case 1:
					alert('管理员删除成功');
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
