<%-- 
    Document   : index
    Created on : Jan 20, 2018, 3:10:16 PM
    Author     : webdesign
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="stars">
    <head>
        <base href="/stars/" target="_blank">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">

        <!--Libraries-->
        <script src="${pageContext.request.contextPath}/webjars/jquery/1.12.0/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/webjars/angularjs/1.5.3/angular.min.js"></script>  
        <script src="${pageContext.request.contextPath}/webjars/nervgh-angular-file-upload/2.1.1/angular-file-upload.min.js"></script>
        <script src="${pageContext.request.contextPath}/webjars/angular-animate/1.5.3/angular-animate.js"></script>  
        <script src="${pageContext.request.contextPath}/webjars/angular-resource/1.2.28/angular-resource.js"></script>  
        <script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>  
        <script src="${pageContext.request.contextPath}/webjars/angular-ui-router/0.2.15/angular-ui-router.js"></script>  
        <script src="${pageContext.request.contextPath}/webjars/angular-ui-bootstrap/1.2.5/ui-bootstrap-tpls.min.js"></script>
        <script src="${pageContext.request.contextPath}/webjars/underscorejs/1.5.1/underscore.min.js"></script>        
        <!--<script src="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.min.js"></script>--> 
        <script src="${pageContext.request.contextPath}/js/lib/angular-google-map.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/angular-simple-logger.js"></script>        
        <!--        <script src="https://maps.googleapis.com/maps/api/js?libraries=geometry,places&region=IN&key=AIzaSyBEYDdJx8BB-fQa_H2qKoUO84oUrH8BFQE"></script>
                <script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>-->
        <script src="${pageContext.request.contextPath}/js/lib/fusioncharts.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/fusioncharts.charts.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/fusioncharts.theme.fint.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/ng-google-chart.js"></script>
<!--        <script src="${pageContext.request.contextPath}/js/lib/calcumateemi.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/googleMap.js"></script>-->
        <script src="${pageContext.request.contextPath}/js/lib/jquery.flexslider-min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/scripts.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/wow.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/lodash.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/angular.filter.min.js"></script>
        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.4/lodash.min.js"></script>-->
        <script src="${pageContext.request.contextPath}/js/lib/angular-bootstrap-lightbox.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/ngComboDatePicker.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/lib/ngComboDatePicker.js"></script>

        <!--Constants-->
        <script>
            angular.module("stars.constants", [])
                    .constant('contextPath', '${pageContext.request.contextPath}')
                    .constant('restRoot', '${pageContext.request.contextPath}/rest')
                    .constant('templateRoot', '${pageContext.request.contextPath}/templates')
                    .constant('imageRoot', '${pageContext.request.contextPath}/images')
                    .constant('paginationLimit', 10);
        </script>

        <!-- According to Spacewood UI -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Spacewood is India's leading premium brand and manufacturer of Modular Furniture. We are a one stop solution for home and office needs.">
        <meta name="description" content="Spacewood is India’s leading premium brand and manufacturer of Modular Furniture. We are a one stop solution for home and office needs.">
        <meta name="author" content>
        <link rel="canonical" href="https://spacewood.in">
        <meta property="og:locale" content="en_US">
        <meta property="og:locale" content="website">
        <meta property="og:title" content="Modular Kitchens, Wardrobes, Living Room, Bedroom, Interior Designers - Spacewood.in">
        <meta property="og:description" content="Spacewood is India’s leading premium brand and manufacturer of Modular Furniture. We are a one stop solution for home and office needs.">
        <meta property="og:url" content="https://spacewood.in/">
        <meta property="og:site_name" content="Modular Kitchens, Wardrobes, Living Room, Bedroom Interior Designers - Spacewood.in - Spacewood">
        <meta name="twitter:card" content="summary">
        <meta name="twitter:description" content="Spacewood is India’s leading premium brand and manufacturer of Modular Furniture. We are a one stop solution for home and office needs.">
        <meta name="twitter:title" content="Modular Kitchens, Wardrobes, Living Room, Bedroom Interior Designers - Spacewood.in">
        <meta name="generator" content="WordPress 4.8.2">

        <!-- CSS Files -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style1.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style2.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
        <!--<link href="file:///C:/Users/webdesign/Desktop/DigitalizationSP/Style/font-awesome.min.css" rel="stylesheet">-->
        <link href="https://fonts.googleapis.com/css?family=Ubuntu:300,300i,400,400i,500,500i,700,700i" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Ubuntu:300,300i,400,400i,500,500i,700,700i" rel="stylesheet">
        <link rel="dns-prefetch" href="//s.w.org">
        <link rel="stylesheet" id="contact-form-7-group-css" href="https://spacewood.in/wp-content/plugins/bwp-minify/min/?f=wp-content/plugins/contact-form-7/includes/css/styles.css,wp-content/plugins/wp-store-locator/css/styles.css" type="text/css" media="all">
        <link rel="https://api.w.org/" href="https://spacewood.in/wp-json/">
        <link rel="EditURI" type="application/rsd+xml" title="RSD" href="https://spacewood.in/xmlrpc.php?rsd">
        <link rel="wlwmanifest" type="application/wlwmanifest+xml" href="https://spacewood.in/wp-includes/wlwmanifest.xml">
        <link rel="shortlink" href="https://spacewood.in/">
        <link rel="alternate" type="application/json+oembed" href="https://spacewood.in/wp-json/oembed/1.0/embed?url=https%3A%2F%2Fspacewood.in%2F">
        <link rel="alternate" type="text/xml+oembed" href="https://spacewood.in/wp-json/oembed/1.0/embed?url=https%3A%2F%2Fspacewood.in%2F&amp;format=xml">

        <!-- Internal CSS -->
        <style type="text/css">
            img.wp-smiley,
            img.emoji {
                display: inline !important;
                border: none !important;
                box-shadow: none !important;
                height: 1em !important;
                width: 1em !important;
                margin: 0 .07em !important;
                vertical-align: -0.1em !important;
                background: none !important;
                padding: 0 !important;
            }</style>

        <style type="text/css">
            .apply { color: black !important;
            }

            a {
                text-decoration: none;
                color: black;
            }
            footer a {
                text-decoration: none;
                color: #fff;
            }
            .screen-reader-response
            {
                display:none;
            }
            a:hover {
                text-decoration: none;
                color: #FBBB00;
            }
            img {
                border: none;
            }
            h2 {
                font: bold 14px/110% Arial, Helvetica, sans-serif;
                margin: 0 0 30px;
                padding: 0 0 20px;
                color: #999;
                border-bottom: solid 1px #ccc;
                clear: both;
            }
            h3 {
                font: bold 16px/120% Arial, Helvetica, sans-serif;
                margin: 0;
            }
            #pagewrap {
                width: 948px;
                margin: 0 auto;
            }
            .middle>.item {
                width: 100%;
                margin: 0 0 0px 0px;
                float: left;
            }
            .middle>.item.first {
                clear: left;
                margin-left: 0;
            }
            .middle>.item img {
                opacity:0;
            }
            .middle>.item img {
                filter: gray;
                /* IE6-9 */
                filter: grayscale(100%);
                /* Microsoft Edge and Firefox 35+ */
                -webkit-filter: grayscale(1);
            }
            .middle>.carousel-control.left {
                left: 0px;
                background-image: url(../images/left-arrow.png);
                background-repeat: no-repeat;
                top: 50%;
            }
            .middle>.carousel-control.right {
                right: 0px;
                background-image: url(../images/right-arrow.png);
                background-repeat: no-repeat;
                top: 50%;
            }
            .middle>.item img:hover {
                filter:0;
                /* IE6-9 */
                filter: grayscale(0%);
                /* Microsoft Edge and Firefox 35+ */
                -webkit-filter: grayscale(0);
            }
        </style>
        <style>
            /* The message box is shown when the user clicks on the password field */
            #message {
                display:none;
                background: #f1f1f1;
                color: #000;
                position: relative;
                padding: 20px;
                margin-top: 10px;
            }

            #message p {
                padding: 10px 35px;
                font-size: 18px;
            }

            /* Add a green text color and a checkmark when the requirements are right */
            .valid {
                color: green;
            }

            .valid:before {
                position: relative;
                left: -35px;
                content: "✔";
            }

            /* Add a red text color and an "x" when the requirements are wrong */
            .invalid {
                color: red;
            }

            .invalid:before {
                position: relative;
                left: -35px;
                content: "✖";
            }
        </style>
        <style>            
            .container {
                position: relative;
                width: 50%;
            }

            .image {
                display: block;
                width: 100%;
                height: 350px;
            }

            .profile-image {
                display: block;
                width: 100%;
                height: 150px;
            }

            .overlay {
                position: absolute;
                top: 0;
                bottom: 0;
                left: 0;
                right: 0;
                height: 100%;
                width: 100%;
                opacity: 0;
                transition: .5s ease;
                background-color: #008CBA;
            }

            .container:hover .overlay {
                opacity: 1;
            }

            .text {
                color: white;
                font-size: 20px;
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                -ms-transform: translate(-50%, -50%);
            }

        </style>
        <!--CSS For Slider in KRA Form 1 -->
        <style>
            .slidecontainer {
                width: 100%;
            }

            .slider {
                -webkit-appearance: none;
                width: 100%;
                height: 15px;
                border-radius: 5px;
                background: #d3d3d3;
                outline: none;
                opacity: 0.7;
                -webkit-transition: .2s;
                transition: opacity .2s;
            }

            .slider:hover {
                opacity: 1;
            }

            .slider::-webkit-slider-thumb {
                -webkit-appearance: none;
                appearance: none;
                width: 25px;
                height: 25px;
                border-radius: 50%;
                background: #4CAF50;
                cursor: pointer;
            }

            .slider::-moz-range-thumb {
                width: 25px;
                height: 25px;
                border-radius: 50%;
                background: #4CAF50;
                cursor: pointer;
            }
        </style>

        <!-- Internal JS Files -->
<!--        <script>
            (function (i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function () {
            (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date();
            a = s.createElement(o),
                    m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
            })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');
            ga('create', 'UA-41214428-1', 'auto');
            ga('send', 'pageview');
        </script>-->

        <script type="application/ld+json">
            {"@context":"http:\/\/schema.org","@type":"WebSite","@id":"#website","url":"https:\/\/spacewood.in\/","name":"Modular Kitchens, Wardrobes, Living Room, Bedroom Interior Designers - Spacewood.in - Spacewood","potentialAction":{"@type":"SearchAction","target":"https:\/\/spacewood.in\/?s={search_term_string}","query-input":"required name=search_term_string"}}
        </script>

        <!-- External JS Libraries -->
        <!--<script async="" src="https://www.google-analytics.com/analytics.js"></script>-->
        <script href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script href="${pageContext.request.contextPath}/js/scrollbar.min.js"></script>
        <script href="${pageContext.request.contextPath}/js/emojisetting.js"></script>
        <script src="https://spacewood.in/wp-includes/js/twemoji.js?ver=4.8.2" type="text/javascript" defer=""></script>
        <script src="https://spacewood.in/wp-includes/js/wp-emoji.js?ver=4.8.2" type="text/javascript" defer=""></script>
        <script type="text/javascript" src="https://spacewood.in/wp-includes/js/jquery/jquery.js?ver=1.12.4"></script>
        <script type="text/javascript" src="https://spacewood.in/wp-includes/js/jquery/jquery-migrate.js?ver=1.4.1"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>

        <!-- Icons & Images -->
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/icons/spacew.ico">

        <title>Modular Kitchens, Wardrobes, Living Room, Bedroom Interior Designers - Spacewood.in</title>
        <!-- Spacewood UI Ends-->       

        <!--Stylesheet-->
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.5/css/bootstrap.min.css" media="all"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/modal-override.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/corporate_site.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/angular-bootstrap-lightbox.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/w3.css"/>

        <!--JavaScript-->
        <script src="${pageContext.request.contextPath}/js/app.js"></script>
        <script src="${pageContext.request.contextPath}/js/auth.js"></script>
        <script src="${pageContext.request.contextPath}/js/filters.js"></script>
<!--        <script src="${pageContext.request.contextPath}/js/directives/datetime_picker.js"></script>
        
        <!--states-->
        <script src="${pageContext.request.contextPath}/js/states.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/admin.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/user.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/kra.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/profile.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/evaluate.js"></script>
<!--        <script src="${pageContext.request.contextPath}/js/states/employee.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/notification.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/party.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/segment.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/sale_type.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/order.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/department.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/reason.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/kitchen_component.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/raw_material.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/standard_carcass_dimension.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/color.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/standard_carcass_price.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/color_constraint.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/finish_price.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/section_profile.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/carcass_subtype.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/panel_material_thickness.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/handle_price.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/shutter_finish_price.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/user.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/shutter_handle_mapping.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/filler_finish_price.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/drawer_handle_mapping.js"></script>
        <script src="${pageContext.request.contextPath}/js/states/rate_contract.js"></script>-->

        <!--Services-->
        <script src="${pageContext.request.contextPath}/js/services/user_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/employee_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/kra_details_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/form2_details_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/additional_details_service.js"></script>
<!--        <script src="${pageContext.request.contextPath}/js/services/notification_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/party_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/segment_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/sale_type_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/order_head_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/department_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/reason_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/kitchen_component_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/raw_material_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/order_details_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/standard_carcass_dimension_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/color_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/standard_carcass_price_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/carcass_order_details_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/color_constraint_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/finish_price_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/section_profile_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/carcass_subtype_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/panel_material_thickness_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/panel_order_details_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/filler_order_details_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/pelmet_order_details_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/cornice_order_details_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/handle_price_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/handle_order_details_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/shutter_order_details_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/shutter_finish_price_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/drawer_order_details_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/shutter_handle_mapping_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/filler_finish_price_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/drawer_handle_mapping_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/rate_contract_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/rate_contract_detail_service.js"></script>
        <script src="${pageContext.request.contextPath}/js/services/erp_integration_service.js"></script>-->

    </head>
    <body style="font-family: 'Roboto', sans-serif;">
        <div data-ui-view></div>
        <!--<h1>Hello World!</h1>-->
    </body>
    <!--    <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
            <h1>Hello World!</h1>
        </body>-->
</html>
