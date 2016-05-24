
var app = angular.module("myShoppingList", []); 
app.controller("myController", function($scope, $http) {
/*	$scope.shoppingList = [{
		id: '',
		updatedValue: ''
	}];*/
	
	this.sendData = function () {
		$http.post("", $scope.shoppingList);
	}
});

