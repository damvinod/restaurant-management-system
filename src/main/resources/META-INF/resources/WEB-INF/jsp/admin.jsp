<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>Foodee Restaurant</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<c:url value="/css/bootstrap-toggle.min.css" var="jstlCss1" />
<link href="${jstlCss1}" rel="stylesheet" />

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>

<script src="js/custom.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery.serializejson.js"></script>
<script src="js/bootstrap-toggle.min.js"></script>
<script type="text/javascript"
	src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<body onLoad="getAllMenuItems('adminFormDetails', 'admin');getAllUsers('adminFormDetails');">
	<input type="hidden" id="menuItemsCount" value="0" />
	<form id="adminFormDetails" name="adminFormDetails"
		enctype="multipart/form-data">
		<nav class="navbar navbar-inverse">
			<div class="container">
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/">Foodee</a></li>
						<li><a href="/customerOrder">Customer Order</a></li>
						<li><a href="/chef">Chef</a></li>
						<li class="active"><a href="/admin">Admin</a></li>
					</ul>
					<a href="/logout" type="button"
						class="btn btn-info navbar-btn pull-right"> <span
						class="glyphicon glyphicon-off"></span>
					</a>
				</div>
			</div>
		</nav>

		<div class="container">
			<div class="container-welcome">
				<h1>Welcome Admin</h1>
			</div>
			<div class="panel panel-success">
				<div class="panel-heading">View Dishes</div>
				<div class="panel-body">
					<div class="table-responsive" id="menuTableDiv"></div>
				</div>
			</div>
			<div class="panel panel-success">
				<div class="panel-heading">Create Dishes</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="input-group">
								<span class="input-group-addon">Item Name</span> <input
									type="text" name="description" id="description"
									style="text-transform: uppercase"
									onkeypress="return isAlphaKey(event)" class="form-control"
									placeholder="Name of the item" aria-describedby="basic-addon1">
							</div>
							<br>
							<div class="input-group">
								<span class="input-group-addon">Duration</span> <input
									onkeypress="return isNumberKey(event)" type="text"
									name="duration" id="duration" class="form-control"
									placeholder="Time to prepare the item in seconds"
									aria-describedby="basic-addon1">
							</div>
						</div>
						<div class="col-lg-6">
							<div class="input-group">
								<span class="input-group-addon">Price $</span> <input
									onkeypress="return isNumberKey(event)" type="text" name="price"
									id="price" class="form-control"
									aria-label="Amount (to the nearest dollar)"> <span
									class="input-group-addon">.00</span>
							</div>
							<br>
							<div class="input-group">
								<span class="input-group-addon">Currency</span> <input
									type="text" name="currencyType" id="currencyType"
									style="text-transform: uppercase"
									onkeypress="return isAlphaKey(event)" class="form-control"
									placeholder="Currecncy Type (e.g.) SGD, USD"
									aria-describedby="basic-addon1">
							</div>
						</div>
					</div>
					<br>
					<div class="input-group">
						<span class="input-group-addon">Additional Information</span> <input
							type="text" name="ingredients" id="ingredients"
							style="text-transform: uppercase" class="form-control"
							placeholder="Add info (e.g.) spicy, rice"
							aria-describedby="basic-addon1">
					</div>
					<br> <input id="myfile" name="myfile" type="file"> <br>
					<div id="itemAddValidationMessage"></div>
					<div class="btn-group" role="group" aria-label="...">
						<button type="button"
							onclick="addMenuItem('adminFormDetails','admin');"
							class="btn btn-secondary">Add Dish</button>
					</div>
				</div>
			</div>
			<div class="panel panel-success">
				<div class="panel-heading">Delete Dishes</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-8">
							<div class="input-group">
								<span class="input-group-addon">Reference No</span> <input
									onkeypress="return isNumberKey(event)" type="text" name="id"
									id="referenceNo" class="form-control"
									placeholder="Provide the reference no which can be seen in the Items available section to delete"
									aria-describedby="basic-addon1">
							</div>
						</div>
					</div>
					<br>
					<div id="userDeleteSuccessMessage"></div>
					<div class="btn-group" role="group" aria-label="...">
						<button type="button"
							onclick="deleteMenuItem('adminFormDetails', 'referenceNo');"
							class="btn btn-secondary">Delete Dish</button>
					</div>
				</div>
			</div>
			<div class="panel panel-success">
				<div class="panel-heading">View User</div>
				<div class="panel-body">
					<div class="table-responsive" id="userTableDiv"></div>
				</div>
			</div>
			<div class="panel panel-success">
				<div class="panel-heading">Create User</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="input-group">
								<span class="input-group-addon">User Name</span> <input
									onkeypress="return isAlphaKey(event)" type="text"
									name="userName" id="userName" class="form-control"
									style="text-transform: uppercase"
									placeholder="Name of the User" aria-describedby="basic-addon1">
							</div>
							<br>
							<div class="input-group">
								<span class="input-group-addon">Role</span> <input
									type="checkbox" data-toggle="toggle" id="role" name="role"
									data-on="CHEF" data-off="ADMIN">
							</div>
						</div>
					</div>
					<br>
					<div id="userAddSuccessMessage"></div>
					<div class="btn-group" role="group" aria-label="...">
						<button type="button"
							onclick="addUser('adminFormDetails','admin');"
							class="btn btn-secondary">Add User</button>
					</div>
				</div>
			</div>

			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 id="itemDescription" class="modal-title"></h4>
						</div>
						<div class="modal-body">
							<div class="text-center">
								<img id="ItemPreview" src="" class="img-thumbnail" alt="...">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
<div class="footer">
	<footer>
		Copyright &copy; Foodee Indo chinese Restaurant. All rights reserved.
		<br />
	</footer>
</div>
</html>