package org.myorg.gwt.client.utils;


import com.google.gwt.i18n.client.DateTimeFormat;

import java.util.Date;

public class TimeBorder {

    public static Border getBorder(final Date current) {


        int hours = Integer.parseInt(DateTimeFormat.getFormat( "HH" ).format( current ));

        Border result;
        if(hours >= 6 && hours < 9 ){
            result = Border.MORNING;
        }
        else if (hours >= 9 && hours < 19 ){
            result = Border.DAY;
        }
        else if (hours >= 19 && hours < 23){
            result = Border.EVENING;
        }
        else {
            result = Border.NIGHT;
        }
        return result;
    }

    public enum Border {
        MORNING, DAY, EVENING, NIGHT
    }

}
