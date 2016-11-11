package com.tenduke.client;

import com.tenduke.client.retrofit.RetrofitUtil;
import okhttp3.OkHttpClient;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProviderRegistryTest {
    
    private OkHttpClient _client;
    private Converter.Factory _converterFactory;
    
    @Before
    public void beforeTest () {
        //
        _client = RetrofitUtil.buildInitialClient();
        _converterFactory = GsonConverterFactory.create ();
    }
    
    @After
    public void afterTest () throws Exception {
        //
        RetrofitUtil.shutdown(_client);
    }

    @Test
    public void testGetProvider () {
        //
        final ApiConfiguration configuration = new ApiConfiguration("http://localhost/api/", _converterFactory, _client);
        final ApiProviderRegistry registry = new ApiProviderRegistry (configuration);
        
        registry.register (WidgetApi.class, new BaseApiProvider<>(WidgetApi.class, configuration));
        
        assertTrue (WidgetApi.class.isAssignableFrom(registry.provide(WidgetApi.class, null).getClass ()));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetProviderNotFound () {
        //
        final ApiConfiguration configuration = new ApiConfiguration("http://localhost/api/", _converterFactory, _client);
        final ApiProviderRegistry registry = new ApiProviderRegistry (configuration);
        
        registry.register (WidgetApi.class);
        registry.provide(Widget.class, null);
    }
}
