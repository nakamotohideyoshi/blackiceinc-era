/* global angular, $,console */
(function() {
'use strict';

angular.module('ng.maintenance-base.controller', [])
    .controller('MaintenanceBaseController', ['$scope', '$route', '$animate', '$http', '$timeout',
        function ($scope, $route, $animate, $http, $timeout) {
            /* Action hook called when a cell intiallity becomes in focus */
            $scope.$on('ngGridEventStartCellEdit', function (data) {
                /* Save a copy of the row prior to entering in-focus; use to check if the row has changed after out-focus */        
                $scope.priorRowEditTemp = angular.copy(data.targetScope.row.entity);                
            });
            $scope.filters;
            /* Action hook called when a cell becomes out of focus */
            $scope.$on('ngGridEventEndCellEdit', function (data) {                                                
                var rowObject = angular.copy(data.targetScope.row.entity);
                var oldNewRowEquals = angular.equals($scope.priorRowEditTemp, rowObject);
                /* Only save if old !== new row ie; the row was edited */
                if (!oldNewRowEquals) {
                    delete rowObject['custom_property_row_selected'];
                    $scope.saveRow(rowObject, function () {
                        $.smallBox({
                            title: 'Changes to record saved successfully',
                            content: '',
                            color: '#739E73',
                            iconSmall: 'fa fa-cloud',                            
                            timeout: 3000
                        });
                    });
                }
            });

            /* Helper function to check if there are no rows selected*/
            $scope.checkNoRowSelected = function () {
                var noRowSelected = true;
                for (var i = 0; i < $scope.myData.length; i++) {
                    var isThisRowSelected = $scope.myData[i]['custom_property_row_selected'];
                    if (isThisRowSelected) {
                        noRowSelected = false;
                        break;
                    }
                }
                return noRowSelected;
            };

            $scope.rowCheckBox = function () {
                $scope.noRowsSelected = $scope.checkNoRowSelected();
            };
            $scope.filterChanaged = function() {
                $scope.pagingOptions.currentPage = 1;
                var actualPageNumberParam =  $scope.pagingOptions.currentPage-1;
                var queryParam = {length : $scope.pagingOptions.pageSize, page : actualPageNumberParam};

                $scope.getPage(queryParam, getPageAjaxCallBack);
            };

            /* Send GET request to jsonURLBasePath with corresponding query parameters */
            $scope.getPage = function (queryParamObject, callback) {
                $scope.noRowsSelected = true;
                $('.dataTables_processing').show();
                $('.spinner-indicator').show();
                for (var attrname in $scope.filters) {
                    var filterValue = $scope.filters[attrname];
                    if (filterValue) {
                        queryParamObject[attrname] = $scope.filters[attrname];
                    }
                }
                queryParamObject.length = $scope.pagingOptions.pageSize;
                $http({
                    url: $scope.jsonURLBasePath,
                    method: 'GET',
                    params: queryParamObject
                }).success(function (data, status, header, config) {
                    /* Define your customized callback procedure if you want to process the result differently */
                    callback(data, status, header, config);
                    $('.dataTables_processing').hide();   
                    $('.spinner-indicator').hide();
                });
            };

            /* Send POST Request to server to create new record */
            $scope.createNewRecord = function(callback) {              
                var emptyObject = {};
                $('.dataTables_processing').show();
                $('.spinner-indicator').show();
                $http({
                    url: $scope.jsonURLBasePath,
                    method: 'POST',
                    data: emptyObject
                }).success(function(response, status, header, config) {
                    console.log(response);
                    callback(response, status, header, config);
                    $('.dataTables_processing').hide();
                    $('.spinner-indicator').hide();
                });
            };

            $scope.saveRow = function(data, callback) {
                $http({
                    url: $scope.jsonURLBasePath + data[$scope.modelIdString],
                    method: 'PUT',
                    data: data
                }).success(function (data, status, header, config) {                    
                    /* Define your customized callback procedure if you want to process the result differently */
                    callback(data, status, header, config);
                });
            };

            $scope.deleteRow = function(deleteRowIdObject, callback) {
                //var recordId = data[$scope.modelIdString];
                var recordId = deleteRowIdObject['id'].pop();
                var jsonString = JSON.stringify(deleteRowIdObject);
                var queryParamObject = {idList : jsonString};
                $('.dataTables_processing').show();
                $('.spinner-indicator').show();
                $http({
                    url: $scope.jsonURLBasePath + recordId,
                    method: 'DELETE',
                    params: queryParamObject
                }).success(function(data, status, header, config) {
                    callback(data, status, header, config);
                    $('.dataTables_processing').hide();
                    $('.spinner-indicator').hide();
                });
            };

            /* Perform delete operation on all rows that are checked */
            $scope.deleteCheckedRows = function() {
                console.log('Entered Delete Selected Rows');
                var deleteRowsIdArray = [];
                var queryParamObject;
                for(var i=0; i<$scope.myData.length; i++) {
                    var isRowChecked = $scope.myData[i]['custom_property_row_selected'];
                    var rowId = $scope.myData[i][$scope.modelIdString];
                    /* We delete that row if it is checked by the user */
                    if (isRowChecked) {
                        deleteRowsIdArray.push(rowId);
                    }
                }

                queryParamObject = {id:deleteRowsIdArray};
                $scope.deleteRow(queryParamObject, function(data) {                    
                    var pageSize = $scope.pagingOptions.pageSize;
                    var currentPage = $scope.pagingOptions.currentPage;
                    var totalPages = Math.ceil($scope.totalServerItems/pageSize);
                    /* If deleted ALL rows on last page then must go to previous page otherwise stay on last page */
                    var pageQueryParam;
                    if ((data.totalElements%pageSize===0) && (currentPage===totalPages)) {
                        pageQueryParam = $scope.pagingOptions.currentPage-2;
                        $scope.pagingOptions.currentPage -= 1;
                    } else {
                        pageQueryParam = $scope.pagingOptions.currentPage-1;
                    }
                    queryParamObject = {length : pageSize, page : pageQueryParam};                    
                    $scope.getPage(queryParamObject, deleteRowAjaxCallBack);
                });
            };

            /* TODO FIX IT */
            $scope.addNewRecord = function() {   
                $scope.createNewRecord(newCreateNewRecordSuccessCallBack);
            };

            var newCreateNewRecordSuccessCallBack = function(result) {
                console.log(result);
                result['custom_property_row_selected'] = true;
                $scope.myData.push(result);

                $timeout(function () {
                  var grid = $scope.gridOptions.ngGrid;
                  $scope.gridOptions.selectItem($scope.myData.length - 1, true);
                  grid.$viewport.scrollTop((($scope.myData.length - 1) * grid.config.rowHeight));
                }, 0);

                $scope.totalServerItems++;
                $scope.noRowsSelected = true;

                $.smallBox({
                    title: 'New Record Successfully Created.',
                    content: '',
                    color: '#3276b1',
                    iconSmall: 'fa fa-plus',
                    timeout: 3000
                });                
            };

            var oldCreateNewRecordSuccessCallBack = function(result, status, header, config) {
                            getPageAjaxCallBack(result, status, header, config);
                            /* Select the new row that was just added */
                            var newRecordId = result.content[$scope.modelIdString];
                            for(var i=0; i<$scope.myData.length; i++) {
                                var recordId = $scope.myData[i][$scope.modelIdString];
                                if (newRecordId === recordId) {
                                    $scope.myData[i]['custom_property_row_selected'] = true;
                                }
                            }
                            /* The newly added row is selected; we set this boolean to false to activate the delete button */
                            $scope.noRowsSelected = false;
                            /* Display alert box to let user know record added successfully */
                            $.smallBox({
                                title: 'Record successfully added.',
                                content: '',
                                color: '#296191',
                                iconSmall: 'fa fa-plus',
                                timeout: 4000
                });      
            };

            /* Function to dynamically resize the table when switching between page sizes */
            function ngGridFlexibleHeightPlugin(opts) {
                var self = this;
                self.grid = null;
                self.scope = null;
                self.init = function (scope, grid, services) {
                    self.domUtilityService = services.DomUtilityService;
                    self.grid = grid;
                    self.scope = scope;
                    var recalcHeightForData = function () { setTimeout(innerRecalcForData, 1); };
                    var innerRecalcForData = function () {
                        var gridId = self.grid.gridId;
                        var footerPanelSel = '.' + gridId + ' .ngFooterPanel';
                        var extraHeight = self.grid.$topPanel.height() + $(footerPanelSel).height();
                        var naturalHeight = self.grid.$canvas.height() + 1;
                        if (opts !== null) {
                            if (opts.minHeight !== null && (naturalHeight + extraHeight) < opts.minHeight) {
                                naturalHeight = opts.minHeight - extraHeight - 2;
                            }
                            if (opts.maxHeight !== null && (naturalHeight + extraHeight) > opts.maxHeight) {
                                naturalHeight = opts.maxHeight;
                            }
                        }

                        var newViewportHeight = naturalHeight + 10;
                        if (!self.scope.baseViewportHeight || self.scope.baseViewportHeight !== newViewportHeight) {
                            self.grid.$viewport.css('height', newViewportHeight + 'px');
                            self.grid.$root.css('height', (newViewportHeight + extraHeight) + 'px');
                            self.scope.baseViewportHeight = newViewportHeight;
                            self.domUtilityService.RebuildGrid(self.scope, self.grid);
                        }
                    };
                    self.scope.catHashKeys = function () {
                        var hash = '',
                            idx;
                        for (idx in self.scope.renderedRows) {
                            hash += self.scope.renderedRows[idx].$$hashKey;
                        }
                        return hash;
                    };
                    self.scope.$watch('catHashKeys()', innerRecalcForData);
                    self.scope.$watch(self.grid.config.data, recalcHeightForData);
                };
            }

            var getPageAjaxCallBack = function(result) {
                $scope.myData = [];
                $scope.myData = result.content;
                /* Set a custom data property to see whether the row is checked */
                for (var i = 0; i < $scope.myData.length; i++) {
                    $scope.myData[i]['custom_property_row_selected'] = false;
                }                
                $scope.totalServerItems = result.totalElements;
            };

            var deleteRowAjaxCallBack = function(result, status, header, config) {
                getPageAjaxCallBack(result, status, header, config);
                $.smallBox({
                    title: 'Record was successfully deleted',
                    content: '',
                    color: '#739E73',
                    iconSmall: 'fa fa-trash-o',
                    timeout: 2000
                });                
            };

            var addNewRowSuccessCallBack = function(result, status, header, config) {
                getPageAjaxCallBack(result, status, header, config);
                $.smallBox({
                    title: 'Record successfully added.',
                    content: '',
                    color: '#296191',
                    iconSmall: 'fa fa-plus',
                    timeout: 3000
                });
            };

            $scope.getPageChild = function(queryParam) {
                $scope.getPage(queryParam, getPageAjaxCallBack);
            };

            /* Listener to watch for the page change */
            $scope.$watch('pagingOptions', function (newVal, oldVal) {
                
                var queryParam = {};

                
                console.log(' -------- > ', queryParam);

                /* The page number changed */
                if (oldVal.currentPage != newVal.currentPage) {
                    var pageNumber = newVal.currentPage - 1;
                    queryParam = {length : newVal.pageSize, page : pageNumber};
                    $scope.getPage(queryParam, getPageAjaxCallBack);
                }
                /* The page size option was changed */ 
                else if (oldVal.pageSize != newVal.pageSize) {
                    /* Reset to page 1 when page size is changed; but query paramter actually page-1 due to client/restAPI not sync. */
                    $scope.pagingOptions.currentPage = 1;
                    queryParam = {length : newVal.pageSize, page : 0};
                    $scope.getPage(queryParam, getPageAjaxCallBack);
                }
                else {
                    console.log('Technically you\'re not suppose to enter here.');
                    return;
                }                
            }, true);

            /* These Variables MUST be overridden by the child class.*/
            $scope.modelIdString;
            $scope.jsonURLBasePath;
            $scope.columnDefs;
            $scope.pagingOptions;
            /*********************/
            /* These Variables are optional for overriding. */
            $scope.currentPageSize = ($scope.currentPageSize == 'undefined') ? 100 : $scope.currentPageSize;
            /*********************/

            $scope.priorRowEditTemp = '';
            /* THIS BOOLEAN IS REVERSED MUST FIX IT */
            $scope.noRowsSelected = true;
            $scope.currentPage = 0;
            $scope.totalPages = 0;
            $scope.totalRecords = 0;            
            $scope.maxNumPageDisplay = 0;
            $scope.showingRecordFrom = ($scope.currentPage * $scope.currentPageSize) + 1;
            $scope.showingRecordTo = ($scope.currentPage * $scope.currentPageSize) + $scope.currentPageSize;
            $scope.pageSizeOptions = [10,25,50];            

            /* ng-grid paging stuff */
            $scope.totalServerItems = 0;
            $scope.pagingOptions = {
                pageSizes: [5 ,25, 50, 100],
                pageSize: 100,
                currentPage: 1
            };



            $scope.myData = [];            
            $scope.gridOptions = {             
                data: 'myData',
                enableCellSelection: true,
                multiSelect: true,
                enableRowSelection: false,
                enableCellEdit: true,
                enableColumnResize: false,
                enablePinning: true,

                /* Row Selction function */
                afterSelectionChange: function() {
                    alert('rambo');
                },

                /* Server Pager */
                enablePaging: true,
                showFooter: true,
                totalServerItems: 'totalServerItems',
                pagingOptions: $scope.pagingOptions,
                /* Comment out/in for auto resizing ng-grid table */          
                //plugins: [new ngGridFlexibleHeightPlugin()],
                rowTemplate: '<div style="height: 100%"  ng-class="{rowChecked: row.getProperty(\'custom_property_row_selected\')}"><div ng-style="{ \'cursor\': row.cursor }" ng-repeat="col in renderedColumns" ng-class="col.colIndex()" class="ngCell " >' +
                               '<div class="ngVerticalBar" ng-style="{height: rowHeight}" ng-class="{ ngVerticalBarVisible: !$last }"> </div>' +
                               '<div ng-cell ></div>' +
                             '</div></div>',
                columnDefs: $scope.columnDefs
            };

            /* Populate records arrray initallty */

            var actualPageNumberParam =  $scope.pagingOptions.currentPage-1;
            //var queryParam = {length : $scope.pagingOptions.pageSize, page : actualPageNumberParam};
            var queryParam = {};

            $scope.getPage(queryParam, getPageAjaxCallBack);
        }
    ]);

})();