var domain = document.domain;

$(document).ready(function () {

    // fill the traffic data
    $.ajax({
        url: "http://ec2-52-201-210-120.compute-1.amazonaws.com" + ":8080/admin/sys/view",
        type: "GET",
        dataType: "json",
        success: function (json) {
            $('#count-visits').append(json.length)

            $.each(json, function (i, item) {
                $('#tbody-visits').append(
                    '<tr><td>' + item.id +
                    '</td><td>' + item.ip +
                    '</td><td>' + item.createBy + '</td></tr>');
            });
            $('#dataTables-visits').dataTable();
        }
    });

    // fill the log data
    $.ajax({
        url: "http://ec2-52-201-210-120.compute-1.amazonaws.com" + ":8080/admin/sys/log",
        type: "GET",
        dataType: "json",
        success: function (json) {
            $('#count-logs').append(json.length)
            $.each(json, function (i, item) {
                $('#tbody-logs').append(
                    '<tr><td>' + item.id +
                    '</td><td>' + item.ip +
                    '</td><td>' + item.createBy +
                    '</td><td>' + item.remark +
                    '</td><td>' + item.operateUrl +
                    '</td><td>' + item.operateBy + '</td></tr>');
            });
            $('#dataTables-logs').dataTable();
        }
    });

    // fill the comment statistic data
    $.ajax({
        url: "http://ec2-52-201-210-120.compute-1.amazonaws.com" + ":8080/api/comment/list",
        type: "GET",
        dataType: "json",
        success: function (json) {
            $('#count-comments').append(json.length)
            $.each(json, function (i, item) {
                $('#tbody-comments').append(
                    '<tr><td>' + item.id +
                    '</td><td>' + item.content +
                    '</td><td>' + item.createBy +
                    '</td><td>' + item.name +
                    '</td><td>' + item.ip +
                    '</td><td>' + item.isEffective +
                    '</td><td><button class="btn btn-danger deleteBtn" onclick="deleteComment(\'' + item.id + '\')"><i class="fa fa-trash-o"></i>delete</button></td></tr>');

            });
            $('#dataTables-comments').dataTable();
        }
    });

});

// first delete the comment
function deleteComment(id) {
    $('#confirmBtn').attr("commentId", id);
    $('#myModal').modal();
};

// delete comment
$('#confirmBtn').click(function () {
    var id = $(this).attr("commentId");
    $.ajax({
        type: "DELETE",
        url: "http://ec2-52-201-210-120.compute-1.amazonaws.com" + ":8080/admin/comment/" + id,
        success: function () {
            // refresh
            location.reload();
        }
    });
});