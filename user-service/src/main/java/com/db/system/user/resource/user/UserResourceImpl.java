package com.db.system.user.resource.user;

import com.db.system.user.data.model.User;
import com.db.system.user.service.UserDetailsService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Path("/api/v1/user")
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


        User user = userDetailsService.getUserByName(name);
        if(user != null) {
            // TODO this should be token
            return Response.ok(user).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public boolean isValidToken(String userToken) {
        LOG.info("isValidToken with userToken: {}", userToken);

        // TODO add JWT with some expiry
        return false;
    }
}
