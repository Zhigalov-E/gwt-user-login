package org.myorg.gwt.client.utils;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.junit.client.GWTTestCase;

import java.util.Date;


public class TimeMessagerEngTest extends GWTTestCase {

    private static  final String SOME_TIME =  "12:00:00.000";

    public String getModuleName() {
        return "org.myorg.gwt.MainENJUnit";
    }

    public  void testGetEnMessage() {
        checkMessageForLocale("Good day");
    }

    private void checkMessageForLocale(String expectedMessage) {
        Date date = DateTimeFormat.getFormat("HH:mm:ss.S").parse(SOME_TIME);
        String message = TimeMessager.getInstance().getMessageResouse(date);
        assertEquals(message, expectedMessage);
    }

}
