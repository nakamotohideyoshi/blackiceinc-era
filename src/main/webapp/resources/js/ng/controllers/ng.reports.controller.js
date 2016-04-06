angular.module('ng.reports.controller', [])
    .controller('ReportsController', ['$scope', '$routeParams', '$http', '$sce', '$location',
        function ($scope, $routeParams, $http, $sce, $location) {

            $scope.showiframe = function (url) {
                document.getElementById("iframeID").contentWindow.location.replace(url);
                $scope.reportDetailView = true;
            };

            $scope.showiframe($scope.ibmcognosUrl);
        }]);