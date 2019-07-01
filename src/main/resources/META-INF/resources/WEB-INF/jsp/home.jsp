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

<script type="text/javascript"
	src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/">Foodee</a></li>
					<li><a href="/customerOrder">Customer Order</a></li>
					<li><a href="/chef">Chef</a></li>
					<li><a href="/admin">Admin</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-welcome">
		<div class="starter-template">
			<h1>Welcome to Foodee Restaurant</h1>
			<P></P>
		</div>
	</div>

	<div class="content-story">
		<h3>"Our Story"</h3>
		<P>In recent years, we have become involved in supporting
			charitable events such as the Harry Chapin Food Bank in which we
			collected canned goods, donated soup for the “Empty Bowls” event,
			and donated a percentage of proceeds for “Dine Out for Hunger”.
			Also, we have joined with the Susan G. Komen Foundation for Breast
			Cancer in which we donated a percentage of one night’s profits. We
			also participated in the “Slice of Hope” breast cancer event,
			held at our restaurant. We enjoy giving back to the community and
			helping such needed foundations.</P>
	</div>

	<div class="container-bottom">
		<div class="bottom-img">
			<h3>Moments</h3>
			<P>In recent years, we have become involved in supporting
				charitable events such as the Harry Chapin Food Bank in which we
				collected canned goods.</P>
		</div>
	</div>
</body>
<div class="footer">
	<footer>
		Copyright &copy; Foodee Indo chinese Restaurant. All rights reserved.
		<br />
	</footer>
</div>
</html>