package com.db.system.user.config.hk2;

import com.db.system.user.resource.user.UserResourceImpl;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(JacksonFeature.class); // Enable JSON support

        // Register REST resources implementation
        register(UserResourceImpl.class);

        // Register HK2 dependency injection
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ServiceLocatorUtilities.bind(locator, new UserServiceBinder());

        // Directly register the HK2 binder
        register(new UserServiceBinder());

        // Disable wadl to avoid warning print-outs in the log
        property("jersey.config.server.wadl.disableWadl", true);
    }
}
