/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states.user", [])
        .config(function ($stateProvider, templateRoot) {
            $stateProvider.state('admin.masters_user', {
                'url': '/user_master?offset',
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
        .controller('UserListController', function (EmployeeService, UserService, $scope, $stateParams, $state, paginationLimit) {
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

            $scope.nextUsers = UserService.query({
                'offset': $scope.nextOffset
            });
            $scope.users = UserService.query({
                'offset': $scope.currentOffset
            }, function (userList) {
                console.log("S :%O", userList);
                angular.forEach($scope.users, function (user) {                    
                    user.employee = EmployeeService.get({
                       'id':user.employeeId 
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
        .controller('UserAddController', function (UserService, EmployeeService, $scope, $stateParams, $state, paginationLimit) {

            $scope.editableUser = {};            
            
            $scope.$watch('editableUser.username', function (username) {
                console.log("Name :" + username);
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

//            $scope.rawMaterialList = RawMaterialService.findAllList();
//            $scope.finishList = FinishPriceService.findAllList();

            $scope.saveUser = function (user) {
                UserService.save(user, function (savedData) {
                    console.log("Saved Data :%O", savedData);
                    $state.go('admin.masters_user', null, {'reload': true});
                });
            };

            $scope.searchEmployees = function (empString) {
                return EmployeeService.findByNameLike({
                    'name': empString
                }).$promise;
            };
            $scope.setEmployee = function (employee) {
//                $scope.partyName = employee.empName;
                $scope.editableUser.employeeId = employee.id;
            };            

        })
        .controller('UserEditController', function (UserService, EmployeeService, $scope, $stateParams, $state, paginationLimit) {            
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
                   'id' : user.employeeId
                });
                $scope.editableUser = user;                
                
            });
            $scope.searchEmployees = function (empString) {
                return EmployeeService.findByNameLike({
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
        });;


