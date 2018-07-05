/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states.admin_menu", [])
        .config(function ($stateProvider, templateRoot) {
            $stateProvider.state('admin.master_menu', {
                'url': '/admin_master_menu',
                'templateUrl': templateRoot + '/masters/admin/masters.html',
                'controller': 'AdminMasterController'
            });
            $stateProvider.state('admin.report_menu', {
                'url': '/admin_report_menu',
                'templateUrl': templateRoot + '/masters/admin/report.html',
                'controller': 'AdminReportMenu'
            });
            $stateProvider.state('admin.department_head_list', {
                'url': '/admin_department_head_list',
                'templateUrl': templateRoot + '/masters/admin/department_head_list.html',
                'controller': 'AdminDepartmentHeadMenu'
            });
            $stateProvider.state('admin.department_resource_list', {
                'url': '/:employeeId/admin_department_resource_list',
                'templateUrl': templateRoot + '/masters/admin/department_resource_list.html',
                'controller': 'AdminDepartmentResourceMenu'
            });
            $stateProvider.state('admin.director_resource_list', {
                'url': '/:employeeId/admin_director_resource_list',
                'templateUrl': templateRoot + '/masters/admin/director_resource_list.html',
                'controller': 'AdminDirectorResourceMenu'
            });
            $stateProvider.state('admin.department_resource_list.update_additional_details', {
                'url': '/:employeeId/update_additional_details',
                'templateUrl': templateRoot + '/masters/admin/update_additional_details.html',
                'controller': 'UpdateAdditionalDetails'
            });
//            $stateProvider.state('admin.hod', {
//                'url': '/hod',
//                'templateUrl': templateRoot + '/masters/hod_menu.html',
//                'controller': 'HodMenu'
//            });
//            $stateProvider.state('admin.logout', {
//                'url': '/logout',
//                'templateUrl': templateRoot + '/logout.html',
//                'controller': 'LogoutController'
//            });
        })
        .controller('AdminMasterController', function ($scope, $rootScope, UserService) {

        })
        .controller('AdminReportMenu', function ($scope, $rootScope, UserService) {
            $scope.user = $rootScope.currentUser;
            console.log("user Object :%O", $scope.user);
            UserService.findByUsername({
               'username':$scope.user.username 
            }, function(userObject){
                if(userObject.role === "ROLE_ADMIN"){
                    $scope.showDirBack = false;
                    $scope.showAdminBack = true;
                }else if(userObject.role === "ROLE_DIRECTOR"){
                    $scope.showDirBack = true;
                    $scope.showAdminBack = false;
                }
            });
        })
        .controller('AdminDepartmentResourceMenu', function (KraDetailsService, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.resourcesList = EmployeeService.findByDepartmentHead({
                'departmentHeadId': $stateParams.employeeId
            }, function (resourcesList) {
                angular.forEach($scope.resourcesList, function (resourceData) {

                    KraDetailsService.findByEmployeeId({
                        'employeeId': resourceData.id
                    }, function (employeeKraList) {
                        if (employeeKraList.length === 0) {
                            resourceData.complete = false;
                            resourceData.inProgress = false;
                            resourceData.notStarted = true;
                        }
                        var a = 0;
                        angular.forEach(employeeKraList, function (kra) {
                            a = a + kra.weightage;
                            resourceData.totalWeightage = a;
                            if (a === 100) {
                                resourceData.complete = true;
                                resourceData.inProgress = false;
                                resourceData.notStarted = false;
                            }
                            if (a > 0 && a < 100) {
                                resourceData.complete = false;
                                resourceData.inProgress = true;
                                resourceData.notStarted = false;
                            }

                        });
                    });

                    AdditionalDetailsService.findByEmployeeId({
                        'employeeId': resourceData.id
                    }).$promise.catch(function (response) {
                        if (response.status === 500) {
                            resourceData.showUpdateButton = false;
                        } else if (response.status === 404) {
                            resourceData.showUpdateButton = false;
                        } else if (response.status === 400) {
                            resourceData.showUpdateButton = false;
                        } else if (response.status === 200) {
                            resourceData.showUpdateButton = true;
                        }
                    }).then(function (additionalDetailsObject) {
                        if (additionalDetailsObject !== undefined) {
                            resourceData.showUpdateButton = true;
                        } else {
                            resourceData.showUpdateButton = false;
                        }
                    });
                });
            });
        })
        .controller('AdminDirectorResourceMenu', function (HodReviewDetailsService, KraDetailsService, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.showSaveButton = true;
            $scope.resourcesList = EmployeeService.findByDepartmentHead({
                'departmentHeadId': $stateParams.employeeId
            }, function (resourcesList) {
                angular.forEach($scope.resourcesList, function (resourceData) {
                    HodReviewDetailsService.findByEmployeeId({
                        'employeeId': resourceData.id
                    }).$promise.catch(function (response) {
                        if (response.status === 500) {
                            resourceData.showSaveButton = true;
                        } else if (response.status === 404) {
                            resourceData.showSaveButton = true;
                        } else if (response.status === 400) {
                            resourceData.showSaveButton = true;
                        } else if (response.status === 200) {
                            resourceData.showSaveButton = false;
                        }
                    }).then(function (hodReviewDetailsObject) {
                        if (hodReviewDetailsObject !== undefined) {
                            resourceData.showSaveButton = false;
                        } else {
                            resourceData.showSaveButton = true;
                        }
                    });
                });
            });
            console.log("Resource List :%O", $scope.resourcesList);
        })
        .controller('UpdateAdditionalDetails', function ($state, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.additionalDetails = AdditionalDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }, function (additionalDetails) {
//                $scope.additionalDetails.correctionFactor = parsadditionalDetails.correctionFactor
            });
            $scope.employeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            $scope.updateAdditionalDetail = function (additionalDetails) {
                console.log("Additional Details :%O", additionalDetails);
                console.log("Department Head :%O", $scope.employeeObject);
                additionalDetails.$save(function () {
                    $state.go('admin.department_resource_list', {'employeeId': $scope.employeeObject.departmentHead}, {'reload': true});
                });
            };
        })
        .controller('AdminDepartmentHeadMenu', function ($rootScope, $scope, UserService, EmployeeService) {
            $scope.user = $rootScope.currentUser;
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                $scope.hodList = UserService.findHodByCompanyId({
                    'companyId': userObject.companyId
                }, function (hodList) {
                    angular.forEach($scope.hodList, function (hodObject) {
                        hodObject.employeeObject = EmployeeService.get({
                            'id': hodObject.employeeId
                        });
                    });
                });
            });
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                $scope.directorList = UserService.findDirectorByCompanyId({
                    'companyId': userObject.companyId
                }, function (directorList) {
                    angular.forEach($scope.directorList, function (hodObject) {
                        hodObject.employeeObject = EmployeeService.get({
                            'id': hodObject.employeeId
                        });
                    });
                });
            });
//
//            $scope.hodList = UserService.findHod(function (hodList) {
//                angular.forEach($scope.hodList, function (hodObject) {
//                    hodObject.employeeObject = EmployeeService.get({
//                        'id': hodObject.employeeId
//                    });
//                });
//            });
            console.log("HOD List :%O", $scope.hodList);
        });
//        .controller('HodMenu', function ($scope, UserService) {
//        })
//        .controller('LogoutController', function (UserService, $scope, $state) {
//            console.log("Coming to logout Controller??");
//            $scope.logout = function () {
//                UserService.logout({}, function () {
//                    $state.go("login", {
//                        'message': 'Logged Out Successfully!'
//                    });
//                });
//            };
//        });


