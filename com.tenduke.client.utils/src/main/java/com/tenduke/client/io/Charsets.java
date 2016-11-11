package com.tenduke.client.io;

import java.nio.charset.Charset;

/** Some character sets, for those environments which don't yet have java.nio.charset.StandardCharsets.
 */

public class Charsets {

    /** UTF-8 */
    public static final Charset UTF8 = Charset.forName("UTF-8");
    
    
    /** Prevents instantiating the class.
     */

    private Charsets () {}
    
}
