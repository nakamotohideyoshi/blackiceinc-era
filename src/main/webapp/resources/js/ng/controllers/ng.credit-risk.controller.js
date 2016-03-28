angular.module('ng.credit-risk.controller', [])
    .controller('CreditRiskController', ['$scope', 'Util', 'CreditRiskService', '$routeParams', '$location',
        function ($scope, Util, CreditRiskService, $routeParams, $location) {
            $scope.mode = 'wholesale';

            $scope.Filter = {
                filterOptions: {},
                filterStaticOptions: {riskRating: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]},
                filterSelection: {}
            };

            var initializing = true;
            $scope.pageLength = parseInt($location.search().length || 25);
            $scope.currentPage = parseInt($location.search().page || 1);
            $scope.loading = true;

            $scope.CreditRisk = {};

            $scope.getCreditRiskData = function (params) {
                CreditRiskService.getAll(params).then(function (response) {
                        $scope.CreditRisk.list = response.content;

                        $scope.CreditRisk.totalElements = response.totalElements;
                        $scope.CreditRisk.totalPages = response.totalPages;
                        $scope.CreditRisk.numberOfElements = response.numberOfElements;

                        $scope.loading = false;
                    }, function (response) {
                        $scope.loading = false;
                    }
                );
            };

            $scope.filterTable = function () {

            };

            CreditRiskService.getFilterOptions().then(function (response) {
                $scope.Filter.filterOptions = response;
            });

            $scope.getCreditRiskData({
                length: $scope.pageLength,
                page: $scope.currentPage - 1
            });

        }
    ]);
