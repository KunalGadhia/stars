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
            $stateProvider.state('admin.resourcekra_list', {
                'url': '/evaluate/:employeeId/kra',
                'templateUrl': templateRoot + '/masters/evaluate/kra_list.html',
                'controller': 'EvaluationKraListController'
            });
            $stateProvider.state('admin.form2', {
                'url': '/form2/:employeeId/kra',
                'templateUrl': templateRoot + '/masters/evaluate/form2.html',
                'controller': 'Form2Controller'
            });
            $stateProvider.state('admin.form3', {
                'url': '/form3/feedback/:employeeId',
                'templateUrl': templateRoot + '/masters/evaluate/form3.html',
                'controller': 'Form3Controller'
            });
            $stateProvider.state('admin.hod_eval', {
                'url': '/hod/:employeeId/eval',
                'templateUrl': templateRoot + '/masters/evaluate/hod_eval.html',
                'controller': 'HodEvalController'
            });
            $stateProvider.state('admin.hod_eval_edit', {
                'url': '/hod/:employeeId/eval/edit',
                'templateUrl': templateRoot + '/masters/evaluate/hod_eval.html',
                'controller': 'HodEvalEditController'
            });
            $stateProvider.state('admin.dir_eval_edit', {
                'url': '/dir/:employeeId/eval/edit',
                'templateUrl': templateRoot + '/masters/evaluate/hod_eval_dir.html',
                'controller': 'HodEvalDirEditController'
            });
            $stateProvider.state('admin.form2.delete', {
                'url': '/form2/:form2DetailId/delete',
                'templateUrl': templateRoot + '/masters/evaluate/delete.html',
                'controller': 'Form2DetailDeleteController'
            });
            $stateProvider.state('print_form1', {
                'url': '/:employeeId/print/form1',
                'templateUrl': templateRoot + '/masters/evaluate/print_form1.html',
                'controller': 'PrintForm1Controller'
            });
            $stateProvider.state('print_form2', {
                'url': '/:employeeId/print/form2',
                'templateUrl': templateRoot + '/masters/evaluate/print_form2.html',
                'controller': 'PrintForm2Controller'
            });
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
            $scope.saveScore = function () {
                $state.go('admin.evaluate', null, {'reload': true});
            };
        })
        .controller('Form2Controller', function (AdditionalDetailsService, Form2DetailsService, KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.user = $rootScope.currentUser;
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                if (userObject.role === "ROLE_HR") {
                    $scope.showHRBack = true;
                    $scope.showAdminBack = false;
                } else if (userObject.role === "ROLE_ADMIN") {
                    $scope.showHRBack = false;
                    $scope.showAdminBack = true;
                }
            });
            $scope.employeeId = $stateParams.employeeId;
            $scope.editableForm2 = {};
            $scope.additionalDetails = {};
            AdditionalDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }).$promise.catch(function (response) {
                if (response.status === 500) {
                    $scope.showSave = true;
//                        $scope.showUpdate = false;
                } else if (response.status === 404) {
                    $scope.showSave = true;
//                        $scope.showUpdate = false;
                } else if (response.status === 400) {
                    $scope.showSave = true;
//                        $scope.showUpdate = false;
                } else if (response.status === 200) {
                    $scope.showSave = false;
                }
            }).then(function (additionalDetailsList) {
                console.log("Additional Details :%O", additionalDetailsList);
                $scope.additionalDetails = additionalDetailsList;
            });
            $scope.form2DetailsList = Form2DetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            });
            $scope.employeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });

            $scope.$watch('editableForm2.rating', function (rating) {
                var rating = parseInt(rating);
                var weightage = parseInt($scope.editableForm2.weightage);
                var finalweightage = (weightage / 100);
                $scope.editableForm2.ratingScore = Math.round((finalweightage * rating) * 100) / 100;
            });

            $scope.approveForm2 = function (editableForm2) {
                var d = new Date();
                editableForm2.employeeId = $stateParams.employeeId;
                editableForm2.year = d.getFullYear();
                $scope.saveForm2(editableForm2);
            };
            $scope.saveForm2 = function (editableForm2Final) {
                Form2DetailsService.save(editableForm2Final, function (savedData) {
                    $scope.editableForm2 = {};
                    $scope.refreshForm2DetailsList();
                });
            };
            $scope.approveAdditionalDetail = function (additionalDetail) {
                var d1 = new Date();
                additionalDetail.employeeId = $stateParams.employeeId;
                additionalDetail.year = d1.getFullYear();
                $scope.saveAdditionalDetail(additionalDetail);
            };
            $scope.saveAdditionalDetail = function (finalAdditionalDetail) {
                console.log("FInal additional Detail :%O", finalAdditionalDetail);
                AdditionalDetailsService.save(finalAdditionalDetail, function (savedData) {
                    $state.go('admin.department_resource_list', {'employeeId': $scope.employeeObject.departmentHead}, {'reload': true});
                });
            };

            $scope.refreshForm2DetailsList = function () {
                $scope.form2DetailsList = Form2DetailsService.findByEmployeeId({
                    'employeeId': $stateParams.employeeId
                });
            };
        })
        .controller('Form3Controller', function (Form3DetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state) {
            $scope.user = $rootScope.currentUser;
            $scope.employeeView = false;
            $scope.hodView = false;
            $scope.adminView = false;
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                $scope.employeeId = userObject.employeeId;
                if (userObject.role === "ROLE_HR") {
                    $scope.employeeObject = EmployeeService.get({
                        'id': $stateParams.employeeId
                    });
                    Form3DetailsService.findByEmployeeId({
                        'employeeId': $stateParams.employeeId
                    }, function (form3Data) {
                        if (form3Data.id === undefined) {
                        } else if (form3Data.id !== undefined) {
                            $scope.editableForm3 = form3Data;
                        }
                        $scope.form3EditData = form3Data;
                    });
                    $scope.showHRBack = true;
                    $scope.showAdminBack = false;
                    $scope.showEmployeeBack = false;
                    $scope.showHodBack = false;
                    $scope.employeeView = false;
                    $scope.hodView = false;
                    $scope.adminView = true;
                } else if (userObject.role === "ROLE_ADMIN") {
                    $scope.employeeObject = EmployeeService.get({
                        'id': $stateParams.employeeId
                    });
                    Form3DetailsService.findByEmployeeId({
                        'employeeId': $stateParams.employeeId
                    }, function (form3Data) {
                        if (form3Data.id === undefined) {
                        } else if (form3Data.id !== undefined) {
                            $scope.editableForm3 = form3Data;
                        }
                        $scope.form3EditData = form3Data;
                    });
                    $scope.showHRBack = false;
                    $scope.showAdminBack = true;
                    $scope.showEmployeeBack = false;
                    $scope.showHodBack = false;
                    $scope.employeeView = false;
                    $scope.hodView = false;
                    $scope.adminView = true;
                } else if (userObject.role === "ROLE_EMPLOYEE") {
                    $scope.employeeObject = EmployeeService.get({
                        'id': userObject.employeeId
                    });
                    Form3DetailsService.findByEmployeeId({
                        'employeeId': userObject.employeeId
                    }, function (form3Data) {
                        console.log("Form 3 Data for ROle Employee:%O", form3Data);
                        if (form3Data.id === undefined) {
                        } else if (form3Data.id !== undefined) {
                            $scope.editableForm3 = form3Data;
                        }
                        $scope.form3EditData = form3Data;
                    }).$promise.catch(function (response) {
                        if (response.status === 500) {
                            $scope.editableForm3.commentVisible = false;
                        } else if (response.status === 404) {
                            $scope.editableForm3.commentVisible = false;
                        } else if (response.status === 400) {
                            $scope.editableForm3.commentVisible = false;
                        }
                    });
                    $scope.showHRBack = false;
                    $scope.showAdminBack = false;
                    $scope.showEmployeeBack = true;
                    $scope.showHodBack = false;
                    $scope.employeeView = true;
                    $scope.hodView = false;
                    $scope.adminView = false;
                } else if (userObject.role === "ROLE_HOD") {
                    $scope.employeeObject = EmployeeService.get({
                        'id': $stateParams.employeeId
                    });
                    Form3DetailsService.findByEmployeeId({
                        'employeeId': $stateParams.employeeId
                    }, function (form3Data) {
                        if (form3Data.id === undefined) {
                        } else if (form3Data.id !== undefined) {
                            $scope.editableForm3 = form3Data;
                        }
                        $scope.form3EditData = form3Data;
                    });
                    $scope.showHRBack = false;
                    $scope.showAdminBack = false;
                    $scope.showEmployeeBack = false;
                    $scope.showHodBack = true;
                    $scope.employeeView = false;
                    $scope.hodView = true;
                    $scope.adminView = false;
                }
            });
            $scope.editableForm3 = {};
            $scope.saveForm3 = function (form3Content) {
                if (form3Content.id === undefined) {
                    form3Content.commentVisible = false;
                    form3Content.employeeId = $scope.employeeObject.id;
                    Form3DetailsService.save(form3Content, function (savedData) {
                        $state.go('admin.employees', null, {'reload': true});
                    });
                } else if (form3Content.id !== undefined) {
                    form3Content.$save(function () {
                        $state.go('admin.employees', null, {'reload': true});
                    });
                }
            };
            $scope.saveForm3Hod = function (form3Content) {
                form3Content.$save(function () {
                    $state.go('admin.evaluate', null, {'reload': true});
                });
            };
        })
        .controller('HodEvalController', function (HodReviewDetailsService, TagService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.editableHodReviewDetails = {};
            $scope.tagDisplay = [];
            $scope.editableHodReviewDetails.tags = [];
            $scope.user = $rootScope.currentUser;
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                $scope.userRole = userObject.role;
            });
            $scope.employeeId = $stateParams.employeeId;
            $scope.employeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            $scope.searchTags = function (tagString) {
                return TagService.findByNameLike({
                    'name': tagString
                }).$promise;
            };
            $scope.setTag = function (tag) {
                $scope.tagDisplay.push(tag);
                $scope.tagName = "";
                $scope.editableHodReviewDetails.tags.push(tag.id);
            };
            $scope.removeTags = function (tags) {
                var index = $scope.tagDisplay.indexOf(tags);
                var index1 = $scope.editableHodReviewDetails.tags.indexOf(tags.id);
                $scope.tagDisplay.splice(index, 1);
                $scope.editableHodReviewDetails.tags.splice(index1, 1);
            };
            $scope.saveHodEval = function (editableHodReviewDetails) {
                if ($scope.userRole === "ROLE_ADMIN") {
                    editableHodReviewDetails.employeeId = $stateParams.employeeId;
                    editableHodReviewDetails.noUpdatedDate = new Date();
                    HodReviewDetailsService.save(editableHodReviewDetails, function (savedData) {
                        $state.go('admin.director_resource_list', {'employeeId': $scope.employeeObject.departmentHead}, {'reload': true});
                    });
                } else if ($scope.userRole === "ROLE_DIRECTOR") {
                    editableHodReviewDetails.employeeId = $stateParams.employeeId;
                    editableHodReviewDetails.dirUpdatedDate = new Date();
                    HodReviewDetailsService.save(editableHodReviewDetails, function (savedData) {
                        $state.go('admin.director_resource_list', {'employeeId': $scope.employeeObject.departmentHead}, {'reload': true});
                    });
                }
            };
        })
        .controller('HodEvalEditController', function (HodReviewDetailsService, TagService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.tagDisplay = [];
            $scope.user = $rootScope.currentUser;
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                $scope.userRole = userObject.role;
            });
            $scope.employeeId = $stateParams.employeeId;
            $scope.employeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            HodReviewDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }, function (hodReviewDetailsObject) {
                $scope.editableHodReviewDetails = hodReviewDetailsObject;
                angular.forEach(hodReviewDetailsObject.tags, function (tag) {
                    $scope.tagDisplay.push(
                            TagService.get({
                                'id': tag
                            })
                            );
                });
            });
            $scope.searchTags = function (tagString) {
                return TagService.findByNameLike({
                    'name': tagString
                }).$promise;
            };
            $scope.setTag = function (tag) {
                $scope.tagDisplay.push(tag);
                $scope.tagName = "";
                $scope.editableHodReviewDetails.tags.push(tag.id);
            };
            $scope.removeTags = function (tags) {
                var index = $scope.tagDisplay.indexOf(tags);
                var index1 = $scope.editableHodReviewDetails.tags.indexOf(tags.id);
                $scope.tagDisplay.splice(index, 1);
                $scope.editableHodReviewDetails.tags.splice(index1, 1);
            };
            $scope.saveHodEval = function (editableHodReviewDetails) {
                if ($scope.userRole === "ROLE_ADMIN") {
                    editableHodReviewDetails.employeeId = $stateParams.employeeId;
                    editableHodReviewDetails.noUpdatedDate = new Date();
                    editableHodReviewDetails.$save(function () {
                        $state.go('admin.director_resource_list', {'employeeId': $scope.employeeObject.departmentHead}, {'reload': true});
                    });
                } else if ($scope.userRole === "ROLE_DIRECTOR") {
                    editableHodReviewDetails.employeeId = $stateParams.employeeId;
                    editableHodReviewDetails.dirUpdatedDate = new Date();
                    editableHodReviewDetails.$save(function () {
                        $state.go('admin.director_resource_list', {'employeeId': $scope.employeeObject.departmentHead}, {'reload': true});
                    });
                }
            };
        })
        .controller('HodEvalDirEditController', function (HodReviewDetailsService, TagService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.tagDisplay = [];
            $scope.user = $rootScope.currentUser;
            UserService.findByUsername({
                'username': $scope.user.username
            }, function (userObject) {
                $scope.userRole = userObject.role;
            });
            $scope.employeeId = $stateParams.employeeId;
            $scope.employeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            HodReviewDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }, function (hodReviewDetailsObject) {
                $scope.editableHodReviewDetails = hodReviewDetailsObject;
                angular.forEach(hodReviewDetailsObject.tags, function (tag) {
                    $scope.tagDisplay.push(
                            TagService.get({
                                'id': tag
                            })
                            );
                });
            });
            $scope.displayData = "";
            $scope.showData = function (parameter) {
                if (parameter === "Performance") {
                    $scope.displayData = $scope.editableHodReviewDetails.performance;
                } else if (parameter === "Challenge") {
                    $scope.displayData = $scope.editableHodReviewDetails.challenge;
                } else if (parameter === "Training") {
                    $scope.displayData = $scope.editableHodReviewDetails.training;
                } else if (parameter === "Expectation") {
                    $scope.displayData = $scope.editableHodReviewDetails.expectation;
                } else if (parameter === "No_Comment") {
                    $scope.displayData = $scope.editableHodReviewDetails.noComment;
                }
                ;
            };
//            $scope.searchTags = function (tagString) {
//                return TagService.findByNameLike({
//                    'name': tagString
//                }).$promise;
//            };
//            $scope.setTag = function (tag) {
//                $scope.tagDisplay.push(tag);
//                $scope.tagName = "";
//                $scope.editableHodReviewDetails.tags.push(tag.id);
//            };
//            $scope.removeTags = function (tags) {
//                var index = $scope.tagDisplay.indexOf(tags);
//                var index1 = $scope.editableHodReviewDetails.tags.indexOf(tags.id);
//                $scope.tagDisplay.splice(index, 1);
//                $scope.editableHodReviewDetails.tags.splice(index1, 1);
//            };
            $scope.saveHodEval = function (editableHodReviewDetails) {
                if ($scope.userRole === "ROLE_ADMIN") {
                    editableHodReviewDetails.employeeId = $stateParams.employeeId;
                    editableHodReviewDetails.noUpdatedDate = new Date();
                    editableHodReviewDetails.$save(function () {
                        $state.go('admin.director_resource_list', {'employeeId': $scope.employeeObject.departmentHead}, {'reload': true});
                    });
                } else if ($scope.userRole === "ROLE_DIRECTOR") {
                    editableHodReviewDetails.employeeId = $stateParams.employeeId;
                    editableHodReviewDetails.dirUpdatedDate = new Date();
                    editableHodReviewDetails.$save(function () {
                        $state.go('admin.director_emp_list', {'employeeId': $scope.employeeObject.departmentHead}, {'reload': true});
                    });
                }
            };
        })
        .controller('Form2DetailDeleteController', function (Form2DetailsService, KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.editableForm2 = Form2DetailsService.get({
                'id': $stateParams.form2DetailId
            });
            $scope.deleteForm2Detail = function (editableForm2) {
                editableForm2.$delete(function () {
                    $state.go('admin.form2', null, {'reload': true});
                });
            };
//            $scope.refreshForm2DetailsList = function (employeeId) {
//                console.log("Employee Id :%O", employeeId);
//                $scope.form2DetailsList = Form2DetailsService.findByEmployeeId({
//                    'employeeId': employeeId
//                });
//            };
        })
        .controller('PrintForm1Controller', function (Form2DetailsService, KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.kraEmployeeObject = EmployeeService.get({
                'id': $stateParams.employeeId
            });
            $scope.kraDetailsList = KraDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            });
        })
        .controller('PrintForm2Controller', function (AdditionalDetailsService, Form2DetailsService, KraDetailsService, EmployeeService, UserService, $scope, $stateParams, $rootScope, $state, paginationLimit) {
            $scope.employeeId = $stateParams.employeeId;
            $scope.editableForm2 = {};
            $scope.additionalDetails = {};
            AdditionalDetailsService.findByEmployeeId({
                'employeeId': $stateParams.employeeId
            }).$promise.catch(function (response) {
                if (response.status === 500) {
                    $scope.showSave = true;
//                        $scope.showUpdate = false;
                } else if (response.status === 404) {
                    $scope.showSave = true;
//                        $scope.showUpdate = false;
                } else if (response.status === 400) {
                    $scope.showSave = true;
//                        $scope.showUpdate = false;
                } else if (response.status === 200) {
                    $scope.showSave = false;
                }
            }).then(function (additionalDetailsList) {
                console.log("Additional Details :%O", additionalDetailsList);
                $scope.additionalDetails = additionalDetailsList;
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




