// Register controller.
admitereApp.controller('registerController', function($scope, $rootScope,
		comunicationFactory) {

	$scope.captchaSiteKey = null;

	$scope.getCaptchaSiteKey = function() {
		var successCallback = function(response) {
			$scope.captchaSiteKey = response.data.content;
		};

		var errorCallback = function(response) {
		};

		comunicationFactory.makeRequest("get_captcha_site_key", "GET",
				$scope.profile, successCallback, errorCallback, null);
	}
	$scope.getCaptchaSiteKey();

	$scope.register = function() {
		if ($scope.registerForm.$valid) {
			var successCallback = function(response) {
				$rootScope.successModal(response);
			};

			var errorCallback = function(response) {
				$rootScope.errorModal(response);
			};

			comunicationFactory.makeRequest("register", "POST", $scope.user,
					successCallback, errorCallback, null);
		} else {
			$rootScope.errorModal("Complete all fields.");
		}
	}
});