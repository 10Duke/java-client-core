package com.tenduke.client;


/** Interface for providers which create API implementations based on interfaces.
 *
 *  @param <T> Type of interface to provide.
 */
public interface ApiProvider <T> {
    
    /** Creates an API implementation based on {@code baseUrl} and credentials.
     *  
     *  <p>
     *  The implementations can be shared as long as the {@code baseUrl} and credentials
     *  stay the same.
     *
     *  <p>
     *  For example in Android app, this method is usually called only once (after logon),
     *  as the credentials likely stay the same.
     *
     *  <p> 
     *  If used from web-application backend, it is possible that a new API needs to
     *  provided for every HTTP-request, if the credentials change. Of course, if the request
     *  does multiple API calls with same credentials, the same API implementation can be used.
     *  
     *  @param credentials Credentials to use on the API (optional)
     *  @return Implementation of the API, ready to use.
     */
    /*@NonNull*/ T provide (/*@Nullable*/ ApiCredentials credentials);
    
}
