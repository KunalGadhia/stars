/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.services.tag", []);
angular.module("stars.services.tag")
        .factory('TagService', function ($resource, restRoot, contextPath) {
            return $resource(restRoot + '/tag/:id', {'id': '@id'}, {

                'findByNameLike': {
                    'method': 'GET',
                    'url': restRoot + '/tag/find/user_like',
                    'params': {
                        'name': '@name'
                    },
                    'isArray': true
                },                
                'findByName': {
                    'method': 'GET',
                    'url': restRoot + '/tag/find/name',
                    'params': {
                        'name': '@name'
                    },
                    'isArray': false
                }
            });
        });