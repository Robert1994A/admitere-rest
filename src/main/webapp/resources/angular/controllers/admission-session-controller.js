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
			$rootScope.successModal(response.message);
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.errorModal(response.message);
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("admission_session/apply/"
				+ admissionSpecializationId, "POST", null, successCallback,
				errorCallback, "");
	}
});

// Admission session controller for statistics.
admitereApp.controller('admissionSessionStatisticsController', function($scope,
		comunicationFactory, $state) {
	$scope.labels = [ '2006', '2007', '2008', '2009', '2010', '2011', '2012' ];
	$scope.series = [ 'Series A' ];
	$scope.data = [ 65, 59, 80, 81, 56, 55, 40 ];

	console.log("Admission session statistics page",
			$state.params.admissionSessionId)
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
		scales: {
	        yAxes: [{
	            ticks: {
	                beginAtZero: true
	            }
	        }]
	    }
	};
	
	$scope.getStatistics();
});
