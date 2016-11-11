package com.tenduke.client;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

interface WidgetApi {

    @GET(value = "api/widgets")
    public Call<List<Widget>> findWidgets();
    
}
