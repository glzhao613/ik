$(function(){
	var updatenewsurl = "/ik/newsadm/updatenews";
	
	initnewsType();
	initUpdateNews();

	$('#update-btn').click(function() {
		newstype = $('#newsType option:selected').val();
		newstitle = $('input[name=newsTitle]').val();
		newsarticle = $('#newsarticle').val()
		newsimg = $('input[name=newsImg]')[0].files[0];
		var formData = new FormData();
		formData.append('newstype', newstype);
		formData.append('newstitle',newstitle);
		formData.append('newsarticle',newsarticle);
		formData.append('newsimg',newsimg);
		
		$.ajax({
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			type : 'post',
			dataType : 'json',
			url : updatenewsurl,
			data : formData,
			success : function(data){
				if(data.success){
					alert("修改成功！");
					window.location.href = "/ik/news/newsupdate";
				}else{
					alert(data.errMsg);
					window.location.href = "/ik/news/newsupdate";
				}
			}
		});
	});
});

function initUpdateNews(){
	var setupdateurl = "/ik/newsadm/setupdateid";
	$.getJSON(setupdateurl,function(data){
		$("#newstitle").attr("value",data.updatenews.newsTitle);
		$("#newsarticle").html(data.updatenews.newsArticle);
	});
}

function initnewsType(){
	var newsTypeurl = "/ik/newsadm/quernewstype";
	$.getJSON(newsTypeurl,function(data){
		var temp = "";
		data.newsTypeList.map(function(item,index){
			temp += "<option value='" + item.newsTypeId + "'>"
			+ item.newsTypeName + "</option>";
		});
		$("#newsType").html(temp);
	});
}