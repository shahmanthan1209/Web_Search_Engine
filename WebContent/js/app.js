var app = angular.module("myApp",[]);

app.controller("searchController", function($scope,$http) {
	
	$scope.search = function(){
		console.log($scope.inputKeyword);
		
		$http({
			method : 'GET',
			url : '/rest/search/s',
//			headers: {'Content-Type': 'application/x-www-form-urlencoded'}
		}).then(function(response) {
			$scope.demoString = response;

		}, function(error) {
			alert("Failed to get data, status=" + error);
		});
	}

	
	
});