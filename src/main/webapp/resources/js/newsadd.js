$(function(){
	var newsTypeurl = "/ik/newsadm/addnewstype";
	var addnewsurl = "/ik/newsadm/addnews";
	
	initnewsType();
	
	function initnewsType(){
		$.getJSON(newsTypeurl,function(data){
			var temp = "";
			data.newsTypeList.map(function(item,index){
				temp += "<option value='" + item.newsTypeId + "'>"
				+ item.newsTypeName + "</option>";
			});
			$("#newsType").html(temp);
		});
	}
	
	$('#add-btn').click(function(){
		var nty = $('#newsType option:selected').val();
		var nti = $('input[name=newsTitle]').val();
		var nar = $('#newsArticle').val()
		var nim = $('input[name=newsImg]')[0].files[0];
		
		var formData = new FormData();
		formData.append('newsType',nty);
		formData.append('newsTitle',nti);
		formData.append('newsArticle',nar);
		formData.append('newsImg',nim);
		
		$.ajax({
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			type : 'post',
			dataType : 'json',
			url : addnewsurl,
			data : formData,
			success : function(data){
				if(data.success){
					alert("添加成功！");
					window.location.href ='/ik/news/addnews';
					
				}else{
					alert(data.errMsg);
					window.location.href ='/ik/news/news';
				}
			}
		});
	});
	
});