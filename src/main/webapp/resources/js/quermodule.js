/**
 * 
 */
$(function() {
	var modulequerurl = '/ik/modulead/modulequer';
	$('#quer-btn').click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : modulequerurl,
			data : {
				modulename : $('input[name=modulename]').val()
			},
			success : function(data) {
				switch (data.success){
				case ad.getAdminlist():
					alert('查询成功');
					window.location.href ='';
				case -1:
					alert(data.errMsg);
				}
			}
		});
	});

});
