package com.tenduke.client.io;

import java.io.IOException;

/** A suppressed IO exception. A sort-of-a-workaround for missing try-with-resources and exception suppression.
 * 
 *  <p>
 *  Typically {@link #getCause()} returns the original exception and {@link #getSuppressedException()} returns
 *  the suppressed exception.
 * 
 *  <p>
 *  Assume following code:
 *  
 *  <pre>
 *    Throwable mainException = null;
 *    InputStream stream = null;
 *    try {
 *       stream = ...;
 *       // do something here
 *    }
 *    catch (final IOException e) {
 *       mainException = e;
 *       throw (e);
 *    }
 *    finally {
 *       IOUtil.close (stream, mainException);
 *    }
 *  </pre>
 * 
 *  <p>
 *  In this example, the {@code IOUtil.close()} may throw a SuppressedIOException, which would return
 *  {@code mainException} from {@code getCause()} and {@code getSuppressedException} would return the
 *  IOException caused by the {@code close()}.
 * 
 */
public class SuppressedIOException extends IOException {
    
    private final IOException _suppressedException;


    /** Constructs new instance.
     * 
     */
    public SuppressedIOException() {
        super ();
        _suppressedException = null;
    }
    

    /** Constructs a new instance.
     * 
     * @param message -
     * @param cause -
     * @param suppressedException -
     */

    public SuppressedIOException(
            final String message,
            final Throwable cause,
            final IOException suppressedException
    ) {
        super(message, cause);
        _suppressedException = suppressedException;
    }


    /** Returns the suppressed exception.
     * 
     *  @return -
     */
    public IOException getSuppressedException() {
        return _suppressedException;
    }
    
}
