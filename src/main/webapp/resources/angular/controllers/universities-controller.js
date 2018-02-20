// Universities controller.
admitereApp.controller('universitiesController', function($scope, $rootScope,
		$log, comunicationFactory, config) {
	$scope.containerId = "universities-container";
	$scope.universitiesFound = false;
	$scope.universities = [];

	// Search initialization.
	$scope.pagination = {};
	$scope.searchText = "";
	$scope.sortDirection = config.sortDirection;
	$scope.sortBy = config.sortBy;
	$scope.perPage = config.perPage;
	$scope.pageNumber = 1;
	// End search initialization.

	$scope.paginate = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.universities = response.data.content.content;
			$rootScope.extractPagination($scope.pagination,
					response.data.content);
			if ($scope.universities.length > 0) {
				$scope.universitiesFound = true;
			}
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$scope.universities = [];
			$scope.universitiesFound = false;
			$rootScope.hideLoader($scope.containerId);
		}

		if ($scope.pagination.number != null
				&& $scope.pagination.number != undefined) {
			$scope.pageNumber = $scope.pagination.number;
		}

		var request = $rootScope.generateSearchRequest($scope.pageNumber,
				$scope.perPage, $scope.searchText, $scope.sortBy,
				$scope.sortDirection);
		comunicationFactory.makeRequest("universities" + request, "GET", null,
				successCallback, errorCallback, null);
	};

	$scope.paginate();

	$scope.search = function() {
		$scope.paginate();
	};
});

admitereApp.controller('universityController', function($scope, $rootScope,
		$state, $log, comunicationFactory, config) {
	$scope.university = {};
	$scope.containerId = "university-container";
	$scope.getUniversity = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.university = response.data.content;
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
			$state.go('^');
		};

		comunicationFactory.makeRequest("universities/"
				+ $state.params.universityId, "GET", null, successCallback,
				errorCallback, "");
	};

	$scope.getUniversity();

});

admitereApp.controller('universityFacultiesController', function($scope,
		$rootScope, $state, $log, comunicationFactory, config) {
	$scope.faculties = {};
	$scope.facultiesFound = false;
	$scope.containerId = "faculties-container";
	$scope.getFaculties = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			if (response.data.content.length > 0) {
				$scope.facultiesFound = true;
			}
			$scope.faculties = response.data.content;
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
			$state.go('^');
		};

		comunicationFactory.makeRequest("universities/"
				+ $state.params.universityId + "/faculties", "GET", null,
				successCallback, errorCallback, "");
	};

	$scope.getFaculties();
});
