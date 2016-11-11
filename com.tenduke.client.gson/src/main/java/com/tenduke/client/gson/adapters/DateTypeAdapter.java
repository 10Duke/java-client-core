package com.tenduke.client.gson.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tenduke.client.util.DateUtil;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/** A Gson type adapter for handling dates.
 * 
 */
public class DateTypeAdapter implements JsonDeserializer<Date>, JsonSerializer<Date> {

    private final DateFormat _formatter = DateUtil.createDefaultDateFormatter();

    /** {@inheritDoc}
     * 
     * @param element {@inheritDoc}
     * @param type {@inheritDoc}
     * @param jdc {@inheritDoc}
     * @return {@inheritDoc}
     * @throws JsonParseException If the given JsonElement does not parse as date.
     */
    @Override
    public Date deserialize(
            final JsonElement element,
            final Type type,
            final JsonDeserializationContext jdc) throws JsonParseException {
        //
        synchronized (_formatter) {
            
            try {
                return _formatter.parse(element.getAsString());
            }
            catch (final ParseException e) {
                throw new JsonParseException ("Invalid date " + element.toString(), e);
            }

        }
    }
    
    /** {@inheritDoc}
     * 
     * @param t {@inheritDoc}
     * @param type {@inheritDoc}
     * @param jsc {@inheritDoc}
     * @return {@inheritDoc}
     */

    @Override
    public JsonElement serialize(
            final Date t,
            final Type type,
            final JsonSerializationContext jsc) {
        //
        synchronized (_formatter) {
            
            return new JsonPrimitive(_formatter.format(t));
            
        }
    }

    
}
