$(document).ready(function() {
	// fill the article data
	$.ajax({
		type: "get",
		url: "http://localhost:8080/api/article/list",
		dataType: "json",
		contentType: "application/json;charset=utf-8",
		success: function(json) {
			$.each(json, function(i, item) {
				if(i == 0) {
					// fill the comments
					addCommentList(item.id)
				}
				$('#articleList').append('<option articleId="' + item.id + '">' + item.title + '</option>');
			});
			//			alert(firstArticleId);
		}
	});
});

// fill the corresponding comment to a article
function addCommentList(id) {
	$.ajax({
		type: "get",
		url: "http://localhost:8080/api/comment/article/" + id,
		dataType: "json",
		contentType: "application/json;charset=utf-8",
		success: function(json) {
			// clear
			$('#tbody-comments').html("");
			$.each(json, function(i, item) {
				$('#tbody-comments').append(
					'<tr><td>' + +item.id +
					'</td><td>' + item.content +
					'</td><td>' + item.name +
					'</td><td>' + item.email +
					'</td><td>' + item.ip +
					'</td><td>' + item.createBy +
					'</td><td><button class="btn btn-danger deleteBtn" onclick="deleteArticleComment(\'' + item.articleCommentId + '\')"><i class="fa fa-trash-o"></i>删除</button></td></tr>');
			});
			$('#dataTables-comments').dataTable();
		}
	});
}

// listen to the selection of the article
document.getElementById("articleList").onchange = function() {
	var articleId = $('#articleList option:selected').attr("articleId");
	//	alert(categoryId);
	addCommentList(articleId);
};

// first click delete
function deleteArticleComment(id) {
	$('#confirmBtn').attr("articleCommentId", id);
	$('#myModal').modal();
};

// delete
$('#confirmBtn').click(function() {
	var id = $(this).attr("articleCommentId");
	//	alert(id);
	$.ajax({
		type: "DELETE",
		url: "http://localhost:8080/admin/comment/article/" + id,
		success: function() {
			// refresh
			location.reload();
		},
		error: function() {
			location.reload();
		}
	});
});