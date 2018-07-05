/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states.user", [])
        .config(function ($stateProvider, templateRoot) {
            $stateProvider.state('admin.masters_user', {
                'url': '/user_master',
                'templateUrl': templateRoot + '/masters/user/list.html',
                'controller': 'UserListController'
            });
            $stateProvider.state('admin.masters_user.add', {
                'url': '/add',
                'templateUrl': templateRoot + '/masters/user/form.html',
                'controller': 'UserAddController'
            });
            $stateProvider.state('admin.masters_user.edit', {
                'url': '/:userId/edit',
                'templateUrl': templateRoot + '/masters/user/form.html',
                'controller': 'UserEditController'
            });
            $stateProvider.state('admin.masters_user.delete', {
                'url': '/:userId/delete',
                'templateUrl': templateRoot + '/masters/user/delete.html',
                'controller': 'UserDeleteController'
            });
        })
        .controller('UserListController', function ($window, $rootScope, EmployeeService, UserService, $scope, $stateParams, $state, paginationLimit) {

            $scope.user = $rootScope.currentUser;
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                if (userObject.role === "ROLE_HR") {
                    $scope.showHRBack = true;
                    $scope.showAdminBack = false;

                    $scope.currentOffset = 0;
                    $scope.mainUserArray = [];
                    $scope.nextUsers = UserService.findUsersByCompany({
                        'companyId': userObject.companyId,
                        'offset': $scope.nextOffset
                    });

                    UserService.findUsersByCompany({
                        'companyId': userObject.companyId,
                        'offset': $scope.currentOffset
                    }, function (userList) {
                        angular.forEach(userList, function (user) {
                            user.employee = EmployeeService.get({
                                'id': user.employeeId
                            });
                            $scope.mainUserArray.push(user);
                        });
                    });

                    $scope.userCall = function (offset) {
                        UserService.findUsersByCompany({
                            'companyId': userObject.companyId,
                            'offset': $scope.currentOffset
                        }, function (userList) {
                            angular.forEach(userList, function (user) {
                                user.employee = EmployeeService.get({
                                    'id': user.employeeId
                                });
                                $scope.mainUserArray.push(user);
                            });
                        });
                    };
                    $scope.enterIntoArray = function (user) {
                        $scope.mainUserArray.push(user);
                    };

                    $scope.nextPage = function () {
                        $scope.currentOffset += paginationLimit;
                        $scope.nextOffset = $scope.currentOffset + 10;
                        $scope.userCall($scope.currentOffset);
                    };

                    angular.element($window).bind('scroll', function (response) {
                        if (this.pageYOffset + this.innerHeight === $(document).height()) {
                            $scope.nextPage();
                        }
                        ;
                    });
                } else if (userObject.role === "ROLE_ADMIN") {
                    $scope.showHRBack = false;
                    $scope.showAdminBack = true;

                    $scope.currentOffset = 0;
                    $scope.mainUserArray = [];
                    $scope.nextUsers = UserService.findUsersByCompany({
                        'companyId': userObject.companyId,
                        'offset': $scope.nextOffset
                    });

                    UserService.findUsersByCompany({
                        'companyId': userObject.companyId,
                        'offset': $scope.currentOffset
                    }, function (userList) {
                        angular.forEach(userList, function (user) {
                            user.employee = EmployeeService.get({
                                'id': user.employeeId
                            });
                            $scope.mainUserArray.push(user);
                        });
                    });

                    $scope.userCall = function (offset) {
                        UserService.findUsersByCompany({
                            'companyId': userObject.companyId,
                            'offset': $scope.currentOffset
                        }, function (userList) {
                            angular.forEach(userList, function (user) {
                                user.employee = EmployeeService.get({
                                    'id': user.employeeId
                                });
                                $scope.mainUserArray.push(user);
                            });
                        });
                    };
                    $scope.enterIntoArray = function (user) {
                        $scope.mainUserArray.push(user);
                    };

                    $scope.nextPage = function () {
                        $scope.currentOffset += paginationLimit;
                        $scope.nextOffset = $scope.currentOffset + 10;
                        $scope.userCall($scope.currentOffset);
                    };

                    angular.element($window).bind('scroll', function (response) {
                        if (this.pageYOffset + this.innerHeight === $(document).height()) {
                            $scope.nextPage();
                        }
                        ;
                    });
                }
            });

        })
        .controller('UserAddController', function ($rootScope, CompanyService, UserService, EmployeeService, $scope, $stateParams, $state, paginationLimit) {

            $scope.editableUser = {};

            $scope.$watch('editableUser.username', function (username) {
                UserService.findByUsername({'username': username}).$promise.catch(function (response) {
                    if (response.status === 500) {
                        $scope.editableUser.repeatUsername = false;
                    } else if (response.status === 404) {
                        $scope.editableUser.repeatUsername = false;
                    } else if (response.status === 400) {
                        $scope.editableUser.repeatUsername = false;
                    }
                }).then(function (username) {
                    if (username.username !== null) {
                        $scope.editableUser.repeatUsername = true;
                    }
                    ;
                });
            });

            $scope.user = $rootScope.currentUser;
            $scope.userObject = UserService.findByUsername({
                'username': $scope.user.username
            });

            $scope.companyList = CompanyService.findALlList();
            $scope.$watch('editableUser.employeeId', function (employeeId) {
                UserService.findByEmployeeId({'employeeId': employeeId}).$promise.catch(function (response) {
                    if (response.status === 500) {
                        $scope.editableUser.repeatEmployee = false;
                    } else if (response.status === 404) {
                        $scope.editableUser.repeatEmployee = false;
                    } else if (response.status === 400) {
                        $scope.editableUser.repeatEmployee = false;
                    }
                }).then(function (employeeId) {
                    if (employeeId !== undefined) {
                        $scope.editableUser.repeatEmployee = true;
                    }
                    ;
                });
            });

//            $scope.rawMaterialList = RawMaterialService.findAllList();
//            $scope.finishList = FinishPriceService.findAllList();

            $scope.saveUser = function (user) {
                UserService.save(user, function (savedData) {
                    console.log("Saved Data :%O", savedData);
                    $state.go('admin.masters_user', null, {'reload': true});
                });
            };

            $scope.searchEmployees = function (empString) {
                return EmployeeService.findByNameLikeByCompany({
                    'companyId': $scope.userObject.companyId,
                    'name': empString
                }).$promise;
            };
            $scope.setEmployee = function (employee) {
                $scope.editableUser.employeeId = employee.id;
            };

        })
        .controller('UserEditController', function ($rootScope, CompanyService, UserService, EmployeeService, $scope, $stateParams, $state, paginationLimit) {
            $scope.editableUser = {};
            $scope.saveUser = function (User) {
                User.$save(function () {
                    $state.go('admin.masters_user', null, {'reload': true});
                });
            };
            UserService.get({
                'id': $stateParams.userId
            }, function (user) {
                user.employee = EmployeeService.get({
                    'id': user.employeeId
                });
                $scope.editableUser = user;

            });

            $scope.user = $rootScope.currentUser;
            $scope.userObject = UserService.findByUsername({
                'username': $scope.user.username
            });

            $scope.companyList = CompanyService.findALlList();
            $scope.searchEmployees = function (empString) {
                return EmployeeService.findByNameLikeByCompany({
                    'companyId': $scope.userObject.companyId,
                    'name': empString
                }).$promise;
            };
            $scope.setEmployee = function (employee) {
                $scope.editableUser.employeeId = employee.id;
            };

        })
        .controller('UserDeleteController', function (UserService, $scope, $stateParams, $state, paginationLimit) {
            $scope.editableUser = UserService.get({'id': $stateParams.userId});
            $scope.deleteUser = function (user) {
                user.$delete(function () {
                    $state.go('admin.masters_user', null, {'reload': true});
                });
            };
        });
;


