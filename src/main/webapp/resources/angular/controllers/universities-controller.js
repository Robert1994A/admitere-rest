// Universities controller.
admitereApp.controller('universitiesController', function($scope, $rootScope,
		comunicationFactory, config) {
	$scope.containerId = "universities-container";
	$scope.universitiesFound = true;
	$scope.search = "";
	$scope.perPage = config.perPage;
	$scope.pageNumber = 0;
	$scope.pagination = {};
	$scope.universities = [];
	$scope.page = {};

	$scope.paginate = function(page, pageSize, search) {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				$scope.universitiesFound = false;
				return;
			}
			$scope.universities = response.data.content.content;
			$rootScope.extractPagination($scope.pagination,
					response.data.content);
			if ($scope.universities.length == 0) {
				$scope.universitiesFound = false;
			}
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$scope.universitiesFound = false;
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("universities?pageNumber=" + page,
				"GET", null, successCallback, errorCallback, "");
	};

	$scope.paginate($scope.pageNumber, $scope.perPage, $scope.search);
});