$(function() {
	var count = 1;
	var pageSize = 2;
	var listUrl = '/ik/filesadmin/listbyuid';
	var pageNum = 1;

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var temp='';
				data.list.map(function(item,index) {
					temp+="<div class='success-bottom'><a href='"+item.filePath+"'><div class='success-bottom-rights'><h4>所属课程："+item.fileCourse.courseName+"</h4><h4>资源类型："+item.fileType.fileTypeName+"</h4><h3>资源名称："+item.fileName+"</h3></div></a><div class='clearfix'></div></div>";
				});
				$('#box').html(temp);
				$('#index').html('第'+pageNum+'页');
				$('#count').html('共'+count+'页');
			} else {
				alert(data.errMsg);
			}
		});
	}

	$('#pre').click(function() {
		if (pageNum <= 1) {
			alert("已经是第一页啦");
		} else {
			pageNum -= 1;
			addItems(pageSize, pageNum);
		}

	});

	$('#next').click(function() {
		if (pageNum >= count) {
			alert("已经是最后一页啦");
		} else {
			pageNum += 1;
			addItems(pageSize, pageNum);
		}

	});
	

});
