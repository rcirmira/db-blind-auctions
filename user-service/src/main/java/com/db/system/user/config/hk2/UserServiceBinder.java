package com.db.system.user.config.hk2;

import com.db.system.user.dao.UserDao;
import com.db.system.user.resource.user.UserResource;
import com.db.system.user.resource.user.UserResourceImpl;
import com.db.system.user.service.JwtService;
import com.db.system.user.service.JwtServiceImpl;
import com.db.system.user.service.UserDetailsService;
import com.db.system.user.service.UserDetailsServiceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class UserServiceBinder extends AbstractBinder {
    @Override
    protected void configure() {
        // DAOs
        bind(UserDao.class).to(UserDao.class);

        // services
        bind(UserDetailsServiceImpl.class).to(UserDetailsService.class);
        bind(JwtServiceImpl.class).to(JwtService.class);

        // resources
        bind(UserResourceImpl.class).to(UserResource.class);
    }
}

