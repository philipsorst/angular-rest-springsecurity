angular.module('exampleApp', ['exampleApp.services'])
	.config(
		[ '$routeProvider', '$locationProvider', '$httpProvider', function($routeProvider, $locationProvider, $httpProvider) {
			
			$routeProvider.when('/loggedin', {
				templateUrl: 'partials/loggedin.html',
				controller: LoginController
			});
			
			$routeProvider.when('/create', {
				templateUrl: 'partials/create.html',
				controller: CreateController
			});
			
			$routeProvider.when('/edit/:id', {
				templateUrl: 'partials/edit.html',
				controller: EditController
			});
			
			$routeProvider.otherwise({
				templateUrl: 'partials/index.html',
				controller: IndexController
			});
			
			$locationProvider.hashPrefix('!');
			
			/* Intercept http errors */
			var interceptor = function ($rootScope, $q, $window) {

		        function success(response) {
		            return response;
		        }

		        function error(response) {
		        	
		            var status = response.status;
		            var config = response.config;
		            var method = config.method;
		            var url = config.url;

//		            if (status == 401 || status == 403) {
//		            	$window.location = $window.location.protocol + "//" + $window.location.host + $window.location.pathname + "login.html";
//		            } else {
		            	$rootScope.error = method + " on " + url + " failed with status " + status;
//		            }
		            
		            return $q.reject(response);
		        }

		        return function (promise) {
		            return promise.then(success, error);
		        };
		    };
		    $httpProvider.responseInterceptors.push(interceptor);
		} ]
	).run(function($rootScope, $location, LoginService) {
		
		/* Reset error when a new view is loaded */
		$rootScope.$on('$viewContentLoaded', function() {
			delete $rootScope.error;
		});
		
		$rootScope.hasRole = function(role) {
			
			if ($rootScope.user === undefined) {
				return false;
			}
			
			if ($rootScope.user.roles[role] === undefined) {
				return false;
			}
			
			return $rootScope.user.roles[role];
		};
		
		LoginService.getUser(function(user) {
			$rootScope.user = user;
			$location.path("/");
		});
		
	});

function LoginController($rootScope, $location, LoginService) {
	
	LoginService.getUser(function(user) {
		$rootScope.user = user;
		$location.path("/");
	});
};

function IndexController($scope, NewsService) {
	
	$scope.newsEntries = NewsService.query();
	
	$scope.deleteEntry = function(newsEntry) {
		newsEntry.$remove(function() {
			$scope.newsEntries = NewsService.query();
		});
	};
};

function EditController($scope, $routeParams, $location, NewsService) {

	$scope.newsEntry = NewsService.get({id: $routeParams.id});
	
	$scope.save = function() {
		$scope.newsEntry.$save(function() {
			$location.path('#!/');
		});
	};
};

function CreateController($scope, $location, NewsService) {
	
	$scope.newsEntry = new NewsService();
	
	$scope.save = function() {
		$scope.newsEntry.$save(function() {
			$location.path('#!/');
		});
	};
};

function RouteController($scope, $routeParams) {
	
	$scope.params = $routeParams;
};

var services = angular.module('exampleApp.services', ['ngResource']);

services.service('LoginService', function($http, $rootScope) {
	
	this.getUser = function(callback) {
		$http.get('rest/user/')
			.success(callback)
			.error(function(data, status, headers, config) {
				self.unauthorizedHandler(status);
				$rootScope.error = "Getting user failed: " + status;
			});
	};
});

services.factory('NewsService', function($resource) {
	
	return $resource('rest/news/:id', {id: '@id'});
});