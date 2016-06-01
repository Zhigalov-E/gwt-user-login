package org.myorg.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;

import com.google.gwt.user.client.ui.*;
import org.myorg.gwt.client.i18n.AppMessages;


public class LoginView extends Composite {
    private static final int MIN_PASSWORD_LEN = 2;
    private static final int MIN_LOGIN_LEN = 2;

    private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
    private Boolean tooShort = true;
    @UiField(provided = true)
    final LoginResources res;
    @UiField(provided = true)
    final AppMessages i18n;
    @UiField
    TextBox loginBox;
    @UiField
    TextBox passwordBox;
    @UiField
    Label completionLabel1;
    @UiField
    Label completionLabel2;
    @UiField
    Button buttonSubmit;

    public LoginView() {
        this.res = GWT.create(LoginResources.class);
        this.i18n = GWT.create(AppMessages.class);
        res.style().ensureInjected();
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("loginBox")
    public void onValueChangeLoginBox(ValueChangeEvent<String> valueChangeEvent) {
        if (!checkLoginLength(valueChangeEvent.getValue())) {
            completionLabel1.setText(i18n.login2Short("" + MIN_LOGIN_LEN));
            tooShort = true;
        } else {
            tooShort = false;
            completionLabel1.setText("");
        }
    }

    @UiHandler("passwordBox")
    public void onValueChange(ValueChangeEvent<String> valueChangeEvent) {
        if (!checkPasswordLength(valueChangeEvent.getValue())) {
            tooShort = true;
            completionLabel2.setText(i18n.password2Short("" + MIN_PASSWORD_LEN));
        } else {
            tooShort = false;
            completionLabel2.setText("");
        }
    }

    public void showLogin() {
        RootPanel.get().add(this);
    }

    public void clearLogin() {
        RootPanel.get().clear();
        loginBox.setValue("");
        passwordBox.setValue("");
        this.tooShort = true;
    }

    private boolean checkLoginLength(String login) {
        boolean result = false;
        if (login.length() >= MIN_LOGIN_LEN) {
            result = true;
        }
        return result;
    }

    private boolean checkPasswordLength(String pwd) {
        boolean result = false;
        if (pwd.length() >= MIN_PASSWORD_LEN) {
            result = true;
        }
        return result;
    }

    public static LoginUiBinder getUiBinder() {
        return uiBinder;
    }

    public static void setUiBinder(LoginUiBinder uiBinder) {
        LoginView.uiBinder = uiBinder;
    }

    public LoginResources getRes() {
        return res;
    }

    public AppMessages getI18n() {
        return i18n;
    }

    public Boolean getTooShort() {
        return tooShort;
    }

    public void setTooShort(Boolean tooShort) {
        this.tooShort = tooShort;
    }

    public TextBox getLoginBox() {
        return loginBox;
    }

    public void setLoginBox(TextBox loginBox) {
        this.loginBox = loginBox;
    }

    public TextBox getPasswordBox() {
        return passwordBox;
    }

    public void setPasswordBox(TextBox passwordBox) {
        this.passwordBox = passwordBox;
    }

    public Label getCompletionLabel1() {
        return completionLabel1;
    }

    public void setCompletionLabel1(Label completionLabel1) {
        this.completionLabel1 = completionLabel1;
    }

    public Label getCompletionLabel2() {
        return completionLabel2;
    }

    public void setCompletionLabel2(Label completionLabel2) {
        this.completionLabel2 = completionLabel2;
    }

    public Button getButtonSubmit() {
        return buttonSubmit;
    }

    public void setButtonSubmit(Button buttonSubmit) {
        this.buttonSubmit = buttonSubmit;
    }

    @UiTemplate("LoginView.ui.xml")
    interface LoginUiBinder extends UiBinder<Widget, LoginView> {
    }
}