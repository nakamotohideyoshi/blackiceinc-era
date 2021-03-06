<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %><!DOCTYPE html>
<c:set var="contextRoot">${pageContext.request.contextPath}</c:set>
<html lang="en-us" id="extr-page" data-ng-app="smartApp">
<head>
	<meta charset="utf-8">
	<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

	<title> ERA - Enterprise Risk Aggregation </title>
	<meta name="description" content="Login page">
	<meta name="author" content="">

	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<!-- Basic Styles -->
	<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/font-awesome.min.css">

	<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
	<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/smartadmin-production.min.css">
	<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/smartadmin-skins.min.css">

	<!-- SmartAdmin RTL Support is under construction
         This RTL CSS will be released in version 1.5
    <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.min.css"> -->

	<!-- We recommend you use "your_style.css" to override SmartAdmin
         specific styles this will also ensure you retrain your customization with each SmartAdmin update.
    <link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

	<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
	<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/demo.min.css">

	<!-- Angular Related CSS -->
	<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/ng.min.css">

	<!-- FAVICONS -->
	<link rel="shortcut icon" href="${contextRoot}/resources/img/favicon/favicon.ico" type="image/x-icon">
	<link rel="icon" href="${contextRoot}/resources/img/favicon/favicon.ico" type="image/x-icon">

	<!-- GOOGLE FONT -->
	<link rel="stylesheet" href="${contextRoot}/resources/css/google-fonts-css.css">

	<!-- Specifying a Webpage Icon for Web Clip
         Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
	<link rel="apple-touch-icon" href="${contextRoot}/resources/mg/splash/sptouch-icon-iphone.png">
	<link rel="apple-touch-icon" sizes="76x76" href="${contextRoot}/resources/img/splash/touch-icon-ipad.png">
	<link rel="apple-touch-icon" sizes="120x120" href="${contextRoot}/resources/img/splash/touch-icon-iphone-retina.png">
	<link rel="apple-touch-icon" sizes="152x152" href="${contextRoot}/resources/img/splash/touch-icon-ipad-retina.png">

	<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">

	<!-- Startup image for web apps -->
	<link rel="apple-touch-startup-image" href="${contextRoot}/resources/img/splash/ipad-landscape.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
	<link rel="apple-touch-startup-image" href="${contextRoot}/resources/img/splash/ipad-portrait.png" media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
	<link rel="apple-touch-startup-image" href="${contextRoot}/resources/img/splash/iphone.png" media="screen and (max-device-width: 320px)">

	<style>
		.btn-gcd {
			color: #fff;
			background-color: #6c8894;
			border-color: #4A8199;
		}
		#extr-page #header #logo {
			margin-top: 0px;
		}

		#extr-page #header #logo img {
			margin-top: 5px;
			width: 90px;
		}

		#extr-page-header-space {
			line-height: 50px;
		}
		#extr-page #header {
			height: 55px;
		}
	</style>

</head>
<body class="animated fadeInDown">
<!-- possible classes: minified, no-right-panel, fixed-ribbon, fixed-header, fixed-width-->
<header id="header">
	<!--<span id="logo"></span>-->

	<div id="logo-group">
		<span id="logo"> <img src="${contextRoot}/resources/img/ERALogo-3.png" alt="ERA"> </span>

		<!-- END AJAX-DROPDOWN -->
	</div>

	<span id="extr-page-header-space"> <!-- <span class="hidden-mobile">Need an account?</span> -->
		<%--<a href="register.html" class="btn btn-gcd">Create account</a> --%>
	</span>

</header>

<div id="main" role="main">

	<!-- MAIN CONTENT -->
	<div id="content" class="container">

		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
				<h1 class="login-header-big">Enterprise Risk Aggregation</h1>
				<div class="hero">

					<div class="pull-left login-desc-box-l">
						<h4 class="paragraph-header">Welcome to the Enterprise Risk Aggregation (ERA) the new generation in enterprise risk data solutions.</h4>
						<p style="color: lightgrey">ver. ${version}</p>
					</div>

					<img src="${contextRoot}/resources/img/ERALogo-1.png" class="pull-right display-image" alt=""
						 style="width: 230px; margin-right: 0px; margin-top: -45px;">

				</div>



			</div>
			<div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
				<c:if test="${not empty error}">
					<div class="alert alert-danger" role="alert">
						Your login attempt was not successful, try again.
					</div>
				</c:if>
				<div class="well no-padding">
					<form action="<c:url value='/login' />" method='POST' id="login-form" class="smart-form client-form">
						<header>
							Sign In
						</header>

						<fieldset>

							<%--<section>--%>
							<%--<label class="label">Domain</label>--%>
							<%--<label class="select"> <i></i>--%>
							<%--<select name="domain" >--%>
							<%--<option value=""> - Select - </option>--%>
							<%--<option value="north.vib.corp">north.vib.corp</option>--%>
							<%--<option value="south.vib.corp">south.vib.corp</option>--%>
							<%--</select>--%>
							<%--<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> Please select domain</b></label>--%>
							<%--</section>--%>

							<section>
								<label class="label">Username</label>
								<label class="input"> <i class="icon-append fa fa-user"></i>
									<input type="text" name="username" value="" >
									<b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> Please enter username</b></label>
							</section>

							<section>
								<label class="label">Password</label>
								<label class="input"> <i class="icon-append fa fa-lock"></i>
									<input type="password" name="password">
									<b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> Enter your password</b> </label>
								<%--<div class="note">--%>
								<%--<a href="forgotpassword.html">Forgot password?</a>--%>
								<%--</div>--%>
							</section>

							<input id="hiddenURL" type="text" name="urlParam" value="" style="display: none">

							<%--<section>--%>
							<%--<label class="checkbox">--%>
							<%--<input type="checkbox" name="remember" checked="">--%>
							<%--<i></i>Stay signed in</label>--%>
							<%--</section>--%>
						</fieldset>
						<footer>
							<button type="submit" class="btn btn-primary">
								Sign in
							</button>
						</footer>

						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>

				</div>

				<!-- <h5 class="text-center"> - Or sign in using -</h5>

                                <ul class="list-inline text-center">
                                    <li>
                                        <a href="javascript:void(0);" class="btn btn-primary btn-circle"><i class="fa fa-facebook"></i></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);" class="btn btn-info btn-circle"><i class="fa fa-twitter"></i></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0);" class="btn btn-warning btn-circle"><i class="fa fa-linkedin"></i></a>
                                    </li>
                                </ul> -->

			</div>
		</div>
	</div>

</div>

<!--================================================== -->

<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)
<script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>-->

<script src="${contextRoot}/resources/js/libs/jquery-2.0.2.min.js"></script>

<script src="${contextRoot}/resources/js/libs/jquery-ui-1.10.3.min.js"></script>

<!-- BOOTSTRAP JS -->
<script src="${contextRoot}/resources/js/bootstrap/bootstrap.min.js"></script>

<!-- JQUERY VALIDATE -->
<script src="${contextRoot}/resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<!-- JQUERY MASKED INPUT -->
<script src="${contextRoot}/resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

<!--[if IE 8]>

<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

<![endif]-->

<script type="text/javascript">


	$(function() {
		// Validation
		$("#login-form").validate({
			// Rules for form validation
			rules : {
//				domain : {
//					required : true
//				},
				email : {
					required : true,
					email : true
				},
				password : {
					required : true,
					minlength : 3,
					maxlength : 20
				}
			},

			// Messages for form validation
			messages : {
//				domain : {
//					required : 'Please select your domain'
//				},
				email : {
					required : 'Please enter your email address',
					email : 'Please enter a VALID email address'
				},
				password : {
					required : 'Please enter your password'
				}
			},

			// Do not change code below
			errorPlacement : function(error, element) {
				error.insertAfter(element.parent());
			}
		});
	});
	$().ready(function(){
		var url = window.location.hash;
		url = url.substring(1);
		$("#hiddenURL").attr("value", url);
	});
</script>

</body>
</html>