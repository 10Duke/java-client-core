package com.tenduke.client;

import com.tenduke.client.retrofit.HeaderAddingRequestInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


/** Basic implementation of {@link ApiProvider}, which creates working API implementations.
 * 
 *  <p>
 *  This class can be shared and re-used.
 *  
 *  @param <T> Interface to create an API from.
 */
public class BaseApiProvider<T> implements ApiProvider<T> {

    private final Class<T> _apiClass;
    private final ApiConfiguration _configuration;


    /** Constructs new instance from given configuration.
     * 
     *  @param apiClass Interface this provider should provide.
     *  @param configuration The configuration to use.
     */
    public BaseApiProvider (
            /*@NonNull*/ final Class<T> apiClass,
            /*@NonNull*/ final ApiConfiguration configuration) {
        //
        _apiClass = apiClass;
        _configuration = configuration;
    }


    /** Builds an OkHttpClient for given credentials.
     *
     *  <p>
     *  The client is "forked" from the initial client and customized for given credentials.
     *  This way OkHttp is able to share its resources, as recommended.
     *
     *  @param credentials -
     *  @return a forked OkHttpClient instance
     */
    protected /*@NonNull*/ OkHttpClient buildClient( /*@Nullable*/ final ApiCredentials credentials) {
        //
        final OkHttpClient.Builder builder = _configuration.getClient().newBuilder();
        
        if (credentials != null) {
            builder.addInterceptor(new HeaderAddingRequestInterceptor(credentials.getHeaders()));
        }
        
        return builder.build();
    }


    /** {@inheritDoc }
     * 
     *  @param credentials {@inheritDoc }
     */
    @Override
    public /*@NonNull*/ T provide(/*@Nullable*/ final ApiCredentials credentials) {
        //
        final Retrofit.Builder builder = new Retrofit.Builder();

        builder.client(buildClient(credentials));
        builder.baseUrl(_configuration.getBaseUrl());
        builder.addConverterFactory (_configuration.getConverterFactory());

        return builder.build ().create(_apiClass);
    }

}
