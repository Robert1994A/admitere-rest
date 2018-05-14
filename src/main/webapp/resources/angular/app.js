// create the module and name it admitereApp
var admitereApp = angular
		.module(
				'admitereApp',
				[ 'ngRoute', 'ui.router', 'chart.js', 'ngAnimate',
						'ngSanitize', 'ui.bootstrap', 'ngMessages',
						'ncy-angular-breadcrumb' ])
		.config(
				function($httpProvider, $breadcrumbProvider, $locationProvider) {
					$breadcrumbProvider.setOptions({
						prefixStateName : 'home',
						template : 'bootstrap3'
					});

					// $locationProvider.html5Mode({
					// enabled : true,
					// requireBase : false
					// });
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
		.run(function($rootScope, $templateCache, $uibModal, config) {
			$rootScope.$on('$viewContentLoaded', function() {
				// $templateCache.removeAll();
			});

			// Open error modal
			$rootScope.errorModal = function(message) {
				$uibModal.open({
					animation : true,
					templateUrl : 'modals/error-modal.html',
					controller : "errorModalController"
				});

				$rootScope.message = message;
			};

			// Open success modal
			$rootScope.successModal = function(message) {
				$uibModal.open({
					animation : true,
					templateUrl : 'modals/success-modal.html',
					controller : "successModalController"
				});

				$rootScope.message = message;
			};

			$rootScope.mainContainerId = "container-loader";

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
				pagination.number = data.number + 1;
				pagination.totalElements = data.totalElements;
				if (data.last == true) {
					pagination.totalPages = pagination.number;
					pagination.numberOfElements = data.size;
				} else {
					pagination.totalPages = data.totalPages;
					pagination.numberOfElements = data.numberOfElements;
				}
				pagination.last = data.last;
				pagination.size = data.size;
				pagination.sort = data.sort;
				pagination.first = data.first;

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
				try {
					if (document.getElementById(containerId) != null) {
						document.getElementById($rootScope.mainContainerId).style.display = "block";
						document.getElementById(containerId).style.display = "none";
					}
				} catch (err) {
					// Stay silent.
				}
			};

			$rootScope.hideLoader = function(containerId) {
				try {
					if (document.getElementById(containerId) != null) {
						setTimeout(
								function() {
									document
											.getElementById($rootScope.mainContainerId).style.display = "none";
									document.getElementById(containerId).style.display = "block";
								}, 500)
					}
				} catch (err) {
					// Stay silent.
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

				if (pageNumber != null && pageNumber != undefined) {
					url = url + "&pageNumber="
							+ window.encodeURIComponent(pageNumber - 1)
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
	}).state('users.add', {
		url : '/add',
		cache : false,
		views : {
			"@" : {
				templateUrl : 'pages/add_user.html',
				controller : 'addUserController'
			}
		},
		ncyBreadcrumb : {
			label : 'Add',
			parent : "users"
		}
	})

	// route for the universities page.
	.state('universities', {
		url : '/universities',
		cache : false,
		templateUrl : 'pages/universities.html',
		controller : 'universitiesController',
		ncyBreadcrumb : {
			label : 'Universities'
		}
	})

	// Get university page.
	.state('universities.detail', {
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
	})

	// Add new university
	.state('universities.add', {
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
	})

	// Get all faculties for university.
	.state('universities.detail.faculties', {
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
	})

	// Get faculty details.
	.state('universities.detail.faculties.detail', {
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
	})

	// Add new faculty.
	.state('universities.detail.faculties.add', {
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
	})

	// List domains for faculty.
	.state('universities.detail.faculties.detail.domains', {
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
	})

	// Add new domain for faculty.
	.state('universities.detail.faculties.detail.domains.add', {
		url : '/add',
		views : {
			"@" : {
				templateUrl : 'pages/add_domain.html',
				controller : 'facultyAddDomainController'
			}
		},
		ncyBreadcrumb : {
			label : 'Add',
			parent : "universities.detail.faculties.detail.domains"
		}
	})

	// List admission sessions
	.state('universities.detail.faculties.detail.sessions', {
		url : '/admission_sessions',
		views : {
			"@" : {
				templateUrl : 'pages/admission_sessions.html',
				controller : 'admissionSessionsController'
			}
		},
		ncyBreadcrumb : {
			label : 'Admission sessions',
			parent : "universities.detail.faculties.detail"
		}
	})

	// Add new admission session page.
	.state('universities.detail.faculties.detail.sessions.add', {
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

	// Get admission session page
	.state('universities.detail.faculties.detail.sessions.detail', {
		url : '/:admissionSessionId',
		views : {
			"@" : {
				templateUrl : 'pages/admission_session.html',
				controller : 'admissionSessionController'
			}
		},
		ncyBreadcrumb : {
			label : 'Admission Session',
			parent : "universities.detail.faculties.detail.sessions"
		}
	})

	// Get admission session statistics page
	.state('universities.detail.faculties.detail.sessions.detail.statistics', {
		url : '/statistics',
		views : {
			"@" : {
				templateUrl : 'pages/admission_session_statistics.html',
				controller : 'admissionSessionStatisticsController'
			}
		},
		ncyBreadcrumb : {
			label : 'Statistics',
			parent : "universities.detail.faculties.detail.sessions.detail"
		}
	})
	
	// Get admission session statistics page
	.state('universities.detail.faculties.detail.sessions.specialization_statistics', {
		url : '/admission_specialization/:admissionSpecializationId/statistics',
		views : {
			"@" : {
				templateUrl : 'pages/admission_specialization_statistics.html',
				controller : 'admissionSpecializationStatisticsController'
			}
		},
		ncyBreadcrumb : {
			label : 'Statistics',
			parent : "universities.detail.faculties.detail.sessions"
		}
	})

	// List all specializations
	.state('universities.detail.faculties.detail.specializations', {
		url : '/specializations',
		views : {
			"@" : {
				templateUrl : 'pages/specializations.html',
				controller : 'facultySpecializationsController'
			}
		},
		ncyBreadcrumb : {
			label : 'Specializations',
			parent : "universities.detail.faculties.detail"
		}
	})
	// Add specialization page.
	.state('universities.detail.faculties.detail.specializations.add', {
		url : '/add',
		views : {
			"@" : {
				templateUrl : 'pages/add_specialization.html',
				controller : 'facultyAddSpecializationController'
			}
		},
		ncyBreadcrumb : {
			label : 'Add',
			parent : "universities.detail.faculties.detail.specializations"
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

	// route for the applied sessions page page.
	.state('appliedSessions', {
		url : '/applied_sessions',
		cache : false,
		templateUrl : 'pages/applied_sessions.html',
		controller : 'appliedSessionsController',
		ncyBreadcrumb : {
			label : 'Applied sessions'
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

	// route for the account page
	.state('account', {
		url : '/account',
		cache : false,
		templateUrl : 'pages/account.html',
		controller : 'accountController',
		ncyBreadcrumb : {
			label : 'My account'
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

	$urlRouterProvider.otherwise('/');
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
	perPage : "10",
	sortBy : "id",
	sortDirection : "ASC",
	notifyDuration : 5000,
	notifyPosition : "center"
});