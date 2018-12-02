/**
 * 
 */
$(function() {
	var addcourseurl = "/ik/courseadmin/updatecourse";
	var initcoursetypeurl="/ik/coursetypeadmin/coursetypelist";
	var initteacherurl="/ik/teacheradmin/teacherlist";
	initCoursetype();
	initTeacher();
	function initCoursetype() {
		$.getJSON(initcoursetypeurl, function(data) {
			if (data.success) {
				var temp = "";
				data.coursetypelist.map(function(item, index) {
					temp += "<option value='" + item.courseTypeId + "'>"
							+ item.courseTypeName + "</option>";

				});
				$("#coursetypelist").html(temp);

			}

		});

	}
	
	function initTeacher() {
		$.getJSON(initteacherurl, function(data) {
			if (data.success) {
				var temp = "";
				data.teacherlist.map(function(item, index) {
					temp += "<option value='" + item.teacherId + "'>"
							+ item.teacherName + "</option>";

				});
				$("#teacherlist").html(temp);

			}

		});

	}
	
	$('#updatecourse-btn').click(function() {
		courseteacher = $("#teacherlist option:selected").val();
		coursename = $('input[name=coursename]').val();
		coursedes = $('input[name=coursedes]').val();
		courseprice = $('input[name=courseprice]').val();
		coursehour = $('input[name=coursehour]').val();
		coursety = $("#coursetypelist option:selected").val();
		var cimg = $('input[name=cimg]')[0].files[0];
		var formData = new FormData();
		formData.append('courseteacher', courseteacher);
		formData.append('coursename',coursename);
		formData.append('coursedes',coursedes);
		formData.append('courseprice',courseprice);
		formData.append('coursehour', coursehour);
		formData.append('coursety',coursety);
		formData.append('cimg',cimg);
		$.ajax({
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			type : 'post',
			dataType : 'json',
			url : addcourseurl,
			data : 	formData,
			success : function(data) {
				if (data.success) {
					alert("课程修改成功");
					window.location.href ='/ik/course/update';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/course/update';
				}
			}
		});
	});

});
