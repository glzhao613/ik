$(function() {
	var count = 1;
	var pageSize = 4;
	var listUrl = '/ik/useradmin/list';
	var delUrl='/ik/useradmin/deluser';
	var setUrl='/ik/useradmin/setid';
	var pageNum = 1;

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var htemp=''+'<tr><th>ID</th><th>账户</th><th>昵称</th><th>联系电话</th><th>操作</th></tr>';
				$('#thead').html(htemp);
				var btemp='';
				data.userList.map(function(item,index) {
					btemp+='<tr><td>'+item.userId+'</td><td>'+item.userAccount+'</td><td>'+item.userName+'</td><td>'+item.userTel+'</td><td><input type="button" value="编辑课程" class="edit" id="'+item.userId+'">&nbsp;<input type="button" value="删除" class="delbtn" id="'+item.userAccount+'"></td></tr>';
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
		var account=$(this).attr('id');
		var formData = new FormData();
		formData.append('useraccount', account);
		$.ajax({
			url : delUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					alert(data.useraccount+'删除成功');
					window.location.href ='/ik/user/showuser';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/user/showuser';
				}
			}
		});

	});
	
	$('#tbody').on('click','.edit',function() {
		var userid=$(this).attr('id');
		var formData = new FormData();
		formData.append('userid', userid);
		$.ajax({
			url : setUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					window.location.href ='/ik/user/usercourse';
					
				}
			}
		});

	});
	
	
	
});
