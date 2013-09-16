package com.github.stefanliebenberg;

import com.github.stefanliebenberg.resources.SpikeResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;


public class SpikeService extends Service<SpikeConfiguration> {
    public static void main(String[] args) throws Exception {
        args = new String[] {
                "server", "spike.yml"
        };
        new SpikeService().run(args);
    }

    @Override
    public void initialize(Bootstrap<SpikeConfiguration> bootstrap) {
        bootstrap.setName("spike");
    }

    @Override
    public void run(SpikeConfiguration configuration,
                    Environment environment) {
        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.addResource(new SpikeResource(template, defaultName));
    }
}
