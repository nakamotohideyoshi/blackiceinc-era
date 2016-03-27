angular.module('ng.credit-risk.controller', [])
    .controller('CreditRiskController', ['$scope', 'Util', 'CreditRiskService', '$routeParams', '$location',
        function ($scope, Util, CreditRiskService, $routeParams, $location) {
                $scope.mode = 'wholesale';

            $scope.Filter = {
                filterOptions: {},
                filterStaticOptions: {riskRating :[10,20,30,40,50,60,70,80,90,100]},
                filterSelection: {}
            };

            var initializing = true;

            $scope.loading = true;

            $scope.CreditRisk = {};

            $scope.getCreditRisk = function(params) {
                $scope.loading = false;
            };

            $scope.filterTable = function() {

            };

            CreditRiskService.getFilterOptions().then(function (response){
                $scope.Filter.filterOptions = response;
            });

        }
    ]);
