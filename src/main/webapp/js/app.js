angular.module('exampleApp', ['ngRoute', 'ngCookies', 'exampleApp.services','restangular'])
	.config(
		[ '$routeProvider', '$locationProvider', '$httpProvider','RestangularProvider', function($routeProvider, $locationProvider, $httpProvider, RestangularProvider) {
			
			$routeProvider.when('/create', {
				templateUrl: 'partials/create.html',
				controller: CreateController
			});
			
			$routeProvider.when('/edit/:id', {
				templateUrl: 'partials/edit.html',
				controller: EditController
			});

			$routeProvider.when('/login', {
				templateUrl: 'partials/login.html',
				controller: LoginController
			});
			
			$routeProvider.otherwise({
				templateUrl: 'partials/index.html',
				controller: IndexController
			});
			
			$locationProvider.hashPrefix('!');
			
			/* Register error provider that shows message on failed requests or redirects to login page on
			 * unauthenticated requests */
		    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
			        return {
			        	'responseError': function(rejection) {
			        		var status = rejection.status;
			        		var config = rejection.config;
			        		var method = config.method;
			        		var url = config.url;
			      
			        		if (status == 401) {
			        			$location.path( "/login" );
			        		} else {
			        			$rootScope.error = method + " on " + url + " failed with status " + status;
			        		}
			              
			        		return $q.reject(rejection);
			        	}
			        };
			    }
		    );
		    
		    /* Registers auth token interceptor, auth token is either passed by header or by query parameter
		     * as soon as there is an authenticated user */
		    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
		        return {
		        	'request': function(config) {
		        		var isRestCall = config.url.indexOf('rest') == 0;
		        		if (isRestCall && angular.isDefined($rootScope.authToken)) {
		        			var authToken = $rootScope.authToken;
		        			if (exampleAppConfig.useAuthTokenHeader) {
		        				config.headers['X-Auth-Token'] = authToken;
		        			} else {
		        				config.url = config.url + "?token=" + authToken;
		        			}
		        		}
		        		return config || $q.when(config);
		        	}
		        };
		    });


		    RestangularProvider.setBaseUrl('rest')
		   
		} ]
		
	).run(function($rootScope, $location, $cookieStore, UserService) {
		
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
		
		$rootScope.logout = function() {
			delete $rootScope.user;
			delete $rootScope.authToken;
			$cookieStore.remove('authToken');
			$location.path("/login");
		};
		
		 /* Try getting valid user from cookie or go to login page */
		var originalPath = $location.path();
		$location.path("/login");
		var authToken = $cookieStore.get('authToken');
		if (authToken !== undefined) {
			$rootScope.authToken = authToken;
			UserService.get("").then(function(user) {
				$rootScope.user = user;
				$location.path("/");
			});
		}
		
		$rootScope.initialized = true;
	});


function IndexController($scope, NewsService) {
	
	NewsService.getList().then(function(entries){
		$scope.newsEntries = entries;
	});
	$scope.deleteEntry = function(newsEntry) {
		newsEntry.remove().then(function() {
			$scope.newsEntries = NewsService.getList().$object
		});
	};
};


function EditController($scope, $routeParams, $location, NewsService) {

	NewsService.get($routeParams.id).then(function(enrty){
		$scope.newsEntry = enrty;
	});

	$scope.save = function() {
		$scope.newsEntry.post().then(function() {
			$location.path('/');
		});
	};
};


function CreateController($scope, $location, NewsService) {
	

	$scope.save = function() {
		NewsService.post($scope.newsEntry).then(function() {
			$location.path('/');
		});
	};
};


function LoginController($scope, $rootScope, $location, $cookieStore, UserService) {
	
	$scope.rememberMe = false;

	$scope.login = function() {

		UserService.customPOST($.param({username: $scope.username, password: $scope.password}),"authenticate",{},{'Content-Type': 'application/x-www-form-urlencoded'}).then(function(authenticationResult) {
			var authToken = authenticationResult.token;
			$rootScope.authToken = authToken;

			if ($scope.rememberMe) {
				$cookieStore.put('authToken', authToken);
			}
			UserService.get("").then(function(user) {
				$rootScope.user = user;
				$location.path("/");
			});

		});
	};
};


var services = angular.module('exampleApp.services', []);

services.factory('UserService', function(Restangular) {
	
	return Restangular.all('user');
});

services.factory('NewsService', function(Restangular) {
	
	return Restangular.all('news');
});