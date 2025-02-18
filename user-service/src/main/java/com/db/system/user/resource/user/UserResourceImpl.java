package com.db.system.user.resource.user;

import com.db.system.data.model.user.User;
import com.db.system.resource.user.UserResource;
import com.db.system.user.service.UserDetailsService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class UserResourceImpl implements UserResource {
    private static final Logger LOG = LoggerFactory.getLogger(UserResourceImpl.class);

    private final UserDetailsService userDetailsService;

    @Inject
    public UserResourceImpl(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Response getUserToken(String name) {
        LOG.info("getUserToken with name: {}", name);

        String token = userDetailsService.getUserToken(name);
        if(token != null) {
            return Response.ok(token).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response getUserByToken(String userToken) {
        LOG.info("isValidToken with userToken: {}", userToken);

        User user = userDetailsService.getUserByToken(userToken);
        if(user != null) {
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
