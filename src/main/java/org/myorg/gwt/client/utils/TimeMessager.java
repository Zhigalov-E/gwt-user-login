package org.myorg.gwt.client.utils;

import com.google.gwt.core.client.GWT;
import org.myorg.gwt.client.i18n.Message;

import java.util.Date;


public class TimeMessager {
    private static TimeMessager instance;
    private final Message messageResourse;

    public static TimeMessager getInstance() {
        if (instance == null) {
            instance = new TimeMessager();
        }
        return instance;
    }

    private TimeMessager() {
        this.messageResourse = GWT.create(Message.class);
    }

    public String getMessageResouse(Date date) {
        TimeBorder.Border dayPart = TimeBorder.getBorder(date);
        String message = "";
        switch (dayPart) {
            case MORNING:
                message = messageResourse.goodMorning();
                break;
            case DAY:
                message = messageResourse.goodDay();
                break;
            case EVENING:
                message = messageResourse.goodEvening();
                break;
            case NIGHT:
                message = messageResourse.goodNight();
                break;
        }
        return message;
    }


}
