// Users page controller.
admitereApp.controller('usersController', function($scope, $rootScope,
		$timeout, $uibModal, comunicationFactory, config) {
	$scope.usersFound = true;
	$scope.pagination = {};
	$scope.users = [];
	$scope.page = {};

	$scope.paginate = function(page, pageSize, search) {
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				$scope.usersFound = false;
				return;
			}
			$scope.users = response.data.content.content;
			$rootScope.extractPagination($scope.pagination,
					response.data.content);
			if ($scope.users.length == 0) {
				$scope.usersFound = false;
			}
		};
		comunicationFactory.makeRequest("users?pageNumber=" + page, "GET",
				null, successCallback, null, null);
	};

	$scope.refresh = function() {
		$scope.paginate(0, 10, "");
	};

	// Find all users.
	$scope.paginate(0, 10, "");

	// Get user details.
	$scope.userDetails = function(userID) {
		// Open user details modal.
		$uibModal.open({
			animation : true,
			templateUrl : 'modals/userDetails.html',
			controller : 'userDetailsController'
		});

		$rootScope.userIDModal = userID;
	};

	// Get user details.
	$scope.userProfile = function(userID) {
		// Open user details modal.
		$uibModal.open({
			animation : true,
			templateUrl : 'modals/userProfile.html',
			controller : 'userProfileController'
		});

		$rootScope.userIDModal = userID;
	};
});

// User details modal controller.
admitereApp.controller('userDetailsController', function($scope, $rootScope,
		$uibModalInstance, comunicationFactory) {
	// Retrieve user details.
	userDetails($rootScope.userIDModal);

	// Get user details based on his identifier.
	function userDetails(id) {
		var successCallback = function(response) {
			$scope.user = response.data.content;
		};

		comunicationFactory.makeRequest("users/" + id, "GET", null,
				successCallback, null, null);
	}

	// Close the modal.
	$scope.cancel = function() {
		$uibModalInstance.close();
	};
});

// User details modal controller.
admitereApp.controller('userProfileController', function($scope, $rootScope,
		$uibModalInstance, comunicationFactory) {
	// Retrieve user details.
	userProfile($rootScope.userIDModal);

	// Get user details based on his identifier.
	function userProfile(id) {
		var successCallback = function(response) {
			$scope.profile = response.data.content;
		};

		comunicationFactory.makeRequest("users/" + id + "/profile", "GET",
				null, successCallback, null, null);
	}

	// Close the modal.
	$scope.cancel = function() {
		$uibModalInstance.close();
	};
});