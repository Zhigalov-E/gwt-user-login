package org.myorg.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import org.myorg.gwt.client.rpc.LoginRpcService;
import org.myorg.gwt.client.utils.TimeMessager;
import org.myorg.gwt.shared.UserDTO;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginEndPoint implements EntryPoint {
    // instantiates a logger
    private static final Logger rootLogger = Logger.getLogger(LoginEndPoint.class.getName());

    private LoginView loginView = GWT.create(LoginView.class);
    private HomePageView homeView = GWT.create(HomePageView.class);


    public void onModuleLoad() {
        loginView.getButtonSubmit().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (loginView.getTooShort()) {
                    rootLogger.log(Level.WARNING, "Login or password too short.");
                    Window.alert(loginView.getI18n().loginOrPwd2Short());
                } else {
                    rootLogger.log(Level.INFO, "Send user auth data to server.");
                    sendToServer(loginView.getLoginBox().getValue(), loginView.getPasswordBox().getValue());
                }
            }
        });

        homeView.getLogOut().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                LoginRpcService.Util.getInstance().logout(new AsyncCallback() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        rootLogger.log(Level.SEVERE, "Error with logout operation.");
                        Window.alert(loginView.getI18n().logoutProblem());
                    }

                    @Override
                    public void onSuccess(Object o) {
                        rootLogger.log(Level.INFO, "Logout DONE.");
                        RootPanel.get().clear();
                        loginView.showLogin();
                    }
                });
            }
        });

        String sessionID = Cookies.getCookie("sid");
        if (sessionID == null) {
            loginView.showLogin();
        } else {
            checkWithServerIfSessionIdIsStillLegal();
        }
    }

    private void sendToServer(String login, String password) {
        LoginRpcService.Util.getInstance().loginServer(login, password, new AsyncCallback<UserDTO>() {
            @Override
            public void onSuccess(UserDTO result) {
                if (result.getLoggedIn()) {
                    rootLogger.log(Level.INFO, "Success login operation.");
                    // load the home app page
                    showHomePage(result);
                    //set session cookie for 1 day expiry.
                    String sessionID = result.getSessionId();
                    final long DURATION = 1000 * 60 * 60 * 24 * 1;
                    Date expires = new Date(System.currentTimeMillis() + DURATION);
                    Cookies.setCookie("sid", sessionID, expires, null, "/", false);
                } else {
                    rootLogger.log(Level.WARNING, "Access Denied. Wrong login or password.");
                    Window.alert(loginView.getI18n().accessDenied());
                }
            }

            @Override
            public void onFailure(Throwable caught) {
                rootLogger.log(Level.SEVERE, "Error with login operation.", caught);
                Window.alert(loginView.getI18n().accessDenied());
            }
        });
    }


    public void showHomePage(UserDTO userDTO) {
        loginView.clearLogin();
        String greeting = TimeMessager.getInstance().getMessageResouse(new Date());
        String userGreeting = homeView.getI18n().userGreeting(greeting, userDTO.getName());
        homeView.getUserGreeting().setText(userGreeting);
        // Add to the root panel.
        RootPanel.get().add(homeView);
    }


    private void checkWithServerIfSessionIdIsStillLegal() {
        LoginRpcService.Util.getInstance().loginFromSessionServer(new AsyncCallback<UserDTO>() {
            @Override
            public void onFailure(Throwable caught) {
                rootLogger.log(Level.SEVERE, "Error with check login session.", caught);
                loginView.showLogin();
            }

            @Override
            public void onSuccess(UserDTO result) {
                if (result == null) {
                    loginView.showLogin();
                } else {
                    if (result.getLoggedIn()) {
                        rootLogger.log(Level.INFO, "Go to home page, user session is still valid.");
                        showHomePage(result);
                    } else {
                        rootLogger.log(Level.INFO, "User session has expired.");
                        loginView.showLogin();
                    }
                }
            }

        });
    }

}