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
        });
//        .controller('UpdateAdditionalDetails', function ($state, AdditionalDetailsService, $scope, $stateParams, UserService, EmployeeService) {            
//            
//        })
//        .controller('AdminDepartmentHeadMenu', function ($scope, UserService, EmployeeService) {
//           
//        });
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


