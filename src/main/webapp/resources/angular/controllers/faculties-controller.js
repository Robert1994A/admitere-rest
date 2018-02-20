// Faculties controller.
admitereApp.controller('facultiesController', function($scope, $rootScope,
		comunicationFactory, config) {
	$scope.faculties = [];
	$scope.facultiesFound = false;
	$scope.getFaculties = function(universityId) {
		var facultyContainerId = "faculties-container";
		$rootScope.showLoader(facultyContainerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.faculties = response.data.content;
			if ($scope.faculties.length > 0) {
				$scope.facultiesFound = true;
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

admitereApp.controller('facultyController', function($scope, $rootScope,
		$state, $log, comunicationFactory, config) {
	$scope.containerId = "faculty-container";
	$scope.faculty = {};
	$scope.getFaculty = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.faculty = response.data.content;
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			if(response != null && response.status == "404"){
				$state.go('^');
			}
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("universities/"
				+ $state.params.universityId + "/faculties/"
				+ $state.params.facultyId, "GET", null, successCallback,
				errorCallback, "");
	};

	$scope.getFaculty();
});

admitereApp.controller('addFacultyController', function($scope, $rootScope,
		$state, $log, comunicationFactory, config) {
});

admitereApp.controller('facultyAddSpecializationController', function($scope,
		$rootScope, $state, $log, comunicationFactory, config) {
});

admitereApp.controller('facultyDomainsController', function($scope, $rootScope,
		$state, $log, comunicationFactory, config) {
	$scope.domains = {};
	$scope.domainsFound = false;
	$scope.containerId = "domains-container";
	$scope.getFacultyDomains = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.domains = response.data.content;
			if ($scope.domains.length > 0) {
				$scope.domainsFound = true;
			}
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("universities/"
				+ $state.params.universityId + "/faculties/"
				+ $state.params.facultyId + "/domains", "GET", null,
				successCallback, errorCallback, "");
	};

	$scope.getFacultyDomains();

});

admitereApp.controller('facultySessionsController', function($scope,
		$rootScope, $state, $log, comunicationFactory, config) {
	$scope.sessions = {};
	$scope.sessionsFound = false;
	$scope.containerId = "sessions-container";
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
});

admitereApp.controller('facultySpecializationsController', function($scope,
		$rootScope, $state, $log, comunicationFactory, config) {
	$scope.specializations = {};
	$scope.specializationsFound = false;
	$scope.containerId = "specializations-container";
	$scope.getFacultySessions = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.specializations = response.data.content;
			if ($scope.specializations.length > 0) {
				$scope.specializationsFound = true;
			}
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("universities/"
				+ $state.params.universityId + "/faculties/"
				+ $state.params.facultyId + "/specializations", "GET", null,
				successCallback, errorCallback, "");
	};

	$scope.getFacultySessions();
});

admitereApp.controller('facultyAddSessionController', function($scope,
		$rootScope, $state, $log, comunicationFactory, config) {
});