/**
 * 
 */
$(function() {
	var updatecoursetypeurl = "/ik/coursetypeadmin/updatecoursetype";
	
	$('#updatecoursetype-btn').click(function() {
		coursetypename = $('input[name=coursetypename]').val();
		var formData = new FormData();
		formData.append('coursetypename', coursetypename);
		$.ajax({
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			type : 'post',
			dataType : 'json',
			url : updatecoursetypeurl,
			data : 	formData,
			success : function(data) {
				if (data.success) {
					alert("课程类型修改成功");
					window.location.href ='/ik/coursetype/update';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/coursetype/update';
				}
			}
		});
	});

});
