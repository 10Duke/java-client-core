package com.tenduke.client;

import java.io.Serializable;
import java.util.Map;

/** Represents credentials to be used with the Apis.
 * 
 */
public interface ApiCredentials extends Serializable {
    
    /** Returns HTTP-headers, needed for authorization.
     * 
     * @return -
     */
    /*@Nullable*/ Map<String, String> getHeaders ();

}
