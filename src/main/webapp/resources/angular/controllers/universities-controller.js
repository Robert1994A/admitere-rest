// Universities controller.
admitereApp.controller('universitiesController', function($scope, $rootScope,
		$log, comunicationFactory, config) {
	$scope.containerId = "universities-container";
	$scope.universitiesFound = false;
	$scope.search = "";
	$scope.perPage = config.perPage;
	$scope.pageNumber = 0;
	$scope.pagination = {};
	$scope.universities = [];
	$scope.page = {};
	$scope.universityNavigationOpened = true;

	$scope.paginate = function(page, pageSize, search) {
		if (page > 0) {
			page = page - 1;
		}
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.universities = response.data.content.content;
			$rootScope.extractPagination($scope.pagination,
					response.data.content);
			// $log.info($scope.pagination);
			if ($scope.universities.length > 0) {
				$scope.universitiesFound = true;
			}
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("universities?pageNumber=" + page,
				"GET", null, successCallback, errorCallback, "");
	};

	$scope.paginate($scope.pageNumber, $scope.perPage, $scope.search);
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

admitereApp.controller('facultyController', function($scope, $rootScope,
		$state, $log, comunicationFactory, config) {
	$scope.faculty = {};
	$scope.containerId = "faculty-container";
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
			$rootScope.hideLoader($scope.containerId);
			$state.go('^');
		};

		comunicationFactory.makeRequest("universities/"
				+ $state.params.universityId + "/faculties/"
				+ $state.params.facultyId, "GET", null, successCallback,
				errorCallback, "");
	};

	$scope.getFaculty();

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

admitereApp.controller('facultyAddSessionController', function($scope,
		$rootScope, $state, $log, comunicationFactory, config) {
});