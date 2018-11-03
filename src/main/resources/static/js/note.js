var domain = document.domain;

window.onload = function () {
    // add note
    $.ajax({
        type: "get",
        url: "localhost" + ":8080/api/note/list",
        dataType: "json",
        success: function (json) {
            $.each(json, function (i, item) {
                $('#allNotes').append(
                    '<table class="table">' +
                    '<tr>'+ '<th class="innerBorder" colspan="2">'+
                    '<H3 class="noteTitle">' + item.title + '</H3>' +
                    '<H6 style="float:right">' + item.createBy + '</H6>' +
                    '</th>'+ '</tr>'+
                    '<tr>'+ '<th class="innerBorder">'+
                    '<p><img class="noteImage" height="150px" width="150px" src="' + item.pictureUrl + '"></a></p>' +
                    '</th>'+
                    '<th>' + '<div class="noteContent">' + item.content + '</div>' + '</th>' +
                    '<hr width="80%">' + '</table>'

                );
            });

        }
    });

};