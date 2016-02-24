var smartApp = angular.module('smartApp', [
   'ngRoute',
   //'ngAnimate', // this is buggy, jarviswidget will not work with ngAnimate :(
   'ui.bootstrap',
   'localytics.directives',
   'ng.modal.directives',

   'app.controllers',

   'app.main',
   'app.navigation',
   'app.localize',
   'app.activity',
   'xeditable',
   'app.smartui',
   'app.services',
   'app.custom',

   'ngMockE2E',
   'ngCookies',

   // Controllers
   'ng.CreditRiskCTRL',
   'ng.run-calculator.controller',
   'ng.cfg-configuration.controller',
   'ng.maintenance-base.controller',
   'ng.TestingScenariosCTRL',
   'ng.RegulatoryReportCTRL',
   'ng.imf-main.controller',
   'ngSlider',
	'ng.nav'
   ]);
smartApp.config(function($compileProvider){
	$compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|javascript):/);
});
smartApp.config(['$routeProvider', '$provide', function($routeProvider, $provide) {

	$routeProvider
		.when('/', {
			redirectTo: '/credit-risk'
		})

	.when('/', {
		templateUrl: function($routeParams) {
			return 'resources/views/dashboard.html';
		},
		controller: 'CreditRiskController',
		reloadOnSearch: false
	})

    .when('/credit-risk', {
      templateUrl: function($routeParams) {
        return 'resources/views/dashboard.html';
      },
      controller: 'CreditRiskController',
      reloadOnSearch: false
    })

    .when('/market-risk', {
      templateUrl: function($routeParams) {
        return 'resources/views/market-risk.html';
      },
      controller: 'MarketRiskController'
    })

    .when('/operational-risk', {
      templateUrl: function($routeParams) {
        return 'resources/views/operational-risk.html';
      },
      controller: 'OperationalRiskController'
    })

    .when('/run-calculator', {
      templateUrl: function($routeParams) {
        return 'resources/views/run-calculator.html';
      },
      controller: 'RunCalculatorController'
    })

    .when('/cfg-configuration', {
      templateUrl: function($routeParams) {
        return 'resources/views/cfg-configuration.html';
      },
      controller: 'CfgConfigurationController'
    })

    .when('/account-settings', {
      templateUrl: function($routeParams) {
        return 'resources/views/account-settings.html';
      },
      controller: 'AccountSettingsController'
    })

    .when('/stress-testing-scen', {
      templateUrl: function($routeParams) {
        return 'resources/views/stress-testing-scen.html';
      },
      controller: 'TestingScenariosCTRL'
    })

	.when('/calculator', {
		templateUrl: function ($routeParams) {
			return 'resources/views/calculatoriframe.html';
		}
	})

    .when('/stress-testing/imf', {
	    templateUrl: function($routeParams) {
	        return 'resources/views/stress-testing/imf.workbook.html?81';
	  	},
		controller: 'ImfMainController'
    })

    .when('/stress-testing/liquidity', {
	    templateUrl: function($routeParams) {
	        return 'resources/views/stress-testing/liquidity.workbook.html?81';
	  	},
		controller: 'ImfMainController'
    })

    .when('/stress-testing/icaap', {
      templateUrl: function($routeParams) {
        return 'resources/views/stress-testing/ecrc.workbook.html';
      },
      controller: 'ImfMainController'
    })

	.when('/stress-testing/loanpricing', {
      templateUrl: function($routeParams) {
        return 'resources/views/stress-testing/loanpricing.html';
      },
      controller: 'ImfMainController'
    })

    .when('/regulatory-report/:type/:subtype?', {
      templateUrl: function($routeParams) {
        return 'resources/views/regulatory-report.html';
      },
      controller: 'RegulatoryReportCTRL'
    })

		//default path
	.otherwise({
		redirectTo: '/main'
	})

	;

	// with this, you can use $log('Message') same as $log.info('Message');
	$provide.decorator('$log', function($delegate) {
		// create a new function to be returned below as the $log service (instead of the $delegate)
		function logger() {
			// if $log fn is called directly, default to "info" message
			logger.info.apply(logger, arguments);
		}
		// add all the $log props into our new logger fn
		angular.extend(logger, $delegate);
		return logger;
	});

}]);
smartApp.run( function run(  $http, $cookies ){
    $http.defaults.headers.post['X-CSRF-TOKEN'] = $cookies['XSRF-TOKEN'];
    $http.defaults.headers.common['X-CSRF-TOKEN'] = $cookies['XSRF-TOKEN'];
});
smartApp.run(['$rootScope', 'settings', function($rootScope, settings) {
	settings.currentLang = settings.languages[0]; // en
}]);

smartApp.directive('testTable', function(){
});
smartApp.run(function(editableOptions) {
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});

smartApp.run(function($httpBackend){
	var filterOptions ={content: {
			credit_risk : [ {db_type:'Wholesale', id:1 }, {db_type:'Retail', id:2}],
			treatment : [ {rwaTreatmentCode:'AIRB', id:1, label:"label"}, {rwaTreatmentCode:'FIRB', id:2, label:"label2"},{rwaTreatmentCode:'STD', id:3, label:"label3"}],
			asset_class : [
			               {"reportAssetClassCode":'CORP', id:1},
			               {"reportAssetClassCode":'BANK', id:2},
			               {"reportAssetClassCode":'PSE', id:3},
			               {"reportAssetClassCode":'SLC', id:4},
			               {"reportAssetClassCode":'SLHVCRE', id:5},
			               {"reportAssetClassCode":'SMECO', id:6},
			               {"reportAssetClassCode":'SOVEREIGN', id:7},
			               {"reportAssetClassCode":'TRAD', id:8},
			               ],
			               exposure_type : [
			                                {exposureTypeCode: 'Drawn', id:1},
			                                {exposureTypeCode: 'Undrawn', id:2},
			                                {exposureTypeCode: 'Repo', id:3},
			                                {exposureTypeCode: 'OTC', id:4},
			                                {exposureTypeCode: 'OBS', id:5},
			                                ],
			                                scenario : [
			                                            {scenario_id: 'RC', id:1},
			                                            {scenario_id: 'EC', id:2},
			                                            ],
			                                            risk_rating :[
			                                                          {range:10},
			                                                          {range:20},
			                                                          {range:30},
			                                                          {range:40},
			                                                          {range:50},
			                                                          {range:60},
			                                                          {range:70},
			                                                          {range:80},
			                                                          {range:90},
			                                                          {range:100}
			                                                          ]
	} };

	var retail={};
	retail.filterOptions = {
			credit_risk : [ {db_type:'Wholesale', id:1 }, {db_type:'Retail', id:2}],
			treatment : [ {treatment:'AIRB', id:1}, {treatment:'FIRB', id:2},{treatment:'STD', id:3}],
			province : [{province:"QC", id:1},
			            {province:"SK", id:2},
			            {province:"ON", id:3},
			            {province:"BC", id:4},
			            {province:"NB", id:5},
			            {province:"NS", id:6},
			            {province:"AB", id:7}
			            ],
			account_type : [{acc_type:"MTGE"},
			                {acc_type:"CL"},
			                {acc_type:"CC"}],
			            asset_class : [
			                           {asset_class:'HELOC', id:1},
			                           {asset_class:'SMERET', id:2},
			                           {asset_class:'ORET', id:3},
			                           {asset_class:'QRRE', id:4},
			                           {asset_class:'RES', id:5} ],
			               exposure_type : [
			                                {exposure_type: 'Drawn', id:1},
			                                {exposure_type: 'Undrawn', id:2},
			                                {exposure_type: 'Repo', id:3},
			                                {exposure_type: 'OTC', id:4},
			                                {exposure_type: 'OBS', id:5}],
			                                scenario : [
			                                            {scenario_id: 'RC', id:1},
			                                            {scenario_id: 'EC', id:2}]
	};

	$httpBackend.whenGET('mock/filterOptions').respond(filterOptions);
	$httpBackend.whenGET('mock/retaiFilterOptions').respond(retail.filterOptions);
	$httpBackend.whenGET(/^resources\//).passThrough();
	$httpBackend.whenGET(/^js\//).passThrough();
	$httpBackend.whenGET(/^api\//).passThrough();
	$httpBackend.whenGET(/^reports\//).passThrough();
	$httpBackend.whenDELETE(/^api\//).passThrough();
	$httpBackend.whenDELETE().passThrough();
	$httpBackend.whenPUT().passThrough();
	$httpBackend.whenPOST().passThrough();

});



function newPopup(url) {
	popupWindow = window.open(
			url,'popUpWindow','height=700,width=800,left=10,top=10,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes')
}
