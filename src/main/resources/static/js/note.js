window.onload = function () {
    // add note
    $.ajax({
        type: "get",
        url: "http://localhost:8080/api/note/list",
        dataType: "json",
        success: function (json) {
            $.each(json, function (i, item) {
                $('#allNotes').append(
                    '<p>' + item.title + '</p>' +
                    '<p>' + item.content + '</p>' +
                    '<p><a href="' + item.pictureUrl + '">View</a></p>');
            });
            // $("#noteTitle").html(json.title);
            // // $("#noteCreateBy").html(json.createBy);
            // $("#noteContent").html(json.content);
            // Prism.highlightAll();
            // $("#notePicture").attr("src", json.pictureUrl);
        }
    });

};