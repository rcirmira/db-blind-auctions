package com.db.system.user.resource.user;

import com.db.system.resource.user.UserResource;
import com.db.system.user.config.database.DatabaseConfig;
import com.db.system.user.dao.UserDao;
import com.db.system.data.model.user.User;
import com.db.system.user.service.JwtService;
import com.db.system.user.service.JwtServiceImpl;
import com.db.system.user.service.UserDetailsService;
import com.db.system.user.service.UserDetailsServiceImpl;
import jakarta.ws.rs.core.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class UserResourceImplTest {
    private UserResource userResource;

    @BeforeMethod
    public void setUp() {
        DatabaseConfig.getDataSource();
        UserDao userDao = new UserDao();
        userDao.insertUser(new User("Adam"));

        JwtService jwtService = new JwtServiceImpl();
        UserDetailsService userDetailsService = new UserDetailsServiceImpl(userDao, jwtService);
        userResource = new UserResourceImpl(userDetailsService);
    }

    @Test
    public void getUserTokenAndSuccessfullyValidateItTest() {
        Response response = userResource.getUserToken("Adam");
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        String token = String.valueOf(response.getEntity());
        response = userResource.getUserByToken(token);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        User user = (User)response.getEntity();
        assertNotNull(user);
        assertEquals(user.getName(), "Adam");
        assertEquals(user.getId(), 1);
    }

    @Test
    public void getUserTokenOfNonExistingUserTest() {
        Response response = userResource.getUserToken("NotExistingName");
        assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void getUserByTokenOfNonExistingTokenTest() {
        Response response = userResource.getUserByToken("eyJhbGciOiJFUzUxMiJ9.eyJzdWIiOiJKb2huIiwiZXhwIjoxNzM5NzY3NzAxLCJpZCI6MX0.ACWfU_2baek5v139J8gFhOC-IEEMO3AaynAe_tWP_weYF6lqfEX605G5l-9AK5aHZdHnqxQriANSgLqmoZ5Ro5-QAE3NWhL85AdYpaS0r8yb5vD1cAayeY01CSaeMJrEK6wqyeAxMz_VT4_ZLPyoW2mNfZFCdJ9OpkBuhVzhRHlmbL7O");
        assertEquals(response.getStatus(), Response.Status.FORBIDDEN.getStatusCode());
    }
}
