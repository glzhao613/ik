/**
 * 
 */
$(function() {
	var moduleupdateurl = '/ik/modulead/moduleupdate';
	$('#update-btn').click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : moduleupdateurl,
			data : {
				modulename : $('input[name=modulename]').val(),
				moduleurl : $('input[name=moduleurl]').val(),
				modulemanageurl : $('input[name=modulemanageurl]').val(),
			},
			success : function(data) {
				switch (data.success){
				case 1:
					alert('模块更新成功');
					window.location.href ='/ik/module/update';
					break;
				case -1:
					alert(data.errMsg);
					break;
				}
			}
		});
	});

});
