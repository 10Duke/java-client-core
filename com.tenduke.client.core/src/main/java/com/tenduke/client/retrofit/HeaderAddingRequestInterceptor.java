package com.tenduke.client.retrofit;

import java.io.IOException;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/** A OkHttp-interceptor, which adds given headers to each request.
 * 
 */
public class HeaderAddingRequestInterceptor implements Interceptor {
    
    private final Map<String, String> _headers;


    /** Constructs new instance without headers.
     * 
     */
    public HeaderAddingRequestInterceptor() {
        //
        this (null);
    }

    
    /** Constructs new instance with given headers.
     * 
     *  @param headers Headers to apply to HTTP-requests.
     */
    public HeaderAddingRequestInterceptor(/*@Nullable*/ final Map<String, String> headers) {
        //
        _headers = headers;
    }
    

    /** Interception implementation. Adds contained headers to the request.
     * 
     *  @param chain {@inheritDoc }
     *  @return {@inheritDoc }
     *  @throws IOException {@inheritDoc }
     */
    @Override
    public Response intercept(final Chain chain) throws IOException {
        //
        final Request.Builder builder = chain.request().newBuilder();

        if (_headers != null) {
            for (final Map.Entry<String,String> entry : _headers.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        return chain.proceed(builder.build());
    }


}
