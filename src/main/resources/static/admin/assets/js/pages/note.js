var domain = document.domain;

$(document).ready(function () {

    // fill the update blog page
    $.ajax({
        type: "get",
        url: "localhost" + ":8080/api/note/list",
        dataType: "json",
        success: function (json) {
            $.each(json, function (i, item) {
                $('#tbody-note').append(
                    '<tr><td>' + +item.id +
                    '</td><td>' + item.title +
                    '</td><td>' + item.top +
                    '</td><td>' + item.traffic +
                    '</td><td><a href="' + item.pictureUrl + '">View</a></td>' +
                    '<td><button class="btn btn-success" onclick="updateNote(' + item.id + ')"><i class="fa fa-edit"></i> edit</button> ' +
                    '<button class="btn btn-danger" onclick="deleteNote(' + item.id + ')"><i class="fa fa-trash-o"> delete</i></button></td></tr>');
            });
            $('#dataTables-notes').dataTable();

        }
    });

});

// Click the delete button
function deleteNote(id) {
    $('#confirmBtn').attr("noteId", id);
    $('#myModal').modal();
};

// Confirm delete operation
$('#confirmBtn').click(function () {
    var id = $(this).attr("noteId");
    $.ajax({
        type: "DELETE",
        url: "localhost" + ":8080/admin/note/" + id,
        success: function () {
            // refresh
            location.reload();
        }
    });
});

// Click the edit button
function updateNote(id) {
    $('#updateBtn').attr("noteId", id);
    $.ajax({
        type: "get",
        url: "localhost" + ":8080/admin/note/" + id,
        dataType: "json",
        async: false,
        success: function (json) {
            $('#noteTitle').val(json.title);
            if (json.top == true) {
                document.getElementById("noteTop").checked = true;
            }
            $('#notePicture').val(json.pictureUrl);
            $('#noteContent').val(json.content);
            console.log(json.content)
        }
    });

    // display modal
    $('#updateModal').modal();
};

// click the update
$('#updateBtn').click(function () {
    var noteId = $('#updateBtn').attr("noteId");
    var noteTitle = $('#noteTitle').val();
    var noteTop = document.getElementById("noteTop").checked;
    var notePicture = $('#notePicture').val();
    var noteContent = $('#noteContent').val();
    var note = {
        id: noteId,
        title: noteTitle,
        top: noteTop,
        pictureUrl: notePicture,
        content: noteContent
    };
    $.ajax({
        type: "PUT",
        url: "localhost" + ":8080/admin/note/" + noteId,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(note),
        success: function () {
            location.reload();
        },
        error: function () {
            location.reload();
        }
    });
});

// create a blog
$('#addNoteBtn').click(function () {
    var noteTitle = $('#addNoteTitle').val();
    var noteTop = document.getElementById("addNoteTop").checked;
    var notePicture = $('#addNotePicture').val();
    var noteContent = $('#addNoteContent').val();
    var note = {
        title: noteTitle,
        top: noteTop,
        pictureUrl: notePicture,
        content: noteContent
    };
    $.ajax({
        type: "POST",
        url: "localhost" + ":8080/admin/note/",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(note),
        success: function () {
            location.reload();
            console.log("successful")
        },
        error: function () {
            location.reload();
        }
    });
});