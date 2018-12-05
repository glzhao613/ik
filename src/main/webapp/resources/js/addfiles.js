/**
 * 
 */
$(function() {
	var addurl = "/ik/filesadmin/addfiles";
	var initcourseurl = "/ik/courseadmin/courselist";
	var initfiletypeurl="/ik/filetypeadmin/filetypelist";
	
	
	initCourse();
	initFileType();

	function initCourse() {
		$.getJSON(initcourseurl, function(data) {
			if (data.success) {
				var temp = "";
				data.courselist.map(function(item, index) {
					temp += "<option value='" + item.courseId + "'>"
							+ item.courseName + "</option>";

				});
				$("#courselist").html(temp);
			}

		});

	}
	
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
			url : addurl,
			data : {
				filesname : $("#name").val(),
				filespath:$("#url").val(),
				courseid:$("#courselist option:selected").val(),
				filetypeid:$("#filetypelist option:selected").val()
			},
			success : function(data) {
				if (data.success) {
					alert("添加成功");
					window.location.href = "/ik/files/showfiles";
				} else {
					alert(data.errMsg);
					window.location.href = "/ik/files/addfiles";
				}
			}
		});

	});

});
