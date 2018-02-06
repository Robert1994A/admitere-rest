// Home controller.
admitereApp.controller('homeController', function($scope) {
});

// About controller
admitereApp.controller('aboutController', function($scope) {
});

// Contact controller.
admitereApp.controller('contactController', function($scope) {
});

// Login controller.
admitereApp.controller('loginController', function($scope) {
});

// Recover controller.
admitereApp.controller('recoverController', function($scope) {
});

// Universities controller.
admitereApp.controller('universitiesController', function($scope, $rootScope,
		comunicationFactory) {

	$scope.universitiesFound = true;
	$scope.pagination = {};
	$scope.universities = [];
	$scope.page = {};

	$scope.paginate = function(page, pageSize, search) {
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
		};

		var errorCallback = function(response) {
			$scope.universitiesFound = false;
		};

		comunicationFactory.makeRequest("universities?pageNumber=" + page,
				"GET", null, successCallback, errorCallback, null);
	};

	$scope.refresh = function() {
		$scope.paginate(0, 10, null);
	};

	$scope.paginate(0, 10, null);
});

// Not found controller.
admitereApp.controller('notFoundController', function($scope) {
});

// Register controller.
admitereApp.controller('registerController', function($scope) {

});