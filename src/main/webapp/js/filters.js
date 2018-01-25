/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('stars.filters', [])
        .filter('getById', function () {
            return function (input, id) {                
                var i = 0, len = input.length;
                for (; i < len; i++) {
                    if (+input[i].id === +id) {
                        return input[i];
                    }
                }
                return null;
            };
        })
        .filter('total', function () {
            return function (input) {                
                var i = input instanceof Array ? input.length : 0;
                var a = arguments.length;
                if (a === 1 || i === 0)
                    return i;
                var keys = [];
                while (a-- > 1) {
                    var key = arguments[a].split('.');
                    var property = getNestedPropertyByKey(input[0], key);
                    if (isNaN(property))
                        throw 'filter sumProduct can count only numeric values';
                    keys.push(key);
                }

                var total = 0.00;
                while (i--) {
                    var product = 1;
                    for (var k = 0; k < keys.length; k++)
                        product *= getNestedPropertyByKey(input[i], keys[k]);
                    total += product;
                }
                return total;
                function getNestedPropertyByKey(data, key) {
                    for (var j = 0; j < key.length; j++)
                        data = data[key[j]];
                    return data;
                }
            };
        })
        .filter('monthName', function () {
            return function (monthName) {
                console.log("monthName", monthName);
                switch (monthName) {
                    case 1 :
                        //console.log("monthNamecase",monthName);
                        return "January";
                        break;
                    case 2 :
                        return "February";
                        break;
                    case 3:
                        return "March";
                        break;
                    case 4:
                        return "April";
                        break;
                    case 5:
                        return "May";
                        break;
                    case 6:
                        return "June";
                        break;
                    case 7:
                        return "July";
                        break;
                    case 8:
                        return "August";
                        break;
                    case 9:
                        return "September";
                        break;
                    case 10:
                        return "October";
                        break;
                    case 11:
                        return "November";
                        break;
                    case 12:
                        return "December";
                        break;
                    default :
                        return "Invalid Month";
                }
            };
        });



