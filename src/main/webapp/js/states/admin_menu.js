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
        .controller('AdminReportMenu', function ($scope, UserService) {
        })
        .controller('AdminDepartmentResourceMenu', function ($scope, $stateParams, UserService,EmployeeService) {
            $scope.resourcesList = EmployeeService.findByDepartmentHead({
                'departmentHeadId': $stateParams.employeeId
            });
        })
        .controller('AdminDepartmentHeadMenu', function ($scope, UserService, EmployeeService) {
            $scope.hodList = UserService.findHod(function (hodList) {
                angular.forEach($scope.hodList, function (hodObject) {
                    hodObject.employeeObject = EmployeeService.get({
                        'id': hodObject.employeeId
                    });
                });
            });
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


