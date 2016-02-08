/* global angular, $,console */
(function() {
'use strict';

angular.module('ng.CreditRiskCTRL', [])
    .controller('CreditRiskController', ['$scope', 'Util', 'FilterOptions', '$routeParams', '$location',
        function ($scope, Util, FilterOptions, $routeParams, $location) {
        

            $scope.page_length = 100;

            $scope.secondLoadPageLength = 200;

            if (!$routeParams.mode)
                $scope.mode = 'wholesale';
            else
                $scope.mode = $routeParams.mode;

            $scope.ChangeView = function () {
                $scope.mode = ($scope.mode === 'wholesale') ? 'retail' : 'wholesale';
                $location.search('mode', $scope.mode);
            };


            $scope.CloseModal = function () {
                angular.element('#reportGraphModal').removeClass('md-show');
            };


            // =================== Bookmark staff ==============//
            $scope.focusinControl = {};
            $scope.OpenModal = function () {
                $scope.focusinControl.openModalNew($scope.mode);
            };


            $scope.isEmpty = function (value) {
                return Util.isEmpty(value);
            };
            $scope.bookmarkFilter = function (id) {
                $scope.filter = {};
                if(id === 1)  $scope.filter.Gap = 'Met';
                else  $scope.filter.Status = '1';
                
                $('#dialog_simple').dialog('close');
                $scope.filterTable();
            };
            //==========bookmark end ============================//

        }])

    .controller('WholesaleController', ['$scope', 'WholesaleService', 'Util', 'FilterOptions', 'Formater','D3graph','$cacheFactory',
        function ($scope, WholesaleService, Util, FilterOptions, Formater,D3graph, $cacheFactory) {
            $scope.labelPDRW = 'PD';

            $scope.GenerateReport = function () {
                WholesaleService.getGraphData({scenario: 'RC'}).then(function (res) {
                    D3graph.generate(res.content, '#bar-chart', null);
                });
                angular.element('#reportGraphModal').addClass('md-show');
            };

            var preDefinedQuery = {
                snapshot_date: '2014-01-01',
                scenario: 'RC',
                treatment: 'AIRB',
                length: $scope.page_length,
                page: 0
            };

            $scope.Wholesale = {};
            $scope.QueryParams = {length: $scope.page_length, page: 0};
            $scope.filterOptions = {};


            $scope.value = '1000;10000000';
            $scope.options = {
                from: 1000,
                to: 10000000,
                step: 1000,
                smooth: true,
                round: 0,
                dimension: ' $',
                calculate: function (value) {
                    return Formater.formatMoney(value, 2, '.', ',');
                },
                callback: function (value) {
                    $scope.QueryParams.loan_size = value;
                    $scope.FilterTable();
                }
            };

            $scope.FilterTable = function () {
                Util.removeNulls($scope.QueryParams);
                if($scope.QueryParams.treatment === 'STD') {
                    $scope.labelPDRW = 'RW';
                } else {
                    $scope.labelPDRW = 'PD';
                }

                console.log($scope.QueryParams);
                getDataFromServer($scope.QueryParams);
            };


            FilterOptions.getWholesaleFilters().then(function (res) {
                $scope.filterOptions = res.content;
                $scope.QueryParams.snapshot_date = $scope.filterOptions.snapshotDate[0].snapshotDate;

                FilterOptions.getMockFilters().then(function (response) {
                    for (var key in response.content) {
                        $scope.filterOptions[key] = response.content[key];
                    }

                    $scope.QueryParams.treatment = $scope.filterOptions.treatment[0].rwaTreatmentCode;
                    $scope.QueryParams.scenario = 'RC';
                });
            });

            function getDataFromServer(params) {
                angular.element('.spinner-indicator').show();

                WholesaleService.findAll(params).then(function (response) {
                    $scope.Wholesale.list = response.content.map(function (item) {
                        item.$$loading = false;
                        return item;
                    });

                    WholesaleService.getSumTotal(params).then(function (response) {
                        $scope.TotalSum = response;
                    });
                    angular.element('.spinner-indicator').hide();
                    params.startAt = params.length;
                    params.length = $scope.secondLoadPageLength;
                    WholesaleService.findAll(params).then(function (response) {
                        response.content.map(function (item) {
                            item.$$loading = false;
                            $scope.Wholesale.list.push(item);
                            return item;
                        });
                    });
                });
            }

            // initial data load
            getDataFromServer(preDefinedQuery);


            $scope.riskRatingLowerboundFilter = function (lowerbound) {
                if (angular.isNumber(lowerbound)) {
                    return function (item) {
                        return item.range >= lowerbound;
                    };
                }
            };

            $scope.riskRatingUpperboundFilter = function (Upperbound) {
                if (angular.isNumber(Upperbound)) {
                    return function (item) {
                        return item.range <= Upperbound;
                    };
                }
            };

            $scope.ResetFilter = function () {
                $scope.value = '1000;10000000'; // reset slider
                angular.copy(preDefinedQuery, $scope.QueryParams);
                $scope.FilterTable();
            };


            $scope.Calculate = function () {
                for (var i = $scope.Wholesale.list.length - 1; i >= 0; i--) {
                    $scope.Wholesale.list[i].riskWeightedLcyAmt = '';
                    $scope.Wholesale.list[i].expectedLossLcyAmt = '';
                    $scope.Wholesale.list[i].$$loading = true;
                }


                angular.element('.spinner-indicator').show();

                WholesaleService.resetCalculate({treatment: $scope.QueryParams.treatment}).then(function (response) {
                    if (response.reset) {
                        $scope.FilterTable();
                    }
                    $cacheFactory().removeAll();
                });
            };


            /* ================ Bookmark staff ================ */
            $scope.saveToBookmark = function () {
                if (Util.isEmpty($scope.QueryParams))
                    return;
                $scope.focusinControl.saveToBookmark($scope.QueryParams, $scope.mode);
            };

            $scope.ApplyFilter = function (filter) {
                //console.log( filter );
                $scope.QueryParams = filter;
                $scope.FilterTable();
            };

        }])

    .controller('RetailController', ['$scope', 'Util', 'FilterOptions', 'CustomHttp', 'RetailService', 'D3graph', '$cacheFactory',
        function ($scope, Util, FilterOptions, CustomHttp, RetailService, D3graph, $cacheFactory) {
            var preDefinedQuery = {
                snapshot_date: '2013-10-01',
                scenario: 'RC',
                treatment: 'AIRB',
                length: $scope.page_length,
                page: 0
            };
            $scope.Retail = {};
            $scope.QueryParams = {};
            $scope.filterOptions = {};


            $scope.FilterTable = function () {
                Util.removeNulls($scope.QueryParams);
                console.log($scope.QueryParams);
                getDataFromServer($scope.QueryParams);
            };


            CustomHttp.get('mock/retaiFilterOptions', {}).then(function (response) {
                $scope.filterOptions = response;
            });


            FilterOptions.getRetailFilters().then(function (response) {

                for (var key in response.content) {
                    $scope.filterOptions[key] = response.content[key];
                }
                angular.copy(preDefinedQuery, $scope.QueryParams);
            });

            getDataFromServer(preDefinedQuery);

            function getDataFromServer(params) {
                angular.element('.spinner-indicator').show();

                RetailService.findAll(params).then(function (response) {
                    $scope.Retail.list = response.content.map(function (item) {
                        item.$$loading = false;
                        return item;
                    });

                    RetailService.getSumTotal(params).then(function (response) {
                        $scope.Retail.TotalSum = response;
                    });
                    angular.element('.spinner-indicator').hide();
                    params.startAt = params.length;
                    params.length = $scope.secondLoadPageLength;
                    RetailService.findAll(params).then(function (response) {
                        response.content.map(function (item) {
                            item.$$loading = false;
                            $scope.Retail.list.push(item);
                            return item;
                        });
                    });
                });
            }

            $scope.Calculate = function () {
                for (var i = $scope.Retail.list.length - 1; i >= 0; i--) {
                    $scope.Retail.list[i].postSecuritizedRwaLcyAmt = '';
                    $scope.Retail.list[i].postSecuritizationExpectedLossLcyAmt = '';
                    $scope.Retail.list[i].$$loading = true;
                }


                angular.element('.spinner-indicator').show();

                RetailService.resetCalculate({treatment: $scope.QueryParams.treatment}).then(function (response) {
                    if (response.reset) {
                        $scope.FilterTable();
                    }
                    $cacheFactory().removeAll();
                });
            };


            /* ================ Bookmark staff ================ */
            $scope.saveToBookmark = function () {
                if (Util.isEmpty($scope.QueryParams))
                    return;
                $scope.focusinControl.saveToBookmark($scope.QueryParams, $scope.mode);
            };

            $scope.ApplyFilter = function (filter) {
                //console.log( filter );
                $scope.QueryParams = filter;
                $scope.FilterTable();
            };

            $scope.graph = function () {
                RetailService.getGraphData({scenario: 'RC'}).then(function (res) {
                    D3graph.generate(res.content, '#retail-chart', null);
                });
                angular.element('#retailGraphModal').addClass('md-show');
            };

            $scope.closeGraphModal = function () {
                angular.element('#retailGraphModal').removeClass('md-show');
            };

        }])

    .controller('MarketRiskController', ['$scope', '$http',
        function () {
            console.log('MarketRiskController');

        }])
    
    .controller('OperationalRiskController', ['$scope', '$http',
        function () {
            console.log('OperationalRiskController');


        }]);

})();



