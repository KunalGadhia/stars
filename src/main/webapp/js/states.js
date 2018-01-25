/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.states", ['ngAnimate', 'ui.bootstrap'])
        .config(function ($stateProvider, templateRoot, $sceDelegateProvider) {
            $stateProvider.state('login', {
                'url': '/login',
                'templateUrl': templateRoot + '/login.html',
                'controller': 'LoginController'
            });
//            $stateProvider.state('main.logout', {
//                'url': '/logout',
//                'templateUrl': templateRoot + '/logout.html',
//                'controller': 'LogoutController'
//            });
            $stateProvider.state('main', {
                'url': '/main',
                'templateUrl': templateRoot + '/main.html'
//                'controller': 'MainController'
            });
            $stateProvider.state('main.masters', {
                'url': '/masters',
                'templateUrl': templateRoot + '/masters/menu.html'
            });
            $sceDelegateProvider.resourceUrlWhitelist([
                'https://www.youtube.com/embed/**'
            ]);
        })
//        .controller('MainController', function ($scope, $state, $window, $location, $anchorScroll) {
////            $scope.introVideo = VideoService.findIntroVideo();
////            var parrentDiv = $('#parrentDiv');
////            parrentDiv.removeClass();
////            parrentDiv.addClass('bg-city-spcl');
//
//            $scope.gotoTop = function () {
//                $location.hash('top');
//                $anchorScroll();
//            };
//        })
        .controller('LoginController', function ($scope, $state, $stateParams, $timeout, UserService, AuthFactory) {
            console.log("Coming to Login COntroller");
            $scope.username = $stateParams.username;
            $scope.message = $stateParams.message;
            $scope.error = $stateParams.error;
            $timeout(function () {
                $scope.message = false;
            }, 3000);
            $scope.login = function (username, password) {
                console.log("COming Inside Login Function :%O", username);
                console.log("COming Inside Login Password :%O", password);
                UserService.login({
                    'username': username,
                    'password': password
                }, function (data) {
                    AuthFactory.refresh();
                    UserService.findByUsername({
                        'username': data.username
                    }, function (data) {
                        console.log("Data ROle :%O", data.role);
                        if (data.role === "ROLE_ADMIN") {
                            $state.go("admin.masters");
                        } else if (data.role === "ROLE_HOD") {
                            $state.go("admin.dealers");
                        } else if (data.role === "ROLE_EMPLOYEE") {
                            $state.go("admin.employees");
                        }
                    });
                }, function () {
                    console.log("Invalid Person");
                    $scope.error = true;
                    $scope.username = "";
                    $scope.password = "";
                });
            };
//            $scope.guestLogin = function () {
//                $scope.login("guest", "guest");
//            };

//            $scope.newUser = {};
//            $scope.confirmPassword;
//            $scope.$watch('confirmPassword', function (confirmPassword) {
//                console.log("confirmPassword ", confirmPassword);
//                if ($scope.newUser.password === confirmPassword) {
//                    $scope.errorMsg = false;
//                } else
//                {
//                    $scope.errorMsg = true;
//                }
//
//            });
//
//            $scope.$watch('newUser.username', function (username) {
//                console.log("User Name :%O", username);
//                UserService.findByUsername({'username': username}).$promise.catch(function (response) {
//                    if (response.status === 500) {
//                        $scope.repeatEmail = false;
//                    } else if (response.status === 404) {
//                        $scope.repeatEmail = false;
//                    } else if (response.status === 400) {
//                        $scope.repeatEmail = false;
//                    }
//                }).then(function (user) {
//                    if (user.username !== null) {
//                        $scope.repeatEmail = true;
//                    }
//                    ;
//                });
//            });
//
//            $scope.searchCities = function (searchTerm) {
//                return CityService.findByNameLike({
//                    'name': searchTerm
//                }).$promise;
//            };
//
//            $scope.setCity = function (city) {
//                $scope.newUser.cityId = city.id;
//                $scope.newUser.city = city;
//            };
//
//            $scope.saveNewUser = function (newUserInfo) {
//                UserService.save(newUserInfo, function () {
//                    $state.go('login', null, {'reload': true});
//                });
//                console.log("new user info :%O", newUserInfo);
//            };
//
////            $scope.$watch('$scope.userType', function (userType) {
////                console.log("Detecting Change with value :%O", userType);
////            });
//
////            function googleTranslateElementInit() {
////                console.log("Into Translator");
////                new google.translate.TranslateElement({pageLanguage: 'en'}, 'google_translate_element');
////            }
////            ;
        });
//        .controller('LogoutController', function (UserService, $scope, $state) {
//            $scope.logout = function () {
//                UserService.logout({}, function () {
//                    $state.go("login", {
//                        'message': 'Logged Out Successfully!'
//                    });
//                });
//            };
//        });



