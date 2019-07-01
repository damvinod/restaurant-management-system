var show = "show";
var hide = "hide";

var adminFormName = "adminFormDetails";
var customerFormName = "customerFormDetails";

var triggerFromAdmin = "admin";
var triggerFromcustomerOrderMenu = "customerOrderMenu";
var triggerFromCustomerConfirmOrder = "customerConfirmOrder";

var successStyle = "alert alert-success fade in";
var failureStyle = "alert alert-danger fade in";

function hideShowDiv(divId, showHideFlag) {

	var x = document.getElementById(divId);

	if (showHideFlag == show) {
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
}

function getAllMenuItems(formName, triggerFrom) {
	console.log($("#" + formName).serializeArray());
	console.log($("#" + formName).serialize());

	var data = JSON.stringify($("#" + formName).serialize());
	console.log(data);

	var postData = {
		"data" : data
	};

	var targetUrl = "getAllItems";

	$.ajax({
		url : targetUrl,
		data : postData,
		dataType : "json",
		async : false,
		cache : false,
		type : "GET",
		success : function(response) {
			if (triggerFrom == triggerFromCustomerConfirmOrder) {
				createCustomerConfirmTable(response, formName);
			} else {
				createMenuTable(response, formName);
			}
		},
		error : function(xhr, status, errorThrown) {
			alert("Sorry, there was a problem!");
			console.log("Error: " + errorThrown);
			console.log("Status: " + status);
			console.dir(xhr);
		},
		// Code to run regardless of success or failure
		complete : function(xhr, status) {
			console.log("The request is complete!");
		}
	});
}

function createMenuTable(response, formName) {
	var $table = $('<table id="menuTable" class="table table-hover">');

	var $tbody = $table.append('<tbody />').children('tbody');

	if (response.length != undefined && response.length > 0) {

		$("#menuItemsCount").val(response.length);

		for (var i = 0; i < response.length; i++) {

			if (i == 0) {
				var firstRows = "<td>" + 'S.No' + "</td>" + "<td>" + 'Name'
						+ "</td>" + "<td>" + 'Price' + "</td>" + "<td>"
						+ 'Image' + "</td>";

				if (formName == adminFormName) {
					firstRows = firstRows + "<td>" + 'Duration (sec)' + "</td>";
					firstRows = firstRows + "<td>" + 'Ref Id' + "</td>";
				} else if (formName == customerFormName) {
					firstRows = firstRows + "<td>" + 'Quantity' + "</td>";
				}
				$tbody.append('<tr />').children('tr:last').append(firstRows);
			}

			var referenceId = response[i].id;
			var name = response[i].description;
			var price = response[i].price;
			var duration = response[i].duration;

			var valuesRow = "<td>"
					+ (Number(i) + Number(1))
					+ "</td>"
					+ "<td>"
					+ name
					+ "</td>"
					+ "<td>"
					+ price
					+ "</td>"
					+ "<td>"
					+ "<button type='button' onclick='downloadImage(this.id);' id="
					+ referenceId
					+ " class='btn btn-info btn-xs' data-toggle='modal' data-target='#myModal'>View</button>"
					+ "</td>";

			if (formName == adminFormName) {
				valuesRow = valuesRow + "<td>" + duration + "</td>";
				valuesRow = valuesRow + "<td>" + referenceId + "</td>";
			} else if (formName == customerFormName) {
				var widgetId = "id='item_" + referenceId + "'" + "name='item_"
						+ referenceId + "'";
				var inputWidget = "<input type='text' onkeypress='return isNumberKey(event)'"
						+ widgetId
						+ " class='form-control' aria-describedby='sizing-addon3'/>";

				valuesRow = valuesRow
						+ "<td><div class='input-group input-group-sm col col-lg-2'><span class='input-group-addon' id='sizing-addon3'>@</span>"
						+ inputWidget + "</div></td>"
			}

			$tbody.append('<tr />').children('tr:last').append(valuesRow)
		}

		$table.appendTo('#menuTableDiv');
	}
}

function addMenuItem(formName, triggerFrom) {

	console.log(JSON.stringify($("#" + formName).serializeJSON()));

	var message = checkMandatoryForAddItem();

	if (message != "") {
		showMessage('itemAddValidationMessage',
				'Please fill the listed mandatory field ' + message,
				failureStyle);
		return false;
	}

	var formData = new FormData();
	formData.append('file', $('#myfile')[0].files[0]);

	var data = $("#" + formName).serializeJSON();

	formData.append('itemType', JSON.stringify($("#" + formName)
			.serializeJSON()));

	var targetUrl = "addItemsWithImage";

	$.ajax({
		url : targetUrl,
		data : formData,
		// dataType : "json",
		async : false,
		cache : false,
		processData : false, // tell jQuery not to process the data
		contentType : false, // tell jQuery not to set contentType
		type : "POST",
		success : function(response) {
			removeTable("menuTable");
			getAllMenuItems(formName, triggerFrom);
		},
		error : function(xhr, status, errorThrown) {
			alert("Sorry, there was a problem!");
			console.log("Error: " + errorThrown);
			console.log("Status: " + status);
			console.dir(xhr);
		},
		// Code to run regardless of success or failure
		complete : function(xhr, status) {
			console.log("The request is complete!");
		}
	});
}

function deleteMenuItem(formName, referenceNoWidgetId) {

	var referenceNo = $("#" + referenceNoWidgetId).val();

	if (referenceNo == "" || referenceNo == 0) {
		showMessage('userDeleteSuccessMessage', 'Please fill Reference No.',
				failureStyle);
		return false;
	}

	console.log(JSON.stringify($("#" + formName).serializeJSON()));

	var data = $("#" + formName).serializeJSON();

	var targetUrl = "deleteItem";

	var postData = {
		"id" : data.id
	};

	$.ajax({
		url : targetUrl,
		data : postData,
		dataType : "json",
		async : false,
		cache : false,
		type : "POST",
		success : function(response) {
			removeTable("menuTable");
			getAllMenuItems(formName, triggerFromAdmin);
			showMessage('userDeleteSuccessMessage',
					'User deleted successfully !', successStyle);
		},
		error : function(xhr, status, errorThrown) {
			alert("Sorry, there was a problem!");
			console.log("Error: " + errorThrown);
			console.log("Status: " + status);
			console.dir(xhr);
		},
		// Code to run regardless of success or failure
		complete : function(xhr, status) {
			console.log("The request is complete!");
		}
	});
}

function removeTable(tabelId) {
	$("#" + tabelId).empty();
	$("#" + tabelId + " > tbody").html("");
}

function resetCustomerSelection() {
	var totalMenuItems = $('#menuItemsCount').val();
	for (var i = 1; i <= totalMenuItems; i++) {
		$("#item_" + i).val("");
	}
}

function checkIfAtleastOneItemSelected() {
	var totalMenuItems = $('#menuItemsCount').val();

	var message = "";
	var passport = false;
	if ($("#tableNumber").val() == "" || $("#tableNumber").val() == 0) {
		message = "Table No, ";
	}

	for (var i = 1; i <= totalMenuItems; i++) {
		if ($("#item_" + i).val() != "" && $("#item_" + i).val() != 0)
			passport = true;
	}

	if (!passport)
		message = message + "Quantity, ";

	if (message != "")
		message = message.slice(0, -2);

	return message;
}

function showCustomerOrderSummary(formName) {

	var message = checkIfAtleastOneItemSelected();

	if (message == "") {
		getAllMenuItems("customerFormDetails", "customerConfirmOrder");

		hideShowDiv("customerSelctionDiv", hide);
		hideShowDiv("customerFinalizationDiv", show);
	} else
		showMessage('customerSelectionValidationMessage',
				'Please fill the listed mandatory field ' + message,
				failureStyle);
}

function createCustomerConfirmTable(response, formName) {
	var $table = $('<table id="confirmMenuTable" class="table table-hover">');

	var $tbody = $table.append('<tbody />').children('tbody');

	if (response.length != undefined && response.length > 0) {

		$("#menuItemsCount").val(response.length);

		var totalPrice = 0;
		var totalQuantity = 0;
		var totalDuration = 0;
		for (var i = 0; i < response.length; i++) {

			if (i == 0) {
				var firstRows = "<td>" + 'S.No' + "</td>" + "<td>" + 'Name'
						+ "</td>" + "<td>" + 'Quantity' + "</td>" + "<td>" + 'Duration (min)' + "</td>" + "<td>"
						+ 'Price ($)' + "</td>";

				$tbody.append('<tr />').children('tr:last').append(firstRows);
			}

			var referenceId = response[i].id;
			var name = response[i].description;
			var price = response[i].price;
			var duration = response[i].duration;
			var quanity = 0;

			var quantityValue = $("#item_" + referenceId).val();

			if (quantityValue != "0" && quantityValue != "") {

				totalPrice = (Number(totalPrice) + Number(price * quantityValue));
				totalQuantity = (Number(totalQuantity) + Number(quantityValue));
				totalDuration = (Number(totalDuration) + Number(duration));
				
				duration = Math.floor(duration / 60);
				
				var valuesRow = "<td>" + (Number(i) + Number(1)) + "</td>"
						+ "<td>" + name + "</td>" + "<td>" + quantityValue
						+ "</td>" + "<td>" + duration + "</td>" + "<td>" + price + "</td>";

				$tbody.append('<tr />').children('tr:last').append(valuesRow)
			}
		}

		totalDuration = Math.floor(totalDuration / 60);
		
		var totalRow = "<td>" + "" + "</td>" + "<td>" + "Total" + "</td>"
				+ "<td>" + totalQuantity + "</td>" + "<td>" + totalDuration
				+ "</td>" + "<td>" + totalPrice + "</td>";

		$tbody.append('<tr />').children('tr:last').append(totalRow)

		$table.appendTo('#confirmMenuTableDiv');
	}
}

function goBackCustomerOrder() {
	removeTable("confirmMenuTable");
	hideShowDiv("customerSelctionDiv", show);
	hideShowDiv("customerFinalizationDiv", hide);
}

function saveCustomerOrder(formName) {

	console.log(JSON.stringify($("#" + formName).serializeJSON()));

	var data = JSON.stringify($("#" + formName).serializeJSON());

	console.log(data);

	var formData = new FormData();
	formData.append('jsonString', data);

	var targetUrl = "confirmOrder";

	$.ajax({
		url : targetUrl,
		data : formData,
		// dataType : "json",
		async : false,
		cache : false,
		processData : false, // tell jQuery not to process the data
		contentType : false, // tell jQuery not to set contentType
		type : "POST",
		success : function(response) {
			showMessage('customerConfirmationValidationMessage',
					'Order is successful.', successStyle);
		},
		error : function(xhr, status, errorThrown) {
			alert("Sorry, there was a problem!");
			console.log("Error: " + errorThrown);
			console.log("Status: " + status);
			console.dir(xhr);
		},
		// Code to run regardless of success or failure
		complete : function(xhr, status) {
			console.log("The request is complete!");
		}
	});
}

function addUser(formName, triggerFrom) {
	console.log(JSON.stringify($("#" + formName).serializeJSON()));

	var userName = $("#userName").val();

	if (userName == "") {
		showMessage('userAddSuccessMessage', 'Please fill User Name.',
				failureStyle);
		return false;
	}

	var data = $("#" + formName).serializeJSON();

	var targetUrl = "addUser";

	$.ajax({
		url : targetUrl,
		data : data,
		dataType : "json",
		async : false,
		cache : false,
		type : "POST",
		success : function(response) {
			showMessage('userAddSuccessMessage', 'User added successfully !',
					successStyle);
			getAllUsers(formName);
		},
		error : function(xhr, status, errorThrown) {
			alert("Sorry, there was a problem!");
			console.log("Error: " + errorThrown);
			console.log("Status: " + status);
			console.dir(xhr);
		},
		// Code to run regardless of success or failure
		complete : function(xhr, status) {
			console.log("The request is complete!");
		}
	});
}

function downloadImage(id) {

	var formData = new FormData();
	formData.append('referenceId', id);

	var targetUrl = "downloadImage";

	$
			.ajax({
				url : targetUrl,
				data : formData,
				// dataType : "json",
				async : false,
				cache : false,
				processData : false, // tell jQuery not to process the data
				contentType : false, // tell jQuery not to set contentType
				type : "POST",
				success : function(response) {
					$('#itemDescription').text(response.description);
					document.getElementById("ItemPreview").src = "data:image/png;base64,"
							+ response.image;
				},
				error : function(xhr, status, errorThrown) {
					alert("Sorry, there was a problem!");
					console.log("Error: " + errorThrown);
					console.log("Status: " + status);
					console.dir(xhr);
				},
				// Code to run regardless of success or failure
				complete : function(xhr, status) {
					console.log("The request is complete!");
				}
			});
}

function getItemsTransaction(formName, status) {
	console.log(JSON.stringify($("#" + formName).serializeJSON()));

	var data = $("#" + formName).serializeJSON();

	var targetUrl = "";

	if (status == "received")
		targetUrl = "getReceivedItemsTransaction";
	else
		targetUrl = "getPendingItemsTransaction";

	$.ajax({
		url : targetUrl,
		data : data,
		dataType : "json",
		async : false,
		cache : false,
		type : "POST",
		success : function(response) {

			createMenuForChefView(response, status);

		},
		error : function(xhr, status, errorThrown) {
			alert("Sorry, there was a problem!");
			console.log("Error: " + errorThrown);
			console.log("Status: " + status);
			console.dir(xhr);
		},
		// Code to run regardless of success or failure
		complete : function(xhr, status) {
			console.log("The request is complete!");
		}
	});
}

function createMenuForChefView(response, status) {

	var $table = $('<table id="confirmMenuTable" class="table table-hover">');

	var $tbody = $table.append('<tbody />').children('tbody');

	var currentUserId = $("#userId").val();

	if (response != null && response != undefined) {

		for (var i = 0; i < response.length; i++) {
			var chefMenuVo = response[i];

			var itemId = chefMenuVo.itemId;
			var itemDescription = chefMenuVo.itemDescription;
			var duration = chefMenuVo.duration;
			var totalQuantity = chefMenuVo.totalQuantity;
			var selectedSequences = chefMenuVo.selectedSequences;
			var userName = chefMenuVo.userName;
			var userId = chefMenuVo.userId;
			
			duration = Math.floor(duration / 60);
			
			if (i == 0) {
				var firstRows = "<td>" + 'S.No' + "</td>" + "<td>"
						+ 'Description' + "</td>" + "<td>" + 'Duration (min)'
						+ "</td>" + "<td>" + 'Quantity' + "</td>" + "<td>"
						+ 'Job Status' + "</td>";

				if (status == "pending")
					firstRows = firstRows + "<td>" + 'Chef Name' + "</td>";

				$tbody.append('<tr />').children('tr:last').append(firstRows);
			}

			var valuesRow = "<td>" + (Number(i) + Number(1)) + "</td>" + "<td>"
					+ itemDescription + "</td>" + "<td>" + duration + "</td>"
					+ "<td>" + totalQuantity + "</td>";

			if (status == "received") {
				valuesRow = valuesRow
						+ "<td>"
						+ "<button type='button' onclick='saveOrderStatusToPending(this.id);' id="
						+ selectedSequences
						+ " class='btn btn-info btn-xs' data-toggle='modal' data-target='#myModal'>Start</button>"
						+ "</td>";
			} else {
				if (userId == currentUserId) {
					valuesRow = valuesRow
							+ "<td>"
							+ "<button type='button' onclick='saveOrderStatusToClosed(this.id);' id="
							+ selectedSequences
							+ " class='btn btn-info btn-xs' data-toggle='modal' data-target='#myModal'>Finish</button>"
							+ "</td>";
				} else {
					valuesRow = valuesRow
							+ "<td>"
							+ "In Progress"
							+ "</td>";
				}

				if (status == "pending")
					valuesRow = valuesRow + "<td>" + userName + "</td>";
			}

			console.log(valuesRow);

			$tbody.append('<tr />').children('tr:last').append(valuesRow)
		}
	}
	if (status == "received")
		$table.appendTo('#chefNextJobTableDiv');
	else
		$table.appendTo('#chefPendingJobTableDiv');
}

function saveOrderStatusToPending(selectedSequences) {

	var userId = $("#userId").val();

	if (userId == "") {
		showMessage('chefNextJobValidationMessage', 'Please fill User Id',
				failureStyle);
		return false;
	}

	var formData = new FormData();

	formData.append('selectedSequences', selectedSequences);
	formData.append('userId', userId);

	var targetUrl = "saveOrderStatusToPending";

	$
			.ajax({
				url : targetUrl,
				data : formData,
				// dataType : "json",
				async : false,
				cache : false,
				processData : false, // tell jQuery not to process the data
				contentType : false, // tell jQuery not to set contentType
				type : "POST",
				success : function(response) {
					if ("EXIST" == response) {
						$('#chefValidationModal')
								.text(
										"Please complete the Pending job before taking up the next job.");
						$("#chefValidationModalDiv").modal('show');
					} else {
						location.reload();
					}
				},
				error : function(xhr, status, errorThrown) {
					alert("Sorry, there was a problem!");
					console.log("Error: " + errorThrown);
					console.log("Status: " + status);
					console.dir(xhr);
				},
				// Code to run regardless of success or failure
				complete : function(xhr, status) {
					console.log("The request is complete!");
				}
			});
}

function saveOrderStatusToClosed(selectedSequences) {
	var formData = new FormData();

	formData.append('selectedSequences', selectedSequences);

	var targetUrl = "saveOrderStatusToClosed";

	$.ajax({
		url : targetUrl,
		data : formData,
		// dataType : "json",
		async : false,
		cache : false,
		processData : false, // tell jQuery not to process the data
		contentType : false, // tell jQuery not to set contentType
		type : "POST",
		success : function(response) {
			location.reload();
		},
		error : function(xhr, status, errorThrown) {
			alert("Sorry, there was a problem!");
			console.log("Error: " + errorThrown);
			console.log("Status: " + status);
			console.dir(xhr);
		},
		// Code to run regardless of success or failure
		complete : function(xhr, status) {
			console.log("The request is complete!");
		}
	});
}

function showMessage(messageDiv, message, style) {

	$('#' + messageDiv)
			.html(
					'<div class="'
							+ style
							+ '"><button type="button" class="close close-alert" data-dismiss="alert" aria-hidden="true">×</button>'
							+ message + ' </div>');
}

function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
		return false;
	return true;
}

function isAlphaKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
		return true;
	return false;
}

function checkMandatoryForAddItem() {

	var description = $("#description").val();
	var duration = $("#duration").val();
	var price = $("#price").val();
	var currencyType = $("#currencyType").val();
	var ingredients = $("#ingredients").val();

	var message = "";

	if (description == "")
		message = "Item Name, ";
	if (duration == "")
		message = message + "Duration (sec), ";
	if (price == "")
		message = message + "Price $, ";
	if (currencyType == "")
		message = message + "Currency, ";
	if (ingredients == "")
		message = message + "Additional Information, ";
	if ($('#myfile').get(0).files.length === 0)
		message = message + "File, ";

	if (message != "")
		message = message.slice(0, -2);

	return message;
}

function getAllUsers(formName) {
	console.log($("#" + formName).serializeArray());
	console.log($("#" + formName).serialize());

	var data = JSON.stringify($("#" + formName).serialize());
	console.log(data);

	var postData = {
		"data" : data
	};

	var targetUrl = "getAllUsers";

	$.ajax({
		url : targetUrl,
		data : postData,
		dataType : "json",
		async : false,
		cache : false,
		type : "GET",
		success : function(response) {
			removeTable("usersTable");
			showUsersTable(response);
		},
		error : function(xhr, status, errorThrown) {
			alert("Sorry, there was a problem!");
			console.log("Error: " + errorThrown);
			console.log("Status: " + status);
			console.dir(xhr);
		},
		// Code to run regardless of success or failure
		complete : function(xhr, status) {
			console.log("The request is complete!");
		}
	});
}

function showUsersTable(response) {
	var $table = $('<table id="usersTable" class="table table-hover">');

	var $tbody = $table.append('<tbody />').children('tbody');

	if (response.length != undefined && response.length > 0) {

		for (var i = 0; i < response.length; i++) {

			if (i == 0) {
				var firstRows = "<td>" + 'S.No' + "</td>" + "<td>" + 'Name'
						+ "</td>" + "<td>" + 'Role' + "</td>" + "<td>"
						+ 'Ref Id' + "</td>";

				$tbody.append('<tr />').children('tr:last').append(firstRows);
			}

			var userName = response[i].userName;
			var role = response[i].role;
			var userId = response[i].userId;

			var valuesRow = "<td>"
					+ (Number(i) + Number(1))
					+ "</td>"
					+ "<td>"
					+ userName
					+ "</td>"
					+ "<td>"
					+ role
					+ "</td>"
					+ "<td>"
					+ userId
					+ "</td>";

			$tbody.append('<tr />').children('tr:last').append(valuesRow)
		}

		$table.appendTo('#userTableDiv');
	}
}