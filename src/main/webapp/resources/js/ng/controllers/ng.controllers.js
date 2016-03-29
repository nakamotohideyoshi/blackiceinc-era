angular.module('app.controllers', ['ui.bootstrap'])
	.factory('settings', ['$rootScope', function($rootScope){
		// supported languages
		
		var settings = {
			languages: [
				{
					language: 'English',
					translation: 'English',
					langCode: 'en',
					flagCode: 'us'
				},
				{
					language: 'Espanish',
					translation: 'Espanish',
					langCode: 'es',
					flagCode: 'es'
				},
				{
					language: 'German',
					translation: 'Deutsch',
					langCode: 'de',
					flagCode: 'de'
				},
				{
					language: 'Korean',
					translation: 'Ã­â€¢Å“ÃªÂµÂ­Ã¬ï¿½Ëœ',
					langCode: 'ko',
					flagCode: 'kr'
				},
				{
					language: 'French',
					translation: 'franÃƒÂ§ais',
					langCode: 'fr',
					flagCode: 'fr'
				},
				{
					language: 'Portuguese',
					translation: 'portuguÃƒÂªs',
					langCode: 'pt',
					flagCode: 'br'
				},
				{
					language: 'Russian',
					translation: 'Ã‘â‚¬Ã‘Æ’Ã‘ï¿½Ã‘ï¿½Ã�ÂºÃ�Â¸Ã�Â¹',
					langCode: 'ru',
					flagCode: 'ru'
				},
				{
					language: 'Chinese',
					translation: 'Ã¤Â¸Â­Ã¥Å“â€¹Ã§Å¡â€ž',
					langCode: 'zh',
					flagCode: 'cn'
				}
			],
			
		};

		return settings;
		
	}])


	.controller('SmartAppController', ['$scope', function($scope) {
		// your main controller
		
		$scope.userRole = USER_ROLE;
		$scope.ibmcognosUrl = IBMCOGNOS_URL;
		
		
	}])

	.controller('LangController', ['$scope', 'settings', 'localize', function($scope, settings, localize) {
		$scope.languages = settings.languages;
		$scope.currentLang = settings.currentLang;
		$scope.setLang = function(lang) {
			settings.currentLang = lang;
			$scope.currentLang = lang;
			localize.setLang(lang);
		};

		// set the default language
		$scope.setLang($scope.currentLang);

	}])
	
;




