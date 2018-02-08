<!DOCTYPE html>
<html lang="en" ng-app="admitereApp">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title>Admitere</title>

<!-- Include CSS files -->
<jsp:include page="include/css.jsp" />

</head>
<body ng-controller="homeController">

	<i id="container-loader" style="display: none;"
		class="fa fa-spinner fa-pulse fa-spin fa-5x fa-fw"></i>

	<!-- Include main navigation bar -->
	<jsp:include page="include/main-navbar.jsp" />

	<!-- Include second navigation bar -->
	<jsp:include page="include/second-navbar.jsp" />

	<!-- Page container -->
	<div class="page-container">

		<!-- Page content -->
		<div class="page-content">

			<!-- Main content -->
			<div class="content-wrapper" ng-view></div>
			<!-- /main content -->

		</div>
		<!-- /page content -->

	</div>
	<!-- /page container -->

	<!-- Include footer -->
	<jsp:include page="include/footer.jsp" />

	<!-- Include javascripts files -->
	<jsp:include page="include/javascript.jsp" />

</body>
</html>
