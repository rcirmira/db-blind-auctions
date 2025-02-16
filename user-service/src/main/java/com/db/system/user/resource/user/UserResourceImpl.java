package com.db.system.user.resource.user;

import com.db.system.user.data.model.User;
import com.db.system.user.service.UserDetailsService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;

@Singleton
@Path("/api/v1/user")
public class UserResourceImpl implements UserResource {
    private final UserDetailsService userDetailsService;

    @Inject
    public UserResourceImpl(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public User getUserAccountByToken(String userToken) {
        if(userDetailsService.isValidToken(userToken)) {
            return userDetailsService.getUser(userToken);
        }
        throw new WebApplicationException("user token not valid!");
    }
}
