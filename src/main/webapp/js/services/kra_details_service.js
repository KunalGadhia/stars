/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.services.kra_details", []);
angular.module("stars.services.kra_details")
        .factory('KraDetailsService', function ($resource, restRoot, contextPath) {            
            return $resource(restRoot + '/kra_details/:id', {'id': '@id'}, {
                
                'findByEmployeeId': {
                    'method': 'GET',
                    'url': restRoot + '/kra_details/find/employeeId',
                    'params': {
                        'employeeId': '@employeeId'
                    },
                    'isArray': true
                },
                'findWeightageByEmployeeId': {
                    'method': 'GET',
                    'url': restRoot + '/kra_details/find/weightage/employeeId',
                    'params': {
                        'employeeId': '@employeeId'
                    },
                    'isArray': false
                }
            });
        });




