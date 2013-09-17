package com.github.stefanliebenberg;

import com.github.stefanliebenberg.resources.SpikeResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.config.FilterBuilder;
import org.atmosphere.cpr.AtmosphereFramework;
import org.atmosphere.cpr.AtmosphereServlet;
import org.eclipse.jetty.servlets.CrossOriginFilter;


public class SpikeService extends Service<SpikeConfiguration> {
    public static void main(String[] args) throws Exception {
        new SpikeService().run(args);
    }

    @Override
    public void initialize(Bootstrap<SpikeConfiguration> bootstrap) {
        bootstrap.setName("spike");
//        bootstrap.addBundle(new AssetsBundle());
    }


    public void initializeAtmosphere(SpikeConfiguration configuration,
                                     Environment environment) {
        FilterBuilder fconfig = environment.addFilter(CrossOriginFilter.class, "/spike");
        fconfig.setInitParam(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        AtmosphereServlet atmosphereServlet = new AtmosphereServlet();
        AtmosphereFramework framework = atmosphereServlet.framework();
        framework.addInitParameter("com.sun.jersey.config.property.packages", "com.github.stefanliebenberg.atmosphere");
        framework.addInitParameter("org.atmosphere.websocket.messageContentType", "application/json");
        // atmosphereServlet.framework().addInitParameter("org.atmosphere.cpr.broadcastFilterClasses", "com.example.helloworld.filters.BadWordFilter");
        environment.addServlet(atmosphereServlet, "/chat/*");
    }

    @Override
    public void run(SpikeConfiguration configuration,
                    Environment environment) {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new SpikeResource(template, defaultName));

    }

}
