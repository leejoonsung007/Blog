$(document).ready(function () {
    // fill the category data of the article
    $.ajax({
        url: "http://localhost:8080/api/category/list",
        type: "GET",
        dataType: "json",
        success: function (json) {
            $.each(json, function (i, item) {
                $('#articleCategories').append('<option categoryId="' + item.id + '">' + item.name + '</option>');
                $('#addCategories').append('<option categoryId="' + item.id + '">' + item.name + '</option>');
            });
        }
    });

    // fill the update blog page
    $.ajax({
        type: "get",
        url: "http://localhost:8080/api/article/list",
        dataType: "json",
        success: function (json) {
            $.each(json, function (i, item) {
                $('#tbody-articles').append(
                    '<tr><td>' + +item.id +
                    '</td><td>' + item.title +
                    '</td><td>' + item.top +
                    '</td><td>' + item.traffic +
                    '</td><td><a href="' + item.pictureUrl + '">View</a></td>' +
                    '<td><button class="btn btn-success" onclick="updateArticle(' + item.id + ')"><i class="fa fa-edit"></i> edit</button> ' +
                    '<button class="btn btn-danger" onclick="deleteArticle(' + item.id + ')"><i class="fa fa-trash-o"> delete</i></button></td></tr>');
            });
            $('#dataTables-articles').dataTable();

        }
    });

});

//listen to the selection of blog category, and fill in corresponding content
document.getElementById("articleCategories").onchange = function () {
    var categoryId = $('#articleCategories option:selected').attr("categoryId");
    //	alert(categoryId);
    if (categoryId === "") {
        var url = "http://localhost:8080/api/article/list";
    } else {
        var url = "http://localhost:8080/api/article/list/sort/" + categoryId;
    }
    // 填充博文分类信息
    $.ajax({
        url: url,
        type: "GET",
        dataType: "json",
        success: function (json) {
            // 先清空博文数据
            $('#tbody-articles').html("");
            $.each(json, function (i, item) {
                $('#tbody-articles').append(
                    '<tr><td>' + +item.id +
                    '</td><td>' + item.title +
                    '</td><td>' + item.top +
                    '</td><td>' + item.traffic +
                    '</td><td><a href="' + item.pictureUrl + '">View</a></td>' +
                    '<td><button class="btn btn-success" onclick="updateArticle(' + item.id + ')"><i class="fa fa-edit"></i> edit</button> ' +
                    '<button class="btn btn-danger" onclick="deleteArticle(' + item.id + ')"><i class="fa fa-trash-o"> delete</i></button></td></tr>');
            });
            $('#dataTables-articles').dataTable();

        }
    });
};

// Click the delete button
function deleteArticle(id) {
    $('#confirmBtn').attr("articleId", id);
    $('#myModal').modal();
};

// Confirm delete operation
$('#confirmBtn').click(function () {
    var id = $(this).attr("articleId");
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/admin/article/" + id,
        success: function () {
            // refresh
            location.reload();
        }
    });
});

// Click the edit button
function updateArticle(id) {
    $('#updateBtn').attr("articleId", id);
    $.ajax({
        type: "get",
        url: "http://localhost:8080/admin/article/" + id,
        dataType: "json",
        async: false,
        success: function (json) {
            $('#articleTitle').val(json.title);
            $('#articleSummary').val(json.summary);
            if (json.top == true) {
                document.getElementById("articleTop").checked = true;
            }
            // fill in category data
            $.ajax({
                url: "http://localhost:8080/api/category/list",
                type: "GET",
                dataType: "json",
                async: false,
                success: function (json) {
                    $('#updateCategories').html("");
                    $.each(json, function (i, item) {
                        $('#updateCategories').append('<option categoryId="' + item.id + '">' + item.name + '</option>');
                    });
                }
            });
            var select = document.getElementById("updateCategories");
            var index;
            for (var i = 0; i < select.options.length; i++) {
                if (select.options[i].innerHTML == json.categoryName) {
                    select.options[i].selected = true;
                    break;
                }
            }
            $('#articlePicture').val(json.pictureUrl);
            $('#articleContent').val(json.content);
        }
    });

    // display modal
    $('#updateModal').modal();
};

// click the update
$('#updateBtn').click(function () {
    var articleId = $('#updateBtn').attr("articleId");
    var articleTitle = $('#articleTitle').val();
    var articleSummary = $("#articleSummary").val();
    var articleTop = document.getElementById("articleTop").checked;
    var articleCategoryId = $("#updateCategories option:selected").attr("categoryId");
    var articlePicture = $('#articlePicture').val();
    var articleContent = $('#articleContent').val();
    var article = {
        id: articleId,
        title: articleTitle,
        summary: articleSummary,
        top: articleTop,
        categoryId: articleCategoryId,
        pictureUrl: articlePicture,
        content: articleContent
    }
    $.ajax({
        type: "PUT",
        url: "http://localhost:8080/admin/article/" + articleId,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(article),
        success: function () {
            location.reload();
        },
        error: function () {
            location.reload();
        }
    });
});

// create a blog
$('#addArticleBtn').click(function () {
    var articleTitle = $('#addArticleTitle').val();
    var articleSummary = $("#addArticleSummary").val();
    var articleTop = document.getElementById("addArticleTop").checked;
    //	alert(articleTop);
    var articleCategoryId = $("#addCategories option:selected").attr("categoryId");
    var articlePicture = $('#addArticlePicture').val();
    var articleContent = $('#addArticleContent').val();
    var article = {
        title: articleTitle,
        summary: articleSummary,
        top: articleTop,
        categoryId: articleCategoryId,
        pictureUrl: articlePicture,
        content: articleContent
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/admin/article/",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(article),
        success: function () {
            location.reload();
        },
        error: function () {
            location.reload();
        }
    });
});