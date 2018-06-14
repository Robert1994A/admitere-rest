// Users page controller.
admitereApp.controller('usersController', function($scope, $rootScope,
		$timeout, $uibModal, comunicationFactory, config) {
	$scope.containerId = "users-container";
	$scope.usersFound = false;
	$scope.users = [];

	// Search initialization.
	$scope.pagination = {};
	$scope.sortDirection = config.sortDirection;
	$scope.sortBy = config.sortBy;
	$scope.perPage = config.perPage;
	$scope.pageNumber = 1;
	$scope.searchText = "";
	// End search initialization.

	$scope.paginate = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.users = response.data.content.content;
			$rootScope.extractPagination($scope.pagination,
					response.data.content);
			if ($scope.users.length > 0) {
				$scope.usersFound = true;
			}
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$scope.users = [];
			$scope.usersFound = false;
			$rootScope.hideLoader($scope.containerId);
		}

		if ($scope.pagination.number != null
				&& $scope.pagination.number != undefined) {
			$scope.pageNumber = $scope.pagination.number;
		}

		var request = $rootScope.generateSearchRequest($scope.pageNumber,
				$scope.perPage, $scope.searchText, $scope.sortBy,
				$scope.sortDirection);
		comunicationFactory.makeRequest("users" + request, "GET", null,
				successCallback, errorCallback, null);
	};

	$scope.search = function(searchText, sortBy, perPage, sortDirection) {
		$scope.searchText = searchText;
		$scope.sortBy = sortBy;
		$scope.perPage = perPage;
		$scope.sortDirection = sortDirection;
		if ($scope.searchText && $scope.searchText != "") {
			$scope.pageNumber = 1;
		}
		$scope.paginate();
	};

	// Find all users.
	$scope.paginate();

	// Get user details.
	$scope.userDetails = function(userID) {
		// Open user details modal.
		$uibModal.open({
			scope : $scope,
			animation : true,
			templateUrl : 'modals/userDetails.html',
			controller : 'userDetailsController'
		});

		$scope.userIDModal = userID;
	};

	// Get user details.
	$scope.userProfile = function(userID) {
		// Open user details modal.
		$uibModal.open({
			scope : $scope,
			animation : true,
			templateUrl : 'modals/userProfile.html',
			controller : 'userProfileController'
		});

		$scope.userIDModal = userID;
	};
});

// User details modal controller.
admitereApp.controller('userDetailsController', function($scope, $rootScope,
		$uibModalInstance, comunicationFactory) {
	// Retrieve user details.
	userDetails($scope.userIDModal);

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
	$scope.profileFound = true;
	$scope.containerId = "user-profile-container";
	// Retrieve user profile.
	userProfile($scope.userIDModal);

	// Get user profile based on his identifier.
	function userProfile(id) {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			$scope.profile = response.data.content;
			if (!($scope.profile && $scope.profile != null)) {
				$scope.profileFound = false;
			}
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("users/" + id + "/profile", "GET",
				null, successCallback, errorCallback, null);
	}

	// Close the modal.
	$scope.cancel = function() {
		$uibModalInstance.close();
	};
});

// Add user controller.
admitereApp.controller('addUserController', function($scope, $rootScope,
		comunicationFactory) {

});