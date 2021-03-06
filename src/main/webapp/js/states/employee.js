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
            $stateProvider.state('admin.masters_employee.upload_photo', {
                'url': '/:employeeId/photo_upload',
                'templateUrl': templateRoot + '/masters/employee/upload_photo.html',
                'controller': 'EmployeePhotoUploadController'
            });
            $stateProvider.state('admin.masters_employee.update_details', {
                'url': '/:employeeId/update_details',
                'templateUrl': templateRoot + '/masters/employee/employee_details.html',
                'controller': 'EmployeeDetailsUpdateController'
            });
            $stateProvider.state('admin.masters_employee.delete', {
                'url': '/:employeeId/delete',
                'templateUrl': templateRoot + '/masters/employee/delete.html',
                'controller': 'EmployeeDeleteController'
            });
        })
        .controller('EmployeeListController', function ($rootScope, CompanyService, EmployeeService, UserService, $scope, $stateParams, $state, paginationLimit) {
            $scope.user = $rootScope.currentUser;
            $scope.userObject = UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                if (userObject.role === "ROLE_HR") {
                    $scope.showHRBack = true;
                    $scope.showAdminBack = false;
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

                    $scope.nextEmployees = EmployeeService.findEmployeeByCompany({
                        'companyId': userObject.companyId,
                        'offset': $scope.nextOffset
                    });

                    $scope.employeeList = EmployeeService.findEmployeeByCompany({
                        'companyId': userObject.companyId,
                        'offset': $scope.currentOffset
                    }, function (s) {
                        angular.forEach($scope.employeeList, function (employee) {
                            employee.reportingToObject = EmployeeService.get({
                                'id': employee.reportingTo
                            });
                            employee.departmentHeadObject = EmployeeService.get({
                                'id': employee.departmentHead
                            });
                        });
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
                } else if (userObject.role === "ROLE_ADMIN") {
                    $scope.showHRBack = false;
                    $scope.showAdminBack = true;
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

                    $scope.nextEmployees = EmployeeService.findEmployeeByCompany({
                        'companyId': userObject.companyId,
                        'offset': $scope.nextOffset
                    });

                    $scope.employeeList = EmployeeService.findEmployeeByCompany({
                        'companyId': userObject.companyId,
                        'offset': $scope.currentOffset
                    }, function (s) {
                        angular.forEach($scope.employeeList, function (employee) {
                            employee.reportingToObject = EmployeeService.get({
                                'id': employee.reportingTo
                            });
                            employee.departmentHeadObject = EmployeeService.get({
                                'id': employee.departmentHead
                            });
                        });
                    });
                    UserService.findHodByCompanyId({
                        'companyId': userObject.companyId
                    }, function (hodList) {
                        $scope.hodList = hodList;
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
                }
            });

            $scope.searchEmployees = function (empString) {
                console.log("User Object :%O", $scope.userObject);
                return EmployeeService.findByNameLikeByCompany({
                    'companyId': $scope.userObject.companyId,
                    'name': empString
                }).$promise;
            };
            $scope.setEmployee = function (employee) {
                $scope.searchEmployeeId = employee.id;
            };
            $scope.searchEmp = function () {

                console.log("Employee Id :%O", $scope.searchEmployeeId);
                $scope.employeeList = [];
                EmployeeService.get({
                    'id': $scope.searchEmployeeId
                }, function (employeeObject) {
                    employeeObject.reportingToObject = EmployeeService.get({
                        'id': employeeObject.reportingTo
                    });
                    employeeObject.departmentHeadObject = EmployeeService.get({
                        'id': employeeObject.departmentHead
                    });
                    $scope.employeeList.push(employeeObject);
                });
            };
            $scope.clearSearch = function () {
                $state.go('admin.masters_employee', null, {'reload': true});
            };
            $scope.searchEmpByHod = function (employeeId) {
                console.log("Employee Id :%O", employeeId);
                $scope.employeeList = [];
                $scope.employeeList = EmployeeService.findByDepartmentHead({
                    'departmentHeadId': employeeId
                }, function (employeeObject) {

                    angular.forEach($scope.employeeList, function (employee) {
                        employee.reportingToObject = EmployeeService.get({
                            'id': employee.reportingTo
                        });
                        employee.departmentHeadObject = EmployeeService.get({
                            'id': employee.departmentHead
                        });
                    });
                });
            };
        })
        .controller('EmployeeAddController', function ($rootScope, UserService, CompanyService, EmployeeService, $scope, $stateParams, $state, paginationLimit) {

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
            $scope.user = $rootScope.currentUser;
            $scope.userObject = UserService.findByUsername({
                'username': $scope.user.username
            });
            $scope.companyList = CompanyService.findALlList();
            $scope.searchReportingEmployees = function (empString) {
                return EmployeeService.findByNameLikeByCompany({
                    'companyId': $scope.userObject.companyId,
                    'name': empString
                }).$promise;
            };
            $scope.setReportingEmployee = function (employee) {
                $scope.editableEmployee.reportingTo = employee.id;
            };
            $scope.searchHeadEmployees = function (empString) {
                return EmployeeService.findByNameLikeByCompany({
                    'companyId': $scope.userObject.companyId,
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
        .controller('EmployeeEditController', function ($rootScope, CompanyService, $filter, UserService, EmployeeService, $scope, $stateParams, $state, paginationLimit) {
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
            $scope.user = $rootScope.currentUser;
            $scope.userObject = UserService.findByUsername({
                'username': $scope.user.username
            });
            $scope.companyList = CompanyService.findALlList();
            $scope.searchReportingEmployees = function (empString) {
                return EmployeeService.findByNameLikeByCompany({
                    'companyId': $scope.userObject.companyId,
                    'name': empString
                }).$promise;
            };
            $scope.setReportingEmployee = function (employee) {
                $scope.editableEmployee.reportingTo = employee.id;
            };
            $scope.searchHeadEmployees = function (empString) {
                return EmployeeService.findByNameLikeByCompany({
                    'companyId': $scope.userObject.companyId,
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
        })
        .controller('EmployeePhotoUploadController', function (FileUploader, restRoot, KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.enableSaveButton = true;
            EmployeeService.get({
                'id': $stateParams.employeeId
            }, function (employee) {
                $scope.employeeObject = employee;
                console.log("Employee Object :%O", $scope.employeeObject);
            });
            $scope.goBack = function () {
                $state.go('admin.masters_employee', {}, {'reload': true});
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
                    $state.go('admin.masters_employee.upload_photo', {
                        'employeeId': $stateParams.employeeId
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
        })
        .controller('EmployeeDetailsUpdateController', function (EmployeeService, $scope, $stateParams, $state, $rootScope, UserService) {
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
            $scope.user = $rootScope.currentUser;
            $scope.userObject = UserService.findByUsername({
                'username': $scope.user.username
            });

            $scope.$watch('editableEmployee.permanentSimilar', function (similar) {
                console.log("Similar :" + similar);
                if(similar === true){
                    $scope.editableEmployee.permanentAddress1 = $scope.editableEmployee.temporaryAddress1;
                    $scope.editableEmployee.permanentAddress2 = $scope.editableEmployee.temporaryAddress2;
                    $scope.editableEmployee.permanentAddress3 = $scope.editableEmployee.temporaryAddress3;
                    $scope.editableEmployee.permanentAddress4 = $scope.editableEmployee.temporaryAddress4;
                }else if(similar === false){
                    $scope.editableEmployee.temporaryAddress1 = "";
                    $scope.editableEmployee.temporaryAddress2 = "";
                    $scope.editableEmployee.temporaryAddress3 = "";
                    $scope.editableEmployee.temporaryAddress4 = "";
                }
            });

            $scope.updateEmployee = function (employeeObject) {
                employeeObject.$save(function () {
                    $state.go('admin.masters_employee', null, {'reload': true});
                });
            };

        });




