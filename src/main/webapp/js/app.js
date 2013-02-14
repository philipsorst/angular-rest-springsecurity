angular.module('exampleApp', ['exampleApp.services']).config(
	[ '$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
		
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
);

function IndexController($scope, NewsService) {
	
	delete $scope.error;
	
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
	
	delete $scope.error;
	
	NewsService.getEntry($routeParams.id, function(newsEntry) {
		$scope.newsEntry = newsEntry;
	});
	
	$scope.save = function() {
		NewsService.saveEntry($scope.newsEntry, function(updatedEntry) {
			$location.path('#!/');
		});
	};
}

function CreateController($scope, $location, NewsService) {
	
	delete $scope.error;
	
	$scope.newsEntry = {
	};
	
	$scope.save = function() {
		NewsService.createEntry($scope.newsEntry, function(createdEntry) {
			$location.path('#!/');
		});
	};
}

function RouteController($scope, $routeParams) {
	$scope.params = $routeParams;
}

var services = angular.module('exampleApp.services', []);

services.service('NewsService', function($http, $rootScope) {
	
	this.getAll = function(callback) {
		$http.get('rest/news/')
			.success(callback)
			.error(function(data, status, headers, config) {
				$rootScope.error = "Getting entries failed: " + status;
			});
	};
	
	this.getEntry = function(id, callback) {
		$http.get('rest/news/' + id)
			.success(callback)
			.error(function(data, status, headers, config) {
				$rootScope.error = "Getting entry " + id + " failed: " +  status;
			});
	};
	
	this.saveEntry = function(newsEntry, callback) {
		$http.put('rest/news/', newsEntry)
			.success(callback)
			.error(function(data, status, headers, config) {
				$rootScope.error = "Saving entry " + newsEntry.id + " failed: " + status;
			});
	};
	
	this.createEntry = function(newsEntry, callback) {
		$http.post('rest/news/', newsEntry)
			.success(callback)
			.error(function(data, status, headers, config) {
				$rootScope.error = "Creating entry failed: " + status;
			});
	};
	
	this.deleteEntry = function(id, callback) {
		$http.delete('rest/news/' + id)
			.success(callback)
			.error(function(data, status, headers, config) {
				$rootScope.error = "Deleting entry " + id + " failed: " +  status;
			});
	};
});