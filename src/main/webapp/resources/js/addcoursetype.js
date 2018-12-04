/**
 * 
 */
$(function() {
	var coursetypeaddurl = '/ik/coursetypeadmin/insertcoursetype';
	$('#insert-btn').click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : coursetypeaddurl,
			data : {
				coursetypename : $('input[name=coursetypename]').val()
			},
			success : function(data) {
				if (data.success) {
					alert("添加成功");
					location.href = "/ik/coursetype/insert";
				} else {
					alert(data.errMsg);
					window.location.href = "/ik/coursetype/insert";
				}
			}
		});
	});

});
