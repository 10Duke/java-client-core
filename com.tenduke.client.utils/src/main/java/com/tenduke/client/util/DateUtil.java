package com.tenduke.client.util;

import java.text.DateFormat;

/** Date-related utilities.
 * 
 */

public class DateUtil {

    /** Creates {@link DateFormat} suitable for parsing/formatting dates in the API.
     * 
     *  <p>
     *  Currently the default is {@link ISO8601DateFormat}.
     * 
     * @return -
     */
    public static DateFormat createDefaultDateFormatter () {
        //
        return new ISO8601DateFormat ();
    }
    

    /** Prevents the class from being instantiated.
     */
    private DateUtil() {}
    

}
