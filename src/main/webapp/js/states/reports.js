/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states.reports", [])
        .config(function ($stateProvider, templateRoot) {
            $stateProvider.state('admin.report1', {
                'url': '/admin_report1',
                'templateUrl': templateRoot + '/masters/reports/form1.html',
                'controller': 'AdminForm1Controller'
            });
            $stateProvider.state('admin.report2', {
                'url': '/admin_report2',
                'templateUrl': templateRoot + '/masters/reports/form2.html',
                'controller': 'AdminForm2Controller'
            });
            $stateProvider.state('admin.report3', {
                'url': '/admin_report3',
                'templateUrl': templateRoot + '/masters/reports/consolidated.html',
                'controller': 'AdminForm3Controller'
            });
            $stateProvider.state('admin.report4', {
                'url': '/admin_report4',
                'templateUrl': templateRoot + '/masters/reports/form4.html',
                'controller': 'AdminForm4Controller'
            });
            $stateProvider.state('admin.report5', {
                'url': '/admin_report5',
                'templateUrl': templateRoot + '/masters/reports/ranking_form.html',
                'controller': 'AdminForm5Controller'
            });
            $stateProvider.state('admin.report6', {
                'url': '/admin_report6',
                'templateUrl': templateRoot + '/masters/reports/form6.html',
                'controller': 'AdminForm6Controller'
            });
            $stateProvider.state('admin.report7', {
                'url': '/admin_report7',
                'templateUrl': templateRoot + '/masters/reports/form7.html',
                'controller': 'AdminForm7Controller'
            });
            $stateProvider.state('admin.report8', {
                'url': '/admin_report8',
                'templateUrl': templateRoot + '/masters/reports/form8.html',
                'controller': 'AdminForm8Controller'
            });
            $stateProvider.state('report1_details', {
                'url': '/:employeeId/report1_details',
                'templateUrl': templateRoot + '/masters/reports/form1_report.html',
                'controller': 'Report1Details'
            });
            $stateProvider.state('report2_details', {
                'url': '/:employeeId/report2_details',
                'templateUrl': templateRoot + '/masters/reports/form2_report.html',
                'controller': 'Report2Details'
            });
            $stateProvider.state('report3_details', {
                'url': '/:employeeId/report3_details',
                'templateUrl': templateRoot + '/masters/reports/form3_report.html',
                'controller': 'Report3Details'
            });
            $stateProvider.state('report4_details', {
                'url': '/:employeeId/report4_details',
                'templateUrl': templateRoot + '/masters/reports/form4_report.html',
                'controller': 'Report4Details'
            });
            $stateProvider.state('report5_details', {
                'url': '/:employeeId/report5_details',
                'templateUrl': templateRoot + '/masters/reports/ranking_report.html',
                'controller': 'Report5Details'
            });
            $stateProvider.state('report6_details', {
                'url': '/:employeeId/report6_details',
                'templateUrl': templateRoot + '/masters/reports/form6_report.html',
                'controller': 'Report6Details'
            });
            $stateProvider.state('report7_details', {
                'url': '/:employeeId/report7_details',
                'templateUrl': templateRoot + '/masters/reports/form7_report.html',
                'controller': 'Report7Details'
            });
            $stateProvider.state('report8_details', {
                'url': '/:employeeId/report8_details',
                'templateUrl': templateRoot + '/masters/reports/form8_report.html',
                'controller': 'Report8Details'
            });
//            $stateProvider.state('admin.department_resource_list.update_additional_details', {
//                'url': '/:employeeId/update_additional_details',
//                'templateUrl': templateRoot + '/masters/admin/update_additional_details.html',
//                'controller': 'UpdateAdditionalDetails'
//            });
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
        .controller('AdminForm1Controller', function ($state, KraDetailsService, EmployeeService, $scope, $rootScope, UserService) {
            $scope.searchEmployees = function (empString) {
                return EmployeeService.findByNameLike({
                    'name': empString
                }).$promise;
            };
            $scope.setEmployee = function (employee) {
                $scope.searchEmployeeId = employee.id;
            };

        })
        .controller('AdminForm2Controller', function ($scope, UserService, EmployeeService) {
            $scope.searchEmployees = function (empString) {
                return EmployeeService.findByNameLike({
                    'name': empString
                }).$promise;
            };
            $scope.setEmployee = function (employee) {
                $scope.searchEmployeeId = employee.id;
            };
        })
        .controller('AdminForm3Controller', function (AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.searchEmployees = function (empString) {
                return EmployeeService.findByNameLike({
                    'name': empString
                }).$promise;
            };
            $scope.setEmployee = function (employee) {
                $scope.searchEmployeeId = employee.id;
            };
        })
        .controller('AdminForm4Controller', function (AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.searchEmployees = function (empString) {
                return EmployeeService.findByNameLike({
                    'name': empString
                }).$promise;
            };
            $scope.setEmployee = function (employee) {
                $scope.searchEmployeeId = employee.id;
            };
        })
        .controller('AdminForm5Controller', function (AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.hodList = UserService.findHod(function (hodList) {
                angular.forEach($scope.hodList, function (hodObject) {
                    hodObject.employeeObject = EmployeeService.get({
                        'id': hodObject.employeeId
                    });
                });
            });
        })
        .controller('AdminForm6Controller', function (AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.hodList = UserService.findHod(function (hodList) {
                angular.forEach($scope.hodList, function (hodObject) {
                    hodObject.employeeObject = EmployeeService.get({
                        'id': hodObject.employeeId
                    });
                });
            });
        })
        .controller('AdminForm7Controller', function (AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.hodList = UserService.findHod(function (hodList) {
                angular.forEach($scope.hodList, function (hodObject) {
                    hodObject.employeeObject = EmployeeService.get({
                        'id': hodObject.employeeId
                    });
                });
            });
        })
        .controller('AdminForm8Controller', function (AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.hodList = UserService.findHod(function (hodList) {
                angular.forEach($scope.hodList, function (hodObject) {
                    hodObject.employeeObject = EmployeeService.get({
                        'id': hodObject.employeeId
                    });
                });
            });
        })
        .controller('Report1Details', function (KraDetailsService, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.kraEmployeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            $scope.kraDetailsList = KraDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            });
        })
        .controller('Report2Details', function (Form2DetailsService, KraDetailsService, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.employeeId = $stateParams.employeeId;
            $scope.editableForm2 = {};
            $scope.additionalDetails = {};
            $scope.additionalDetails = AdditionalDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            });

            $scope.form2DetailsList = Form2DetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }, function (form2DetailsList) {
                var b = 0;
                angular.forEach(form2DetailsList, function (form2Detail) {
                    b = b + form2Detail.ratingScore;
                    $scope.ratingForm2 = b;
                });
            });
            $scope.employeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
        })
        .controller('Report3Details', function (Form2DetailsService, KraDetailsService, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.employeeId = $stateParams.employeeId;
            $scope.editableForm2 = {};
            $scope.additionalDetails = {};
            $scope.additionalDetails = AdditionalDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            });
            $scope.form2DetailsList = Form2DetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }, function (form2DetailsList) {
                var b = 0;
                angular.forEach(form2DetailsList, function (form2Detail) {
                    b = b + form2Detail.ratingScore;
                    $scope.ratingForm2 = b;
                });
            });
            $scope.kraEmployeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            $scope.kraDetailsList = KraDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }, function (kraDetailsList) {
                var a = 0;
                angular.forEach(kraDetailsList, function (kraDetail) {

                    a = a + kraDetail.ratingScore;
                    $scope.ratingForm1 = a;
                });
            });
        })
        .controller('Report4Details', function (Form2DetailsService, KraDetailsService, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.employeeId = $stateParams.employeeId;
            $scope.editableForm2 = {};
            $scope.additionalDetails = {};
            $scope.additionalDetailPromise = AdditionalDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }, function (additionalDetailsObject) {
                $scope.additionalDetails = additionalDetailsObject;
                $scope.correctionFactor = additionalDetailsObject.correctionFactor;
                $scope.totalScore = parseInt($scope.ratingForm1 + $scope.ratingForm2 + $scope.correctionFactor);
            });
            $scope.form2DetailsPromise = Form2DetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }, function (form2DetailsList) {
                $scope.form2DetailsList = form2DetailsList;
                var b = 0;
                angular.forEach(form2DetailsList, function (form2Detail) {
                    b = b + form2Detail.ratingScore;
                    $scope.ratingForm2 = b;
                });
            });
            $scope.kraEmployeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            $scope.form1DetailsPromise = KraDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }, function (kraDetailsList) {
                $scope.kraDetailsList = kraDetailsList;
                var a = 0;
                angular.forEach(kraDetailsList, function (kraDetail) {

                    a = a + kraDetail.ratingScore;
                    $scope.ratingForm1 = a;
                });
            });

            $scope.form1DetailsPromise.$promise.then(function (form1Details) {
                $scope.form2DetailsPromise.$promise.then(function (form2Details) {
                    $scope.additionalDetailPromise.$promise.then(function (additionalDetails) {
                        console.log("Sc 1  :%O", $scope.ratingForm1);
                        console.log("Sc 2  :%O", $scope.ratingForm2);
                        console.log("Sc 3  :%O", $scope.correctionFactor);
                        $scope.totalScore = ($scope.ratingForm1 + $scope.ratingForm2 + $scope.correctionFactor);
                    });
                });
            });
            console.log("Total Score :%O", $scope.totalScore);
        })
        .controller('Report5Details', function (Form2DetailsService, KraDetailsService, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.departmentHeadObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            $scope.resourcesList = EmployeeService.findByDepartmentHead({
                'departmentHeadId': $stateParams.employeeId
            }, function (resourcesList) {
                $scope.mainResourceList = [];
                angular.forEach($scope.resourcesList, function (resourceData) {
                    $scope.form1DetailsPromise = KraDetailsService.findByEmployeeId({
                        'employeeId': resourceData.id
                    }, function (kraDetailsList) {
                        var a = 0;
                        angular.forEach(kraDetailsList, function (kraDetail) {

                            a = a + kraDetail.ratingScore;
                            resourceData.ratingForm1 = a;
                        });
                    });
                    $scope.form2DetailsPromise = Form2DetailsService.findByEmployeeId({
                        'employeeId': resourceData.id
                    }, function (form2DetailsList) {
                        var b = 0;
                        angular.forEach(form2DetailsList, function (form2Detail) {
                            b = b + form2Detail.ratingScore;
                            resourceData.ratingForm2 = b;
                        });
                    });
                    $scope.form1DetailsPromise.$promise.then(function (form1Details) {
                        $scope.form2DetailsPromise.$promise.then(function (form2Details) {
                            resourceData.totalScore = (resourceData.ratingForm1 + resourceData.ratingForm2);
                            $scope.mainResourceList.push(resourceData);
                        });
                    });
                });
            });
        })
        .controller('Report6Details', function (Form2DetailsService, KraDetailsService, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.departmentHeadObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            $scope.resourcesList = EmployeeService.findByDepartmentHead({
                'departmentHeadId': $stateParams.employeeId
            }, function (resourcesList) {
                $scope.mainResourceList = [];
                angular.forEach($scope.resourcesList, function (resourceData) {
                    $scope.form1DetailsPromise = KraDetailsService.findByEmployeeId({
                        'employeeId': resourceData.id
                    }, function (kraDetailsList) {
                        var a = 0;
                        angular.forEach(kraDetailsList, function (kraDetail) {

                            a = a + kraDetail.ratingScore;
                            resourceData.ratingForm1 = a;
                        });
                    });
                    $scope.form2DetailsPromise = Form2DetailsService.findByEmployeeId({
                        'employeeId': resourceData.id
                    }, function (form2DetailsList) {
                        var b = 0;
                        angular.forEach(form2DetailsList, function (form2Detail) {
                            b = b + form2Detail.ratingScore;
                            resourceData.ratingForm2 = b;
                        });
                    });
                    $scope.additionalDetailPromise = AdditionalDetailsService.findByEmployeeId({
                        'employeeId': resourceData.id
                    }, function (additionalDetailsObject) {
                        resourceData.correctionFactor = additionalDetailsObject.correctionFactor;
                    });
                    $scope.form1DetailsPromise.$promise.then(function (form1Details) {
                        $scope.form2DetailsPromise.$promise.then(function (form2Details) {
                            resourceData.totalScore = (resourceData.ratingForm1 + resourceData.ratingForm2 + resourceData.correctionFactor);
                            $scope.mainResourceList.push(resourceData);
                        });
                    });
                });
            });
        })
        .controller('Report7Details', function (Form2DetailsService, KraDetailsService, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.departmentHeadObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            $scope.resourcesList = EmployeeService.findByDepartmentHead({
                'departmentHeadId': $stateParams.employeeId
            }, function (resourcesList) {
//                $scope.mainResourceList = [];
                angular.forEach($scope.resourcesList, function (resourceData) {
                    $scope.form1DetailsPromise = KraDetailsService.findByEmployeeId({
                        'employeeId': resourceData.id
                    }, function (kraDetailsList) {
                        var a = 0;
                        angular.forEach(kraDetailsList, function (kraDetail) {

                            a = a + kraDetail.ratingScore;
                            resourceData.ratingForm1 = a;
                        });
                    });
                    $scope.form1DetailsPromise.$promise.then(function (form1Details) {
                        resourceData.totalScore = (resourceData.ratingForm1);
                    });
                });
            });
        })
        .controller('Report8Details', function (Form2DetailsService, KraDetailsService, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {
            $scope.departmentHeadObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            $scope.resourcesList = EmployeeService.findByDepartmentHead({
                'departmentHeadId': $stateParams.employeeId
            }, function (resourcesList) {
//                $scope.mainResourceList = [];
                angular.forEach($scope.resourcesList, function (resourceData) {

                    $scope.form2DetailsPromise = Form2DetailsService.findByEmployeeId({
                        'employeeId': resourceData.id
                    }, function (form2DetailsList) {
                        var b = 0;
                        angular.forEach(form2DetailsList, function (form2Detail) {
                            b = b + form2Detail.ratingScore;
                            resourceData.ratingForm2 = b;
                        });
                    });

                    $scope.form2DetailsPromise.$promise.then(function (form2Details) {
                        resourceData.totalScore = (resourceData.ratingForm2);
//                        $scope.mainResourceList.push(resourceData);
                    });
                });
            });
        });