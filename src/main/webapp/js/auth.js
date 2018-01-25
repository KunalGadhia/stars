/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

angular.module('stars.states.auth', ['stars.constants'])
        .factory('AuthFactory', ['$http', 'contextPath', '$q', '$timeout', function ($http, contextPath, $q, $timeout) {
                console.log("Coming to Auth Js??");
                function User() {
                }
                ;

                User.prototype.hasRole = function (roleString) {
                    var isRolePresent = false;
                    angular.forEach(this.authorities, function (authorityObj) {
                        if (authorityObj["authority"] === roleString) {
                            isRolePresent = true;
                        }
                    });
                    return isRolePresent;
                };
                User.prototype.isEmployee = function () {
                    return this.hasRole("ROLE_EMPLOYEE");
                };
                User.prototype.isHod = function () {
                    return this.hasRole("ROLE_HOD");
                };                
                User.prototype.isAdmin = function () {
                    return this.hasRole("ROLE_ADMIN");
                };

                //Singleton Variable to store the currently logged in User
                var currentUser = null;

                var userChangeCallbacks = [];

                var notifyUserChange = function (newUser) {
                    angular.forEach(userChangeCallbacks, function (callback) {
                        $timeout(function () {
                            callback(newUser);
                        });
                    });
                };

                var exported = {
                    getCurrentUser: function () {
                        return currentUser;
                    },
                    refresh: function () {
                        return $q(function (resolve, reject) {
                            //Get the current user
                            $http.get(contextPath + '/rest/user/current')
                                    .success(function (data) {
//                                        console.log("1. $http success data", data);
                                        currentUser = new User();
                                        for (var key in data) {
//                                            console.log("2. $http success key", key);
                                            currentUser[key] = data[key];
                                        }
                                        notifyUserChange(currentUser);
//                                        console.log("3. $http success key", currentUser.username);
                                        resolve(currentUser);
                                    })
                                    .error(function (reason) {
                                        reject(reason);
                                    });
                        });
                    },
                    registerUserChangeHandler: function (callback) {
                        console.log("registered handler: " + callback);
                        userChangeCallbacks.push(callback);
                    }
                };
                return exported;
            }]);

