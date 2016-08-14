angular.module('ng.account-settings.controller', [])
    .controller('AccountSettingsController', ['$scope', '$timeout', 'AccountSettingsService',
        'Util', '$routeParams', 'VNotificationService', '$location', 'ConfirmService',
        function ($scope, $timeout, AccountSettingsService, Util, $routeParams,
                  VNotificationService, $location, ConfirmService) {
            var CONSTANT_id = 'id';

            var initializing = true;
            $scope.pageLength = parseInt($location.search().length || 25);
            $scope.currentPage = parseInt($location.search().page || 1);

            $scope.loading = true;
            $scope.noRowsSelected = true;

            $scope.AccountSettings = {};
            $scope.UserModal = {};

            $scope.getAccountSettings = function (params) {
                AccountSettingsService.getAll(params).then(function (response) {
                    $scope.AccountSettings.list = response.content;
                    $scope.loading = false;
                    $scope.AccountSettings.totalElements = response.totalElements;
                    $scope.AccountSettings.totalPages = response.totalPages;
                    $scope.AccountSettings.numberOfElements = response.numberOfElements;
                });
            };

            $scope.getRoles = function () {
                AccountSettingsService.getRoles().then(function (response) {
                    $scope.UserModal.roles = response;
                });
            };

            $scope.filterTable = function () {
                Util.removeNulls($scope.Filter.filters);
                var params = {};

                params.length = $scope.pageLength;
                params.page = $scope.currentPage - 1;

                $scope.loading = true;
                $scope.getAccountSettings(params);
            };

            $scope.setBtnsAvailability = function () {
                var numberOfCheckedRows = 0;
                for (var i = 0; i < $scope.AccountSettings.list.length; i++) {
                    if ($scope.AccountSettings.list[i].$checked) {
                        numberOfCheckedRows++;
                    }
                }
                $scope.noRowsSelected = (numberOfCheckedRows > 0) ? false : true;
                $scope.canEdit = (numberOfCheckedRows === 1) ? true : false;
            };


            $scope.AccountSettings.delete = function () {
                ConfirmService.open('Are you sure you want to delete?', function () {
                    var idListStr = '';
                    for (var i = $scope.AccountSettings.list.length; i--;) {
                        if ($scope.AccountSettings.list[i].$checked) {
                            if ($scope.AccountSettings.list[i][CONSTANT_id]) {
                                idListStr += $scope.AccountSettings.list[i][CONSTANT_id] + '|';
                            }
                        }
                    }

                    // remove the last line
                    idListStr = idListStr.substring(0, idListStr.length - 1);

                    if (idListStr) {
                        $scope.loading = true;
                        AccountSettingsService.remove({
                            idListStr: idListStr
                        }).then(function (response) {
                                var responseItems = response.records;
                                var successItemsIndex = [];
                                var errorItemsIndex = [];
                                for (var i = $scope.AccountSettings.list.length; i--;) {
                                    for (var j = 0; j < responseItems.length; j++) {
                                        if ($scope.AccountSettings.list[i][CONSTANT_id] == responseItems[j].id) {
                                            if (responseItems[j].success == true) {
                                                successItemsIndex.push(i);
                                            } else if (responseItems[j].success == false) {
                                                errorItemsIndex.push(i);
                                            }
                                        }
                                    }
                                }

                                for (var i = 0; i < successItemsIndex.length; i++) {
                                    $scope.AccountSettings.list.splice(successItemsIndex[i], 1);
                                }

                                $scope.AccountSettings.totalElements = response.totalElements;
                                $scope.loading = false;
                            },
                            function (response) {
                                $scope.loading = false;
                                ConfirmService.open(response.message, null, true);
                            });
                    }

                    $scope.setBtnsAvailability();
                });
            };

            $scope.openEditUserModal = function () {
                var selectedRow = null;
                for (var i = 0; i < $scope.AccountSettings.list.length; i++) {
                    if ($scope.AccountSettings.list[i].$checked) {
                        selectedRow = $scope.AccountSettings.list[i];
                    }
                }

                if (selectedRow) {
                    $scope.UserModal.userForm = {
                        id: selectedRow.id,
                        username: selectedRow.username,
                        role: selectedRow.role
                    };
                    angular.element('#userModal').addClass('md-show');
                }
            };

            $scope.openNewUserModal = function () {
                $scope.hideValidityStyle = true;
                $scope.UserModal.userForm = {
                    id: null,
                    username: '',
                    role: ''
                };
                angular.element('#userModal').addClass('md-show');
            };

            $scope.closeUserModal = function () {
                angular.element('#userModal').removeClass('md-show');
            };

            $scope.getFirstCheckedRow = function () {
                for (var i = 0; i < $scope.AccountSettings.list.length; i++) {
                    if ($scope.AccountSettings.list[i].$checked) {
                        return $scope.AccountSettings.list[i];
                    }
                }
            }

            $scope.AccountSettings.MasterCheckbox = function (masterCheck) {
                for (var i = $scope.AccountSettings.list.length; i--;) {
                    $scope.AccountSettings.list[i].$checked = masterCheck;
                }
            };

            $scope.UserModal.save = function () {
                if (!$scope.userForm.$invalid) {
                    $scope.UserModal.$saving = true;

                    if ($scope.UserModal.userForm.id == null) {
                        AccountSettingsService.create($scope.UserModal.userForm).then(
                            function (response) {
                                $scope.UserModal.$saving = false;
                                $scope.AccountSettings.totalElements = response.totalElements;
                                var result = response.content;
                                result.$checked = true;

                                var index = -1;
                                for (var i = 0; i < $scope.AccountSettings.list.length; i++) {
                                    if (result.username == $scope.AccountSettings.list[i].username) {
                                        index = i;
                                        break;
                                    }
                                }

                                if (index != -1) {
                                    $scope.AccountSettings.list.splice(index, 1);
                                }

                                $scope.AccountSettings.list.unshift(result);

                                $scope.closeUserModal();
                                $scope.setBtnsAvailability();
                            },
                            function (response) {
                                $scope.UserModal.$saving = false;
                                ConfirmService.open(response.message, null, true);
                            }
                        );
                    } else {
                        AccountSettingsService.update($scope.UserModal.userForm).then(
                            function (response) {
                                $scope.UserModal.$saving = false;
                                $scope.AccountSettings.totalElements = response.totalElements;
                                var result = response.content;

                                var checkedRow = $scope.getFirstCheckedRow();
                                checkedRow.username = result.username;
                                checkedRow.role = result.role;
                                checkedRow.roleDisplayName = result.roleDisplayName;

                                $scope.closeUserModal();
                            },
                            function (response) {
                                $scope.UserModal.$saving = false;
                                ConfirmService.open(response.message, null, true);
                            }
                        );
                    }
                } else {
                    $scope.hideValidityStyle = false;
                    ConfirmService.open('Please fill in required fields.', null, true);
                }
            };

            // Pagination
            $scope.maxRows = function () {
                var data_length = ($scope.AccountSettings.list) ? $scope.AccountSettings.list.length : $scope.pageLength;
                var ret = Math.max($scope.AccountSettings.totalElements, data_length);
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
                $scope.currentPage = $scope.AccountSettings.totalPages;
            };

            $scope.pageToFirst = function () {
                $scope.currentPage = 1;
            };

            $scope.cantPageForward = function () {
                var curPage = $scope.currentPage;
                var maxPages = $scope.maxPages();
                var data_length = ($scope.AccountSettings.list) ? $scope.AccountSettings.list.length : $scope.pageLength;
                if ($scope.AccountSettings.totalElements > 0) {
                    return curPage >= maxPages;
                } else {
                    return data_length < 1;
                }
            };

            $scope.cantPageToLast = function () {
                if ($scope.AccountSettings.totalElements > 0) {
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
                    //console.log('page changed to ' + $scope.currentPage);
                    $location.search('page', $scope.currentPage);
                    $location.search('length', $scope.pageLength);
                    $scope.filterTable();
                }
            });

            // Get all all run calculations
            $scope.getAccountSettings({
                length: $scope.pageLength,
                page: $scope.currentPage - 1
            });

            // Get all roles
            $scope.getRoles();
        }
    ]);
