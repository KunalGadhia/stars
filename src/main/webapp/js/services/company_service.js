/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.services.company", []);
angular.module("stars.services.company")
        .factory('CompanyService', function ($resource, restRoot, contextPath) {            
            return $resource(restRoot + '/company/:id', {'id': '@id'}, {
//                
//                'findByEmployeeId': {
//                    'method': 'GET',
//                    'url': restRoot + '/additional_details/find/employeeId',
//                    'params': {
//                        'employeeId': '@employeeId'
//                    },
//                    'isArray': false
//                },
                'findALlList': {
                    'method': 'GET',
                    'url': restRoot + '/company/find_all_list',
                    'isArray': true
                }
            });
        });




