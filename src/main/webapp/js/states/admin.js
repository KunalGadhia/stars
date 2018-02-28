/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states.admin", [])
        .config(function ($stateProvider, templateRoot) {
            $stateProvider.state('admin', {
                'url': '/admin',
                'templateUrl': templateRoot + '/admin.html',
                'controller': 'AdminController'
            });
            $stateProvider.state('admin.masters', {
                'url': '/masters',
                'templateUrl': templateRoot + '/masters/menu.html',
                'controller': 'AdminMasterMenu'
            });
            $stateProvider.state('admin.employees', {
                'url': '/employees',
                'templateUrl': templateRoot + '/masters/employee_menu.html',
                'controller': 'EmployeeMenu'
            });
            $stateProvider.state('admin.hod', {
                'url': '/hod',
                'templateUrl': templateRoot + '/masters/hod_menu.html',
                'controller': 'HodMenu'
            });
            $stateProvider.state('admin.hr', {
                'url': '/hr',
                'templateUrl': templateRoot + '/masters/hr_menu.html',
                'controller': 'HrMenu'
            });
            $stateProvider.state('admin.logout', {
                'url': '/logout',
                'templateUrl': templateRoot + '/logout.html',
                'controller': 'LogoutController'
            });
        })
        .controller('AdminController', function ($scope, $rootScope, UserService) {
//            NotificationService.findAllList(function (notificationList) {
//                $scope.notificationList = notificationList;
//            });
            $scope.user = $rootScope.currentUser;
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                $scope.notificationUserObject = userObject;
            });
        })
        .controller('AdminMasterMenu', function ($rootScope, $scope, UserService) {
//            $scope.user = $rootScope.currentUser;
//            UserService.findByUsername({
//                'username': $scope.user.username
//            }, function (userObject) {
//                
//            });
        })
        .controller('EmployeeMenu', function ($scope, UserService) {
        })
        .controller('HodMenu', function ($scope, UserService) {
        })
        .controller('HrMenu', function ($scope, UserService) {
        })
        .controller('LogoutController', function (UserService, $scope, $state) {
            console.log("Coming to logout Controller??");
            $scope.logout = function () {
                UserService.logout({}, function () {
                    $state.go("login", {
                        'message': 'Logged Out Successfully!'
                    });
                });
            };
        });


