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
	onLoad="getItemsTransaction('chefFormDetails', 'received');getItemsTransaction('chefFormDetails','pending')">
	<form id="chefFormDetails" name="chefFormDetails">
		<nav class="navbar navbar-inverse">
			<div class="container">
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/">Foodee</a></li>
						<li><a href="/customerOrder">Customer Order</a></li>
						<li class="active"><a href="/chef">Chef</a></li>
						<li><a href="/admin">Admin</a></li>
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
				<h1>Welcome ${userName}</h1>
			</div>

			<div class="panel panel-success">
				<div class="panel-heading">Pending Job List</div>
				<div class="panel-body">
					<div class="table-responsive" id="chefPendingJobTableDiv"></div>
				</div>
			</div>

			<div class="panel panel-success">
				<div class="panel-heading">Next Job List</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="input-group">
								<span class="input-group-addon">User Id</span> <input readOnly
									type="text" name="userId" id="userId" value="${userId}"
									onkeypress="return isNumberKey(event)" class="form-control"
									placeholder="Provide the Chef Id"
									aria-describedby="basic-addon1">
							</div>
						</div>
					</div>
					<br>
					<div id="chefNextJobValidationMessage"></div>
				</div>
				<div class="panel-body">
					<div class="table-responsive" id="chefNextJobTableDiv"></div>
				</div>
			</div>
			<div class="modal fade" id="chefValidationModalDiv" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Validation Message</h4>
						</div>
						<div class="modal-body">
							<div class="text-center">
								<p id="chefValidationModal"></p>
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