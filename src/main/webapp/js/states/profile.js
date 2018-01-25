/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states.profile", [])
        .config(function ($stateProvider, templateRoot) {
            $stateProvider.state('admin.profile', {
                'url': '/profile/list',
                'templateUrl': templateRoot + '/masters/profile/list.html',
                'controller': 'ProfileListController'
            });
            $stateProvider.state('admin.profile.password_change', {
                'url': '/:employeeId/profile/change_pass',
                'templateUrl': templateRoot + '/masters/profile/password_change.html',
                'controller': 'ProfileChangePasswordController'
            });
//            $stateProvider.state('admin.kra_employeelist.delete', {
//                'url': '/:kraDetailId/kra/delete',
//                'templateUrl': templateRoot + '/masters/kra/delete.html',
//                'controller': 'KraForm1DeleteController'
//            });

        })

        .controller('ProfileListController', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.user = $rootScope.currentUser;
            console.log("This is User  :%O", $scope.user);
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                $scope.userObject = userObject;
                EmployeeService.get({
                    'id': userObject.employeeId
                }, function (employeeObject) {
                    console.log("Employee Object %O", employeeObject);
                    $scope.employeeObject = employeeObject;
                });

//                $scope.kraDetailsList = KraDetailsService.findByEmployeeId({
//                    'employeeId': userObject.employeeId
//                });

            });
//            console.log("User Object :%O", $scope.user);
        })
        .controller('ProfileChangePasswordController', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            console.log("Profile Change Password Controller");
            $scope.passform1 = true;
            $scope.passform2 = false;
            $scope.showErrorMessage = false;
            $scope.userVal = $rootScope.currentUser;
            console.log("This is User  :%O", $scope.userVal);
            UserService.findByUsername({
                'username': $scope.userVal.username
            }, function (userObject) {
                $scope.userValidationObject = userObject;
            });
            $scope.validateCurrentPassword = function (currentPass) {
                console.log("User Validation Object :%O", $scope.userValidationObject);
                $scope.editableUser = UserService.get({
                    'id': $scope.userValidationObject.id
                });
                console.log("Current Pass :%O", currentPass);
                if (currentPass === $scope.userValidationObject.password) {
                    console.log("Password Matched");
                    $scope.showErrorMessage = false;
                    $scope.passform1 = false;
                    $scope.passform2 = true;
                } else {
                    console.log("Password Not Matched");
                    $scope.showErrorMessage = true;
                    $scope.passform1 = true;
                    $scope.passform2 = false;
                }
            };            

            $scope.updatePassword = function (editableUser) {
                console.log("Update User Object :%O", editableUser);
                editableUser.$save(function () {
                    $state.go('admin.profile', null, {'reload': true});
                });
            };

        });
//        .controller('KraForm1DeleteController', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {            
//            $scope.editableKRA = KraDetailsService.get({'id': $stateParams.kraDetailId});
//            $scope.deleteKraDetail = function (kraDetail) {
//                kraDetail.$delete(function () {
//                    $state.go('admin.kra_employeelist', null, {'reload': true});
//                });
//            };
//        });




