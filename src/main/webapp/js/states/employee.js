/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states.employee", [])
        .config(function ($stateProvider, templateRoot) {
            $stateProvider.state('admin.masters_employee', {
                'url': '/employee_master?offset',
                'templateUrl': templateRoot + '/masters/employee/list.html',
                'controller': 'EmployeeListController'
            });
            $stateProvider.state('admin.masters_employee.add', {
                'url': '/add',
                'templateUrl': templateRoot + '/masters/employee/form.html',
                'controller': 'EmployeeAddController'
            });
            $stateProvider.state('admin.masters_employee.edit', {
                'url': '/:employeeId/edit',
                'templateUrl': templateRoot + '/masters/employee/form.html',
                'controller': 'EmployeeEditController'
            });
            $stateProvider.state('admin.masters_employee.delete', {
                'url': '/:employeeId/delete',
                'templateUrl': templateRoot + '/masters/employee/delete.html',
                'controller': 'EmployeeDeleteController'
            });
        })
        .controller('EmployeeListController', function (EmployeeService, UserService, $scope, $stateParams, $state, paginationLimit) {
            if (
                    $stateParams.offset === undefined ||
                    isNaN($stateParams.offset) ||
                    new Number($stateParams.offset) < 0)
            {
                $scope.currentOffset = 0;
            } else {
                $scope.currentOffset = new Number($stateParams.offset);
            }

            $scope.nextOffset = $scope.currentOffset + 10;
            $scope.nextEmployees = EmployeeService.query({
                'offset': $scope.nextOffset
            });
            $scope.employeeList = EmployeeService.query({
                'offset': $scope.currentOffset
            }, function (employeeList) {
                angular.forEach($scope.employeeList, function (employee) {
                    employee.reportingToObject = EmployeeService.get({
                        'id': employee.reportingTo
                    });
                    employee.departmentHeadObject = EmployeeService.get({
                        'id': employee.departmentHead
                    });
                });
//                $scope.colorConstraints = s;
            });
            $scope.nextPage = function () {
                $scope.currentOffset += paginationLimit;
                $state.go(".", {'offset': $scope.currentOffset}, {'reload': true});
            };
            $scope.previousPage = function () {
                if ($scope.currentOffset <= 0) {
                    return;
                }
                $scope.currentOffset -= paginationLimit;
                $state.go(".", {'offset': $scope.currentOffset}, {'reload': true});
            };
        })
        .controller('EmployeeAddController', function (EmployeeService, $scope, $stateParams, $state, paginationLimit) {

            $scope.editableEmployee = {};
            $scope.$watch('editableEmployee.empNo', function (empNo) {
                console.log("Emp No :%O", empNo);
                EmployeeService.findByEmpNumLike({'empNo': empNo}).$promise.catch(function (response) {
                    if (response.status === 500) {
                        $scope.editableEmployee.repeatEmployeeNumber = false;
                    } else if (response.status === 404) {
                        $scope.editableEmployee.repeatEmployeeNumber = false;
                    } else if (response.status === 400) {
                        $scope.editableEmployee.repeatEmployeeNumber = false;
                    }
                }).then(function (employeeObject) {
                    if (employeeObject.length !== 0) {
                        $scope.editableEmployee.repeatEmployeeNumber = true;
                    } else {
                        $scope.editableEmployee.repeatEmployeeNumber = false;
                    }
                    ;
                });
            });
            $scope.searchReportingEmployees = function (empString) {
                return EmployeeService.findByNameLike({
                    'name': empString
                }).$promise;
            };
            $scope.setReportingEmployee = function (employee) {
                $scope.editableEmployee.reportingTo = employee.id;
            };
            $scope.searchHeadEmployees = function (empString) {
                return EmployeeService.findByNameLike({
                    'name': empString
                }).$promise;
            };
            $scope.setHeadEmployee = function (employee) {
                $scope.editableEmployee.departmentHead = employee.id;
            };
            $scope.saveEmployee = function (employee) {
                console.log("Employee Save Object :%O", employee);
                EmployeeService.save(employee, function (savedData) {
                    console.log("Saved Data :%O", savedData);
                    $state.go('admin.masters_employee', null, {'reload': true});
                });
            };
        })
        .controller('EmployeeEditController', function ($filter, UserService, EmployeeService, $scope, $stateParams, $state, paginationLimit) {
            console.log("State Params :%O", $stateParams);
            EmployeeService.get({
                'id': $stateParams.employeeId
            }, function (employeeObject) {
                console.log("WHat is Employee Object :%O", employeeObject);
                employeeObject.empDoj = new Date(employeeObject.empDoj);
                employeeObject.empDob = new Date(employeeObject.empDob);
                employeeObject.totalExp = parseInt(employeeObject.totalExp);
                $scope.reportingEmployeeObject = EmployeeService.get({
                    'id': employeeObject.reportingTo
                });
                $scope.headEmployeeObject = EmployeeService.get({
                    'id': employeeObject.departmentHead
                });
                $scope.editableEmployee = employeeObject;
            });
            $scope.searchReportingEmployees = function (empString) {
                return EmployeeService.findByNameLike({
                    'name': empString
                }).$promise;
            };
            $scope.setReportingEmployee = function (employee) {
                $scope.editableEmployee.reportingTo = employee.id;
            };
            $scope.searchHeadEmployees = function (empString) {
                return EmployeeService.findByNameLike({
                    'name': empString
                }).$promise;
            };
            $scope.setHeadEmployee = function (employee) {
                $scope.editableEmployee.departmentHead = employee.id;
            };
            $scope.saveEmployee = function (employee) {
                employee.$save(function () {
                    $state.go('admin.masters_employee', null, {'reload': true});
                });
            };
        })
        .controller('EmployeeDeleteController', function (EmployeeService, $scope, $stateParams, $state, paginationLimit) {
            $scope.editableEmployee = EmployeeService.get({'id': $stateParams.employeeId});
            $scope.deleteEmployee = function (employee) {
                employee.$delete(function () {
                    $state.go('admin.masters_employee', null, {'reload': true});
                });
            };
        });




