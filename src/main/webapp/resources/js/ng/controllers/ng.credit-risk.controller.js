/*global angular, $ */
(function () {
    'use strict';


    angular.module('ng.credit-risk.controller', [])
        .controller('CreditRiskController', ['$scope', '$timeout', 'Util', 'CreditRiskService', 'CreditRiskState', '$routeParams', '$location',
            function ($scope, $timeout, Util, CreditRiskService, CreditRiskState, $routeParams, $location) {
                var CONSTANT_snapshotDate = 'snapshotDate';
                var CONSTANT_loadJobNbr = 'loadJobNbr';
                var CONSTANT_scenarioId = 'scenarioId';
                var CONSTANT_industry = 'industry';
                var CONSTANT_profitCentre = 'profitCentre';
                var CONSTANT_assetClass = 'assetClass';
                var CONSTANT_exposureType = 'exposureType';
                var CONSTANT_entityType = 'entityType';
                var CONSTANT_productType = 'productType';
                var CONSTANT_riskRatingFrom = 'riskRatingFrom';
                var CONSTANT_riskRatingTo = 'riskRatingTo';

                $scope.Filter = {
                    filterOptions: {},
                    filterStaticOptions: {riskRating: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]},
                    filterSelection: {}
                };

                var initializing = true;
                var state = CreditRiskState.get();

                $scope.pageLength = state.pageLength;
                $scope.currentPage = state.currentPage;
                $scope.Filter.filterSelection.snapshotDate = state.snapshotDate;
                $scope.Filter.filterSelection.loadJobNbr = state.loadJobNbr;
                $scope.Filter.filterSelection.scenarioId = state.scenarioId;

                $scope.Filter.filterSelection.industry = state.industry;
                $scope.Filter.filterSelection.profitCentre = state.profitCentre;
                $scope.Filter.filterSelection.assetClass = state.assetClass;
                $scope.Filter.filterSelection.exposureType = state.exposureType;
                $scope.Filter.filterSelection.entityType = state.entityType;
                $scope.Filter.filterSelection.productType = state.productType;

                $scope.loading = true;

                $scope.CreditRisk = {};

                $scope.getCreditRiskData = function (params) {
                    CreditRiskService.getAll(params).then(function (response) {
                            $scope.CreditRisk.list = response.data.content;

                            $scope.CreditRisk.totalElements = response.data.totalElements;
                            $scope.CreditRisk.totalPages = response.data.totalPages;
                            $scope.CreditRisk.numberOfElements = response.data.numberOfElements;

                            $scope.loading = false;
                        }, function (response) {
                            $scope.loading = false;
                        }
                    );
                };

                $scope.getCreditRiskSums = function (params) {
                  CreditRiskService.getSums(params).then(function (response){
                     $scope.CreditRisk.sums = response;
                  });
                };

                $scope.filterTable = function () {
                    var params = getParams();
                    $scope.loading = true;
                    $scope.getCreditRiskData(params);
                };

                $scope.search = function () {
                    saveState();
                    $scope.filterTable();
                    $scope.getCreditRiskSums(getParams());
                };

                $scope.resetFilter = function () {
                    $scope.Filter.filterSelection = {};
                    saveState();
                    $scope.filterTable();
                    $scope.getCreditRiskSums(getParams());
                };

                // Pagination
                $scope.maxRows = function () {
                    var data_length = ($scope.CreditRisk.list) ? $scope.CreditRisk.list.length : $scope.pageLength;
                    var ret = Math.max($scope.CreditRisk.totalElements, data_length);
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
                    $scope.currentPage = $scope.CreditRisk.totalPages;
                };

                $scope.pageToFirst = function () {
                    $scope.currentPage = 1;
                };

                $scope.cantPageForward = function () {
                    var curPage = $scope.currentPage;
                    var maxPages = $scope.maxPages();
                    var data_length = ($scope.CreditRisk.list) ? $scope.CreditRisk.list.length : $scope.pageLength;
                    if ($scope.CreditRisk.totalElements > 0) {
                        return curPage >= maxPages;
                    } else {
                        return data_length < 1;
                    }
                };

                $scope.cantPageToLast = function () {
                    if ($scope.CreditRisk.totalElements > 0) {
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
                        var state = CreditRiskState.get();
                        state.currentPage = $scope.currentPage;
                        state.pageLength = $scope.pageLength;

                        $scope.filterTable();
                    }
                });
                // pagination end

                CreditRiskService.getFilterOptions().then(function (response) {
                    $scope.Filter.filterOptions = response;
                });

                $scope.filterTable();
                $scope.getCreditRiskSums(getParams());

                // static functions
                function getParams() {
                    var params = {};
                    params.length = $scope.pageLength;
                    params.page = $scope.currentPage - 1;

                    var snapshotDate = $scope.Filter.filterSelection.snapshotDate;
                    var loadJobNbr = $scope.Filter.filterSelection.loadJobNbr;
                    var scenarioId = $scope.Filter.filterSelection.scenarioId;

                    var industry = $scope.Filter.filterSelection.industry;
                    var profitCentre = $scope.Filter.filterSelection.profitCentre;
                    var assetClass = $scope.Filter.filterSelection.assetClass;
                    var exposureType = $scope.Filter.filterSelection.exposureType;
                    var entityType = $scope.Filter.filterSelection.entityType;
                    var productType = $scope.Filter.filterSelection.productType;
                    var riskRatingFrom = $scope.Filter.filterSelection.riskRatingFrom;
                    var riskRatingTo = $scope.Filter.filterSelection.riskRatingTo;

                    if (snapshotDate) {
                        params[CONSTANT_snapshotDate] = snapshotDate;
                    }

                    if (loadJobNbr) {
                        params[CONSTANT_loadJobNbr] = loadJobNbr;
                    }

                    if (scenarioId) {
                        params[CONSTANT_scenarioId] = scenarioId;
                    }

                    if (industry) {
                        params[CONSTANT_industry] = industry;
                    }

                    if (profitCentre) {
                        params[CONSTANT_profitCentre] = profitCentre;
                    }

                    if (assetClass) {
                        params[CONSTANT_assetClass] = assetClass;
                    }

                    if (exposureType) {
                        params[CONSTANT_exposureType] = exposureType;
                    }

                    if (entityType) {
                        params[CONSTANT_entityType] = entityType;
                    }

                    if (productType) {
                        params[CONSTANT_productType] = productType;
                    }

                    if (riskRatingFrom) {
                        params[CONSTANT_riskRatingFrom] = riskRatingFrom;
                    }

                    if (riskRatingTo) {
                        params[CONSTANT_riskRatingTo] = riskRatingTo;
                    }

                    return params;
                }

                function saveState() {
                    var state = CreditRiskState.get();
                    state.snapshotDate = $scope.Filter.filterSelection.snapshotDate;
                    state.loadJobNbr = $scope.Filter.filterSelection.loadJobNbr;
                    state.scenarioId = $scope.Filter.filterSelection.scenarioId;
                    state.industry = $scope.Filter.filterSelection.industry;
                    state.profitCentre = $scope.Filter.filterSelection.profitCentre;
                    state.assetClass = $scope.Filter.filterSelection.assetClass;
                    state.exposureType = $scope.Filter.filterSelection.exposureType;
                    state.entityType = $scope.Filter.filterSelection.entityType;
                    state.productType = $scope.Filter.filterSelection.productType;
                }

            }
        ]);
})();