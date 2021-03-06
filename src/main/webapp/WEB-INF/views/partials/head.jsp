<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:set var="contextRoot">${pageContext.request.contextPath}</c:set>
		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/font-awesome.min.css">

		<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
		<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/smartadmin-production.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/smartadmin-skins.min.css">

		<!-- SmartAdmin RTL Support is under construction-->
		<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/smartadmin-rtl.min.css">

		<!-- ng-grid styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/ng-grid.css">		
		 
		 		 
		<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/nifty.modal.css"> 
		<link rel="stylesheet" href="${contextRoot}/resources/css/chosen.css">
		<link rel="stylesheet" href="${contextRoot}/resources/css/xeditable.css">

		<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
		<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/demo.min.css">

		<!-- Angular Related CSS -->
		<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/ng.min.css">
		<link rel="stylesheet" type="text/css" href="${contextRoot}/resources/js/ng-slider/css/ng-slider.min.css">

		<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/your_style.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${contextRoot}/resources/css/custom-stylesheet.css">
		

		<!--  ------------------------------------ -->
		

		<!-- FAVICONS -->
		<link rel="shortcut icon" href="${contextRoot}/resources/img/favicon/favicon.ico?v=2" type="image/x-icon">
		<link rel="icon" href="${contextRoot}/resources/img/favicon/favicon.ico?v=2" type="image/x-icon">

		<!-- GOOGLE FONT -->
		<link rel="stylesheet" href="${contextRoot}/resources/css/google-fonts-css.css">

		<!-- Specifying a Webpage Icon for Web Clip
			 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
		<link rel="apple-touch-icon" href="${contextRoot}/resources/img/splash/sptouch-icon-iphone.png">
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
		
		<!-- <link rel="stylesheet" media="screen" href="${contextRoot}/resources/bower_components/handsontable/dist/handsontable.full.css"> -->
