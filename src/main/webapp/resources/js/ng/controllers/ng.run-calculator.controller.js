angular.module('ng.run-calculator.controller', [])
    .controller('RunCalculatorController', ['$scope', 'RunCalculatorService', 'Util', '$routeParams',
        function ($scope, RunCalculatorService, Util, $routeParams) {
        
    		var filtersDefault = {};
    		$scope.filters={};
    		
    		$scope.pageLength =  parseInt(25);
    		$scope.currentPage = parseInt(1);
    		
    		$scope.loading=true;

    		$scope.RunCalculator = {};
    		
    		$scope.getRunCalculators = function(params) {
    			RunCalculatorService.getAll(params).then(function(response){
    				console.log("Succesful first request!");
    				$scope.RunCalculator.list = response.content;
    				$scope.loading = false;
    				$scope.RunCalculator.totalElements = response.totalElements;
                    $scope.RunCalculator.totalPages = response.totalPages;
                    $scope.RunCalculator.numberOfElements = response.numberOfElements;
    			});
    		};
    		
    		$scope.RunCalculator.Add = function() {
//    			if (!$scope.filters.directive || !$scope.filters.runDate) {
//    				return ConfirmService.open('Filter options must be selected!', null, true);
//    			}
    		};
    		
    		$scope.RunCalculator.MasterCheckbox = function(masterCheck) {
                for (var i= $scope.RunCalculator.list.length; i--;)
                    $scope.RunCalculator.list[i].$checked = masterCheck;
            };
            
            RunCalculatorService.filterOptions().then(function(response){
            	$scope.filterOptions = Util.parseFilterOption( response );
            	angular.copy($scope.filters, filtersDefault);
            	window.filter = $scope.filterOptions;
            });
    		
    		// Get all all run calculations
    		$scope.getRunCalculators({length : $scope.pageLength, page: $scope.currentPage-1});
        }]
    );
