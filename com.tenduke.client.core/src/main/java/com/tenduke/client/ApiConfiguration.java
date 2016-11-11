package com.tenduke.client;

import okhttp3.OkHttpClient;
import retrofit2.Converter;


/** Configuration of an API.
 *
 *  <p>
 *  10Duke services are often deployed on cloud or hosted on local datacenters. For this reason a single
 *  Swagger API cannot describe the URL. The {@code baseUrl} property is used to configure the deployment URL
 *  of the service. The {@code baseUrl} overrides the properties "{@code scheme}", "{@code host}" and
 *  "{@code basePath}" of the Swagger JSON.
 *  
 *  <p>
 *  The implementation holds an {@code OkHttpClient}. The best practice is to share the {@code OkHttpClient}
 *  (see OkHttpClient javadoc). You can do this by first creating the {@code OkHttpClient} and then
 *  constructing the ApiConfiguration. Later, pass the ApiConfiguration to either the {@link ApiProviderRegistry}
 *  or to {@link BaseApiProvider} (if you want to handle the ApiProviders yourself).
 * 
 *  <p>
 *  To build a default {@code OkHttpClient} client, you can use {@link com.tenduke.client.retrofit.RetrofitUtil#buildInitialClient() }.
 * 
 */
public class ApiConfiguration {

    private final String _baseUrl;
    private final OkHttpClient _client;
    private final Converter.Factory _converterFactory;

    /** Constructs new instance.
     * 
     * @param baseUrl baseUrl, must end in slash.
     * @param converterFactory converted factory to use.
     * @param client client to use
     */
    public ApiConfiguration (
            /*@NonNull*/ final String baseUrl,
            /*@NonNull*/ final Converter.Factory converterFactory,
            /*@NonNull*/ final OkHttpClient client) {
        //
        _baseUrl = baseUrl;
        _client = client;
        _converterFactory = converterFactory;
    }

    
    /** Returns the base URL.
     * 
     * @return -
     */
    public /*@NonNull*/ String getBaseUrl() {
        return _baseUrl;
    }


    /** Returns the client.
     * 
     * @return -
     */
    public /*@NonNull*/ OkHttpClient getClient() {
        return _client;
    }
    
    
    /** Returns the converter factory.
     * 
     * @return -
     */
    public /*@NonNull*/ Converter.Factory getConverterFactory() {
        return _converterFactory;
    }

}
