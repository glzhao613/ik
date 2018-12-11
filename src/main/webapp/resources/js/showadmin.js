$(function() {
	var count = 1;
	var pageSize = 4;
	var listUrl = '/ik/adminad/list';
	var delUrl='/ik/adminad/admindelete';
	var setUrl='/ik/adminad/setid';
	var pageNum = 1;

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var htemp=''+'<tr><th>管理员ID</th><th>管理员名称</th><th>账号</th><th>管理员类型</th><th>管理员模块</th><th>课程操作</th></tr>';
				$('#thead').html(htemp);
				var btemp='';
				data.adminList.map(function(item,index) {
					btemp+='<tr><td>'+item.adminId+'</td><td>'+item.adminName+'</td><td>'+item.adminAccount+'</td><td>'+item.adminType+'</td><td>'+item.adminModule.moduleName+'</td><td><input type="button" value="编辑管理员" class="edit" id="'+item.adminId+'">&nbsp;<input type="button" value="删除" class="delbtn" id="'+item.adminId+'"></td></tr>';
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
		formData.append('adminid', id);
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
					window.location.href ='/ik/admin/showadmin';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/admin/showadmin';
				}
			}
		});

	});
	
	$('#tbody').on('click','.edit',function() {
		var adminid=$(this).attr('id');
		var formData = new FormData();
		formData.append('adminid', adminid);
		$.ajax({
			url : setUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ='/ik/admin/update';
					
				}
			}
		});

	});
	
	
	
});
