$(function() {
	var count = 1;
	var pageSize = 4;
	var listUrl = '/ik/modulead/list';
	var delUrl='/ik/modulead/moduledelete';
	var setUrl='/ik/modulead/setid';
	var pageNum = 1;

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var htemp=''+'<tr><th>模块ID</th><th>模块名称</th><th>模块链接</th><th>管理模块链接</th><th>模块操作</th></tr>';
				$('#thead').html(htemp);
				var btemp='';
				data.moduleList.map(function(item,index) {
					btemp+='<tr><td>'+item.moduleId+'</td><td>'+item.moduleName+'</td><td>'+item.moduleUrl+'</td><td>'+item.moduleManageUrl+'</td><td><input type="button" value="编辑模块" class="edit" id="'+item.moduleId+'">&nbsp;<input type="button" value="删除" class="delbtn" id="'+item.moduleId+'"></td></tr>';
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
		formData.append('moduleid', id);
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
					window.location.href ='/ik/module/showmodule';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/module/showmodule';
				}
			}
		});

	});
	
	$('#tbody').on('click','.edit',function() {
		var moduleid=$(this).attr('id');
		var formData = new FormData();
		formData.append('moduleid', moduleid);
		$.ajax({
			url : setUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ='/ik/module/update';
					
				}
			}
		});

	});
	
	
	
});
