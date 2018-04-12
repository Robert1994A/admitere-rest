// Home controller.
admitereApp.controller('homeController', function($scope) {
});

// About controller
admitereApp.controller('aboutController', function($scope, $rootScope) {

});

// Contact controller.
admitereApp.controller('contactController', function($scope) {
});

// Login controller.
admitereApp.controller('loginController', function($scope) {
});

// Recover controller.
admitereApp.controller('recoverController', function($scope) {
});

// Not found controller.
admitereApp.controller('notFoundController', function($scope) {
});

// Account controller.
admitereApp.controller('accountController', function($scope) {

});

admitereApp.controller('errorModalController', function($scope, $rootScope,
		$uibModalInstance) {

	// Close the modal.
	$scope.cancel = function() {
		$uibModalInstance.close();
	};
});

admitereApp.controller('successModalController', function($scope, $rootScope,
		$uibModalInstance) {

	// Close the modal.
	$scope.cancel = function() {
		$uibModalInstance.close();
	};
});

admitereApp.controller('appliedSessionsController', function($scope,
		$rootScope, $state, $log, comunicationFactory, config) {
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
});