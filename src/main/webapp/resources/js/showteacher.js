$(function() {
	var count = 1;
	var pageSize = 4;
	var listUrl = '/ik/teacheradmin/list';
	var delUrl='/ik/teacheradmin/deleteteacher';
	var setUrl='/ik/teacheradmin/setid';
	var pageNum = 1;

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var htemp=''+'<tr><th>教师ID</th><th>教师名称</th><th>教师描述</th><th>教师操作</th></tr>';
				$('#thead').html(htemp);
				var btemp='';
				data.courseList.map(function(item,index) {
					btemp+='<tr><td>'+item.teacherId+'</td><td>'+item.teacherName+'</td><td>'+item.teacherDes+'</td><td><input type="button" value="编辑教师" class="edit" id="'+item.teacherId+'">&nbsp;<input type="button" value="删除" class="delbtn" id="'+item.teacherId+'"></td></tr>';
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
		var teacherid=$(this).attr('id');
		var formData = new FormData();
		formData.append('teacherid', teacherid);
		$.ajax({
			url : delUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert(data.teachername+'删除成功');
					window.location.href ='/ik/teacher/showteacher';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/teacher/showteacher';
				}
			}
		});

	});
	
	$('#tbody').on('click','.edit',function() {
		var teacherid=$(this).attr('id');
		var formData = new FormData();
		formData.append('teacherid', teacherid);
		$.ajax({
			url : setUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ='/ik/teacher/update';
					
				}
			}
		});

	});
	
	
	
});
