$(function() {
	var count = 1;
	var pageSize = 10;
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
					temp+="<div class='content'><a href='"+item.filePath+"'><ul><li><b>资源名称：</b></li><li>&nbsp;&nbsp;"+item.fileName+"</li><li><b>资源类型：</b></li><li>&nbsp;&nbsp;"+item.fileType.fileTypeName+"</li><li><b>所属课程：</b></li><li>&nbsp;&nbsp;"+item.fileCourse.courseName+"</li></ul></a></div>";
				});
				$('#boxlist').html(temp);
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
