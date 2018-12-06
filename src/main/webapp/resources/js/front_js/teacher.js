$(function() {
	var count = 1;
	var pageSize = 1;
	var listUrl = '/ik/teacheradmin/list';
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
				var btemp='';
				data.teacherList.map(function(item,index) {
					btemp+='<div style="display:inline-block; width:200px; height:350px;"><img id="teacherimg" src="'+item.teacherImg+'" class="'+item.teacherId+'" style="width:200x; height:350px;"></div>';
				});
				$('#showteacher').html(btemp);
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
	
	
	$('#showteacher').on('click',"#teacherimg",function() {
		var teacherid=$(this).attr('class');
		var formData = new FormData();
		alert(teacherid);
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
