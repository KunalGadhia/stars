/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.services.form2_details", []);
angular.module("stars.services.form2_details")
        .factory('Form2DetailsService', function ($resource, restRoot, contextPath) {            
            return $resource(restRoot + '/form2_details/:id', {'id': '@id'}, {
                
                'findByEmployeeId': {
                    'method': 'GET',
                    'url': restRoot + '/form2_details/find/employeeId',
                    'params': {
                        'employeeId': '@employeeId'
                    },
                    'isArray': true
                },
                'findALlList': {
                    'method': 'GET',
                    'url': restRoot + '/form2_details/find_all_list',
                    'isArray': true
                }
            });
        });




