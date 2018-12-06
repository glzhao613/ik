$(function() {
	var count = 1;
	var pageSize = 4;
	var listUrl = '/ik/courseadmin/list';
	var pageNum = 1;

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var btemp='';
				data.courseList.map(function(item,index) {
					var temp=""+"<img src='"+item.courseImg+"'>";
					var btemp=""+"<h5>课程名称：'"+item.courseName+"'</h5><h5>授课老师：'"+item.courseTeacher.teacherName+"'</h5><h5>课程类型：'"+item.courseType.courseTypeName+"'</h5><h5>课程时长：'"+item.courseHour+"'</h5><h5>课程价格：'"+item.coursePrice+"'</h5><p>'"+item.courseDes+"'</p>";
					$("#courimg").html(temp);
					$("#cc").html(btemp);
				});

			} else {
				alert(data.errMsg);
			}
		});
	}


	
	
});
