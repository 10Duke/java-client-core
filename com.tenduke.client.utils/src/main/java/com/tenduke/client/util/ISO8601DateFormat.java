package com.tenduke.client.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/** Simple DateFormat, which has basic, limited, support of ISO-8601 dates.
 *
 *  <p>
 *  The 10Duke APIs use ISO8601 date-time format, in UTC zone. This class exists because some
 *  environments (eg. some Androids) do not support {@link SimpleDateFormat} flag X.
 * 
 *  <p>
 *  The supported date format is yyyy-MM-dd'T'HH:mm:ss.SSSX.
 * 
 *  <p>
 *  Formatting normalizes dates to UTC and outputs with appended, eg. 2000-01-01T00:00:00.000Z.
 *  Parsing supports either Z-timezone or the timezone in +-HHMM -format, eg. 2001-01-01T00:00:00.000+0200.
 *  (parsing does NOT support +-HH:MM or +-HH).
 * 
 *  <p>
 *  This class is NOT thread safe.
 */

public class ISO8601DateFormat extends DateFormat {
    
    private static final long serialVersionUID = 1L;

    private static final TimeZone UTC = TimeZone.getTimeZone ("UTC");

    private final SimpleDateFormat _formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private final SimpleDateFormat _parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");


    /** Constructs new instance. Format and timezone are pre-set.
     * 
     */
    public ISO8601DateFormat() {
        //
        _formatter.setTimeZone(UTC);
        _formatter.setLenient(false);
        _parser.setTimeZone(UTC);
        _parser.setLenient(false);
    }


    /** {@inheritDoc}
     * 
     *  @param date {@inheritDoc}
     *  @param toAppendTo {@inheritDoc}
     *  @param fieldPosition {@inheritDoc}
     *  @return {@inheritDoc}
     */

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        //
        return _formatter.format(date, toAppendTo, fieldPosition).append ('Z');
    }


    /** {@inheritDoc}
     * 
     *  @param source {@inheritDoc}
     *  @param pos {@inheritDoc}
     *  @return {@inheritDoc}
     */
    @Override
    public Date parse(String source, ParsePosition pos) {
        //
        return _parser.parse(source.replaceAll ("Z$", "+0000"), pos);
    }
    

    /** This method throws {@link UnsupportedOperationException}: Time-zone cannot be set.
     * 
     * @param zone -
     */
    @Override
    public final void setTimeZone(TimeZone zone) {
        //
        throw new UnsupportedOperationException("Timezone for " + ISO8601DateFormat.class.getSimpleName() + " cannot be changed.");
    }
    
}
