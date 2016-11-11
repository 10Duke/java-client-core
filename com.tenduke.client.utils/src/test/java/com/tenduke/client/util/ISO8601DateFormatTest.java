package com.tenduke.client.util;

import java.util.Date;
import java.util.TimeZone;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ISO8601DateFormatTest {
    
    private static final Date Y2K = new Date (946684800000L);
    private static final String Y2K_EET = "2000-01-01T02:00:00.000+0200";
    private static final String Y2K_UTC = "2000-01-01T00:00:00.000Z";

    @Test
    public void testFormatter () throws Exception {
        //
        final ISO8601DateFormat formatter = new ISO8601DateFormat ();
        
        assertEquals (Y2K, formatter.parse (Y2K_UTC));
        assertEquals (Y2K, formatter.parse (Y2K_EET));
        assertEquals (Y2K_UTC, formatter.format (Y2K));
    }

    @Test(expected=UnsupportedOperationException.class)
    public void testSetTimezone () throws Exception {
        //
        final ISO8601DateFormat formatter = new ISO8601DateFormat ();
        
        formatter.setTimeZone(TimeZone.getDefault());
    }

}
