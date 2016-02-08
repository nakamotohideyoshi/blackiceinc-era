angular.module("CalculatorModule", [])
    .controller("CalcControl", ['$scope', function ($scope) {
        $scope.variable1 = 500;

        $scope.calculated = {};
        $scope.calculated.total=0;

        $scope.input1 = 100;
        $scope.input2 = 150;
        $scope.input3 = 90;
        $scope.input4 = 800;


        var sliderBind = function (event, ui) {
            $scope.variable1 = ui.value;
            $scope.updateCalculator();
            $scope.$apply();
        };

        $("#slider1").slider({
            max: 1000,
            value: $scope.variable1,
            slide: sliderBind,
            change: sliderBind
        });

        var sliderInput1 = function (event, ui) {
            $scope.input1 = ui.value;
            $scope.updateCalculator();
            $scope.$apply();
        };
        $("#slider2").slider({
            max: 1000,
            value: $scope.input1,
            slide: sliderInput1,
            change: sliderInput1
        });

        $scope.updateCalculator = function () {
            $scope.calculated.c1 = $scope.input1 * .5;
            $scope.calculated.c2 = $scope.input2 * .6;
            $scope.calculated.c3 = $scope.input3 * .7;
            $scope.calculated.c4 = $scope.input4 * .8;

            $scope.calculated.c5 = $scope.calculated.c1 + $scope.calculated.c2 + $scope.calculated.c3 + $scope.calculated.c4;
            $scope.calculated.total = $scope.calculated.c5 * $scope.variable1;
        }
        $scope.updateCalculator();
    }]);
