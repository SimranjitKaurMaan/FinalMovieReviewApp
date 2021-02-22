package com.company.services;

import com.company.abstractions.IUserService;
import com.company.models.User;
import com.company.utilities.Arg;

import java.util.HashMap;
import java.util.Map;

public class UserService implements IUserService
{
    private HashMap<String, User> users;

    public UserService()
    {
        users = new HashMap<>();
    }

    /**
     * Add User.
     * @param userName User name.
     */
    @Override
    public void addUser(String userName) {
        Arg.isNotNullOrWhiteSpace(userName);
        User user = new User(userName);
        users.put(userName, user);
    }

    /**
     *  Gets the Users.
     * @return Map of users.
     */
    @Override
    public Map<String, User> getUsers() {
        return this.users;
    }
}
