// Users page controller.
admitereApp.controller('usersController', function($scope, $rootScope,
		$timeout, $uibModal, comunicationFactory, config) {
	$scope.containerId = "users-container";
	$scope.usersFound = true;
	$scope.pagination = {};
	$scope.users = [];
	$scope.page = {};
	$scope.search = "";
	$scope.perPage = config.perPage;
	$scope.pageNumber = 0;

	$scope.paginate = function(page, pageSize, search) {
		$rootScope.showLoader($scope.containerId);
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
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
		}
		comunicationFactory.makeRequest("users?pageNumber=" + page, "GET",
				null, successCallback, errorCallback, null);
	};

	$scope.refresh = function() {
		$scope.paginate(0, config.perPage, "");
	};

	// Find all users.
	$scope.paginate(0, $scope.perPage, $scope.search);

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
	$scope.profileFound = false;

	// Retrieve user profile.
	userProfile($scope.userIDModal);

	// Get user profile based on his identifier.
	function userProfile(id) {
		var successCallback = function(response) {
			$scope.profile = response.data.content;
			$scope.profileFound = true;
		};

		comunicationFactory.makeRequest("users/" + id + "/profile", "GET",
				null, successCallback, null, null);
	}

	// Close the modal.
	$scope.cancel = function() {
		$uibModalInstance.close();
	};
});