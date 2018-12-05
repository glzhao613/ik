/**
 * 
 */
$(function() {
	var adduserurl = "/ik/filetypeadmin/addfiletype";

	$("#btn").click(function() {

		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : adduserurl,
			data : {
				filetypename : $("#name").val(),
			},
			success : function(data) {
				if (data.success) {
					alert("添加成功");
					window.location.href = "/ik/filetype/showfiletype";
				} else {
					alert(data.errMsg);
					window.location.href = "/ik/filetype/addfiletype";
				}
			}
		});

	});

});
