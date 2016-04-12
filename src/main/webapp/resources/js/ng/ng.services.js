angular.module('app.services', [])
	.factory('DirectiveService', ['$scope', '$http',  function($scope, $http){
		console.log('DirectiveService');
	}])
	.service('Formater', function() {
		return {
			formatMoney : function(xx, c, d, t) {
				var n = xx,
                    c = isNaN(c = Math.abs(c)) ? 2 : c,
                    d = d == undefined ? "." : d,
                    t = t == undefined ? "," : t,
                    s = n < 0 ? "-" : "",
                    i = parseInt(n = Math.abs(+n || 0).toFixed(c)) + "",
                    j = (j = i.length) > 3 ? j % 3 : 0;
                return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) //+ (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
			}
		}
	})
	.filter('truncate', function () {
        return function (text, length, end) {
            if (isNaN(length))
                length = 10;

            if (end === undefined)
                end = "...";

            if (text.length <= length || text.length - end.length <= length) {
                return text;
            }
            else {
                return String(text).substring(0, length-end.length) + end;
            }

        };
    })
	.service('MessageService', function(){
		var date = null;
		return {
			getData:function(){
				return data;
			},
			setData:function(value) {
				data = value;
			}
		};
	})
	.factory('sharedService', ['$rootScope', function($rootScope){
		var sharedService = {};
		sharedService.message='';
		sharedService.data = {};
		sharedService.data2 = {};
		sharedService.prepForBroadcast = function(msg, data){
			this.message = msg;
			this.data = data;
			this.broadcastItem();

		};
		sharedService.prepForBroadcast2 = function(msg, data2){
			this.message = msg;
			this.data2 = data2;
			this.broadcastItem();
		};

		sharedService.broadcastItem = function(){
			$rootScope.$broadcast('handleBroadcast');
		};

		return sharedService;
	}])

	.factory('VNotificationService', ['$rootScope', function($rootScope) {
        var VNotification = {};
        var defaultTimeout = 3000;
        VNotification.message = '';

        VNotification.info = function(msg, timeout) {
            this.message = msg;
            this.timeout = timeout || defaultTimeout;
            $rootScope.$broadcast('vNotificationInfo');
        };

        VNotification.success = function(msg, timeout) {
            this.message = msg;
            this.timeout = timeout || defaultTimeout;
            $rootScope.$broadcast('vNotificationSuccess');
        };

        VNotification.failure = function(msg, timeout, header) {
            this.message = msg;
            if(header) this.header = header;
            this.timeout = timeout || defaultTimeout;
            $rootScope.$broadcast('vNotificationFailure');
            this.header = null;
        };

        VNotification.error = function(msg, timeout) {
            this.message = msg;
            this.timeout = timeout || defaultTimeout;
            $rootScope.$broadcast('vNotificationError');
        };

        VNotification.warning = function(msg, timeout) {
            this.message = msg;
            this.timeout = timeout || defaultTimeout;
            $rootScope.$broadcast('vNotificationWarning');
        };
        VNotification.clear = function() {
            $rootScope.$broadcast('vNotificationClear');
        };



        return VNotification;
    }])

	.factory('Util', function(){

		return {
			removeNulls : function(obj) {
				for (var i in obj)
				 	if (obj[i] === null || obj[i] === undefined || obj[i]==="")
				    	delete obj[i];
				return obj;
			},
			parseFilterOption : function(data) {
				for (var i = 0; i < data.directive.length; i++)
                 	data.directive[i].type = data.directive[i].directiveType.txtDirectiveTypeName;
                return data;
			},
			isEmpty:function(obj) {
			    for(var prop in obj) {
			        if(obj.hasOwnProperty(prop))
			            return false;
			    }

			    return true;
			},
			serialize: function(obj){
				var str = [];
				for(var p in obj)
				    if (obj.hasOwnProperty(p)) {
				      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				    }
				return str.join("&");
			},
			merge : function (obj1,obj2){
			    var obj3 = {};
			    for (var attrname in obj1) { obj3[attrname] = obj1[attrname]; }
			    for (var attrname in obj2) { obj3[attrname] = obj2[attrname]; }
			    return obj3;
			}

		};
	})

	.service('D3graph', function(){
		return {
			generate:function(json_data, selector, type ) {
				if (!window.d3) {
					loadScript("http://d3js.org/d3.v3.min.js", function(){
						loadScript("resources/js/EraGraph.js", function(){
							console.log(json_data, selector, type)
							var g = new EraGraph(json_data, selector);
						});
					});
				} else {
					var g = new EraGraph(json_data, selector);
				}
			}
		}

	})

	.service('CustomHttp2', function($q, $http) {
		return ({
			get : get
		});
		function get(url, params, isCache) {
			var request =  $http({
				method: 'GET',
				url: url,
				params: params,
				cache: isCache
			});

			return( request.then(handleSuccess, handleError) );
		}
		function handleSuccess(response) {
			return( response );
		}
		function handleError(response) {

			if ( ! angular.isObject( response.data ) || ! response.data.message) {
				$.smallBox({
					title : "An unknown error occurred.",
					content : "",
					color : "#A65858",
					iconSmall : "fa fa-times",
					timeout : 5000
				});
				return( $q.reject( "An unknown error occurred." ) );
			}

			return( $q.reject( response.data.message ) );
		}
	})

	.service('CustomHttp', function($q, $http) {
		return ({
			get : get,
			post : post,
			put : put,
			remove : remove
		});
		function post(url, data) {
			var request =  $http({
				method: 'POST',
				url: url,
				data: data
			});
			return( request.then(handleSuccess, handleError) );
		}

		function get(url, params, isCache) {
			var request =  $http({
				method: 'GET',
				url: url,
				params: params,
				cache: isCache
			});

			return( request.then(handleSuccess, handleError) );
		}
		function put(url, data) {
			var request =  $http({
				method: 'PUT',
				url: url,
				data: data
			});

			return( request.then(handleSuccess, handleError) );
		}
		function remove(url, idArray) {
			if(idArray) {
				var jsonString = JSON.stringify(idArray);
				var request =  $http({
					method: 'DELETE',
					url: url,
					params: {idList : jsonString}
				});
			} else {
				var request = $http({
					method:'DELETE',
					url: url
				});
			}

			return( request.then(handleSuccess, handleError) );
		}
		function handleError(response) {

			if ( ! angular.isObject( response.data ) || ! response.data.message) {
				$.smallBox({
					title : "An unknown error occurred.",
					content : "",
					color : "#A65858",
					iconSmall : "fa fa-times",
					timeout : 5000
				});
				return( $q.reject( "An unknown error occurred." ) );
			}

			return( $q.reject( response.data.message ) );
		}
		function handleSuccess(response) {
			return( response.data );
		}
	})

	.service("WholesaleService", function($http, $q, CustomHttp) {
		return({
			getSumTotal : getSumTotal,
			findAll : function(params) {
				return CustomHttp.get('api/wholesale/' , params || {}, true);
			},
			resetCalculate: function(params) {
				return CustomHttp.get('api/wholesale/caculate', params || {})
			},
			getGraphData:function(params){
				return CustomHttp.get('api/graph/wholesale', params || {})
			}

		});

		function getSumTotal(ajax_paramater) {
			var request = $http({
				method:'GET',
				url: "api/wholesale/total",
				params: ajax_paramater
			});
			return( request.then(handleSuccess, handleError) );
		}


		function handleError() {
			if (
				! angular.isObject( response.data ) ||
				! response.data.message
				) {

				return( $q.reject( "An unknown error occurred." ) );
			}

			return( $q.reject( response.data.message ) );
		}
		function handleSuccess(response) {
			return( response.data );
		}

	})
	.service('RetailService', function(CustomHttp) {
		return({
			findAll : function(params) {
				return CustomHttp.get('api/retail/' , params || {}, true);
			},
			getSumTotal : function(params) {
				return CustomHttp.get('api/retail/total' , params || {});
			},
			getGraphData:function(params){
				return CustomHttp.get('api/graph/retail', params || {})
			},
			resetCalculate: function(params) {
				return CustomHttp.get('api/retail/calculate', params || {})
			}
		});

	})
	.service('FilterOptions', function(CustomHttp){
		return({
			getWholesaleFilters : function(params){
				return CustomHttp.get( 'api/filter/wholesale' , params || {}, true);
			},
			getMockFilters : function(){
				return CustomHttp.get('mock/filterOptions', {});
			},
			getRetailFilters : function(){
				return CustomHttp.get( 'api/filter/retail' , {}, true);
			},
		});
	})

	.service('RunCalculatorService', function(CustomHttp){
		return({
			filterOptions : function(params) {
				return CustomHttp.get( 'api/runCalculator/filter-options' , params || {}, true);
			},
			snapshotDateOptions : function(params) {
				return CustomHttp.get( 'api/runCalculator/snapshotDateOptions' , params || {}, true);
			},
			getAll : function(params) {
				return CustomHttp.get('api/runCalculator?' + $.param(params), {});
			},
			generateCheck : function(params) {
			    return CustomHttp.get('api/runCalculator/check', params);
			},
			create : function(data) {
			    return CustomHttp.post('api/runCalculator', data);
			},
			remove : function( idArray ) {
                return CustomHttp.remove('api/runCalculator', idArray);
            },
            runCalculation: function(data) {
                return CustomHttp.post('api/runCalculator/runCalculation', data);
            },
            closeCalculation: function(id) {
                return CustomHttp.post('api/runCalculator/closeCalculation/'+id, {});
            }
		});
	})

	.service('CfgConfigurationService', function($http, CustomHttp){
		return ({
			getAll : function(params) {
				return CustomHttp.get('api/configuration?' + $.param(params), {});
			},
			save : function(params) {
				var fd = new FormData();
				fd.append('data', angular.toJson(params.configObj));
        fd.append('file', params.file);
        var request = $http.post("api/configuration", fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        });
				return request;
			},
			remove : function( idArray ) {
          return CustomHttp.remove('api/configuration', idArray);
      },
			download : function( id ) {
				var request = $http.get('api/configuration/download?id='+id);
				return request;
			},
			export : function(row) {
				var request = $http.post('api/configuration/'+row.id+'/export');
				return request;
			},
			import : function(row) {
				var request = $http.post('api/configuration/'+row.id+'/import');
				return request;
			}
		});
	})

	.service('AccountSettingsService', function(CustomHttp){
		return ({
			getAll : function(params) {
				return CustomHttp.get('api/user?' + $.param(params), {});
			},
			getRoles : function() {
				return CustomHttp.get('api/user/roles');
			},
			create : function(data) {
			    return CustomHttp.post('api/user', data);
			},
			update : function(data) {
				return CustomHttp.put('api/user', data);
			},
			remove : function( idArray ) {
          return CustomHttp.remove('api/user', idArray);
      }
		});
	})

	.service('CreditRiskService', function(CustomHttp, CustomHttp2){
		return ({
			getFilterOptions : function(params) {
				return CustomHttp.get('api/credit-risk/filter-options', {});
			},
			getAll : function(params) {
				return CustomHttp2.get('api/credit-risk?' + $.param(params), {});
			},
			getSums : function(params) {
				return CustomHttp.get('api/credit-risk/sums?' + $.param(params), {});
			}
		});
	})

	.factory('CreditRiskState', function(){
		var savedData = {
			currentPage: 1,
			pageLength: 25,
			snapshotDate: ''
		};

		function set(data) {
			savedData = data;
		}

		function get() {
			return savedData;
		}

		return {
			set: set,
			get: get
		}
	})

	.service("BookmarkService", function(CustomHttp){
		var url = 'api/bookmarks/';
		return ({
			findAll : function(params) {
				return CustomHttp.get( url , params );
			},
			update : function(data) {
				return CustomHttp.put( url , data );
			},
			create : function(data) {
				return CustomHttp.post( url , data );
			},
			remove : function(id) {
				return CustomHttp.remove( url + id );
			}
		})
	})

	.service('CustomHttp', function($http, $q) {
		return ({
			get : get,
			post : post,
			put : put,
			remove : remove
		});
		function post(url, data) {
			var request =  $http({
				method: 'POST',
				url: url,
				data: data
			});
			return( request.then(handleSuccess, handleError) );
		}

		function get(url, params, isCache) {
			var request =  $http({
				method: 'GET',
				url: url,
				params: params,
				cache: isCache
			});

			return( request.then(handleSuccess, handleError) );
		}
		function put(url, data) {
			var request =  $http({
				method: 'PUT',
				url: url,
				data: data
			});

			return( request.then(handleSuccess, handleError) );
		}
		function remove(url, idArray) {
			if(idArray) {
//				var jsonString = JSON.stringify(idArray);
				var request =  $http({
					method: 'DELETE',
					url: url,
					params: idArray
				});
			} else {
				var request = $http({
					method:'DELETE',
					url: url
				});
			}

			return( request.then(handleSuccess, handleError) );
		}
		function handleError(response) {

			if ( ! angular.isObject( response.data ) || ! response.data.message) {
				$.smallBox({
					title : "An unknown error occurred.",
					content : "",
					color : "#A65858",
					iconSmall : "fa fa-times",
					timeout : 5000
				});
				return( $q.reject( "An unknown error occurred." ) );
			}

			return( $q.reject( response.data.message ) );
		}
		function handleSuccess(response) {
			return( response.data );
		}
	})
