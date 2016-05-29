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
    private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);

    private static int MIN_PASSWORD_LEN = 2;
    private static int MIN_LOGIN_LEN = 2;

    /*
    * @UiTemplate is not mandatory but allows multiple XML templates
    * to be used for the same widget.
    * Default file loaded will be <class-name>.ui.xml
    */
    @UiTemplate("LoginView.ui.xml")
    interface LoginUiBinder extends UiBinder<Widget, LoginView> {

    }

    @UiField(provided = true)
    final LoginResources res;

    @UiField(provided = true)
    final AppMessages i18n;

    public LoginView() {
        this.res = GWT.create(LoginResources.class);
        this.i18n = GWT.create(AppMessages.class);
        res.style().ensureInjected();
        initWidget(uiBinder.createAndBindUi(this));
    }

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

    private Boolean tooShort = true;


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


    public Button getButtonSubmit() {
        return buttonSubmit;
    }

    public Label getCompletionLabel2() {
        return completionLabel2;
    }

    public Label getCompletionLabel1() {
        return completionLabel1;
    }

    public TextBox getPasswordBox() {
        return passwordBox;
    }

    public TextBox getLoginBox() {
        return loginBox;
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
}