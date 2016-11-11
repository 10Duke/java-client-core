package com.tenduke.client.util;

import java.text.DateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DateUtilTest {
    
    private static final Date Y2K = new Date (946684800000L);
    private static final String Y2K_EET = "2000-01-01T02:00:00.000+0200";
    private static final String Y2K_UTC = "2000-01-01T00:00:00.000Z";

    @Test
    public void testFormatter () throws Exception {
        //
        final DateFormat formatter = DateUtil.createDefaultDateFormatter();
        
        assertEquals (Y2K, formatter.parse (Y2K_UTC));
        assertEquals (Y2K, formatter.parse (Y2K_EET));
        assertEquals (Y2K_UTC, formatter.format (Y2K));
    }

}
