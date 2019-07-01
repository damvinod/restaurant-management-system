<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Foodee Restaurant</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<script src="js/custom.js"></script>
<script src="js/jquery.js"></script>
<script src="js/jquery.serializejson.js"></script>
<script type="text/javascript"
	src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<body
	onLoad="getAllMenuItems('customerFormDetails', 'customerOrderMenu');hideShowDiv('customerFinalizationDiv', 'hide');">
	<form id="customerFormDetails" name="customerFormDetails">
		<input type="hidden" id="menuItemsCount" name="menuItemsCount"
			value="0" />
		<nav class="navbar navbar-inverse">
			<div class="container">
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/">Foodee</a></li>
						<li class="active"><a href="/customerOrder">Customer
								Order</a></li>
						<li><a href="/chef">Chef</a></li>
						<li><a href="/admin">Admin</a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="container">
			<div class="container-welcome">
				<h1>Choose the items from below menu</h1>
			</div>
			<div class="panel panel-success">
				<div class="panel-heading">Menu</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="input-group">
								<span class="input-group-addon">Table No</span> <input
									onkeypress="return isNumberKey(event)" type="text"
									name="tableNumber" id="tableNumber" class="form-control"
									placeholder="Provide the table number"
									aria-describedby="basic-addon1">
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body" id="customerSelctionDiv">
					<div class="table-responsive" id="menuTableDiv"></div>

					<div id="customerSelectionValidationMessage"></div>
					
					<div class="btn-toolbar" role="toolbar"
						aria-label="Toolbar with button groups">
						<div class="btn-group mr-2" role="group" aria-label="First group">
							<button type="button" onclick="resetCustomerSelection();"
							class="btn btn-secondary">Reset Selection</button>
							
						</div>
						<div class="btn-group mr-2" role="group" aria-label="Second group">
							<button type="button" onclick="showCustomerOrderSummary('customerFormDetails');"
							class="btn btn-secondary">Show Summary</button>
							
						</div>
					</div>
				</div>
				<div class="panel-body" id="customerFinalizationDiv">
					<div class="table-responsive" id="confirmMenuTableDiv"></div>
					<div id="customerConfirmationValidationMessage"></div>
					
					<div class="btn-toolbar" role="toolbar"
						aria-label="Toolbar with button groups">
						<div class="btn-group mr-2" role="group" aria-label="First group">
							<button type="button" onclick="goBackCustomerOrder('customerFormDetails');"
							class="btn btn-secondary">Go Back</button>
							
						</div>
						<div class="btn-group mr-2" role="group" aria-label="Second group">
							<button type="button" onclick="saveCustomerOrder('customerFormDetails');"
							class="btn btn-secondary">Confirm Order</button>
							
						</div>
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