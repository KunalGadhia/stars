/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.services.additional_details", []);
angular.module("stars.services.additional_details")
        .factory('AdditionalDetailsService', function ($resource, restRoot, contextPath) {            
            return $resource(restRoot + '/additional_details/:id', {'id': '@id'}, {
                
                'findByEmployeeId': {
                    'method': 'GET',
                    'url': restRoot + '/additional_details/find/employeeId',
                    'params': {
                        'employeeId': '@employeeId'
                    },
                    'isArray': false
                },
                'findALlList': {
                    'method': 'GET',
                    'url': restRoot + '/additional_details/find_all_list',
                    'isArray': true
                },
                'findByCommSkill': {
                    'method': 'GET',
                    'url': restRoot + '/additional_details/find_comm_skill',
                    'isArray': true
                },
                'findBySupervisoryDevelopment': {
                    'method': 'GET',
                    'url': restRoot + '/additional_details/find_supervisory_dev',
                    'isArray': true
                },
                'findByTeamBuilding': {
                    'method': 'GET',
                    'url': restRoot + '/additional_details/find_team_building',
                    'isArray': true
                },
                'findByNegotiationSkill': {
                    'method': 'GET',
                    'url': restRoot + '/additional_details/find_negotiation_skill',
                    'isArray': true
                },
                'findByCrm': {
                    'method': 'GET',
                    'url': restRoot + '/additional_details/find_crm',
                    'isArray': true
                },
                'findByPresentationSkill': {
                    'method': 'GET',
                    'url': restRoot + '/additional_details/find_presentation_skill',
                    'isArray': true
                },
                'findByTimeManagement': {
                    'method': 'GET',
                    'url': restRoot + '/additional_details/find_time_management',
                    'isArray': true
                },
                'findByFunctional': {
                    'method': 'GET',
                    'url': restRoot + '/additional_details/find_functional',
                    'isArray': true
                }
            });
        });




