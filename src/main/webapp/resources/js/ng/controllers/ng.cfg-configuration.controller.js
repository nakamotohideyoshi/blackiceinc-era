angular.module('ng.cfg-configuration.controller', [])
    .controller('CfgConfigurationController', ['$scope', '$timeout', 'CfgConfigurationService', 'CfgConfigurationState',
        'Util', '$routeParams', 'VNotificationService', '$location', 'ConfirmService',
        function ($scope, $timeout, CfgConfigurationService, CfgConfigurationState, Util, $routeParams,
                  VNotificationService, $location, ConfirmService) {
            var CONSTANT_id = 'id';

            $scope.loading = true;
            $scope.noRowsSelected = true;

            $scope.Configuration = {};
            $scope.ConfigurationModal = {};
            $scope.Filter = {
                form: {
                    name: ''
                }
            };

            var initializing = true;
            var state = CfgConfigurationState.get();

            $scope.pageLength = state.pageLength;
            $scope.currentPage = state.currentPage;
            $scope.Filter.form.name = state.name;

            $scope.getConfigurations = function (params) {
                CfgConfigurationService.getAll(params).then(function (response) {
                    $scope.Configuration.list = response.content;
                    $scope.loading = false;
                    $scope.Configuration.totalElements = response.totalElements;
                    $scope.Configuration.totalPages = response.totalPages;
                    $scope.Configuration.numberOfElements = response.numberOfElements;
                });
            };

            $scope.search = function() {
                saveState();
                $scope.filterTable();
            };

            $scope.resetFilter = function () {
                $scope.Filter.form.name = '';
                saveState();
                $scope.filterTable();
            };

            $scope.download = function (id, name) {
                CfgConfigurationService.download(id).then(function (response) {
                        console.log("download :)");

                        var anchor = angular.element('#downloadLink');
                        anchor.attr({
                            href: 'data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,' + response.data,
                            target: '_blank',
                            download: name
                        })[0].click();

                    },
                    function (response) {

                    });


            };

            $scope.filterTable = function () {
                var params = {};
                params["name"] = $scope.Filter.form.name;
                $scope.loading = true;
                $scope.getConfigurations(params);
            };

            $scope.exportSelectedConfiguration = function () {
                var checkedRow = $scope.getFirstCheckedRow();
                $scope.loading = true;
                CfgConfigurationService.export(checkedRow).then(function () {
                    $scope.loading = false;
                    ConfirmService.open("Export successfully run.", null, true);
                }, function (response) {
                    $scope.loading = false;

                    if ( ! angular.isObject( response.data ) || ! response.data.message) {
                        $.smallBox({
                            title : "An unknown error occurred.",
                            content : "",
                            color : "#A65858",
                            iconSmall : "fa fa-times",
                            timeout : 5000
                        });
                    }else{
                        ConfirmService.open(response.data.message, null, true);
                    }
                });
            };

            $scope.importSelectedConfiguration = function () {
                var checkedRow = $scope.getFirstCheckedRow();
                $scope.loading = true;
                CfgConfigurationService.import(checkedRow).then(function (response) {
                    $scope.loading = false;

                    ConfirmService.open("Import successfully run.", null, true);

                    var importedRow = response.data.content;

                    for (var i = $scope.Configuration.list.length; i--;) {
                        if ($scope.Configuration.list[i][CONSTANT_id] == importedRow[CONSTANT_id]) {
                            $scope.Configuration.list[i].status = importedRow.status;
                        } else {
                            $scope.Configuration.list[i].status = 'NULL';
                        }
                    }
                }, function (response) {
                    $scope.loading = false;

                    if ( ! angular.isObject( response.data ) || ! response.data.message) {
                        $.smallBox({
                            title : "An unknown error occurred.",
                            content : "",
                            color : "#A65858",
                            iconSmall : "fa fa-times",
                            timeout : 5000
                        });
                    }else{
                        ConfirmService.open(response.data.message, null, true);
                    }
                });
            };

            $scope.getFirstCheckedRow = function () {
                for (var i = 0; i < $scope.Configuration.list.length; i++) {
                    if ($scope.Configuration.list[i].$checked) {
                        return $scope.Configuration.list[i];
                    }
                }
            };

            $scope.setBtnsAvailability = function () {
                var numberOfCheckedRows = 0;
                for (var i = 0; i < $scope.Configuration.list.length; i++) {
                    if ($scope.Configuration.list[i].$checked) {
                        numberOfCheckedRows++;
                    }
                }
                $scope.noRowsSelected = (numberOfCheckedRows > 0) ? false : true;
                $scope.canExportImport = (numberOfCheckedRows === 1) ? true : false;
            };

            // pagination functions begin

            $scope.maxRows = function () {
                var data_length = ($scope.Configuration.list) ? $scope.Configuration.list.length : $scope.pageLength;
                var ret = Math.max($scope.Configuration.totalElements, data_length);
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
                $scope.currentPage = $scope.Configuration.totalPages;
            };

            $scope.pageToFirst = function () {
                $scope.currentPage = 1;
            };

            $scope.cantPageForward = function () {
                var curPage = $scope.currentPage;
                var maxPages = $scope.maxPages();
                var data_length = ($scope.Configuration.list) ? $scope.Configuration.list.length : $scope.pageLength;
                if ($scope.Configuration.totalElements > 0) {
                    return curPage >= maxPages;
                } else {
                    return data_length < 1;
                }
            };

            $scope.cantPageToLast = function () {
                if ($scope.Configuration.totalElements > 0) {
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
                    var state = CfgConfigurationState.get();
                    state.currentPage = $scope.currentPage;
                    state.pageLength = $scope.pageLength;

                    $scope.filterTable();
                }
            });

            // pagination functions end

            $scope.Configuration.MasterCheckbox = function (masterCheck) {
                for (var i = $scope.Configuration.list.length; i--;) {
                    $scope.Configuration.list[i].$checked = masterCheck;
                }

                $scope.setBtnsAvailability();
            };

            $scope.Configuration.delete = function () {
                ConfirmService.open('Are you sure you want to delete?', function () {
                    var idListStr = '';
                    for (var i = $scope.Configuration.list.length; i--;) {
                        if ($scope.Configuration.list[i].$checked) {
                            if ($scope.Configuration.list[i][CONSTANT_id]) {
                                idListStr += $scope.Configuration.list[i][CONSTANT_id] + '|';
                            }
                        }
                    }

                    // remove the last line
                    idListStr = idListStr.substring(0, idListStr.length - 1);

                    if (idListStr) {
                        $scope.loading = true;
                        CfgConfigurationService.remove({
                            idListStr: idListStr
                        }).then(function (response) {
                                var responseItems = response.records;
                                var successItemsIndex = [];
                                var errorItemsIndex = [];
                                for (var i = $scope.Configuration.list.length; i--;) {
                                    for (var j = 0; j < responseItems.length; j++) {
                                        if ($scope.Configuration.list[i][CONSTANT_id] == responseItems[j].id) {
                                            if (responseItems[j].success == true) {
                                                successItemsIndex.push(i);
                                            } else if (responseItems[j].success == false) {
                                                errorItemsIndex.push(i);
                                            }
                                        }
                                    }
                                }

                                for (var i = 0; i < successItemsIndex.length; i++) {
                                    $scope.Configuration.list.splice(successItemsIndex[i], 1);
                                }

                                $scope.Configuration.totalElements = response.totalElements;
                                $scope.loading = false;
                                $scope.setBtnsAvailability();
                            },
                            function (response) {
                                $scope.loading = false;
                                ConfirmService.open(response, null, true);
                                $scope.setBtnsAvailability();
                            });
                    }


                });
            };

            $scope.ConfigurationModal.openConfigurationModal = function () {
                $scope.ConfigurationModal.hideValidityStyle = true;
                $scope.ConfigurationModal.file = null;

                angular.forEach(
                    angular.element("input[type='file']"),
                    function (inputElem) {
                        angular.element(inputElem).val(null);
                    });

                angular.element('#configurationModal').addClass('md-show');
            };

            $scope.ConfigurationModal.closeConfigurationModal = function () {
                angular.element('#configurationModal').removeClass('md-show');
            };

            $scope.ConfigurationModal.saveConfiguration = function () {
                if ($scope.ConfigurationModal.file!=null) {
                    var file = $scope.ConfigurationModal.file;

                    var params = {
                        file: file
                    };

                    $scope.ConfigurationModal.$saving = true;
                    CfgConfigurationService.save(params).then(function (response) {
                        $scope.ConfigurationModal.$saving = false;
                        $scope.Configuration.totalElements = response.data.totalElements;
                        var result = response.data.content;
                        result.$checked = true;

                        var index = -1;
                        for (var i = 0; i < $scope.Configuration.list.length; i++) {
                            if (result.fileName == $scope.Configuration.list[i].fileName) {
                                index = i;
                                break;
                            }
                        }

                        if (index != -1) {
                            $scope.Configuration.list.splice(index, 1);
                        }

                        $scope.Configuration.list.unshift(result);

                        $scope.ConfigurationModal.closeConfigurationModal();

                        $scope.setBtnsAvailability();
                    }, function (response) {
                        $scope.ConfigurationModal.$saving = false;
                    });
                } else {
                    $scope.ConfigurationModal.hideValidityStyle = false;
                    ConfirmService.open('Please fill in required fields.', null, true);
                }
            };

            // Get all all configurations
            $scope.filterTable();

            function saveState() {
                var state = CfgConfigurationState.get();
                state.name = $scope.Filter.form.name;
            }
        }
    ]);
