package org.myorg.gwt.server.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.myorg.gwt.server.entity.User;
import org.myorg.gwt.server.utils.HibernateUtil;

public class UserDAOImpl implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    public User getUserByLogin(String login) {
        Session session = null;
        User user = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.createCriteria(User.class, "users")
                    .add(Restrictions.eq("users.login", login)).uniqueResult();
            logger.info("Get user data from DB.");
        } catch (Exception e) {
            logger.error("DB error:", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }
}
