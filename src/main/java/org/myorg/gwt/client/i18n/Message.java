package org.myorg.gwt.client.i18n;

import com.google.gwt.i18n.client.LocalizableResource;
import com.google.gwt.i18n.client.Messages;

@LocalizableResource.DefaultLocale("en")
public interface Message extends Messages {
    String goodMorning();
    String goodDay();
    String goodEvening();
    String goodNight();
}