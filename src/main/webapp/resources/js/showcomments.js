$(function() {
	var count = 1;
	var pageSize = 3;
	var pageNum = 1;
	var listUrl = '/ik/commentsadmin/list';
	var setUrl='/ik/commentsadmin/setcourseid';
	var initcourseurl = "/ik/courseadmin/courselist";
	var delUrl='/ik/commentsadmin/delcomments';
	initCourse();

	function initCourse() {
		$.getJSON(initcourseurl, function(data) {
			if (data.success) {
				var temp = "";
				data.courselist.map(function(item, index) {
					temp += "<option value='" + item.courseId + "'>"
							+ item.courseName + "</option>";

				});
				$("#courselist").html(temp);

			}

		});

	}
	
	$("#login-btn").click(function() {
		$.ajax({
			async : false,
			cache : false,
			type : 'post',
			dataType : 'json',
			url : setUrl,
			data : {
				courseid : $("#courselist option:selected").val()
			},
			success : function(data) {
				if (data.success) {
					addItems(pageSize, pageNum);
				} else {
					alert(data.errMsg);
					window.location.href = "/ik/comments/showcomments";
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
				var htemp=''+'<tr><th>ID</th><th>评论者</th><th>评论内容</th><th>评论时间</th><th>操作</th></tr>';
				$('#thead').html(htemp);
				var btemp='';
				data.list.map(function(item,index) {
					btemp+='<tr><td>'+item.commentId+'</td><td>'+item.commentUser.userAccount+'</td><td>'+item.commentArticle+'</td><td>'+getDate(item.commentDate)+'</td><td><input type="button" value="删除" class="delbtn" id="'+item.commentId+'"></td></tr>';
				});
				$('#tbody').html(btemp);
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
	
	$('#tbody').on('click','.delbtn',function() {
		var id=$(this).attr('id');
		var formData = new FormData();
		formData.append('commentid', id);
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
					window.location.href ='/ik/comments/showcomments';
					
				} else {
					alert(data.errMsg);
					window.location.href ='/ik/comments/showcomments';
				}
			}
		});

	});
	
	
	
});
