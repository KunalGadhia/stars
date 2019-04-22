/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.services.form3_details", []);
angular.module("stars.services.form3_details")
        .factory('Form3DetailsService', function ($resource, restRoot, contextPath) {            
            return $resource(restRoot + '/form3_details/:id', {'id': '@id'}, {
                
                'findByEmployeeId': {
                    'method': 'GET',
                    'url': restRoot + '/form3_details/find/employeeId',
                    'params': {
                        'employeeId': '@employeeId'
                    },
                    'isArray': false
                },
                'findALlList': {
                    'method': 'GET',
                    'url': restRoot + '/form3_details/find_all_list',
                    'isArray': true
                }
            });
        });




