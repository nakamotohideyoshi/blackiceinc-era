angular.module('app.custom', [])
	
	.directive('ngFloatHead', function($timeout){
		return {
			restrict: 'A',
			link : function(scope, elem, attrs) {
				

			    elem.floatThead({
                    scrollingTop: 100,
                    scrollContainer: function($table){
                        return $table.closest('.wrapper');
                    },
                    useAbsolutePositioning: false
                });
			}
		}
	})
	.directive('fixedHeader', ['$timeout', function ($timeout) {
	    return {
	        restrict: 'A',
	        scope: {
	            tableHeight: '@'
	        },
	        link: function ($scope, $elem, $attrs, $ctrl) {
	        	console.log("fixed header 2")
	            // wait for content to load into table and the tbody to be visible
	            $scope.$watch(function () { return $elem.find("tbody").is(':visible') },
	                function (newValue, oldValue) {
	                    if (newValue === true) {
	                        // reset display styles so column widths are correct when measured below
	                        $elem.find('thead, tbody').css('display', '');
	 
	                        // wrap in $timeout to give table a chance to finish rendering
	                        $timeout(function () {
	                            // set widths of columns
	                            $elem.find('th').each(function (i, thElem) {
	                                thElem = $(thElem);
	                                tdElems = $elem.find('tbody tr:first td:nth-child(' + (i + 1) + ')');
	 
	                                var columnWidth = tdElems.width();
	                                thElem.width(columnWidth);
	                                tdElems.width(columnWidth);
	                            });
	 
	                            // set css styles on thead and tbody
	                            $elem.find('thead').css({
	                                'display': 'block',
	                            });
	 
	                            $elem.find('tbody').css({
	                                'display': 'block',
	                                'height': $scope.tableHeight || '400px',
	                                'overflow': 'auto'
	                            });
	 
	                            // reduce width of last column by width of scrollbar
	                            var scrollBarWidth = $elem.find('thead').width() - $elem.find('tbody')[0].clientWidth;
	                            if (scrollBarWidth > 0) {
	                                // for some reason trimming the width by 2px lines everything up better
	                                scrollBarWidth -= 2;
	                                $elem.find('tbody tr:first td:last-child').each(function (i, elem) {
	                                    $(elem).width($(elem).width() - scrollBarWidth);
	                                });
	                            }
	                        });
	                    }
	                });
	        }
	    }
	}])
	.directive('confirm',['ConfirmService', function(ConfirmService){
        return {
            restrict: 'A',
            scope: {
                eventHandler: '&ngClick'
            },
            link: function(scope, element, attrs){
              element.unbind("click");
              element.bind("click", function(e) {
                ConfirmService.open(attrs.confirm, scope.eventHandler);
              });
            }
        }
    }])

    .service('ConfirmService',['$modal', function($modal) {
      var service = {};
      service.open = function (text, onOk, isAlert) {
        var modalInstance = $modal.open({
           template:        '<div class="modal-header">' +
                                '<button type="button" class="close" ng-click="cancel()"  data-dismiss="modal" aria-hidden="true">' +
                                    '<i class="fa fa-times"></i>' +
                                '</button>' +
                                '<div class="modal-title "><Strong class = "desc">{{text}}</Strong></div>' +
                            '</div>' +
                            '<div class="modal-body text-right">' +
                                '<div>'+
                                    '<button type="button" class="btn btn-primary" ng-click="ok()"  aria-hidden="true" style="margin-right:5px;width: 66px;">OK</button> ' +
                                    '<button type="button" ng-hide="isAlert" class="btn btn-default" ng-click="cancel()" aria-hidden="true" >Cancel</button>' +
                                '</div>' +
                            '</div>',
          controller: 'ModalConfirmCtrl',
          size: 'sm',
          backdrop: 'static',
          resolve: {
            text: function () {
              return text;
            },
            isAlert: function(){
                return isAlert;
            }
          }
        });

        modalInstance.result.then(function (selectedItem) {
            if(onOk) onOk();
        }, function () {
        });
      };

      return service;
    }])

    .controller('ModalConfirmCtrl',['$scope', '$modalInstance', 'text', 'isAlert', function ($scope, $modalInstance, text, isAlert) {

    	  $scope.text = text;
    	  $scope.isAlert = isAlert;

    	  $scope.ok = function () {
    	    $modalInstance.close(true);
    	  };

    	  $scope.cancel = function () {
    	    $modalInstance.dismiss('cancel');
    	  };
    }])
    .directive('vNotification', ['$sce','$window','$timeout', 'VNotificationService',  function($sce, $window, $timeout, VNotificationService){
        return {
            restrict: 'A',
            template:'<div class="v-Notification tray dark small closable login-help v-Notification-animate-in v-position-bottom  v-position-center" '+
                    'style="margin-left: 0px;margin-top: 0px;z-index: 20000;position: absolute;">' +
                        '<div class="popupContent">' +
                            '<div class="gwt-HTML">' +
                                '<h1>{{NoticationHeader}}</h1>' +
                                '<p ng-bind-html="NoticationMessage"></p>' +
                            '</div>' +
                        '<div>' +
                    '</div>',
            link: function(scope, elem, attrs){

                var firstChild = angular.element(elem[0].firstChild);
                var timer;
                var windowWidth = $window.innerWidth;
                scope.NoticationHeader = 'Info';
                scope.NoticationMessage = $sce.trustAsHtml( '<span> <b>Message body</b>. Hello World!</span>');

                firstChild.css({
                    'overflow': 'visible',
                    'visibility': 'hidden'
                });

                var ismobile = (/iphone|ipad|ipod|android|blackberry|mini|windows\sce|palm/i.test(navigator.userAgent.toLowerCase()));
                if(ismobile) {
                    // add this class "v-Notification-system v-position-bottom"
                    firstChild.addClass('v-Notification-system v-position-bottom')
                }

                var show = function() {
                    var clientWidth = elem.find('div')[0].clientWidth
                    var windowWidth = $window.innerWidth;
                    var width = (windowWidth/2) - (clientWidth/2);
                    firstChild.css({
                        'left' : width + 'px',
                        'visibility' : 'visible'
                    });
                };

                var hide = function(){
                    firstChild.css('visibility', 'hidden');
                };

                //Event handler for resize notification when window changes width
                // angular.element(window).bind('resize', function(event){
                // 	if(windowWidth !== $window.innerWidth) {
                // 		windowWidth = $window.innerWidth;
                // 		show();
                // 		scope.$apply();
                // 	}
                // });


                // Event listener to close the notification
                firstChild.bind('click', function(event){
                    var x = event.pageX - firstChild.offset().left;
                    var y = event.pageY - firstChild.offset().top;
                    var width = elem.find('div')[0].clientWidth;
                    if(x > (width - 50) && y < 50) {
                        hide();
                    }
                });

                scope.$on('vNotificationInfo', function() {
                    scope.NoticationHeader = 'Info';
                    ToggleNotifcationClass('dark');
                });

                scope.$on('vNotificationSuccess', function() {
                    scope.NoticationHeader = 'Success';
                    ToggleNotifcationClass('success');
                });

                scope.$on('vNotificationFailure', function() {
                    scope.NoticationHeader = 'Failure';
                    ToggleNotifcationClass('failure');
                });

                scope.$on('vNotificationError', function() {
                    scope.NoticationHeader = 'Error';
                    ToggleNotifcationClass('error');
                });
                scope.$on('vNotificationWarning', function() {
                    scope.NoticationHeader = 'Warning';
                    ToggleNotifcationClass('warning');
                });
                scope.$on('vNotificationClear', function() {
                    hide();
                });

                function ToggleNotifcationClass(_class) {
                    scope.NoticationMessage = $sce.trustAsHtml(VNotificationService.message);
                    if(VNotificationService.header)
                        scope.NoticationHeader = VNotificationService.header

                    firstChild.removeClass('dark success failure error warning').addClass(_class);
                    $timeout.cancel(timer);
                    hide();
                    $timeout(show, 100);
                    timer = $timeout(function(){
                        hide();
                    }, VNotificationService.timeout);
                }
            }
        }
    }])
    .directive('datePicker', [function(){
        return {
            restrict: 'A',
            link : function(scope, elem, attrs) {

                if(attrs.readonly) return;
                elem.datepicker({
                    dateFormat : attrs.dateformat || 'yy-mm-dd',
                    prevText : '<i class="fa fa-chevron-left"></i>',
                    nextText : '<i class="fa fa-chevron-right"></i>',
                });

            }
        }
    }])
    .directive('ngDateMask', [function(){
        return {
            restrict: 'A',
            link : function(scope, elem, attrs) {
                var mask = attrs['mask'] || 'error...', mask_placeholder = attrs['maskPlaceholder'] || 'X';
                elem.mask(mask, {
                    placeholder : mask_placeholder
                });
            }
        }
    }])
    ;