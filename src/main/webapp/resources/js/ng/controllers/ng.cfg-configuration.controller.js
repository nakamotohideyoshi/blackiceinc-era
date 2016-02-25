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
      $scope.ConfigurationModal = {};

      $scope.getConfigurations = function(params) {
        CfgConfigurationService.getAll(params).then(function(response) {
          $scope.Configuration.list = response;
          $scope.loading = false;
        });
      };

      $scope.Configuration.MasterCheckbox = function(masterCheck) {
        for (var i = $scope.Configuration.list.length; i--;)
          $scope.Configuration.list[i].$checked = masterCheck;
      };

      $scope.Configuration.delete = function() {
        ConfirmService.open('Are you sure you want to delete?', function() {

        });
      };

      $scope.ConfigurationModal.openConfigurationModal = function() {
        $scope.ConfigurationModal.hideValidityStyle = true;
        $scope.ConfigurationModal.newConfiguration = {
          id: null,
          name: '',
          documents: [],
          status: ''
        };

        angular.element('#configurationModal').addClass('md-show');
      };

      $scope.ConfigurationModal.closeConfigurationModal = function() {
        angular.element('#configurationModal').removeClass('md-show');
      };

      $scope.ConfigurationModal.saveConfiguration = function() {

      };

      // Get all all configurations
      $scope.getConfigurations({
        length: $scope.pageLength,
        page: $scope.currentPage - 1
      });


    }
  ]);
