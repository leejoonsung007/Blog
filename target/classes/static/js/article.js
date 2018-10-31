window.onload = function () {
    var articleId = getQueryVariable("articleId");

    // add article
    $.ajax({
        type: "get",
        url: "http://localhost:8080/api/article/" + articleId,
        dataType: "json",
        success: function (json) {
            $("#articleTitle").html(json.title);
            $("#articleCreateBy").html(json.createBy);
            $("#articleContent").html(json.content);
            Prism.highlightAll();
            $("#articlePicture").attr("src", json.pictureUrl);
        }
    });

    // add comment
    $.ajax({
        type: "get",
        url: "http://localhost:8080/api/comment/article/" + articleId,
        dataType: "json",
        success: function (json) {
            $.each(json, function (i, item) {
                $('#commentList').append(
                    '<div class="comment">' +
                    '<label class="commentName">' + item.name + '</label> <label class="commentTime">' + item.createBy + '</label><br />' +
                    '<lable class="commentContent">' + item.content + '</lable>' +
                    '</div></div>'
                );
            });
        }
    })

}

// get the variable
function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}

// post a comment
$('#addComment').click(function () {
    var articleId = getQueryVariable("articleId");
    var name = $('#commentName').val();
    var email = $('#commentEmail').val();
    var content = $('#commentContent').val();

    // isEmpty?
    if ("" == name || "" == content) {
        $('#modal').modal();
        return;
        return;
    }

    var comment = {
        name: name,
        email: email,
        content: content
    }

    $.ajax({
        url: "http://localhost:8080/api/comment/article/" + articleId,
        type: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(comment),
        success: function () {
            $('#addModal').modal();
        },
        error: function () {
            $('#addModal').modal();
        }
    })
});

$('#addConfirmBtn').click(function () {
    location.reload();
});