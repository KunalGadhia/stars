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
            $stateProvider.state('print_kra', {
                'url': '/:employeeId/profile/print_kra',
                'templateUrl': templateRoot + '/masters/profile/print_kra.html',
                'controller': 'ProfilePrintKra'
            });
            $stateProvider.state('admin.profile.photo_upload', {
                'url': '/:employeeId/profile/photo_upload',
                'templateUrl': templateRoot + '/masters/profile/upload_photo.html',
                'controller': 'ProfilePhotoUpload'
            });

        })

        .controller('ProfileListController', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.user = $rootScope.currentUser;
            console.log("This is User  :%O", $scope.user);
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                $scope.userObject = userObject;
                if (userObject.role === "ROLE_HR") {
                    $scope.showHRBack = true;
                    $scope.showAdminBack = false;
                    $scope.showEmployeeBack = false;
                    $scope.showHodBack = false;
                    $scope.hideProfile = true;
                } else if (userObject.role === "ROLE_ADMIN") {
                    $scope.showHRBack = false;
                    $scope.showAdminBack = true;
                    $scope.showEmployeeBack = false;
                    $scope.showHodBack = false;
                    $scope.hideProfile = true;
                } else if (userObject.role === "ROLE_EMPLOYEE") {
                    $scope.showHRBack = false;
                    $scope.showAdminBack = false;
                    $scope.showEmployeeBack = true;
                    $scope.showHodBack = false;
                    $scope.hideProfile = false;
                } else if (userObject.role === "ROLE_HOD") {
                    $scope.showHRBack = false;
                    $scope.showAdminBack = false;
                    $scope.showEmployeeBack = false;
                    $scope.showHodBack = true;
                    $scope.hideProfile = false;
                }
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

        })
        .controller('ProfilePrintKra', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.kraEmployeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            $scope.kraDetailsList = KraDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            });
        })
        .controller('ProfilePhotoUpload', function (FileUploader, restRoot, KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.enableSaveButton = true;
            EmployeeService.get({
                'id': $stateParams.employeeId
            }, function (employee) {
                $scope.employeeObject = employee;
                console.log("Employee Object :%O", $scope.employeeObject);
            });
            $scope.goBack = function () {
                $state.go('admin.profile', { }, {'reload': true});
            };
            var uploader = $scope.fileUploader = new FileUploader({
                url: restRoot + '/employee/' + $stateParams.employeeId + '/attachment',
                autoUpload: true,
                alias: 'attachment'
            });
            uploader.onBeforeUploadItem = function (item) {
                $scope.uploadInProgress = true;
                $scope.uploadSuccess = false;
                console.log("before upload item:", item);
                console.log("uploader", uploader);
            };
            uploader.onErrorItem = function (fileItem, response, status, headers) {
                $scope.uploadFailed = true;
                $scope.uploadInProgress = false;
                $scope.uploadSuccess = false;
//                    $state.go('.', {}, {'reload': true});
                console.log("upload error");
//                $scope.refreshRawMarketPrice();
            };
            uploader.onCompleteItem = function (fileItem, response, status, headers) {
                if (status === 200) {
                    console.log("Upload Successful");
                    $state.go('admin.profile.photo_upload', {
                        'colorId': $stateParams.employeeId
                    }, {'reload': true});
                    $scope.uploadInProgress = false;
                    $scope.uploadFailed = false;
                    $scope.uploadSuccess = true;
                    $scope.enableSaveButton = false;
                } else if (status === 500)
                {
                    $scope.uploadInProgress = false;
                    $scope.uploadFailed = false;
//                    $scope.uploadWarning = true;
                } else {
                    $scope.uploadInProgress = false;
                    $scope.uploadFailed = true;
                }

                console.log("upload completion", response);
            };
//            $scope.kraEmployeeObject = EmployeeService.get({
//               'id' : $stateParams.employeeId 
//            });
//            $scope.kraDetailsList = KraDetailsService.findByEmployeeId({
//               'employeeId': $stateParams.employeeId 
//            });
        });




