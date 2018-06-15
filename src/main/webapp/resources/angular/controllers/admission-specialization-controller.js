// Admission specialization controller.
admitereApp.controller('admissionSpecializationController', function($scope,
		$rootScope, comunicationFactory, $state) {
	$scope.containerId = "admission-specialization-container";
	$scope.admissionSpecialization = {};
	$scope.admissionSpecializationFound = false;
	$scope.getAdmissionSpecialization = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.admissionSpecialization = response.data.content;
			if ($scope.admissionSpecialization) {
				$scope.admissionSpecializationFound = true;
			}
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("admission_specialization/"
				+ $state.params.admissionSpecializationId, "GET", null,
				successCallback, errorCallback, "");
	};

	$scope.getAdmissionSpecialization();
});

// Admission specialization controller for users.
admitereApp.controller('admissionSpecializationUsersController', function(
		$scope, comunicationFactory, $state, $rootScope, $uibModal) {
	$scope.containerId = "admission-specialization-users-container";
	$scope.users = {};
	$scope.usersFound = false;
	$scope.getUsers = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.users = response.data.content;
			if ($scope.users.length > 0) {
				$scope.usersFound = true;
			}
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("admission_specialization/"
				+ $state.params.admissionSpecializationId + "/users", "GET",
				null, successCallback, errorCallback, "");
	};

	$scope.getUsers();

	// Get user details.
	$scope.userProfile = function(userID) {
		$scope.userIDModal = userID;
		// Open user details modal.
		$uibModal.open({
			scope : $scope,
			animation : false,
			templateUrl : 'modals/userProfile.html',
			controller : 'userProfileController'
		});
	};
});

// Admission specialization controller for statistics.
admitereApp.controller('admissionSpecializationStatisticsController', function(
		$scope, $rootScope, comunicationFactory, $state) {
	$scope.containerId = "admission-specialization-statistics-container";
	$scope.statistics = {};
	$scope.statisticsFound = false;
	$scope.getStatistics = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.statistics = response.data.content;
			if ($scope.statistics.length > 0) {
				$scope.statisticsFound = true;
			}
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("admission_specialization/"
				+ $state.params.admissionSpecializationId + "/statistics",
				"GET", null, successCallback, errorCallback, "");
	};

	$scope.chartOptions = {
		scales : {
			yAxes : [ {
				ticks : {
					beginAtZero : true
				}
			} ]
		}
	};

	$scope.getStatistics();
});
