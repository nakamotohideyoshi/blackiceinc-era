angular.module('ng.RegulatoryReportCTRL', [])
    .filter('dashToSpace', function () {
        return function (text) {
            return text.replace(/-/g, ' ');
        };
    })
    .controller('RegulatoryReportCTRL', ['$scope', '$routeParams', '$http', '$sce', '$location','$route', '$cacheFactory',
        function ($scope, $routeParams, $http, $sce, $location,$route, $cacheFactory) {


            $scope.reportLinks = {};
            $scope.reportDetailView = false;
            $scope.reportType = $routeParams.type;
            $scope.reportSubtype = $routeParams.subtype;
            $scope.enableRefresh = true;

            $http.get('resources/cognos/cognos.json?'+Math.random()).
                success(function (data) {
                    if ($routeParams.subtype) {
                        $scope.reportLinks = data.filter(function (elem) {
                            return elem.hasOwnProperty("directory") && elem.directory.replace(/ /g,"-") === $routeParams.type;
                        })[0].content.filter(function (elem) {
                                return elem.hasOwnProperty("directory") && elem.directory.replace(/ /g,"-") === $routeParams.subtype;
                            })[0].content.filter(function (elem) {
                                return elem.hasOwnProperty("report");
                            });
                    } else {
                        $scope.reportLinks = data.filter(function (elem) {
                            return elem.hasOwnProperty("directory") && elem.directory.replace(/ /g,"-") === $routeParams.type;
                        })[0].content.filter(function (elem) {
                                return elem.hasOwnProperty("report");
                            });
                    }

                });

            $scope.showiframe = function (url) {
                document.getElementById("iframeID").contentWindow.location.replace(url);
                $scope.reportDetailView = true;
                history.pushState($location.path());
            };

            window.onpopstate = function (event) {
                $route.reload();
            };

            $scope.refresh = function(){
                $scope.enableRefresh=false;
                $http.get('api/refreshReportJson').
                    success(function (data) {
                        console.log(data);
                        window.location.reload();
                    });
            }
        }]);