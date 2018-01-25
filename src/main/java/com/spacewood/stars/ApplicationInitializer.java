/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author webdesign
 */
public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.getEnvironment().setActiveProfiles("container");
        ctx.setConfigLocation("com.spacewood.stars.config");
        sc.addListener(new ContextLoaderListener(ctx));
        ServletRegistration.Dynamic dispacher = sc.addServlet("spring", new DispatcherServlet(ctx));
        dispacher.setLoadOnStartup(1);
        dispacher.addMapping("/rest/*");
    }
}
