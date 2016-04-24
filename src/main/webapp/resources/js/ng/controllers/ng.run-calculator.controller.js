angular.module('ng.run-calculator.controller', [])
    .controller('RunCalculatorController', ['$scope', '$timeout', 'RunCalculatorService', 'RunCalculatorState',
        'Util', '$routeParams', 'VNotificationService', '$location', 'ConfirmService',
        function ($scope, $timeout, RunCalculatorService, RunCalculatorState, Util, $routeParams,
                  VNotificationService, $location, ConfirmService) {
            var CONSTANT_id = 'id';
            var CONSTANT_snapshotDate = 'snapshotDate';
            var CONSTANT_loadJobNbr = 'loadJobNbr';
            var CONSTANT_scenarioId = 'scenarioId';
            var CONSTANT_status = 'status';

            $scope.Filter = {
                filterOptions: {},
                filtersDefault: {},
                filters: {},
                snapshotDateOptions: {}
            };

            var initializing = true;
            var state = RunCalculatorState.get();
            $scope.pageLength = state.pageLength;
            $scope.currentPage = state.currentPage;
            $scope.Filter.filters.snapshotDate = state.snapshotDate;
            $scope.Filter.filters.loadJobNbr = state.loadJobNbr;
            $scope.Filter.filters.scenarioId = state.scenarioId;

            $scope.loading = true;
            $scope.noRowsSelected = true;
            $scope.disableBtnRunCalculator = true;
            $scope.disableBtnCloseRun = true;

            $scope.RunCalculator = {};

            $scope.getRunCalculators = function (params) {
                RunCalculatorService.getAll(params).then(function (response) {
                    $scope.RunCalculator.list = response.content;
                    $scope.loading = false;
                    $scope.RunCalculator.totalElements = response.totalElements;
                    $scope.RunCalculator.totalPages = response.totalPages;
                    $scope.RunCalculator.numberOfElements = response.numberOfElements;
                });
            };

            $scope.filterTable = function () {
                Util.removeNulls($scope.Filter.filters);
                var params = {};

                params.length = $scope.pageLength;
                params.page = $scope.currentPage - 1;

                var snapshotDate = $scope.Filter.filters.snapshotDate;
                var loadJobNbr = $scope.Filter.filters.loadJobNbr;
                var scenarioId = $scope.Filter.filters.scenarioId;

                if (snapshotDate) {
                    params[CONSTANT_snapshotDate] = snapshotDate;
                }

                if (loadJobNbr) {
                    params[CONSTANT_loadJobNbr] = loadJobNbr;
                }

                if (scenarioId) {
                    params[CONSTANT_scenarioId] = scenarioId;
                }

                $scope.loading = true;
                $scope.getRunCalculators(params);
            };

            $scope.RunCalculator.delete = function () {
                ConfirmService.open('Are you sure you want to delete?', function () {
                    var idListStr = '';
                    for (var i = $scope.RunCalculator.list.length; i--;) {
                        if ($scope.RunCalculator.list[i].$checked) {
                            if ($scope.RunCalculator.list[i][CONSTANT_id]) {
                                idListStr += $scope.RunCalculator.list[i][CONSTANT_id] + '|';
                            }
                        }
                    }

                    // remove the last line
                    idListStr = idListStr.substring(0, idListStr.length - 1);

                    if (idListStr) {
                        $scope.loading = true;
                        RunCalculatorService.remove({
                            idListStr: idListStr
                        }).then(function (response) {
                                var responseItems = response.records;
                                var successItemsIndex = [];
                                var errorItemsIndex = [];
                                for (var i = $scope.RunCalculator.list.length; i--;) {
                                    for (var j = 0; j < responseItems.length; j++) {
                                        if ($scope.RunCalculator.list[i][CONSTANT_id] == responseItems[j].id) {
                                            if (responseItems[j].success == true) {
                                                successItemsIndex.push(i);
                                            } else if (responseItems[j].success == false) {
                                                errorItemsIndex.push(i);
                                            }
                                        }
                                    }
                                }

                                for (var i = 0; i < successItemsIndex.length; i++) {
                                    $scope.RunCalculator.list.splice(successItemsIndex[i], 1);
                                }

                                $scope.RunCalculator.totalElements = response.totalElements;
                                $scope.loading = false;
                            },
                            function (response) {
                                $scope.loading = false;
                                ConfirmService.open(response, null, true);
                            });
                    }
                });
            };

            $scope.addNewRunCalculator = function () {
                $scope.hideValidityStyle = true;
                $scope.newRunCalculator = {
                    id: null,
                    snapshotDate: '',
                    loadJobNbr: '',
                    scenarioId: '',
                    status: ''
                };

                angular.element('#runCalculatorModal').addClass('md-show');
            };

            $scope.closeRunCalculatorModal = function () {
                angular.element('#runCalculatorModal').removeClass('md-show');
            };

            $scope.saveRunCalculatorModal = function () {
                if (!$scope.newRunCalculatorForm.$invalid) {
                    $scope.RunCalculator.$saving = true;
                    RunCalculatorService.create($scope.newRunCalculator).then(function (response) {
                        $scope.RunCalculator.$saving = false;
                        $scope.RunCalculator.totalElements = response.totalElements;
                        var result = response.content;
                        result.$checked = true;
                        $scope.RunCalculator.list.unshift(response.content);
                        $scope.closeRunCalculatorModal();

                        if ($scope.Filter.filterOptions.snapshotDate.indexOf(response.content.snapshotDate) == -1) {
                            $scope.Filter.filterOptions.snapshotDate.push(response.content.snapshotDate);
                        }

                        if ($scope.Filter.filterOptions.loadJobNbr.indexOf(response.content.loadJobNbr) == -1) {
                            $scope.Filter.filterOptions.loadJobNbr.push(response.content.loadJobNbr);
                        }

                        if ($scope.Filter.filterOptions.scenarioId.indexOf(response.content.scenarioId) == -1) {
                            $scope.Filter.filterOptions.scenarioId.push(response.content.scenarioId);
                        }

                        $scope.setRunCalculationBtnsAvailability();

                    }, function (response) {
                        $scope.RunCalculator.$saving = false;
                        ConfirmService.open(response, null, true);
                    });
                } else {
                    $scope.hideValidityStyle = false;
                    ConfirmService.open('Please fill in required fields.', null, true);
                }

            };


            $scope.runSelectedCalculator = function () {
                var checkedRow = $scope.getFirstCheckedRow();
                $scope.loading = true;
                RunCalculatorService.runCalculation(checkedRow).then(function () {
                    $scope.loading = false;
                    ConfirmService.open("Calculation succesfully run.", null, true);
                }, function (response) {
                    $scope.loading = false;
                    ConfirmService.open(response, null, true);
                });
            };

            $scope.closeSelectedRun = function () {
                var checkedRow = $scope.getFirstCheckedRow();
                $scope.loading = true;
                RunCalculatorService.closeCalculation(checkedRow.id).then(function (response) {
                    for (var i = 0; i < $scope.RunCalculator.list.length; i++) {
                        if ($scope.RunCalculator.list[i].id === checkedRow.id) {
                            $scope.RunCalculator.list[i] = response.content;
                        }
                    }
                    $scope.loading = false;
                    $scope.setRunCalculationBtnsAvailability();
                });
            };

            $scope.search = function () {
                saveState();
                $scope.filterTable();
            };

            $scope.resetFilter = function () {
                $scope.Filter.filters = {};
                saveState();
                $scope.filterTable();
            };

            $scope.setRunCalculationBtnsAvailability = function () {
                var enabled = shouldCalculationBtnsBeEnabled();
                $scope.disableBtnRunCalculator = !enabled;
                $scope.disableBtnCloseRun = !enabled;

                var numberOfCheckedRows = 0;
                for (var i = 0; i < $scope.RunCalculator.list.length; i++) {
                    if ($scope.RunCalculator.list[i].$checked) {
                        numberOfCheckedRows++;
                    }
                }
                $scope.noRowsSelected = (numberOfCheckedRows > 0) ? false : true;

            };

            shouldCalculationBtnsBeEnabled = function () {
                var result = true;

                var checkedRow;
                for (var i = 0; i < $scope.RunCalculator.list.length; i++) {
                    if (!$scope.RunCalculator.list[i].$deleted && !$scope.RunCalculator.list[i].$new && $scope.RunCalculator.list[i].$checked) {
                        if (checkedRow) {
                            result = false;
                            break;
                        } else {
                            if ($scope.RunCalculator.list[i][CONSTANT_status] != 'Closed') {
                                checkedRow = $scope.RunCalculator.list[i];
                            } else {
                                result = false;
                                break;
                            }
                        }
                    }
                }

                if (!checkedRow) {
                    result = false;
                }

                return result;
            };

            $scope.getFirstCheckedRow = function () {
                for (var i = 0; i < $scope.RunCalculator.list.length; i++) {
                    if ($scope.RunCalculator.list[i].$checked) {
                        return $scope.RunCalculator.list[i];
                    }
                }
            };

            $scope.RunCalculator.MasterCheckbox = function (masterCheck) {
                for (var i = $scope.RunCalculator.list.length; i--;){
                    $scope.RunCalculator.list[i].$checked = masterCheck;
                }

                $scope.setRunCalculationBtnsAvailability();
            };

            $scope.maxRows = function () {
                var data_length = ($scope.RunCalculator.list) ? $scope.RunCalculator.list.length : $scope.pageLength;
                var ret = Math.max($scope.RunCalculator.totalElements, data_length);
                return ret;
            };


            $scope.maxPages = function () {
                if ($scope.maxRows() === 0) {
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

            $scope.cantPageForward = function () {
                var curPage = $scope.currentPage;
                var maxPages = $scope.maxPages();
                var data_length = ($scope.RunCalculator.list) ? $scope.RunCalculator.list.length : $scope.pageLength;
                if ($scope.RunCalculator.totalElements > 0) {
                    return curPage >= maxPages;
                } else {
                    return data_length < 1;
                }
            };

            $scope.cantPageToLast = function () {
                if ($scope.RunCalculator.totalElements > 0) {
                    return $scope.cantPageForward();
                } else {
                    return true;
                }
            };

            $scope.cantPageBackward = function () {
                var curPage = $scope.currentPage;
                return curPage <= 1;
            };

            /**
             * Table pagination controllers
             * @type {Boolean}
             */
            $scope.$watchGroup(['currentPage', 'pageLength'], function () {
                if (initializing) {
                    $timeout(function () {
                        initializing = false;
                    });
                } else {
                    var state = RunCalculatorState.get();
                    state.currentPage = $scope.currentPage;
                    state.pageLength = $scope.pageLength;

                    $scope.filterTable();
                }
            });

            RunCalculatorService.filterOptions().then(function (response) {
                $scope.Filter.filterOptions = response;
                angular.copy($scope.Filter.filters, $scope.Filter.filtersDefault);
            });

            RunCalculatorService.snapshotDateOptions().then(function (response) {
                $scope.Filter.snapshotDateOptions = response;
            });

            $scope.filterTable();

            function saveState() {
                var state = RunCalculatorState.get();
                state.snapshotDate = $scope.Filter.filters.snapshotDate;
                state.loadJobNbr = $scope.Filter.filters.loadJobNbr;
                state.scenarioId = $scope.Filter.filters.scenarioId;
            }
        }
    ]);
