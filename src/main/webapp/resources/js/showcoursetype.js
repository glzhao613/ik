$(function() {
	var count = 1;
	var pageSize = 4;
	var listUrl = '/ik/coursetypeadmin/list';
	var delUrl='/ik/coursetypeadmin/deletecoursetype';
	var setUrl='/ik/coursetypeadmin/setid';
	var pageNum = 1;

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var htemp=''+'<tr><th>课程类型ID</th><th>课程类型名称</th><th>课程操作</th></tr>';
				$('#thead').html(htemp);
				var btemp='';
				data.coursetypeList.map(function(item,index) {
					btemp+='<tr><td>'+item.courseTypeId+'</td><td>'+item.courseTypeName+'</td><td><input type="button" value="编辑课程" class="edit" id="'+item.courseTypeId+'">&nbsp;<input type="button" value="删除" class="delbtn" id="'+item.courseTypeId+'"></td></tr>';
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
		formData.append('coursetypeid', id);
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
					window.location.href ='/ik/coursetype/showcoursetype';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/coursetype/showcoursetype';
				}
			}
		});

	});
	
	$('#tbody').on('click','.edit',function() {
		var coursetypeid=$(this).attr('id');
		var formData = new FormData();
		formData.append('coursetypeid', coursetypeid);
		$.ajax({
			url : setUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ='/ik/coursetype/update';
					
				}
			}
		});

	});
	
	
	
});
