angular.module('ng.modal.directives', [])
    .directive('ngDraggable', function($log){
        return {
            restrict: 'AE',
            link : function(scope, elem, attrs) {

                var mousemove;
                var mousedown = elem.on('mousedown' , function() {
                    $log.info('mousedown')
         
                    mousemove = elem.bind('mousemove' , function() {
                    $log.info('mousemove')
                    })
                })
                 
                var mouseup = elem.on('mouseup' , function() {
                    $log.info('mouseup')
                    elem.unbind('mousemove', mousemove)
                })
                $log.info("ngDraggable")
            }
        }
    })
    .directive('modalBookmark', function($http, $timeout,BookmarkService, $log) {
        return {
            restrict: 'AE',
            replace: 'true',
            scope: {
                control: '=',
                ApplyFilter: '&callback'
            },
            templateUrl: 'resources/includes/modal/bookmark.modal.tmpl.html',
            link: function (scope, elem, attrs) {
                
                
                scope.internalControl = scope.control || {};
                 scope.bookmarks=[];
                var overlay = angular.element(document.querySelector('.md-overlay'));
                var user_id=1;
                
                overlay.bind('click', function () {
                    scope.CloseModal();
                })
               

                scope.OpenModal = function (bookmark_type) {
                    $log.info('opening bookmark for ' + bookmark_type)

                    if(!scope.bookmarks.length) {
                        scope.loading = true;
                        BookmarkService.findAll({userID:user_id, bookmark_type:bookmark_type }).then(function(response){
                            scope.bookmarks = response.content
                            scope.loading = false;
                            console.log(scope.bookmarks);
                        });
                    }
                    elem.addClass('md-show');
                    overlay.addClass("md-overlay-show")
                };

                scope.CloseModal = function () {
                    scope.openSubForm = false;
                    elem.removeClass('md-show')
                    overlay.removeClass("md-overlay-show")
                };

                scope.internalControl.saveToBookmark = function (filter, bookmark_type ) {
                    $log.info("filter " + filter);
                    scope.OpenModal(bookmark_type);
                    scope.bookmark = {
                        "userId":user_id,
                        "bookmarkName":"",
                        "txtUrlPath":JSON.stringify(filter),
                        "bookmarkType" : bookmark_type
                    };
                    scope.bookmark.$$new = true;
                    $timeout(function(){
                        scope.openSubForm = true;
                    },200);
                }

                scope.LoadFilter = function(filter) {
                    scope.CloseModal();
                    scope.ApplyFilter({ 'filter': angular.fromJson(filter) });
                }

                scope.internalControl.openModalNew = function(bookmark_type) {
                    scope.OpenModal(bookmark_type);
                };

                scope.Cancel = function() {

                    scope.openSubForm = false;
                }

                scope.Save = function() {
                    if(!scope.bookmark.bookmarkName) 
                        return scope.bookmark.$$error = true;
                    console.log("saving")
                    
                    scope.loading = true;
                    if(scope.bookmark.$$new) {
                        
                        BookmarkService.create(scope.bookmark).then(function(response){
                            response.content.$highlight = true;
                            scope.loading = false;
                            scope.bookmarks.unshift(response.content);
                        })
                    } else {
                        BookmarkService.update(scope.bookmark).then(function(response){
                            console.log(response);
                            scope.loading = false;
                        })
                    }
                    scope.bookmark.$$error = false;
                    scope.openSubForm = false;
                }

                scope.Edit = function(bookmark) {
                    scope.bookmark = bookmark;
                    scope.bookmark.$$new = false;
                    scope.openSubForm = true;
                }

                scope.Delete = function(bookmark) {
                    scope.openSubForm = false;
                    BookmarkService.remove(bookmark.bookmarkId).then(function(response){
                        scope.bookmarks.splice(scope.bookmarks.indexOf(bookmark),1)
                    });
                }
               

            }
        }
    })
    .directive('modalBestPractice', function(){
        return {
            restrict: 'AE',
            replace: 'true',
            scope: {
                control: '='
            },
            templateUrl: 'resources/includes/modal/best-practice.modal.html',
            link:function(scope, elem, attrs) {
                scope.internalControl = scope.control || {};
                var overlay = angular.element(document.querySelector('.md-overlay'));
                overlay.bind('click', function () {
                    scope.CloseModal();
                })
                scope.internalControl.OpenModal = function () {

                    
                    elem.addClass('md-show');
                    overlay.addClass("md-overlay-show")
                };
                scope.CloseModal = function () {
                    elem.removeClass('md-show')
                    overlay.removeClass("md-overlay-show")
                };
                scope.bp = {};
                scope.internalControl.setContent = function(value) {
                    scope.bp = value;
                }

            }
        }
    })
    .directive('modalDetailsImpl', function(){
        return {
            restrict: 'AE',
            replace: 'true',
            scope: {
                control: '='
            },
            templateUrl: 'resources/includes/modal/impl.detail.modal.html',
            link:function(scope, elem, attrs) {
                scope.internalControl = scope.control || {};
                var overlay = angular.element(document.querySelector('.md-overlay'));
                overlay.bind('click', function () {
                    scope.CloseModal();
                })
                scope.internalControl.OpenModal = function () {

                    
                    elem.addClass('md-show');
                    overlay.addClass("md-overlay-show")
                };
                scope.CloseModal = function () {
                    elem.removeClass('md-show')
                    overlay.removeClass("md-overlay-show")
                };
                scope.impl = {};
                scope.internalControl.setContent = function(value) {
                    scope.impl = value;
                }

            }
        }
    })

