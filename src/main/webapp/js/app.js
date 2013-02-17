angular.module('exampleApp', ['exampleApp.services'])
	.config(
		[ '$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
			
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
	
	NewsService.getAll(function(newsEntries) {
		$scope.newsEntries = newsEntries;	
	});
	
	$scope.deleteEntry = function(id) {
		NewsService.deleteEntry(id, function() {
			NewsService.getAll(function(newsEntries) {
				$scope.newsEntries = newsEntries;	
			});	
		});
	};
};

function EditController($scope, $routeParams, $location, NewsService) {

	NewsService.getEntry($routeParams.id, function(newsEntry) {
		$scope.newsEntry = newsEntry;
	});
	
	$scope.save = function() {
		NewsService.saveEntry($scope.newsEntry, function(updatedEntry) {
			$location.path('#!/');
		});
	};
};

function CreateController($scope, $location, NewsService) {
	
	$scope.newsEntry = {
	};
	
	$scope.save = function() {
		NewsService.createEntry($scope.newsEntry, function(createdEntry) {
			$location.path('#!/');
		});
	};
};

function RouteController($scope, $routeParams) {
	
	$scope.params = $routeParams;
};

var services = angular.module('exampleApp.services', []);

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

services.service('NewsService', function($http, $rootScope, $window) {
	
	var self = this;
	
	/* TODO: This should probably be done with an interceptor */
	this.unauthorizedHandler = function(status) {
		if (status == 401 || status == 403) {
			$window.location = $window.location.protocol + "//" + $window.location.host + $window.location.pathname + "login.html";
		}
	};
	
	this.getAll = function(callback) {
		$http.get('rest/news/')
			.success(callback)
			.error(function(data, status, headers, config) {
				self.unauthorizedHandler(status);
				$rootScope.error = "Getting entries failed: " + status;
			});
	};
	
	this.getEntry = function(id, callback) {
		$http.get('rest/news/' + id)
			.success(callback)
			.error(function(data, status, headers, config) {
				self.unauthorizedHandler(status);
				$rootScope.error = "Getting entry " + id + " failed: " +  status;
			});
	};
	
	this.saveEntry = function(newsEntry, callback) {
		$http.put('rest/news/', newsEntry)
			.success(callback)
			.error(function(data, status, headers, config) {
				self.unauthorizedHandler(status);
				$rootScope.error = "Saving entry " + newsEntry.id + " failed: " + status;
			});
	};
	
	this.createEntry = function(newsEntry, callback) {
		$http.post('rest/news/', newsEntry)
			.success(callback)
			.error(function(data, status, headers, config) {
				self.unauthorizedHandler(status);
				$rootScope.error = "Creating entry failed: " + status;
			});
	};
	
	this.deleteEntry = function(id, callback) {
		$http['delete']('rest/news/' + id)
			.success(callback)
			.error(function(data, status, headers, config) {
				self.unauthorizedHandler(status);
				$rootScope.error = "Deleting entry " + id + " failed: " +  status;
			});
	};
});