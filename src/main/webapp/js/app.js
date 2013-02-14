angular.module('exampleApp', ['exampleApp.services']).config(
	[ '$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
		
//		$routeProvider.when('/changelog/', {
//			templateUrl: 'partials/changelog.html',
//			controller: ChangelogController
//		});
		
		$routeProvider.otherwise({
			templateUrl: 'partials/index.html',
			controller: IndexController
		});
		
		$locationProvider.hashPrefix('!');
	} ]
);

function IndexController($scope, NewsService) {
	NewsService.getAll(function(newsEntries) {
		$scope.newsEntries = newsEntries;	
	});
};

function RouteController($scope, $routeParams) {
	$scope.params = $routeParams;
}

var services = angular.module('exampleApp.services', []);

services.service('NewsService', function($http) {
	
	this.getAll = function(callback) {
		$http.get('rest/news/').success(callback);
	};
});