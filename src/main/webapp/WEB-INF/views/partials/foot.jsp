<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script type="text/javascript">
    FileAPI = {
        debug: true,
        //forceLoad: true, html5: false //to debug flash in HTML5 browsers
        //wrapInsideDiv: true, //experimental for fixing css issues
        //only one of jsPath or jsUrl.
        //jsPath: '/js/FileAPI.min.js/folder/',
        //jsUrl: 'yourcdn.com/js/FileAPI.min.js',

        //only one of staticPath or flashUrl.
        //staticPath: '/flash/FileAPI.flash.swf/folder/'
        //flashUrl: 'yourcdn.com/js/FileAPI.flash.swf'
    };
    var USER_ROLE = "${user_role}";
</script>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script>
    if (!window.jQuery) {
        document.write('<script src="resources/js/libs/jquery-2.0.2.min.js"><\/script>');
    }
</script>

<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script>
    if (!window.jQuery.ui) {
        document.write('<script src="resources/js/libs/jquery-ui-1.10.3.min.js"><\/script>');
    }
</script>

<script src="resources/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script>
<script src="resources/js/bootstrap/bootstrap.min.js"></script>

<script src="resources/js/plugin/jquery.floatThead.js"></script>
<script src="resources/js/plugin/scrolltop/jquery.scrollTo.min.js"></script>

<script src="resources/js/notification/SmartNotification.min.js"></script>
<script src="resources/js/smartwidgets/jarvis.widget.min.js"></script>

<script src="resources/js/plugin/jquery-form/jquery-form.min.js"></script>
<script src="resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>
<script src="resources/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

<script src="resources/js/plugin/select2/select2.min.js"></script>
<script src="resources/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>
<script src="resources/js/plugin/msie-fix/jquery.mb.browser.min.js"></script>
<script src="resources/js/plugin/fastclick/fastclick.min.js"></script>

<script src="resources/js/plugin/html2canvas/html2canvas.js"></script>


<script src="resources/js/libs/angular-file-upload-shim.js?_cacheuid=1.0.23"></script>

<!-- AngularJS -->

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular-cookies.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular-route.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular-animate.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular-sanitize.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular-mocks.js"></script>

<script src="resources/js/libs/angular/ui-bootstrap-custom-tpls-0.11.0.js"></script>
<script src="resources/js/plugin/xeditable.min.js"></script>
<script src="resources/js/app.js"></script>
<script src="resources/js/ng-slider/ng-slider.min.js"></script>
<script src="resources/js/ng-slider/ng-slider.tmpl.min.js"></script>

<script src="resources/js/ng/ng.app.js"></script>

<script src="resources/js/libs/angular-file-upload.js?_cacheuid=1.0.23"></script>

<script src="resources/js/ng/controllers/ng.controllers.js"></script>
<script src="resources/js/ng/controllers/ng.CreditRiskCTRL.js"></script>
<script src="resources/js/ng/controllers/ng.run-calculator.controller.js"></script>
<script src="resources/js/ng/controllers/ng.cfg-configuration.controller.js"></script>
<script src="resources/js/ng/controllers/ng.account-settings.controller.js"></script>
<script src="resources/js/ng/controllers/ng.TestingScenariosCTRL.js"></script>
<script src="resources/js/ng/controllers/ng.RegulatoryReportCTRL.js"></script>
<script src="resources/js/ng/controllers/ng.excelController.js"></script>
<script src="resources/js/ng/directives/ng.directives.js"></script>
<script src="resources/js/ng/directives/ng.custom.directives.js"></script>
<script src="resources/js/ng/directives/ng.modal.directives.js"></script>
<script src="resources/js/ng/directives/ng.select2.js"></script>
<script src="resources/js/ng/directives/ng.chosen.directive.js"></script>
<script src="resources/js/ng/ng.services.js"></script>
<script src="resources/js/ng/controllers/ng.maintenance-base.controller.js"></script>
<script src="resources/js/ng/controllers/ng.navCTRL.js"></script>

<!-- flot library -->
<script src="resources/js/plugin/flot/jquery.flot.cust.min.js"></script>
<script src="resources/js/plugin/flot/jquery.flot.resize.min.js"></script>
<script src="resources/js/plugin/flot/jquery.flot.fillbetween.min.js"></script>
<script src="resources/js/plugin/flot/jquery.flot.orderBar.min.js"></script>
<script src="resources/js/plugin/flot/jquery.flot.pie.min.js"></script>
<script src="resources/js/plugin/flot/jquery.flot.tooltip.min.js"></script>
