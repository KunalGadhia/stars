/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states.kra", [])
        .config(function ($stateProvider, templateRoot) {
            $stateProvider.state('admin.kra_employeelist', {
                'url': '/kra/formList1',
                'templateUrl': templateRoot + '/masters/kra/list.html',
                'controller': 'KraFormListController'
            });
            $stateProvider.state('admin.kra_employeelist.form1', {
                'url': '/:employeeId/kra/form1',
                'templateUrl': templateRoot + '/masters/kra/form1.html',
                'controller': 'KraForm1Controller'
            });
            $stateProvider.state('admin.kra_employeelist.editForm1', {
                'url': '/:kraDetailId/kra/edit/form1',
                'templateUrl': templateRoot + '/masters/kra/form1.html',
                'controller': 'KraForm1EditController'
            });
            $stateProvider.state('admin.kra_employeelist.rateKra', {
                'url': '/:kraDetailId/kra/:employeeId/rate/form1',
                'templateUrl': templateRoot + '/masters/kra/rate_kra.html',
                'controller': 'RateKraForm1EditController'
            });
            $stateProvider.state('admin.kra_employeelist.delete', {
                'url': '/:kraDetailId/kra/delete',
                'templateUrl': templateRoot + '/masters/kra/delete.html',
                'controller': 'KraForm1DeleteController'
            });
//            $stateProvider.state('admin.masters_order_details', {
//                'url': '/:orderHeadId/order_details',
//                'templateUrl': templateRoot + '/masters/order/order_details.html',
//                'controller': 'OrderDetailsController'
//            });
//            $stateProvider.state('admin.masters_dealer_order_details', {
//                'url': '/:orderHeadId/dealer_order_details',
//                'templateUrl': templateRoot + '/masters/order/dealer_order_detail.html',
//                'controller': 'DealerOrderDetailsController'
//            });            
        })

        .controller('KraFormListController', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.user = $rootScope.currentUser;
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                $scope.userObject = userObject;
                $scope.employeeBack = false;
                $scope.hodBack = false;
                $scope.adminBack = false;

                $scope.kraDetailsList = KraDetailsService.findByEmployeeId({
                    'employeeId': userObject.employeeId
                });
                console.log("KRA Details List :%O", $scope.kraDetailsList);
                if (userObject.role === "ROLE_EMPLOYEE") {
                    $scope.employeeBack = true;
                    $scope.hodBack = false;
                    $scope.adminBack = false;
                } else if (userObject.role === "ROLE_HOD") {
                    $scope.employeeBack = false;
                    $scope.hodBack = true;
                    $scope.adminBack = false;
                } else if (userObject.role === "ROLE_ADMIN") {
                    $scope.employeeBack = false;
                    $scope.hodBack = false;
                    $scope.adminBack = true;
                }
            });
            console.log("User Object :%O", $scope.user);
        })
        .controller('KraForm1Controller', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.editableKRA = {};
            $scope.approveKra = function (editableKRA) {
                var d = new Date();
                editableKRA.weightage = $("#demo").val();
                editableKRA.employeeId = $stateParams.employeeId;
                editableKRA.year = d.getFullYear();

                console.log("Editable KRA :%O", editableKRA);
                $scope.saveKra(editableKRA);
            };
            $scope.saveKra = function (editableKRA) {
                console.log("Final Save :%O", editableKRA);
                KraDetailsService.save(editableKRA, function (savedData) {
                    console.log("Saved Data :%O", savedData);
                    $state.go('admin.kra_employeelist', null, {'reload': true});
                });
            };
            $scope.employeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
        })
        .controller('KraForm1EditController', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.editableKRA = KraDetailsService.get({
                'id': $stateParams.kraDetailId
            });
            $scope.approveKra = function (editableKRA) {
                var d = new Date();
                editableKRA.weightage = $("#demo").val();
//                editableKRA.employeeId = $stateParams.employeeId;
                editableKRA.year = d.getFullYear();

                console.log("Editable KRA :%O", editableKRA);
                $scope.saveKra(editableKRA);
            };
            $scope.saveKra = function (editableKRA) {
                console.log("Final Save :%O", editableKRA);
                editableKRA.$save(function () {
                    $state.go('admin.kra_employeelist', null, {'reload': true});
                });
            };
        })
        .controller('RateKraForm1EditController', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.editableKRA = KraDetailsService.get({
                'id': $stateParams.kraDetailId
            });
            $scope.approveKra = function (editableKRA) {
                var d = new Date();
                editableKRA.weightage = $("#demo").val();
//                editableKRA.employeeId = $stateParams.employeeId;
                editableKRA.year = d.getFullYear();

                console.log("Editable KRA :%O", editableKRA);
                $scope.saveKra(editableKRA);
            };
            $scope.$watch('editableKRA.rating', function (rating) {
                console.log("Rating :%O", rating);
                console.log("Weightage :%O", $scope.editableKRA.weightage);
                var rating = parseInt(rating);
                var weightage = parseInt($scope.editableKRA.weightage);
                console.log("Rating 1 :%O", rating);
                console.log("Weightage 1 :%O", weightage);
                var finalweightage = (weightage / 100);
                console.log("Final Weightage :%O", finalweightage);
                $scope.editableKRA.ratingScore = Math.round((finalweightage * rating) * 100) / 100;
//                console.log("weightage :%O", parseInt($scope.editableKRA.weightage));
//                var ratingScore =  (rating * parseInt($scope.editableKRA.weightage));
//                console.log("Rating Scowe :%O", ratingScore);
            });
            $scope.saveKra = function (editableKRA) {
                console.log("Final Save :%O", editableKRA);
                editableKRA.$save(function () {
                    $state.go('admin.resourcekra_list', {employeeId: $stateParams.employeeId}, {'reload': true});
                });
            };
        })
        .controller('KraForm1DeleteController', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.editableKRA = KraDetailsService.get({'id': $stateParams.kraDetailId});
            $scope.deleteKraDetail = function (kraDetail) {
                kraDetail.$delete(function () {
                    $state.go('admin.kra_employeelist', null, {'reload': true});
                });
            };
        });




