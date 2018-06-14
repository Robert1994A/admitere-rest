// Admission session controller.
admitereApp.controller('admissionSessionController', function($scope,
		comunicationFactory, $state) {

	console.log("Admisison session page controller",
			$state.params.admissionSessionId)

});

admitereApp.controller('admissionSessionsController', function($scope,
		$rootScope, $state, $log, comunicationFactory, config) {
	$scope.sessions = {};
	$scope.sessionsFound = false;
	$scope.containerId = "admission-sessions-container";
	$scope.getFacultySessions = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.sessions = response.data.content;
			if ($scope.sessions.length > 0) {
				$scope.sessionsFound = true;
			}
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("universities/"
				+ $state.params.universityId + "/faculties/"
				+ $state.params.facultyId + "/sessions", "GET", null,
				successCallback, errorCallback, "");
	};

	$scope.getFacultySessions();

	$scope.apply = function(admissionSpecializationId) {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			$rootScope.successModal(response);
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.errorModal(response);
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("admission_specialization/"
				+ admissionSpecializationId + "/apply", "POST", null,
				successCallback, errorCallback, "");
	}
});

// Admission specialization controller for users.
admitereApp.controller('admissionSessionUsersController', function($scope,
		comunicationFactory, $state, $rootScope, $uibModal) {
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

		comunicationFactory.makeRequest("admission_session/"
				+ $state.params.admissionSessionId + "/users", "GET", null,
				successCallback, errorCallback, "");
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

// Admission session controller for statistics.
admitereApp.controller('admissionSessionStatisticsController', function($scope,
		comunicationFactory, $state, $rootScope) {
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

		comunicationFactory.makeRequest("admission_session/"
				+ $state.params.admissionSessionId + "/statistics", "GET",
				null, successCallback, errorCallback, "");
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

admitereApp.controller('admissionSpecializationController', function($scope,
		comunicationFactory, $state) {

	// $state.params.admissionSessionId

});
