package org.myorg.gwt.client;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import org.myorg.gwt.client.i18n.AppMessages;

public class HomePageView extends Composite {
    private static HomePageView.LoginUiBinder uiBinder = GWT
            .create(HomePageView.LoginUiBinder.class);
    @UiField(provided = true)
    final AppMessages i18n;
    @UiField
    Anchor logOut;
    @UiField
    Label userGreeting;

    public HomePageView() {
        this.i18n = GWT.create(AppMessages.class);
        initWidget(uiBinder.createAndBindUi(this));
    }

    public static LoginUiBinder getUiBinder() {
        return uiBinder;
    }

    public static void setUiBinder(LoginUiBinder uiBinder) {
        HomePageView.uiBinder = uiBinder;
    }

    public Label getUserGreeting() {
        return userGreeting;
    }

    public void setUserGreeting(Label userGreeting) {
        this.userGreeting = userGreeting;
    }

    public Anchor getLogOut() {
        return logOut;
    }

    public void setLogOut(Anchor logOut) {
        this.logOut = logOut;
    }

    public AppMessages getI18n() {
        return i18n;
    }

    @UiTemplate("HomePageView.ui.xml")
    interface LoginUiBinder extends UiBinder<Widget, HomePageView> {
    }
}

