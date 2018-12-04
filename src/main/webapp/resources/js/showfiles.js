$(function() {
	var count = 1;
	var pageSize = 4;
	var listUrl = '/ik/filesadmin/list';
	var delUrl='/ik/filesadmin/delfiles';
	var setUrl='/ik/filesadmin/setfilesid';
	var pageNum = 1;

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var htemp=''+'<tr><th>ID</th><th>资源名</th><th>资源类型</th><th>所属课程</th><th>操作</th></tr>';
				$('#thead').html(htemp);
				var btemp='';
				data.list.map(function(item,index) {
					btemp+='<tr><td>'+item.fileId+'</td><td>'+item.fileName+'</td><td>'+item.fileType.fileTypeName+'</td><td>'+item.fileCourse.courseName+'</td><td><input type="button" value="编辑" class="edit" id="'+item.fileId+'">&nbsp;<input type="button" value="删除" class="delbtn" id="'+item.fileId+'"></td></tr>';
				});
				$('#tbody').html(btemp);
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
	
	$('#tbody').on('click','.delbtn',function() {
		var id=$(this).attr('id');
		var formData = new FormData();
		formData.append('filesid', id);
		$.ajax({
			url : delUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert('删除成功');
					window.location.href ='/ik/files/showfiles';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/files/showfiles';
				}
			}
		});

	});
	
	$('#tbody').on('click','.edit',function() {
		var id=$(this).attr('id');
		var formData = new FormData();
		formData.append('filesid', id);
		$.ajax({
			url : setUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ='/ik/files/updatefiles';
					
				}
			}
		});

	});

});
