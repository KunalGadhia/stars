/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states.tag", [])
        .config(function ($stateProvider, templateRoot) {
            $stateProvider.state('admin.masters_tag', {
                'url': '/tag_master?offset',
                'templateUrl': templateRoot + '/masters/tag/list.html',
                'controller': 'TagListController'
            });
            $stateProvider.state('admin.masters_tag.add', {
                'url': '/add',
                'templateUrl': templateRoot + '/masters/tag/form.html',
                'controller': 'TagAddController'
            });
            $stateProvider.state('admin.masters_tag.edit', {
                'url': '/:tagId/edit',
                'templateUrl': templateRoot + '/masters/tag/form.html',
                'controller': 'TagEditController'
            });
            $stateProvider.state('admin.masters_tag.delete', {
                'url': '/:tagId/delete',
                'templateUrl': templateRoot + '/masters/tag/delete.html',
                'controller': 'TagDeleteController'
            });
        })
        .controller('TagListController', function (UserService, $rootScope, TagService, $scope, $stateParams, $state, paginationLimit) {
            $scope.user = $rootScope.currentUser;
            UserService.findByUsername({
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
                    $scope.nextTags = TagService.query({
                        'offset': $scope.nextOffset
                    });
                    $scope.tags = TagService.query({
                        'offset': $scope.currentOffset
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
                    $scope.nextTags = TagService.query({
                        'offset': $scope.nextOffset
                    });
                    $scope.tags = TagService.query({
                        'offset': $scope.currentOffset
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
        })
        .controller('TagAddController', function (TagService, $scope, $stateParams, $state, paginationLimit) {

            $scope.editableTag = {};
            $scope.$watch('editableTag.tagName', function (tagName) {
                console.log("Name :" + tagName);
                TagService.findByName({'name': tagName}).$promise.catch(function (response) {
                    if (response.status === 500) {
                        $scope.editableTag.repeatTag = false;
                    } else if (response.status === 404) {
                        $scope.editableTag.repeatTag = false;
                    } else if (response.status === 400) {
                        $scope.editableTag.repeatTag = false;
                    }
                }).then(function (tagName) {
                    if (tagName.tagName !== null) {
                        $scope.editableTag.repeatTag = true;
                    }
                    ;
                });
            });
//            $scope.rawMaterialList = RawMaterialService.findAllList();
//            $scope.finishList = FinishPriceService.findAllList();

            $scope.saveTag = function (tag) {
                TagService.save(tag, function (savedData) {
                    console.log("Saved Data :%O", savedData);
                    $state.go('admin.masters_tag', null, {'reload': true});
                });
            };
        })
        .controller('TagEditController', function (TagService, $scope, $stateParams, $state, paginationLimit) {
            $scope.editableTag = {};
            $scope.saveTag = function (tag) {
                tag.$save(function () {
                    $state.go('admin.masters_tag', null, {'reload': true});
                });
            };
            TagService.get({
                'id': $stateParams.tagId
            }, function (tag) {
                $scope.editableTag = tag;
            });
        })
        .controller('TagDeleteController', function (TagService, $scope, $stateParams, $state, paginationLimit) {
            $scope.editableTag = TagService.get({'id': $stateParams.tagId});
            $scope.deleteTag = function (tag) {
                tag.$delete(function () {
                    $state.go('admin.masters_tag', null, {'reload': true});
                });
            };
        });
;


