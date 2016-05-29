package org.myorg.gwt.server.dao;

import org.myorg.gwt.server.entity.User;

public interface UserDAO {
    User getUserByLogin(String login);
}
