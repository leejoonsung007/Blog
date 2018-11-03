var domain = document.domain;

window.onload = function() {
	$.ajax({
		url: "localhost" + ":8080/api/article/list/latest",
		type: "GET",
		dataType: "json",
		success: function(json) {
			$.each(json, function(i, item) {
				// picture
				$(".smallPictures img[location=" + i + "]").attr("src", item.pictureUrl);
				$(".smallPictures img[location=" + i + "]").attr("pictureUrl", item.pictureUrl);
				$(".smallPictures img[location=" + i + "]").attr("articleId", item.id);
				$(".smallPictures img[location=" + i + "]").attr("title", item.title);
				$(".smallPictures img[location=" + i + "]").attr("summary", item.summary);

				// show the first article information
				if(i == "0") {
					$("#articleTitle").html(item.title);
					$("#articleSummary").html(item.summary);
					$("#articlePicture img").attr("src", item.pictureUrl);
					$("#showArticle").attr("articleId", item.id);
				}
			});
		}
	});

	$.ajax({
		url: "localhost" + ":8080/api/note/latest",
		type:"GET",
		dataType:"json",
		success:function (json) {
			$("#noteTitle").html(json.title);
			$("#noteContent").html(json.content);
			console.log(json.title);
			console.log(json.content);
        }
	});
};

// enter the details page
$("#showArticle").click(function() {
	var articleId = $(this).attr("articleId");
	var url = "article.html?articleId=" + articleId;
	window.location.href = url;
});

$(".smallPictures img").mouseenter(function() {
	var pictureUrl = $(this).attr("pictureUrl");
	var articleId = $(this).attr("articleId")
	var title = $(this).attr("title");
	var summary = $(this).attr("summary");
	$("#articlePicture img").attr("src", pictureUrl);
	$("#showArticle").attr("articleId", articleId);
	$("#articleTitle").html(title);
	$("#articleSummary").html(summary);
});


