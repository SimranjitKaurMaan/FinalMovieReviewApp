package com.company.abstractions;

import com.company.models.User;
import java.util.Map;

public interface IUserService
{
    /**
     * Add users.
     * @param userName name of the user.
     */
    void addUser(String userName);

    /**
     * Gets the Users map.
     * @return map of users.
     */
    Map<String, User> getUsers();
}
