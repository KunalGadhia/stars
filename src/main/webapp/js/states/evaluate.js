/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states.evaluate", [])
        .config(function ($stateProvider, templateRoot) {
            $stateProvider.state('admin.evaluate', {
                'url': '/evaluate/list',
                'templateUrl': templateRoot + '/masters/evaluate/list.html',
                'controller': 'EvaluationListController'
            });
            $stateProvider.state('admin.resource_list', {
                'url': '/evaluate/:employeeId/kra',
                'templateUrl': templateRoot + '/masters/evaluate/kra_list.html',
                'controller': 'EvaluationKraListController'
            });
//            $stateProvider.state('admin.resource_list.rating', {
//                'url': '/evaluate/:kraDetailId/kra',
//                'templateUrl': templateRoot + '/masters/evaluate/kra_list.html',
//                'controller': 'EvaluationKraListController'
//            });
//            $stateProvider.state('admin.profile.password_change', {
//                'url': '/:employeeId/profile/change_pass',
//                'templateUrl': templateRoot + '/masters/profile/password_change.html',
//                'controller': 'ProfileChangePasswordController'
//            });
//            $stateProvider.state('print_kra', {
//                'url': '/:employeeId/profile/print_kra',
//                'templateUrl': templateRoot + '/masters/profile/print_kra.html',
//                'controller': 'ProfilePrintKra'
//            });
//            $stateProvider.state('admin.profile.photo_upload', {
//                'url': '/:employeeId/profile/photo_upload',
//                'templateUrl': templateRoot + '/masters/profile/upload_photo.html',
//                'controller': 'ProfilePhotoUpload'
//            });

        })

        .controller('EvaluationListController', function ($filter, KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.user = $rootScope.currentUser;
            console.log("This is User  :%O", $scope.user);
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                $scope.userObject = userObject;
                EmployeeService.get({
                    'id': userObject.employeeId
                }, function (employeeObject) {
                    console.log("Employee Object %O", employeeObject);
                    $scope.employeeObject = employeeObject;
                });
                $scope.resourcesList = EmployeeService.findByDepartmentHead({
                    'departmentHeadId': userObject.employeeId
                });

//                $scope.kraDetailsList = KraDetailsService.findByEmployeeId({
//                    'employeeId': userObject.employeeId
//                });

            });
//            $scope.filterList = $filter("total")('ratingScore');
//            $scope.saveScore = function (score) {
//                console.log("Score :%O", score);
//            };
//            console.log("User Object :%O", $scope.user);
        })
        .controller('EvaluationKraListController', function ($filter, KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            console.log("Inside List with state param:%O", $stateParams);
            $scope.employeeId = $stateParams.employeeId;
            $scope.kraDetailsList = KraDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }, function (kraDetailsList) {
                var a = 0;
                angular.forEach(kraDetailsList, function (kraDetail) {

                    a = a + kraDetail.ratingScore;                  
                    $scope.ratingScore = a;
                });
            });
//            $scope.filterList = $filter("total")('ratingScore');
            $scope.saveScore = function (ratingScore) {
                console.log("Rating Score" + ratingScore);
            };
        });
//        .controller('ProfilePrintKra', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {            
//            $scope.kraEmployeeObject = EmployeeService.get({
//               'id' : $stateParams.employeeId 
//            });
//            $scope.kraDetailsList = KraDetailsService.findByEmployeeId({
//               'employeeId': $stateParams.employeeId 
//            });
//        })
//        .controller('ProfilePhotoUpload', function (KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {            
////            $scope.kraEmployeeObject = EmployeeService.get({
////               'id' : $stateParams.employeeId 
////            });
////            $scope.kraDetailsList = KraDetailsService.findByEmployeeId({
////               'employeeId': $stateParams.employeeId 
////            });
//        });




