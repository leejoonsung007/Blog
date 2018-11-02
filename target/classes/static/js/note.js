window.onload = function () {
    // add note
    $.ajax({
        type: "get",
        url: "http://localhost:8080/api/note/list",
        dataType: "json",
        success: function (json) {
            $.each(json, function (i, item) {
                $('#allNotes').append(
                    '<table class="table" >' +
                    '<tr>'+ '<th colspan="2">'+
                    '<H3 class="noteTitle">' + item.title + item.createBy + '</H3>' +
                    '</th>'+ '</tr>'+
                    '<tr>'+ '<th>'+
                    '<p><img class="noteImage" height="150px" width="150px" src="' + item.pictureUrl + '"></a></p>' +
                    '</th>'+
                    '<th>' + '<div class="noteContent">' + item.content + '</div>' + '</th>' +
                    '<hr width="80%">' + '</table>'

                );
            });
            // $("#noteTitle").html(json.title);
            // // $("#noteCreateBy").html(json.createBy);
            // $("#noteContent").html(json.content);
            // Prism.highlightAll();
            // $("#notePicture").attr("src", json.pictureUrl);
        }
    });

};