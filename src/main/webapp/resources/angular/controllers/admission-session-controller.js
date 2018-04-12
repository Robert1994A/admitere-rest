// Admission session controller.
admitereApp.controller('admissionSessionController', function($scope,
		comunicationFactory, $state) {

	alert($state.params.admissionSessionId)

});

admitereApp.controller('admissionSessionsController', function($scope,
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

		comunicationFactory.makeRequest("/admission_session/apply/"
				+ admissionSpecializationId, "POST", null, successCallback,
				errorCallback, "");
	}
});
