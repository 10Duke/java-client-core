package com.tenduke.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DateParameterTest {

    private static final Date Y2K = new Date (946684800000L);
    private static final String Y2K_EET = "2000-01-01T02:00:00.000+0200";
    private static final String Y2K_UTC = "2000-01-01T00:00:00.000Z";
    private static final String MASK = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
 
    @Test
    public void testDefaultConstructor () {
        //
        final DateParameter param = new DateParameter ();
        assertTrue (Math.abs (param.getTime() - new Date().getTime ()) < 100); // Close enough...
    }

    @Test
    public void testLongConstructor () {
        //
        final DateParameter actual = new DateParameter (Y2K.getTime());
        assertEquals (Y2K.getTime(), actual.getTime());
        assertEquals (Y2K, actual);
    }

    @Test
    public void testDateConstructor () {
        //
        final DateParameter actual = new DateParameter (Y2K);
        assertEquals (Y2K.getTime(), actual.getTime());
        assertEquals (Y2K, actual);
    }
    
    
    @Test
    public void testToString () throws Exception {
        //
        SimpleDateFormat formatter = new SimpleDateFormat (MASK);
        
        assertEquals (Y2K_UTC, new DateParameter (formatter.parse(Y2K_UTC)).toString());
    }
    
    @Test
    public void testDateParameterOnAnotherTimezone () throws Exception {
        //
        SimpleDateFormat formatter = new SimpleDateFormat (MASK);
        
        assertEquals (Y2K_UTC, new DateParameter (formatter.parse(Y2K_EET)).toString());
    }
    
}
