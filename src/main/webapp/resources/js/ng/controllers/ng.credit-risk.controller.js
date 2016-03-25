angular.module('ng.credit-risk.controller', [])
    .controller('CreditRiskController', ['$scope', 'Util', 'FilterOptions', '$routeParams', '$location',
        function ($scope, Util, FilterOptions, $routeParams, $location) {
                $scope.mode = 'wholesale';
        }
    ]);
