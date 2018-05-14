/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module("stars.services.employee", []);
angular.module("stars.services.employee")
        .factory('EmployeeService', function ($resource, restRoot, contextPath) {
            return $resource(restRoot + '/employee/:id', {'id': '@id'}, {

                'findByNameLike': {
                    'method': 'GET',
                    'url': restRoot + '/employee/find/user_like',
                    'params': {
                        'name': '@name'
                    },
                    'isArray': true
                },
                'findByNameLikeByCompany': {
                    'method': 'GET',
                    'url': restRoot + '/employee/find/name_like/company',
                    'params': {
                        'companyId': '@companyId',
                        'name': '@name'
                    },
                    'isArray': true
                },
                'findSfplEmployee': {
                    'method': 'GET',
                    'url': restRoot + '/employee/find/sfpl',
                    'params': {
                        'offset': '@offset'
                    },
                    'isArray': true
                },
                'findSosEmployee': {
                    'method': 'GET',
                    'url': restRoot + '/employee/find/sos',
                    'params': {
                        'offset': '@offset'
                    },
                    'isArray': true
                },
                'findEmployeeByCompany': {
                    'method': 'GET',
                    'url': restRoot + '/employee/find/employee/company',
                    'params': {
                        'companyId': '@companyId',
                        'offset': '@offset'
                    },
                    'isArray': true
                },
                'findByCompany': {
                    'method': 'GET',
                    'url': restRoot + '/employee/find/company',
                    'params': {
                        'companyId': '@companyId'                        
                    },
                    'isArray': true
                },
                'findByEmpNumLike': {
                    'method': 'GET',
                    'url': restRoot + '/employee/find/emp_no_like',
                    'params': {
                        'empNo': '@empNo'
                    },
                    'isArray': true
                },
                'findByDepartmentHead': {
                    'method': 'GET',
                    'url': restRoot + '/employee/find/department_head',
                    'params': {
                        'departmentHeadId': '@departmentHeadId'
                    },
                    'isArray': true
                },
                'findByName': {
                    'method': 'GET',
                    'url': restRoot + '/employee/find/name',
                    'params': {
                        'name': '@name'
                    },
                    'isArray': false
                }
            });
        });




