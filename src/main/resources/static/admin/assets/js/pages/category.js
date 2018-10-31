$(document).ready(function() {
	// fill the traffic data
	$.ajax({
		url: "http://localhost:8080/api/category/list",
		type: "GET",
		dataType: "json",
		success: function(json) {
			$.each(json, function(i, item) {
				$('#tbody-categoris').append(
					'<tr><td>' + +item.id +
					'</td><td>' + item.name +
					'</td><td>' + item.number +
					'</td><td>' + item.createBy +
					'</td><td>' + item.modifiedBy +
					'</td><td><button class="btn btn-danger deleteBtn" onclick="deleteCategory(\'' + item.id + '\')"><i class="fa fa-trash-o"></i>删除</button></td></tr>');
				$('#select-category').append(
					'<option categoryId="' + item.id + '">' + item.name + '</option>'
				);
			});
			$('#dataTables-categoris').dataTable();
		}
	});
});

// click the delete button
function deleteCategory(id) {
	$('#confirmBtn').attr("categoryId", id);
	$('#myModal').modal();
};

// delete
$('#confirmBtn').click(function() {
	var id = $(this).attr("categoryId");
	$.ajax({
		type: "DELETE",
		url: "http://localhost:8080/admin/category/" + id,
		success: function() {
			// refresh
			location.reload();
		}
	});
});

// add a new category
$('#addCategoryBtn').click(function() {
	var categoryName = $('#addName').val();
	var json = {
		name: categoryName
	};
	$.ajax({
		type: "POST",
		dataType: "json",
		contentType: "application/json;charset=utf-8",
		url: "http://localhost:8080/admin/category",
		data: JSON.stringify(json),
		success: function() {
			// Refresh
			location.reload();
		},
		error: function() {
			location.reload();
		}
	});
});

// update the category
$('#updateCategoryBtn').click(function() {
	var categoryId = $('#select-category option:selected').attr("categoryId");
	var categoryName = $('#updateName').val();
	var categoryJson = {
		id: categoryId,
		name: categoryName
	};
	$.ajax({
		type: "PUT",
		url: "http://localhost:8080/admin/category/" + categoryId,
		data: JSON.stringify(categoryJson),
		dataType: "json",
		contentType: "application/json;charset=utf-8",
		success: function() {
			// refresh
			location.reload();
		},
		error: function() {
			$('#updateName').val("");
			location.reload();
		}
	})
})