// create the module and name it admitereApp
var admitereApp = angular.module(
		'admitereApp',
		[ 'ngRoute', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'bw.paging',
				'ngMessages' ]).config(function($httpProvider) {
	// $httpProvider.interceptors.push(loaderInterceptor);
});

// Interceptor to show loader when a request is made.
var loaderInterceptor = function($q) {
	return {
		request : function(config) {
			// Show loader
			return config;
		},

		response : function(result) {
			// Hide loader
			return result;
		},
		responseError : function(rejection) {
			// Hide loader.
			return $q.reject(rejection);
		}
	}
}

admitereApp.run(function($rootScope, $templateCache) {
	$rootScope.$on('$viewContentLoaded', function() {
		// $templateCache.removeAll();
	});
	$rootScope.isAuthenticated = false;

	$rootScope.authenticatedUser = {};

	$rootScope.dateFormat = 'dd-MMMM-yyyy';

	maxDate = new Date();
	maxDate.setFullYear(maxDate.getFullYear() - 17);
	$rootScope.maxDate = maxDate;

	$rootScope.dateOptions = {
		formatYear : 'yy',
		maxDate : maxDate,
		minDate : new Date(1910, 1, 1),
		startingDay : 1
	};

	$rootScope.extractPagination = function(pagination, data) {
		pagination.totalPages = data.totalPages;
		pagination.totalElements = data.totalElements;
		pagination.last = data.last;
		pagination.size = data.size;
		pagination.number = data.number;
		pagination.sort = data.sort;
		pagination.first = data.first;
		pagination.numberOfElements = data.numberOfElements;
	}

	$rootScope.getAuthenticatedUserDetails = function() {
		var successCallback = function(response) {
			$rootScope.isAuthenticated = true;
			$rootScope.authenticatedUser = response.content;
		};

		var errorCallback = function(response) {
			$rootScope.isAuthenticated = false;
		};

		comunicationFactory.makeRequest("/security/authenticationDetails",
				"GET", null, successCallback, errorCallback, null);
	};

	$rootScope.showLoader = function(containerId) {
		document.getElementById("container-loader").style.display = "block";
		document.getElementById(containerId).style.display = "none";
	};

	$rootScope.hideLoader = function(containerId) {
		setTimeout(function() {
			document.getElementById("container-loader").style.display = "none";
			document.getElementById(containerId).style.display = "block";
		}, 500)
	};
});

// configure our routes
admitereApp.config(function($routeProvider) {
	$routeProvider

	// route for the home page
	.when('/', {
		cache : false,
		templateUrl : 'pages/home.html',
		controller : 'homeController'
	})

	// route for the users page.
	.when('/users', {
		cache : false,
		templateUrl : 'pages/users.html',
		controller : 'usersController'
	})

	// route for the university page.
	.when('/universities', {
		cache : false,
		templateUrl : 'pages/universities.html',
		controller : 'universitiesController'
	})

	// route for the users page.
	.when('/profile', {
		cache : false,
		templateUrl : 'pages/profile.html',
		controller : 'profileController'
	})

	// route for the about page
	.when('/about', {
		cache : false,
		templateUrl : 'pages/about.html',
		controller : 'aboutController'
	})

	// route for the contact page
	.when('/contact', {
		cache : false,
		templateUrl : 'pages/contact.html',
		controller : 'contactController'
	})

	// route for the login page
	.when('/login', {
		cache : false,
		templateUrl : 'pages/login.html',
		controller : 'loginController'
	})

	// route for the register page.
	.when('/register', {
		cache : false,
		templateUrl : 'pages/register.html',
		controller : 'registerController'
	})

	.when('/recover', {
		cache : false,
		templateUrl : 'pages/recover.html',
		controller : 'recoverController'
	})

	// route for account page.
	.when('/identity_documents', {
		templateUrl : 'pages/identity_documents.html',
		controller : 'identityDocumentsController'
	})

	// route for the 404 not found.
	.when('/404', {
		templateUrl : 'pages/404.html',
		controller : 'notFoundController'
	})

	// redirect to 404.
	.otherwise({
		redirectTo : '/404'
	});
});

// Read CSRF value.
var csrfHeaderName = $("meta[name='_csrf_header']").attr("content");
var csrfHeaderValue = $("meta[name='_csrf']").attr("content");

admitereApp.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.defaults.headers.post = {
		'X-CSRF-TOKEN' : csrfHeaderValue
	};
} ]);

// Block modal on page. until cancel is press
angular.module('ui.bootstrap').config(function($provide) {
	$provide.decorator('$uibModal', function($delegate) {
		var open = $delegate.open;
		$delegate.open = function(options) {
			options = angular.extend(options || {}, {
				backdrop : 'static',
				keyboard : false
			});

			return open(options);
		};
		return $delegate;
	})
});

admitereApp.constant('config', {
	appName : 'Admitere application',
	appVersion : 1.0,
	url : 'http://localhost/',
	perPage: 25
});