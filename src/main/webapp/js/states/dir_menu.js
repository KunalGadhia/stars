/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states.dir_menu", [])
        .config(function ($stateProvider, templateRoot) {
            $stateProvider.state('admin.director_emp_list', {
                'url': '/admin_department_emp_list',
                'templateUrl': templateRoot + '/masters/dir/director_employee_list.html',
                'controller': 'DirectorEmployeeMenu'
            });
        })
        .controller('DirectorEmployeeMenu', function (HodReviewDetailsService, $rootScope, $scope, $stateParams, UserService, EmployeeService) {
            $scope.user = $rootScope.currentUser;
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                console.log("User Object :%O", userObject);
                $scope.resourcesList = EmployeeService.findByDepartmentHead({
                    'departmentHeadId': userObject.employeeId
                }, function (rsList) {
                    console.log("RS List :%O", rsList);
                    angular.forEach($scope.resourcesList, function (rsObject) {
                        HodReviewDetailsService.findByEmployeeId({
                            'employeeId': rsObject.id
                        }).$promise.catch(function (response) {
                            if (response.status === 500) {
                                rsObject.reviewed = false;
                            } else if (response.status === 404) {
                                rsObject.reviewed = false;
                            } else if (response.status === 400) {
                                rsObject.reviewed = false;
                            } else if (response.status === 200) {
                                rsObject.reviewed = true;
                            }
                        }).then(function (hodReviewObject) {
                            if (hodReviewObject !== undefined) {
                                rsObject.reviewed = true;
                            } else {
                                rsObject.reviewed = false;
                            }
                        });
                        console.log("RS Object :%O", rsObject);
                    });
                });
            });
        });        