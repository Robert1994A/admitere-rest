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
admitereApp.controller('loginController', function($scope, $location) {
	$scope.successfully = false;
	if ($location.search() && $location.search().logout) {
		$scope.successfullyLogout = true;
	}
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
