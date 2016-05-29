package org.myorg.gwt.client.i18n;


import com.google.gwt.i18n.client.LocalizableResource;
import com.google.gwt.i18n.client.Messages;

@LocalizableResource.DefaultLocale("en")
public interface AppMessages extends Messages {
    String login();

    String password();

    String submitBtn();

    String logoutProblem();

    String loginOrPwd2Short();

    String login2Short(String length);

    String password2Short(String length);

    String accessDenied();

    String userGreeting(String greeting, String name);

    String logout();
}
