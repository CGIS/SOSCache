package org.cgis.dev.SOSUtility.Decoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by zil on 2016/9/21.
 */
public class TimezoneHelper {

    public static Date toTimezone(String time) throws ParseException {
        if( time == null || time.equals("") )
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        return sdf.parse(time);
    }
}
