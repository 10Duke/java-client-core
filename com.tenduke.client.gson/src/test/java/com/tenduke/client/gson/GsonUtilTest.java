package com.tenduke.client.gson;

import com.google.gson.Gson;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class GsonUtilTest {
    
    private static final Date Y2K = new Date (946684800000L);
    private static final String Y2K_EET = "2000-01-01T02:00:00.000+0200";
    private static final String Y2K_UTC = "2000-01-01T00:00:00.000Z";

    @Test
    public void testCreateDefaultGson() {
        //
        final Gson gson = GsonUtil.createDefaultGson();
        
        assertNotNull (gson);
        
        assertEquals (Y2K, gson.fromJson("\"" + Y2K_EET + "\"", Date.class));
        assertEquals (Y2K, gson.fromJson("\"" + Y2K_UTC + "\"", Date.class));
        
        assertEquals ("\"" + Y2K_UTC + "\"", gson.toJson(Y2K));
    }
    
}
