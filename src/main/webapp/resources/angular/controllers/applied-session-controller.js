admitereApp.controller('appliedSessionsController', function($scope,
		$rootScope, $state, $log, comunicationFactory, config, $uibModal) {
	$scope.appliedSessionsFound = false;
	$scope.containerId = "applied-sessions-container";

	$scope.getUserAppliedSessions = function() {
		$rootScope.showLoader($scope.containerId);
		var successCallback = function(response) {
			if (response.data.content == undefined) {
				return;
			}
			$scope.appliedSessions = response.data.content;
			if ($scope.appliedSessions.length > 0) {
				$scope.appliedSessionsFound = true;
			}
			$rootScope.hideLoader($scope.containerId);
		};

		var errorCallback = function(response) {
			$rootScope.hideLoader($scope.containerId);
		};

		comunicationFactory.makeRequest("applied_sessions", "GET", null,
				successCallback, errorCallback, "");
	};

	$scope.getUserAppliedSessions();

	$scope.moreDetails = function(appliedSessionId) {
		$uibModal.open({
			scope : $scope,
			animation : true,
			templateUrl : 'appliedSessionDetail.html',
			controller : "appliedSessionDetailsController"
		});

		$scope.appliedSession = {};
		for (var i = 0; i < $scope.appliedSessions.length; i++) {
			var tmp = $scope.appliedSessions[i];
			if (tmp && tmp.id && tmp.id == appliedSessionId) {
				$scope.appliedSession = tmp;
				break;
			}
		}
	}
});

// User details modal controller.
admitereApp
		.controller(
				'appliedSessionDetailsController',
				function($scope, $rootScope, $uibModalInstance,
						comunicationFactory) {
					$scope.facultySpecializationNomenclature = $scope.appliedSession.admissionSpecialization.facultySpecializationNomenclature;
					$scope.facultyDomainNomenclature = $scope.facultySpecializationNomenclature.facultyDomainNomenclature;
					$scope.faculty = $scope.facultyDomainNomenclature.faculty;
					$scope.university = $scope.facultyDomainNomenclature.faculty.university;

					// Close the modal.
					$scope.cancel = function() {
						$uibModalInstance.close();
					};
				});