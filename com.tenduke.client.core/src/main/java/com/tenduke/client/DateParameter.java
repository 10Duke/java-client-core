package com.tenduke.client;

import com.tenduke.client.util.DateUtil;
import java.text.DateFormat;
import java.util.Date;

/** A Date, which formats itself to ISO-8601 in {@link #toString()}, in UTC.
 * 
 */
public class DateParameter extends Date {
    
    private static final long serialVersionUID = 1L;
    
    private static final DateFormat DATE_FORMATTER = DateUtil.createDefaultDateFormatter();
    
    /** Constructs new DateParameter, same result as {@link java.util.Date#Date()}.
     * 
     */
    public DateParameter () {
        super ();
    }
    
    
    /** Constructs a new DateParameter from a time. Same as {@link java.util.Date#Date(long)}.
     * 
     * @param time -
     */
    public DateParameter (final long time) {
        //
        super (time);
    }


    /** Copy-constructs from given date.
     * 
     * @param fromDate -
     */
    public DateParameter (/*@NonNull*/ final Date fromDate) {
        //
        super (fromDate.getTime ());
    }


    /** {@inheritDoc}
     * 
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        
        synchronized (DATE_FORMATTER) {
            return DATE_FORMATTER.format(this);
        }
        
    }
    
}
