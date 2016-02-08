angular.module('ng.nav', [])
    .filter('spaceToHyphen', function () {
        return function (text) {
            return text.replace(/ /g,"-");
        };
    })
    .controller('navController', ['$scope', '$http', function ($scope, $http) {
        $scope.rootDir = [];
        $scope.rootParentDir = [];
        $http.get('resources/cognos/cognos.json').
            success(function (data) {
                $scope.rootDir = data.filter(function (elem) {
                    return elem.hasOwnProperty("directory") && elem.content[0].hasOwnProperty("report")
                });
                $scope.rootParentDir = data.filter(function (elem) {
                    return elem.hasOwnProperty("directory") && elem.content[0].hasOwnProperty("directory")
                });
            });
    }]);
