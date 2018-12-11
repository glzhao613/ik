$(function() {
	var count = 1;
	var pageSize = 2;
	var pageNum = 1;
	var listUrl = '/ik/commentsadmin/list';
	var addUrl="/ik/commentsadmin/addcomments";
	
	
	$("#subbtn").click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : addUrl,
			data : {
				article : $("#comment").val()
			},
			success : function(data) {
				if (data.success) {
					alert("评论成功");
					addItems(pageSize, pageNum);
				} else {
					alert(data.errMsg);
					window.location.href = "/ik/pre/t_detail";
				}
			}
		});

	});

	// 加载第一页
	addItems(pageSize, pageNum);

	function addItems(pageSize, pageIndex) {

		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize;
		$.getJSON(url, function(data) {
			if (data.success) {
				count=data.count;
				var btemp='';
				data.list.map(function(item,index) {
					btemp+="<table border='0px' width='100%' bordercolor='#f1f1f1' class='tablecss'><tr><td width='80px' rowspan='2' class='tdimg'><img src='"+item.commentUser.userImg+"'></td><td>"+item.commentUser.userName+"</td><td class='detail-time'>"+getDate(item.commentDate)+"</td></tr><tr><td colspan='2' class='tdheight'>"+item.commentArticle+"</td></tr></table>";
				});
				$('#clist').html(btemp);
				$('#index').html('第'+pageNum+'页');
				$('#count').html('共'+count+'页');
			} else {
				alert(data.errMsg);
			}
		});
	}
	
	function getDate(tm){
		var time = new Date(parseInt(tm)).toLocaleString();
		return time;
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
