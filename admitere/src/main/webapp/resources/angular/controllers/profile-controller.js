// Profile controller.
admitereApp.controller('profileController', function($scope, $rootScope,
		comunicationFactory) {
	$scope.profile = {};
	$scope.countries = [];
	$scope.states = [];
	$scope.cities = [];
	$scope.profileData = {};
	$scope.profile.birthDate = $rootScope.maxDate;

	initInterfaceData();

	function initInterfaceData() {
		var successCallback = function(response) {
			$scope.countries = response.data.content;
		}

		comunicationFactory.makeRequest("countries", "GET", null,
				successCallback, null, null);

		var successCallback = function(response) {
			$scope.profileData = response.data;
		}

		comunicationFactory.makeRequest("profile/data", "GET", null,
				successCallback, null, null);
	}

	$scope.$watch("profile.country.id", function(newValue, oldValue) {
		if (newValue != undefined && newValue != oldValue) {
			var successCallback = function(response) {
				$scope.states = response.data.content;
			}

			var errorCallback = function(response) {
				$scope.states = [ {
					"id" : 0,
					"name" : "No state was found for selected country."
				} ];
			}

			comunicationFactory.makeRequest("states?countryId=" + newValue,
					"GET", null, successCallback, errorCallback, null);
		}
	});

	$scope.$watch("profile.state.id", function(newValue, oldValue) {
		if (newValue != undefined && newValue != oldValue) {
			var successCallback = function(response) {
				$scope.cities = response.data.content;
			}

			var errorCallback = function(response) {
				$scope.cities = [ {
					"id" : 0,
					"name" : "No city was found for selected state."
				} ];
			}

			comunicationFactory.makeRequest("cities?stateId=" + newValue,
					"GET", null, successCallback, errorCallback, null);
		}
	});

	$scope.saveProfile = function() {
		var successCallback = function(response) {
			$scope.profileCreated = true;
			console.log("profile response success", response);
		};

		var errorCallback = function(response) {
			console.log("profile response error", response);
		};

		comunicationFactory.makeRequest("profile", "POST", $scope.profile,
				successCallback, errorCallback, null);

	}

	$scope.getProfile = function() {
		var successCallback = function(response) {
			$scope.profileCreated = true;
			console.log("Get profile success", response);
		};

		var errorCallback = function(response) {
			console.log("Get profile error", response);
		};

		comunicationFactory.makeRequest("profile", "GET", $scope.profile,
				successCallback, errorCallback, null);

	}

	$scope.getProfile();

	$scope.clear = function() {
		$scope.profile.birthDate = null;
	};

	$scope.datePopupOpened = false;

	$scope.setDate = function(year, month, day) {
		$scope.profile.birthDate = new Date(year, month, day);
	};

	$scope.altInputFormats = [ 'M!/d!/yyyy' ];

	$scope.openDatePopup = function() {
		$scope.datePopupOpened = true;
	};
});