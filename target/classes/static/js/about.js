window.onload = function () {
    // get message
    $.ajax({
        url: "http://localhost:8080/api/comment/list",
        type: "GET",
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
};

// add message
$('#addComment').click(function () {
    var name = $('#commentName').val();
    var email = $('#commentEmail').val();
    var content = $('#commentContent').val();

    // isEmpty?
    if ("" == name || "" == content) {
        $('#modal').modal();
        return;
        return;
    }

    // only is not empty can add
    var comment = {
        name: name,
        email: email,
        content: content
    }

    $.ajax({
        url: "http://localhost:8080/api/comment/",
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
    // refresh
    location.reload();
});