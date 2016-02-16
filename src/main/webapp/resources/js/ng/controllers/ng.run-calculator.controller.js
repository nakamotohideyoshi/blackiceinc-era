angular.module('ng.run-calculator.controller', [])
    .controller('RunCalculatorController', ['$scope', '$timeout', 'RunCalculatorService', 'Util', '$routeParams', 'VNotificationService', '$location', 'ConfirmService',
        function ($scope, $timeout, RunCalculatorService, Util, $routeParams, VNotificationService, $location, ConfirmService) {
            var CONSTANT_id = 'id';
            var CONSTANT_snapshotDate = 'snapshotDate';
            var CONSTANT_loadJobNbr = 'loadJobNbr';
            var CONSTANT_scenarioId = 'scenarioId';
            var CONSTANT_status = 'status';

    		var filtersDefault = {};
    		$scope.filters={};

    		var initializing = true;
    		$scope.pageLength =  parseInt($location.search().length ||  25);
    		$scope.currentPage = parseInt($location.search().page ||  1);
    		
    		$scope.loading=true;
    		$scope.disableBtnRunCalculator = true;
            $scope.disableBtnCloseRun = true;

    		$scope.RunCalculator = {};
    		
    		$scope.getRunCalculators = function(params) {
    			RunCalculatorService.getAll(params).then(function(response){
    				$scope.RunCalculator.list = response.content;
    				$scope.loading = false;
    				$scope.RunCalculator.totalElements = response.totalElements;
                    $scope.RunCalculator.totalPages = response.totalPages;
                    $scope.RunCalculator.numberOfElements = response.numberOfElements;
    			});
    		};

    		$scope.filterTable = function() {
    		    Util.removeNulls($scope.filters);
                var params = {};

                var snapshotDate = $scope.filters.snapshotDate;
                var loadJobNbr = $scope.filters.loadJobNbr;
                var scenarioId = $scope.filters.scenarioId;

                params.length=$scope.pageLength;
                params.page = $scope.currentPage - 1;

                if (snapshotDate){
                    params[ CONSTANT_snapshotDate ] = snapshotDate;
                }

                if (loadJobNbr){
                    params[ CONSTANT_loadJobNbr ] = loadJobNbr;
                }

                if (scenarioId){
                    params[ CONSTANT_scenarioId ] = scenarioId;
                }

                $scope.loading=true;
                $scope.getRunCalculators( params );
    		}
    		
    		$scope.RunCalculator.Add = function() {
    			if (!$scope.filters.snapshotDate || !$scope.filters.loadJobNbr
    			    || !$scope.filters.scenarioId ) {
    				return ConfirmService.open('Filter options must be selected!', null, true);
    			}

    			$('.wrapper:first').scrollTo(0);
    			var newItem = {
    			    id           : null,
    			    snapshotDate : $scope.filters.snapshotDate,
    			    loadJobNbr   : $scope.filters.loadJobNbr,
    			    scenarioId   : $scope.filters.scenarioId,
    			    status       : ''
    			};

    			$scope.RunCalculator.list.unshift({
    			    $edit: true, $new: true, newItem: newItem
    			});

    			$scope.RunCalculator.$isDisabled = true;
    		};

    		$scope.RunCalculator.Cancel = function(removeDeletedOnly) {
    		    if (!removeDeletedOnly) {
    		        $scope.RunCalculator.$isDisabled = false;
    		    }

    		    $scope.RunCalculator.$emptyError = false;
    		    for (var i=$scope.RunCalculator.list.length; i--;) {
                    if(removeDeletedOnly) {
                        if($scope.RunCalculator.list[i].$deleted)
                            $scope.RunCalculator.list.splice(i,1);
                    } else {
                        if($scope.RunCalculator.list[i].$edit || $scope.RunCalculator.list[i].$deleted)
                            $scope.RunCalculator.list.splice(i,1);
                    }
                }
    		};

    		$scope.RunCalculator.Save = function() {
                var count = 0;
                var newItems = [];

                for (var i = 0, len = $scope.RunCalculator.list.length; i < len; i++) {
                    if($scope.RunCalculator.list[i].$new) {
                        newItems.push({ data:$scope.RunCalculator.list[i].newItem, hashKey:$scope.RunCalculator.list[i].$$hashKey });
                    }
                }

                // Finds duplicates within editable rows
                function compressArray(original) {
                    var compressed = [];
                    // make a copy of the input array
                    var copy = [];
                    angular.copy( original , copy);

                    // first loop goes over every element
                    for (var i = 0; i < original.length; i++) {
                        var myCount = 0;
                        var hashKeys = [];

                        // loop over every element in the copy and see if it's the same
                        for (var w = 0; w < copy.length; w++) {
                            if( angular.equals(original[i].data, copy[w].data) ){
                                myCount++;
                                hashKeys.push(copy[w].hashKey);
                                delete copy[w].data;
                            }
                        }

                        if (myCount > 0) {
                            var a = {};
                            a.value = original[i].data;
                            a.count = myCount;
                            a.hashKeys = hashKeys;
                            compressed.push(a);
                        }
                    }

                    return compressed;
                }

                var isError = false;
                var newArray = compressArray(newItems);

                // check if compressArray() function found any duplicates
                // if found highlight with error
                newArray.forEach(function(value){
                    if(value.count > 1) {
                        value.hashKeys.forEach(function(hashKey){
                            $scope.AssignError(hashKey, true);
                            isError=true;
                        });
                    } else {
                        $scope.AssignError(value.hashKeys[0], false);
                    }
                });

                if(isError) {
                    VNotificationService.error('Duplicate entry');
                }

                /**
                 * For loop will check if newly created rows have any duplicates in database
                 * if found it will stop the saving and prompts to correct the data
                 *
                 * @param  {Number} var i             [description]
                 */
                for (var i = 0, len = $scope.RunCalculator.list.length; i < len; i++) {
                    if($scope.RunCalculator.list[i].$new && !$scope.RunCalculator.list[i].$error) {
                        count++;
                        var row = $scope.RunCalculator.list[i].newItem;
                        var params = { hashKey : $scope.RunCalculator.list[i].$$hashKey };

                        if (row.snapshotDate) {
                            params[ CONSTANT_snapshotDate ] = row.snapshotDate;
                        }

                        if (row.loadJobNbr) {
                            params[ CONSTANT_loadJobNbr ] = row.loadJobNbr;
                        }

                        if (row.scenarioId) {
                            params[ CONSTANT_scenarioId ] = row.scenarioId;
                        }

                        Util.removeNulls(params);

                        if(isError) continue;

                        $scope.RunCalculator.$saving = true;
                        RunCalculatorService.generateCheck(params).then(function(response){
                              $scope.RunCalculator.$saving = false;
                              $scope.AssignError(response.hashKey, response.exists);
                              if(!response.exists) {
                                  count--;
                                  saveNow();
                              } else {
                                  VNotificationService.error('Duplicate entry');
                              }
                        });

                        function saveNow() {
                            if(count === 0) {
                                for (var i = 0; i < $scope.RunCalculator.list.length; i++) {
                                    if($scope.RunCalculator.list[i].$new) {
                                        $scope.RunCalculator.$saving = true;
                                        RunCalculatorService.create($scope.RunCalculator.list[i].newItem).then(function(response){
                                            $scope.RunCalculator.totalElements = response.totalElements;
                                            $scope.RunCalculator.$saving = false;
                                            $scope.RunCalculator.list.unshift(response.content);
                                            $scope.RunCalculator.Cancel();
                                        });
                                    }
                                }
                            }
                            return;
                        };

                    }
                }
    		};

    		$scope.RunCalculator.Delete = function() {
    		    ConfirmService.open('Are you sure you want to delete?', function(){
    		        var idListStr = '';
    		        for (var i= $scope.RunCalculator.list.length; i--;) {
    		            if($scope.RunCalculator.list[i].$checked) {
                            $scope.RunCalculator.list[i].$deleted = true;
                            if ($scope.RunCalculator.list[i][ CONSTANT_id ]) {
                              idListStr += $scope.RunCalculator.list[i][ CONSTANT_id ] +'|';
                            }
                        }
    		        }

    		        idListStr = idListStr.substring(0, idListStr.length - 1);

    		        if( idListStr ) {
    		            $scope.RunCalculator.$saving = true;
                        RunCalculatorService.remove( { idListStr: idListStr } ).then(function(response){
                            $scope.RunCalculator.totalElements = response.totalElements;
                            $scope.RunCalculator.$saving = false;
                            $scope.RunCalculator.Cancel(true);
                        });
    		        }
    		    });
    		};

    		$scope.addNewRunCalculator = function() {
    		    $scope.hideValidityStyle = true;
                $scope.newRunCalculator = {
                    id           : null,
                    snapshotDate : '',
                    loadJobNbr   : '',
                    scenarioId   : '',
                    status       : ''
                };

    		    angular.element('#runCalculatorModal').addClass('md-show');
    		};

    		$scope.closeRunCalculatorModal = function () {
                angular.element('#runCalculatorModal').removeClass('md-show');
            };

            $scope.saveRunCalculatorModal = function() {
                if (!$scope.newRunCalculatorForm.$invalid) {
                    var row = $scope.newRunCalculator;

                    $scope.RunCalculator.$saving = true;
                    RunCalculatorService.create($scope.newRunCalculator).then(function(response){
                        $scope.RunCalculator.$saving = false;
                        $scope.RunCalculator.totalElements = response.totalElements;
                        var result = response.content;
                        result.$checked = true;
                        $scope.RunCalculator.list.unshift(response.content);
                        $scope.closeRunCalculatorModal();

                        if ($scope.filterOptions.snapshotDate.indexOf(response.content.snapshotDate)==-1){
                            $scope.filterOptions.snapshotDate.push(response.content.snapshotDate);
                        }

                        if ($scope.filterOptions.loadJobNbr.indexOf(response.content.loadJobNbr)==-1){
                            $scope.filterOptions.loadJobNbr.push(response.content.loadJobNbr);
                        }

                        if ($scope.filterOptions.scenarioId.indexOf(response.content.scenarioId)==-1){
                            $scope.filterOptions.scenarioId.push(response.content.scenarioId);
                        }

                        $scope.setRunCalculationBtnsAvailability();

                    }, function(response){
                        $scope.RunCalculator.$saving = false;
                        ConfirmService.open(response, null, true);
                    });
                }else{
                    $scope.hideValidityStyle = false;
                    ConfirmService.open('Please fill in required fields.', null, true);
                }

            };


    		$scope.RunSelectedCalculator = function() {
                var checkedRow = getFirstCheckedRow();
                $scope.loading = true;
                RunCalculatorService.runCalculation( checkedRow ).then(function(){
                    $scope.loading = false;
                }, function(response){
                    $scope.loading = false;
                    ConfirmService.open(response, null, true);
                });
    		};

    		$scope.CloseSelectedRun = function() {
                var checkedRow = getFirstCheckedRow();
                $scope.loading = true;
                RunCalculatorService.closeCalculation( checkedRow.id ).then(function(response){
                    for (var i = 0; i < $scope.RunCalculator.list.length; i++) {
                        if($scope.RunCalculator.list[i].id === checkedRow.id ) {
                            $scope.RunCalculator.list[i] = response.content;
                        }
                    }
                    $scope.loading = false;
                    $scope.setRunCalculationBtnsAvailability();
                });
    		};

    		$scope.resetFilter = function() {
    		    $scope.filters = {};
                angular.copy(filtersDefault, $scope.filters);
                $scope.filterTable();
    		}

            $scope.setRunCalculationBtnsAvailability = function() {
                var enabled = shouldCalculationBtnsBeEnabled();
                $scope.disableBtnRunCalculator = !enabled;
                $scope.disableBtnCloseRun = !enabled;
            }

    		shouldCalculationBtnsBeEnabled = function() {
    		    var result = true;

    		    var checkedRow;
                for (var i = 0; i < $scope.RunCalculator.list.length; i++) {
                    if(!$scope.RunCalculator.list[i].$deleted
                            &&!$scope.RunCalculator.list[i].$new
                            && $scope.RunCalculator.list[i].$checked) {
                       if (checkedRow){
                            result = false;
                            break;
                       }else {
                            if ($scope.RunCalculator.list[i][ CONSTANT_status ] != 'Closed'){
                                checkedRow = $scope.RunCalculator.list[i];
                            } else {
                                result = false;
                                break;
                            }
                       }
                    }
                }

                if (!checkedRow){
                    result = false;
                }

                return result;
    		};

    		getFirstCheckedRow = function() {
    		    for (var i = 0; i < $scope.RunCalculator.list.length; i++) {
                    if($scope.RunCalculator.list[i].$checked) {
                        return $scope.RunCalculator.list[i];
                    }
                }
    		}

    		// function will highlight errored rows
            $scope.AssignError = function(hashKey, bool) {
                for (var i = $scope.RunCalculator.list.length - 1; i >= 0; i--) {
                    if($scope.RunCalculator.list[i].$$hashKey === hashKey) {
                        $scope.RunCalculator.list[i].$error = bool;
                        return false;
                    }
                }
            };
    		
    		$scope.RunCalculator.MasterCheckbox = function(masterCheck) {
                for (var i= $scope.RunCalculator.list.length; i--;)
                    $scope.RunCalculator.list[i].$checked = masterCheck;
            };

            $scope.maxRows = function () {
                var data_length = ($scope.RunCalculator.list) ? $scope.RunCalculator.list.length : $scope.pageLength;
                var ret = Math.max($scope.RunCalculator.totalElements, data_length);
                return ret;
            };


            $scope.maxPages = function () {
                if($scope.maxRows() === 0) {
                    return 1;
                }
                return Math.ceil($scope.maxRows() / $scope.pageLength);
            };

            $scope.pageForward = function () {
                $scope.currentPage += 1;
            };

            $scope.pageBackward = function () {
                $scope.currentPage -= 1;
            };

            $scope.pageToLast = function () {
                $scope.currentPage = $scope.RunCalculator.totalPages;
            };

            $scope.pageToFirst = function () {
                $scope.currentPage = 1;
            };

            $scope.cantPageForward = function() {
                var curPage = $scope.currentPage;
                var maxPages = $scope.maxPages();
                var data_length = ($scope.RunCalculator.list) ? $scope.RunCalculator.list.length : $scope.pageLength;
                if ($scope.RunCalculator.totalElements > 0) {
                    return curPage >= maxPages;
                } else {
                    return data_length < 1;
                }
            };

            $scope.cantPageToLast = function() {
                if ($scope.RunCalculator.totalElements > 0) {
                    return $scope.cantPageForward();
                } else {
                    return true;
                }
            };

            $scope.cantPageBackward = function() {
                var curPage = $scope.currentPage;
                return curPage <= 1;
            };

            /**
             * Table pagination controllers
             * @type {Boolean}
             */
            $scope.$watchGroup(['currentPage', 'pageLength'], function() {
                if (initializing) {
                    $timeout(function() { initializing = false; });
                } else {
                    //console.log('page changed to ' + $scope.currentPage);
                    $location.search('page', $scope.currentPage);
                    $location.search('length', $scope.pageLength);
                    $scope.filterTable();
                }
            });

            RunCalculatorService.filterOptions().then(function(response){
            	$scope.filterOptions = response;
            	angular.copy($scope.filters, filtersDefault);
            	window.filter = $scope.filterOptions;
            });
    		
    		// Get all all run calculations
    		$scope.getRunCalculators({length : $scope.pageLength, page: $scope.currentPage-1});
        }]
    );
