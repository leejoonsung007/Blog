var domain = document.domain;

window.onload = function () {
    $.ajax({
        url: "http://ec2-52-201-210-120.compute-1.amazonaws.com" + ":8080/api/category/list/",
        type: "GET",
        dataType: "json",
        success: function (json) {

            // category
            $.each(json, function (i, item) {
                var categoryInfo = document.querySelector("#categoryInfo");
                categoryInfo.content.querySelector("a").innerHTML = item.name + "(" + item.number + ")";
                categoryInfo.content.querySelector("a").href = "?categoryId=" + item.id;
                document.getElementById("category").appendChild(categoryInfo.content.cloneNode(true));
            });

            // check
            var categoryId = getQueryVariable("categoryId");
            //			alert(categoryId);
            if (categoryId == false) {

                // if no specification - all
                showAllArticleInfo();
            } else {
                // if there is a specification - corresponding articles
                showArticleByCategoryId(categoryId);
            }

        }
    });
};

// get variable
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

// show all the articles
function showAllArticleInfo() {

    $.ajax({
        type: "get",
        url: "http://ec2-52-201-210-120.compute-1.amazonaws.com" + ":8080/api/article/list",
        dataType: "json",
        success: function (json) {
            $.each(json, function (i, item) {
                var articleInfo = document.querySelector("#articleInfo");
                articleInfo.content.querySelector("img").src = item.pictureUrl;
                if (item.top == true) {
                    articleInfo.content.querySelector("h5").innerHTML = "[Topping] " + item.title;
                } else {
                    articleInfo.content.querySelector("h5").innerHTML = item.title;
                }
                articleInfo.content.querySelector("h6").innerHTML = item.id;
                document.getElementById("articleInfos").appendChild(articleInfo.content.cloneNode(true));

            });
        }
    });
}

function showArticleByCategoryId(id) {
    $.ajax({
        type: "get",
        url: "http://ec2-52-201-210-120.compute-1.amazonaws.com" + ":8080/api/article/list/sort/" + id,
        dataType: "json",
        success: function (json) {
            $.each(json, function (i, item) {
                // 填充文章信息
                var articleInfo = document.querySelector("#articleInfo");
                articleInfo.content.querySelector("img").src = item.pictureUrl;
                if (item.top == true) {
                    articleInfo.content.querySelector("h5").innerHTML = "[置顶]" + item.title;
                    //					articleInfo.content.querySelector("h5").style.fontWeight = "bold";
                } else {
                    articleInfo.content.querySelector("h5").innerHTML = item.title;
                }
                articleInfo.content.querySelector("h6").innerHTML = item.id;
                document.getElementById("articleInfos").appendChild(articleInfo.content.cloneNode(true));
            });
        }
    });
}

// jump
function showArticle(_this) {
    var articleId = $(_this).children("h6").text();
    var url = "article.html?articleId=" + articleId;
    window.location.href = url;
}