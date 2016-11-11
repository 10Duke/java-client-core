package com.tenduke.client.gson.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class DateTypeAdapterTest {

    private static final Date Y2K = new Date (946684800000L);
    private static final String Y2K_EET = "2000-01-01T02:00:00.000+0200";
    private static final String Y2K_UTC = "2000-01-01T00:00:00.000Z";
    
    private DateTypeAdapter _adapter;
    private JsonDeserializationContext _jdContext;
    private JsonSerializationContext _jsContext;

    
    @Before
    public void beforeTest () {
        //
        _adapter = new DateTypeAdapter();
        _jdContext = mock (JsonDeserializationContext.class);
        _jsContext = mock (JsonSerializationContext.class);
    }
    
    
    @Test(expected=JsonParseException.class)
    public void testDeserializeException1() {
        //
        _adapter.deserialize(new JsonPrimitive ("2000-13-01T00:00:00.000Z"), Date.class, _jdContext);
    }


    @Test(expected=JsonParseException.class)
    public void testDeserializeException2() {
        //
        _adapter.deserialize(new JsonPrimitive ("2000a01-01T00:00:00.000Z"), Date.class, _jdContext);
    }


    @Test
    public void testDeserializeSuccess() {
        //
        assertEquals (Y2K, _adapter.deserialize(new JsonPrimitive (Y2K_UTC), Date.class, _jdContext));
        assertEquals (Y2K, _adapter.deserialize(new JsonPrimitive (Y2K_EET), Date.class, _jdContext));
    }


    @Test
    public void testSerialize() {
        assertEquals (new JsonPrimitive (Y2K_UTC), _adapter.serialize(Y2K, Date.class, _jsContext));
    }
    
}
