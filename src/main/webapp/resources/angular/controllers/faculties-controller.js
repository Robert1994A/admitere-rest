// Faculties controller.
admitereApp.controller('facultiesController', function($scope, $rootScope,
		comunicationFactory, config) {
	$scope.faculties = [];
	$scope.facultiesFound = false;
	$scope.facultyNavigationOpened = false;
	$scope.getFaculties = function(universityId, universityName) {
		var facultyContainerId = "faculties-container";
		$rootScope.showLoader(facultyContainerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.faculties = response.data.content;
			if ($scope.faculties.length > 0) {
				$scope.universityNavigationOpened = false;
				$scope.facultyNavigationOpened = true;
				$scope.facultiesFound = true;
				$scope.universityName = universityName;
			}
			$rootScope.hideLoader(facultyContainerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader(facultyContainerId);
		};

		comunicationFactory
				.makeRequest("universities/" + universityId + "/faculties",
						"GET", null, successCallback, errorCallback, "");
	}
});