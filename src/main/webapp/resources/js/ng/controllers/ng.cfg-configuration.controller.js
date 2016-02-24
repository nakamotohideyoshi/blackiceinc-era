angular.module('ng.cfg-configuration.controller', [])
  .controller('CfgConfigurationController', ['$scope', '$timeout', 'CfgConfigurationService',
    'Util', '$routeParams', 'VNotificationService', '$location', 'ConfirmService',
    function($scope, $timeout, CfgConfigurationService, Util, $routeParams,
      VNotificationService, $location, ConfirmService) {

      var initializing = true;
      $scope.pageLength = parseInt($location.search().length || 25);
      $scope.currentPage = parseInt($location.search().page || 1);

      $scope.loading = true;

      $scope.Configuration = {};

      $scope.Configuration.MasterCheckbox = function(masterCheck) {
        for (var i = $scope.Configuration.list.length; i--;)
          $scope.Configuration.list[i].$checked = masterCheck;
      };

      $scope.getConfigurations = function(params) {
        CfgConfigurationService.getAll(params).then(function(response) {
          $scope.Configuration.list = response;
          $scope.loading = false;
        });
      };

      // Get all all configurations
      $scope.getConfigurations({
        length: $scope.pageLength,
        page: $scope.currentPage - 1
      });

    }
  ]);
