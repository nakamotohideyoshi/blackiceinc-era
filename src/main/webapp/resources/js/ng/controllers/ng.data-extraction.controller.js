angular.module('ng.data-extraction.controller', [])
    .controller('DataExtractionController', ['$scope', 'DataExtractionService',
        'VNotificationService', 'ConfirmService',
        function ($scope, DataExtractionService,
                  VNotificationService, ConfirmService) {

            $scope.Filter = {
                filterOptions: {
                    entities: [
                        "COLLATERAL",
                        "COLLATERAL_CATEGORY_CODE",
                        "COLLATERAL_CUSTOMER_ASSOC",
                        "COLLATERAL_FACILITY_ASSOC",
                        "COLLATERAL_MODULE",
                        "COLLATERAL_SECURITY_ASSOC",
                        "COLLATERAL_SUB_TYPE_CODE",
                        "COLLATERAL_TYPE_CODE",

                        "CONTRACT_TYPE_CODE",
                        "COUNTRY_CODE",
                        "CREDIT_RATING_TYPE_CODE",
                        "CRM_ALLOCATE",
                        "CURRENCY_CODE",

                        "CUSTOMER",
                        "CUSTOMER_ADDRESS",
                        "CUSTOMER_BALANCE_SHEET",
                        "CUSTOMER_CATEGORY_CODE",
                        "CUSTOMER_CREDIT_RATING",
                        "CUSTOMER_CREDIT_SCORE_DETAILS",
                        "CUSTOMER_INCOME_STATEMENT",
                        "CUSTOMER_TYPE_CODE",

                        "DEAL",
                        "DEAL_CLASS_CODE",
                        "DEAL_TYPE_CODE",

                        "ERA_BOOKMARK",
                        "ERA_CONFIG_FILE",
                        "ERA_RUN_CALCULATOR",

                        "FACILITY_DELEGATED_MID_JAN",
                        "FACILITY_DELEGATED_NOV30",
                        "FACILITY_STATUS_CODE",
                        "FACILITY_TYPE_CODE",
                        "FACILITY_TYPE_CODE_N30",

                        "GL_BALANCE",

                        "INDUSTRY_CLASS_TYPE_CODE",
                        "INDUSTRY_CODE",
                        "INSTRUMENT",
                        "INSTRUMENT_CLASS_TYPE_CODE",
                        "INSTRUMENT_CLASS_TYPE_CODE_N30",
                        "INSTRUMENT_MID_JAN",
                        "INSTRUMENT_NOV30",
                        "INSTRUMENT_TYPE_CODE",
                        "INSTRUMENT_TYPE_CODE_NOV30",
                        "INTEREST_RATE_TYPE_CODE",
                        "LENDING_TYPE_CODE",
                        "MARKET_RISK",
                        "MEASUREMENT_SENSITIVITY",
                        "MIGR_DATATYPE_TRANSFORM_MAP",
                        "MIGR_DATATYPE_TRANSFORM_RULE",
                        "MIGR_GENERATION_ORDER",
                        "MIGRATION_RESERVED_WORDS",
                        "MIGRLOG",
                        "ORG_UNIT_CODE",
                        "PROCESS_TYPE_CODE",
                        "PROVINCE_STATE_CODE",
                        "REGION_CODE",
                        "RELATIONSHIP_CODE",
                        "SBV_SUMMARY",
                        "SECURITY"
                    ]
                },
                selectedFilters: {}
            };

            $scope.extract = function () {
                // check for selection
                if ($scope.Filter.selectedFilters.entity) {
                    var $preparingFileModal = $("#preparing-file-modal");
                    $preparingFileModal.dialog({modal: true});

                    $.fileDownload('api/data-extraction/extract', {
                        data: $scope.Filter.selectedFilters,
                        successCallback: function (url) {
                            $preparingFileModal.dialog('close');
                        },
                        failCallback: function (responseHtml, url) {
                            $preparingFileModal.dialog('close');
                            $("#error-modal").dialog({modal: true});
                        }
                    });
                } else {
                    ConfirmService.open('Please select entity', null, true);
                }
            }

        }
    ]);
