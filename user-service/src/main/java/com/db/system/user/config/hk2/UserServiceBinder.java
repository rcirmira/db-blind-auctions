package com.db.system.user.config.hk2;

import com.db.system.user.resource.user.UserResource;
import com.db.system.user.resource.user.UserResourceImpl;
import com.db.system.user.service.UserDetailsService;
import com.db.system.user.service.UserDetailsServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class UserServiceBinder extends AbstractBinder {
    @Override
    protected void configure() {
        // services
        bind(UserDetailsServiceImpl.class).to(UserDetailsService.class);

        // resources
        bind(UserResourceImpl.class).to(UserResource.class);
    }
}

