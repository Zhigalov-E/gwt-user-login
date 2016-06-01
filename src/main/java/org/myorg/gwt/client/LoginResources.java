package org.myorg.gwt.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;


public interface LoginResources extends ClientBundle {

    @Source("org/myorg/gwt/client/Login.css")
    MyCss style();

    interface MyCss extends CssResource {
        String blackText();

        String redText();

        String loginButton();

        String box();

        String background();

        String loginForm();

    }
}