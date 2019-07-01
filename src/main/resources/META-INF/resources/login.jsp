<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Foodee Restaurantt</title>
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
<body>
	<form action="/login.jsp" method="post">
		<nav class="navbar navbar-inverse">
			<div class="container">
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/">Foodee</a></li>
						<li><a href="/customerOrder">Customer Order</a></li>
						<li class="active"><a href="/chef">Chef</a></li>
						<li><a href="/admin">Admin</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="container">

			<div class="container-welcome">
				<h1>Sing In</h1>
			</div>
			<div class="row" style="margin-top: 20px">
				<div
					class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
					<fieldset>

						<c:if test="${param.error ne null}">
							<div class="alert alert-danger">Invalid username and
								password.</div>
						</c:if>

						<c:if test="${param.logout ne null}">
							<div class="alert alert-info">You have been logged out.</div>
						</c:if>

						<div class="form-group">
							<input type="text" name="username" id="username"
								class="form-control input-lg" placeholder="UserName"
								required="true" autofocus="true" />
						</div>
						<div class="form-group">
							<input type="password" name="password" id="password"
								class="form-control input-lg" placeholder="Password"
								required="true" />
						</div>

						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<input type="submit" class="btn btn-lg btn-primary btn-block"
									value="Sign In" />
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6"></div>
						</div>
					</fieldset>
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