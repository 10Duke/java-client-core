package com.tenduke.client.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tenduke.client.gson.adapters.DateTypeAdapter;

/** Gson-related utilities.
 * 
 */
public class GsonUtil {


    /** Creates default Gson for API-access.
     * 
     *  @return Gson instance, which handles the API-requirements.
     */
    public static Gson createDefaultGson () {
        
        return createDefaultBuilder().create ();
        
    }
    

    /** Creates default Gson-builder, pre-configured for the API-access.
     *
     *  <p>
     *  This builder can be used to further customize the Gson.
     * 
     *  @return Gson-builder instance
     */

    public static GsonBuilder createDefaultBuilder () {
        
        return new GsonBuilder ()
                . registerTypeAdapter(java.util.Date.class, new DateTypeAdapter ())
        ;
        
    }
    

    /** Prevent the util from being instantiated.
     * 
     */
    private GsonUtil () {}

}
