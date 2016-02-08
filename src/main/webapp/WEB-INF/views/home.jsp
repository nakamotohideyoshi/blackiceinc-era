<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %><!DOCTYPE html>
<html lang="en-us" data-ng-app="smartApp">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title> ERA - Basel Dashboard </title>
		<meta name="description" content="">
		<meta name="author" content="">
		<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<jsp:include page="partials/head.jsp" />

	</head>
	<body class=" smart-style-2 " data-ng-controller="SmartAppController">

		<!-- POSSIBLE CLASSES: minified, fixed-ribbon, fixed-header, fixed-width, top-menu
			 You can also add different skin classes such as "smart-skin-0", "smart-skin-1" etc...-->

		<!-- HEADER -->
		<header id="header" data-ng-include="'resources/includes/header.html'"></header>
		<!-- END HEADER -->

		<!-- Left panel : Navigation area -->
		<!-- Note: This width of the aside area can be adjusted through LESS variables -->
		<aside id="left-panel"><span data-ng-include="'resources/includes/left-panel.html'" ></span></aside>
		<!-- END NAVIGATION -->

		<!-- MAIN PANEL -->
		<div id="main" role="main">

			<!-- MAIN CONTENT -->
			<div id="content" data-ng-view="" style="min-width: 1165px;" class="view-animate"></div>
			<!-- END MAIN CONTENT -->

		</div>
		<!-- END MAIN PANEL -->

		<!-- PAGE FOOTER -->
		<div class="page-footer"><span data-ng-include="'resources/includes/footer.html'"></span></div>
		<!-- END FOOTER -->

		<!-- SHORTCUT AREA : With large tiles (activated via clicking user name tag)
		Note: These tiles are completely responsive,
		you can add as many as you like
		-->
		<div id="shortcut"><span data-ng-include="'resources/includes/shortcut.html'"></span></div>
		<!-- END SHORTCUT AREA -->
		<div class="md-overlay"></div>

		<jsp:include page="partials/foot.jsp" />

	</body>

</html>