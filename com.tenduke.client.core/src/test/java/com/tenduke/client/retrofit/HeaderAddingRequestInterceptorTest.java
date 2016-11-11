package com.tenduke.client.retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HeaderAddingRequestInterceptorTest {
    
    Interceptor.Chain _chain;
    Request _request;
    ArgumentCaptor<Request> _resultingRequest;
    
    @Before
    public void beforeTest () {
        //
        _chain = mock (Interceptor.Chain.class);
        _request = new Request.Builder().url("http://www.example.net").build();
        
        when (_chain.request()).thenReturn(_request);
 
        _resultingRequest = ArgumentCaptor.forClass(Request.class);
    }


    @Test
    public void testInterceptWithHeaders () throws IOException {
        //
        final Map<String, String> headers = new HashMap<> ();
        headers.put ("NAME1", "VALUE1");
        headers.put ("NAME2", "VALUE2");
        
        final Headers actual = execute (new HeaderAddingRequestInterceptor (headers), 2);
        
        for (final String headerName : actual.names()) {

            final List<String> headerValues = actual.values(headerName);
            assertEquals (1, headerValues.size ());
            switch (headerName) {
                case "NAME1":
                    assertEquals ("VALUE1", headerValues.get(0));
                    break;
                case "NAME2":
                    assertEquals ("VALUE2", headerValues.get(0));
                    break;
                default:
                    fail ("Unexpected header " + headerName);
                    break;
            }
        }
    }


    @Test
    public void testInterceptWithNullHeaders () throws IOException {
        //
        execute (new HeaderAddingRequestInterceptor (null), 0);
    }


    @Test
    public void testInterceptWithDefaultConstructor () throws IOException {
        //
        execute (new HeaderAddingRequestInterceptor (), 0);
    }
    

    private Headers execute (
            final HeaderAddingRequestInterceptor interceptor,
            final int expectedNumHeaders
    ) throws IOException
    {
        interceptor.intercept(_chain);

        verify(_chain).proceed (_resultingRequest.capture());
        final Headers actual = _resultingRequest.getValue().headers();

        assertEquals (expectedNumHeaders, actual.size());
        return actual;
    }
    
}
