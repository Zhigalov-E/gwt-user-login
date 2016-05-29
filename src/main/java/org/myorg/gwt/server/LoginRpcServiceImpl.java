package org.myorg.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.myorg.gwt.client.rpc.LoginRpcService;
import org.myorg.gwt.server.dao.UserDAO;
import org.myorg.gwt.server.dao.UserDAOImpl;
import org.myorg.gwt.server.entity.User;
import org.myorg.gwt.server.utils.HibernateUtil;
import org.myorg.gwt.server.utils.PasswordEncryptionService;
import org.myorg.gwt.shared.FieldVerifier;
import org.myorg.gwt.shared.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginRpcServiceImpl extends RemoteServiceServlet implements LoginRpcService {

    private static final Logger logger = Logger.getLogger(LoginRpcServiceImpl.class);

    private final UserDAO userDAO;

    public LoginRpcServiceImpl() {
        this.userDAO = new UserDAOImpl();
/*        org.hsqldb.util.DatabaseManagerSwing.main(
                new String[] { "--url", "jdbc:hsqldb:mem:userDb",
                        "--user", "sa", "--password", "1"});*/
    }


    @Override
    public UserDTO loginServer(String login, String password) {
        //validate username and password
        if (!FieldVerifier.isValidName(login) || !FieldVerifier.isValidName(password)) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
                    "Name must be at least 3 characters long");
            logger.error("Not valid login or password",illegalArgumentException);
            throw illegalArgumentException;
        }
        UserDTO userDTO = new UserDTO();
        User user = userDAO.getUserByLogin(login);
        if(user != null && PasswordEncryptionService.authenticate(password, user.getHashPwd())) {
            logger.info("Success login and password.");
            userDTO.setLoggedIn(true);
            userDTO.setName(user.getName());
            //store the user/session id
            storeUserInSession(userDTO);
        } else {
            logger.warn("Wrong login or password.");
            userDTO.setLoggedIn(false);
        }
        return userDTO;
    }

    @Override
    public UserDTO loginFromSessionServer() {
        return getUserAlreadyFromSession();
    }

    @Override
    public void logout() {
        deleteUserFromSession();
    }


    private UserDTO getUserAlreadyFromSession() {
        logger.info("Check existing user session.");
        UserDTO user = null;
        HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        Object userObj = session.getAttribute("user");
        if (userObj != null && userObj instanceof UserDTO) {
            logger.info("User session not exists.");
            user = (UserDTO) userObj;
        }
        return user;
    }

    private void storeUserInSession(UserDTO user) {
        logger.info("Login: store user session.");
        HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession(true);
        user.setSessionId(session.getId());
        session.setAttribute("user", user);
    }

    private void deleteUserFromSession() {
        logger.info("Logout: delete user session.");
        HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
        HttpSession session = httpServletRequest.getSession();
        session.removeAttribute("user");
    }


}
