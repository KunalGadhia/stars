angular.module("stars", [
    //    include libraries
    'ui.router',
    'ngResource',
    'angularFileUpload',
    'googlechart',
    'ngAnimate',
    'angular.filter',
    //  include filter
    'stars.filters',
    //  include constants
    'stars.constants',
    // include directives
    'stars.services.user',
    'stars.services.employee',
    'stars.services.kra_details',
    'stars.services.form2_details',
    'stars.services.additional_details',
    'stars.services.company',
    'stars.services.tag',

    // include controllers and states
    'stars.states',
    'stars.states.admin',
    'stars.states.user',
    'stars.states.kra',
    'stars.states.profile',
    'stars.states.evaluate',
    'stars.states.admin_menu',
    'stars.states.reports',
    'stars.states.employee',
    'stars.states.hr_menu',
    'stars.states.tag',
    'stars.states.auth'

])

        .run(['$state', '$rootScope', 'AuthFactory', '$location', 'UserService', function ($state, $rootScope, AuthFactory, $location, UserService) {
                AuthFactory.registerUserChangeHandler(function (currentUser) {
                    $rootScope.currentUser = currentUser;
                });

                AuthFactory.refresh().then(function (currentUser) {
                }, function (reason) {
                    $location.path("login");

                });

            }]);
