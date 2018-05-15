/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.services.hod_review", []);
angular.module("stars.services.hod_review")
        .factory('HodReviewDetailsService', function ($resource, restRoot, contextPath) {            
            return $resource(restRoot + '/review_hod/:id', {'id': '@id'}, {
                
                'findByEmployeeId': {
                    'method': 'GET',
                    'url': restRoot + '/review_hod/find/employeeId',
                    'params': {
                        'employeeId': '@employeeId'
                    },
                    'isArray': false
                }
//                'findALlList': {
//                    'method': 'GET',
//                    'url': restRoot + '/additional_details/find_all_list',
//                    'isArray': true
//                }
            });
        });




