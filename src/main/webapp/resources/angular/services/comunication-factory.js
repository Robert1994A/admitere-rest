admitereApp.factory('comunicationFactory', function($http, config) {
	return {
		makeRequest : function(url, type, data, successCallback, errorCallback, finallyCalback) {
			$http({
				url : config.url + url,
				method : type,
				data : data,
				headers : {
					"Accept" : "application/json",
					"Content-Type": "application/json"
				}
			}).then(function(response) {
				successCallback(response);
			}).catch(function(data) {
				if (errorCallback == null) {
					console.log(data);
				} else {
					errorCallback(data);
				}
			}).finally(function(data) {
				if (finallyCalback === null || finallyCalback === "") {
					// Stay silent.
					// console.log(data);
				} else {
					// finallyCalback(data);
				}
			});
		},
		restRequest : function(url, type, data) {
			$http({
				url : url,
				method : type,
				data : data,
				headers : {
					'Accept' : 'application/json'
				}
			}).then(function(response) {
				return response.data;
			});
		}
	};
});