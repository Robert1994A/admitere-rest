// create the module and name it admitereApp
var admitereApp = angular.module(
		'admitereApp',
		[ 'ngRoute', 'ui.router', 'ngAnimate', 'ngSanitize', 'ui.bootstrap',
				'ngMessages', 'ncy-angular-breadcrumb' ]).config(
		function($httpProvider, $breadcrumbProvider) {
			$breadcrumbProvider.setOptions({
				prefixStateName : 'home',
				template : 'bootstrap3'
			});
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

admitereApp
		.run(function($rootScope, $templateCache, config) {
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

				comunicationFactory.makeRequest(
						"/security/authenticationDetails", "GET", null,
						successCallback, errorCallback, null);
			};

			$rootScope.showLoader = function(containerId) {
				if (angular.element('#' + containerId).length > 0) {
					document.getElementById("container-loader").style.display = "block";
					document.getElementById(containerId).style.display = "none";
				}
			};

			$rootScope.hideLoader = function(containerId) {
				if (angular.element('#' + containerId).length > 0) {
					setTimeout(
							function() {
								document.getElementById("container-loader").style.display = "none";
								document.getElementById(containerId).style.display = "block";
							}, 700)
				}
			};

			$rootScope.generateSearchRequest = function(pageNumber, pageSize,
					searchText, sortBy, sortDirection) {
				var url = "?";
				if (searchText != null && searchText != undefined) {
					url = url + "&search="
							+ window.encodeURIComponent(searchText);
				} else {
					url = url + "&search=";

				}

				if (sortBy != null && sortBy != undefined) {
					url = url + "&sortBy=" + window.encodeURIComponent(sortBy);
				} else {
					url = url + "&sortBy=";
				}

				if (sortDirection != null && sortDirection != undefined) {
					url = url + "&direction="
							+ window.encodeURIComponent(sortDirection);
				} else {
					url = url + "&direction=";
				}

				if (pageNumber != null && pageSize != undefined) {
					url = url + "&pageNumber="
							+ window.encodeURIComponent(pageNumber)
				} else {
					url = url + "&pageNumber=0";
				}

				if (pageSize != null && pageSize != undefined) {
					url = url + "&pageSize="
							+ window.encodeURIComponent(pageSize)
				} else {
					url = url + "&pageSize=" + config.perPage;
				}

				return url;
			}
		});

// configure our routes
admitereApp.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider.state('home', {
		url : '/',
		cache : false,
		templateUrl : 'pages/home.html',
		controller : 'homeController',
		ncyBreadcrumb : {
			label : 'Home'
		}
	})

	// route for the users page.
	.state('users', {
		url : '/users',
		cache : false,
		templateUrl : 'pages/users.html',
		controller : 'usersController',
		ncyBreadcrumb : {
			label : 'Users'
		}
	})

	// route for the university page.
	.state('universities', {
		url : '/universities',
		cache : false,
		templateUrl : 'pages/universities.html',
		controller : 'universitiesController',
		ncyBreadcrumb : {
			label : 'Universities'
		}
	}).state('universities.detail', {
		url : '/:universityId',
		cache : false,
		resolve : {
			universityId : [ '$stateParams', function($stateParams) {
				return $stateParams.universityId;
			} ]
		},
		views : {
			"@" : {
				templateUrl : 'pages/university.html',
				controller : "universityController"
			}
		},
		ncyBreadcrumb : {
			label : 'University',
			parent : "universities"
		}
	}).state('universities.add', {
		url : '/add',
		cache : false,
		views : {
			"@" : {
				templateUrl : 'pages/add_university.html',
				controller : "addUniversityController"
			}
		},
		ncyBreadcrumb : {
			label : 'Add',
			parent : "universities"
		}
	}).state('universities.detail.faculties', {
		url : '/faculties',
		views : {
			"@" : {
				templateUrl : 'pages/faculties.html',
				controller : 'universityFacultiesController'
			}
		},
		ncyBreadcrumb : {
			label : 'Faculties',
			parent : "universities.detail"
		}
	}).state('universities.detail.faculties.add', {
		url : '/add',
		views : {
			"@" : {
				templateUrl : 'pages/add_faculty.html',
				controller : 'addFacultyController'
			}
		},
		ncyBreadcrumb : {
			label : 'Add',
			parent : "universities.detail.faculties"
		}
	}).state('universities.detail.faculties.detail', {
		url : '/:facultyId',
		views : {
			"@" : {
				templateUrl : 'pages/faculty.html',
				controller : 'facultyController'
			}
		},
		ncyBreadcrumb : {
			label : 'Faculty',
			parent : "universities.detail.faculties"
		}
	}).state('universities.detail.faculties.detail.domains', {
		url : '/domains',
		views : {
			"@" : {
				templateUrl : 'pages/domains.html',
				controller : 'facultyDomainsController'
			}
		},
		ncyBreadcrumb : {
			label : 'Domains',
			parent : "universities.detail.faculties.detail"
		}
	}).state('universities.detail.faculties.detail.sessions', {
		url : '/sessions',
		views : {
			"@" : {
				templateUrl : 'pages/sessions.html',
				controller : 'facultySessionsController'
			}
		},
		ncyBreadcrumb : {
			label : 'Sessions',
			parent : "universities.detail.faculties.detail"
		}
	}).state('universities.detail.faculties.detail.sessions.add', {
		url : '/add',
		views : {
			"@" : {
				templateUrl : 'pages/add_session.html',
				controller : 'facultyAddSessionController'
			}
		},
		ncyBreadcrumb : {
			label : 'Add',
			parent : "universities.detail.faculties.detail.sessions"
		}
	})

	// route for the users page.
	.state('profile', {
		url : '/profile',
		cache : false,
		templateUrl : 'pages/profile.html',
		controller : 'profileController',
		ncyBreadcrumb : {
			label : 'Profile'
		}
	})

	// route for the about page
	.state('about', {
		url : '/about',
		cache : false,
		templateUrl : 'pages/about.html',
		controller : 'aboutController',
		ncyBreadcrumb : {
			label : 'About'
		}
	})

	// route for the contact page
	.state('contact', {
		url : '/contact',
		cache : false,
		templateUrl : 'pages/contact.html',
		controller : 'contactController',
		ncyBreadcrumb : {
			label : 'Contact'
		}
	})

	// route for the login page
	.state('login', {
		url : '/login',
		cache : false,
		templateUrl : 'pages/login.html',
		controller : 'loginController',
		ncyBreadcrumb : {
			label : 'Login'
		}
	})

	// route for the register page.
	.state('register', {
		url : '/register',
		cache : false,
		templateUrl : 'pages/register.html',
		controller : 'registerController',
		ncyBreadcrumb : {
			label : 'Register'
		}
	})

	.state('recover', {
		url : '/recover',
		cache : false,
		templateUrl : 'pages/recover.html',
		controller : 'recoverController',
		ncyBreadcrumb : {
			label : 'Recover'
		}
	})

	// route for the 404 not found.
	.state('404', {
		url : '/404',
		templateUrl : 'pages/404.html',
		controller : 'notFoundController',
		ncyBreadcrumb : {
			label : '404 Not Found'
		}
	})

	$urlRouterProvider.otherwise('/404');
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
	perPage : "25",
	sortBy : "id",
	sortDirection : "ASC"
});