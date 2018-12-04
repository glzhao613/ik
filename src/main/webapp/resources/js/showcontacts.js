$(function() {
	var count = 1;
	var pageSize = 4;
	var listUrl = '/ik/contactsad/list';
	var delUrl='/ik/contactsad/contactsdelete';
	var setUrl='/ik/contactsad/setid';
	var pageNum = 1;

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var htemp=''+'<tr><th>联系人ID</th><th>联系人名称</th><th>联系人电话</th><th>联系人邮箱</th><th>联系人内容</th><th>联系人操作</th></tr>';
				$('#thead').html(htemp);
				var btemp='';
				data.contactsList.map(function(item,index) {
					btemp+='<tr><td>'+item.contactId+'</td><td>'+item.contactName+'</td><td>'+item.contactTel+'</td><td>'+item.contactEmail+'</td><td>'+item.contactArticle+'</td><td><input type="button" value="删除" class="delbtn" id="'+item.contactId+'"></td></tr>';
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
		var contactid=$(this).attr('id');
		var formData = new FormData();
		formData.append('contactid', contactid);
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
					window.location.href ='/ik/contacts/showcontacts';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/contacts/showcontacts';
				}
			}
		});

	});
		
});
