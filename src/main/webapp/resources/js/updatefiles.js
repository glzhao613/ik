/**
 * 
 */
$(function() {
	var updateurl = "/ik/filesadmin/updatefiles";
	var initfiletypeurl="/ik/filetypeadmin/filetypelist";
	
	initFileType();
	
	function initFileType() {
		$.getJSON(initfiletypeurl, function(data) {
			if (data.success) {
				var temp = "";
				data.filetypelist.map(function(item, index) {
					temp += "<option value='" + item.fileTypeId + "'>"
							+ item.fileTypeName + "</option>";

				});
				$("#filetypelist").html(temp);
			}

		});

	}
	

	$("#btn").click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : updateurl,
			data : {
				filesname : $("#name").val(),
				filespath:$("#url").val(),
				filetypeid:$("#filetypelist option:selected").val()
			},
			success : function(data) {
				if (data.success) {
					alert("修改成功");
					window.location.href = "/ik/files/showfiles";
				} else {
					alert(data.errMsg);
					window.location.href = "/ik/files/updatefiles";
				}
			}
		});

	});

});
