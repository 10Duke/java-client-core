package com.tenduke.client;

import com.google.gson.GsonBuilder;
import com.tenduke.client.retrofit.RetrofitUtil;
import java.util.Arrays;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApiProviderTest {

    private List<Widget> _widgets;
    private MockWebServer _server;
    private OkHttpClient _client;
    private HttpUrl _url;
    private ApiConfiguration _configuration;
    
    @Before
    public void beforeTest () {
        //
        _widgets = Arrays.asList(new Widget ("Widget 1"), new Widget ("Widget 2"), new Widget ("Widget 3"));
        _server = new MockWebServer ();
        _client = RetrofitUtil.buildInitialClient();
        _url = _server.url("");
        _configuration = new ApiConfiguration (_url.toString(), GsonConverterFactory.create(), _client);
    }
    
    @After
    public void afterTest () throws Exception {
        _server.shutdown();
        RetrofitUtil.shutdown(_client);
    }
    
    @Test
    public void testProvide () throws Exception {
        //
        _server.enqueue(new MockResponse().setBody(new GsonBuilder().create().toJson(_widgets)));
        
        final BaseApiProvider<WidgetApi> provider = new BaseApiProvider<> (WidgetApi.class, _configuration);
        final WidgetApi api = provider.provide(new FakeApiCredentials(true));
        
        final Response<List<Widget>> response = api.findWidgets().execute();
        
        assertTrue (response.isSuccessful());
        assertEquals (_widgets, response.body());
        
        RecordedRequest request = _server.takeRequest();
        assertEquals ("Fake zombie/undead", request.getHeader("AUTHORIZATION"));
    }


    @Test
    public void testProvideWithoutCredentials () throws Exception {
        //
        _server.enqueue(new MockResponse().setBody(new GsonBuilder().create().toJson(_widgets)));
        
        final BaseApiProvider<WidgetApi> provider = new BaseApiProvider<> (WidgetApi.class, _configuration);
        final WidgetApi api = provider.provide(null);
        
        final Response<List<Widget>> response = api.findWidgets().execute();
        
        assertTrue (response.isSuccessful());
        assertEquals (_widgets, response.body());
        
        RecordedRequest request = _server.takeRequest();
        assertNull (request.getHeader("AUTHORIZATION"));
    }
    
}
