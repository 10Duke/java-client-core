package com.tenduke.client.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Scanner;


/** IO-related utilities.
 * 
 */
public class IOUtil {


    /** Reads an {@link InputStream} into a {@link String}, interpreting the streams as given {@link Charset}.
     * 
     *  <p>
     *  The stream is not closed.
     *
     *  @param stream Stream to convert
     *  @param charset Interpret the stream as this character set. Optional, if null, UTF-8 is used.
     *  @return A string containing the contents of the stream. If stream is empty, empty String is returned.
     *  @throws IOException -
     */

    public static /*@NonNull*/ String streamToString (/*@NonNull*/ final InputStream stream, /*@Nullable*/ final Charset charset) throws IOException {
        // "Stupid Scanner Trick"
        final Scanner scanner = new Scanner (stream, (charset == null ? Charsets.UTF8 : charset).name()).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next () : "";
    }


    /** Reads an {@link InputStream} into a {@link String}, interpreting the stream as UTF-8.
     * 
     *  <p>
     *  Wraps to {@link #streamToString(InputStream, Charset)}.
     *
     *  @param stream Stream to convert
     *  @return A string containing the contents of the stream. If stream is empty, empty String is returned.
     *  @throws IOException -
     */

    public static /*@NonNull*/ String streamToString (/*@NonNull*/ final InputStream stream) throws IOException {
        //
        return streamToString(stream, Charsets.UTF8);
    }


    /** Closes the stream, chaining exceptions.
     *
     *  <p>
     *  This is mostly usable on older SDKs, which don't support try-with-resources. You should normally use
     *  try-with-resources.
     *
     *  Example use:
     *  <pre>
     *      InputStream stream = null;
     *      IOException mainException = null;
     *
     *      try {
     *          stream = ...;
     *          //doSomething with the stream
     *      }
     *      catch (final IOException e) {
     *          mainException = e;
     *          throw e;
     *      }
     *      finally {
     *          IOUtil.close(stream, mainException);
     *      }
     *  </pre>
     *
     *  @param stream {@link InputStream} to close. If {@code null}, does nothing.
     *  @param throwable "main exception", used to chain exceptions. If {@code null}, the actual closing exception is thrown.
     *  @throws IOException if closing failed.
     */

    public static void close (/*@Nullable*/ final InputStream stream, /*@Nullable*/ final Throwable throwable) throws IOException {
        //
        if (stream != null) {
            try {
                stream.close ();
            }
            catch (final IOException e) {
                if (throwable == null) {
                    throw e;
                }
                else {
                    throw new SuppressedIOException ("IOException on closing " + stream.getClass().getName() + " suppressed, see cause. Message of closing: " + e.getMessage(), throwable, e);
                }
            }
        }
    }


    /** Closes an output stream.
     *  
     *  <p>
     *  For details, see {@link #close(java.io.InputStream, java.lang.Throwable) }.
     * 
     *  @param stream the OutputStream to close
     *  @param throwable "main exception", used to chain exceptions. If {@code null}, the actual closing exception is thrown.
     *  @throws IOException if closing failed.
     */

    public static void close (/*@Nullable*/ final OutputStream stream, /*@Nullable*/ final Throwable throwable) throws IOException {
        //
        if (stream != null) {
            try {
                stream.close ();
            }
            catch (final IOException e) {
                if (throwable == null) {
                    throw e;
                }
                else {
                    throw new SuppressedIOException ("IOException on closing " + stream.getClass().getName() + " suppressed, see cause. Message of closing: " + e.getMessage(), throwable, e);
                }
            }
        }
    }

    
    /** Prevents the class from being instantiated.
     */
    private IOUtil() {}
    
    

}
