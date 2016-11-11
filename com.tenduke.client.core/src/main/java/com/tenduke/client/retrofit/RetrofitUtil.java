package com.tenduke.client.retrofit;

import java.io.IOException;
import okhttp3.OkHttpClient;


/** Retrofit-related utilities.
 * 
 */
public class RetrofitUtil {


    /** Builds new initial client, from which custom requests are "forked" from.
     *  
     *  <p>
     *  The initial client built by this method is good for single-user applications.
     * 
     *  @return initial client suitable for single-user applications.
     */
    public static /*@NonNull*/ OkHttpClient buildInitialClient () {
        //
        final OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        
        return builder.build ();
    }


    /** Shuts down the given OkHttpClient.
     * 
     *  <p>
     *  Shutdown is not necessary, but speeds up closing application remarkably.
     * 
     *  @param client the client to shutdown
     */
    public static void shutdown (/*@NonNull*/ final OkHttpClient client) {
        //
        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
        
        try {
            if (client.cache() != null) {
                client.cache().close();
            }
        }
        catch (final IOException e) {
            throw new RuntimeException (e);
        }

    }
    

    /** Prevents the class from being instantiated.
     */
    private RetrofitUtil (){}
    
}
