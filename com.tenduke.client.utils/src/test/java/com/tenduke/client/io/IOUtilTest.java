package com.tenduke.client.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Test;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IOUtilTest {

    private static final String STRING = "Lö\nkokkemoo\npara ply\nkikkerii\nkokkeroo";
    private static final Charset ISO_8859_1 = Charset.forName ("ISO-8859-1");
    
    private InputStream inputStream;
    private OutputStream outputStream;
    private IOException mainIOException;
    private IOException closingIOException;

    @Before
    public void beforeTest () {
        inputStream = mock (InputStream.class);
        outputStream = mock (OutputStream.class);
        mainIOException = new IOException ();
        closingIOException = new IOException();
    }
    
    
    @Test
    public void testStreamToString () throws IOException {
        doTestStreamToString (Charsets.UTF8, null);
        doTestStreamToString (Charsets.UTF8, Charsets.UTF8);
        doTestStreamToString (ISO_8859_1, ISO_8859_1);
    }

    
    @Test
    public void testStreamToStringException () throws IOException {
        try {
            doTestStreamToString (Charsets.UTF8, ISO_8859_1);
            fail ("Should have thrown exception");
        }
        catch (final ComparisonFailure ignoredIntentionally) {
            // Ignored intentionally
        }
    }
    
    
    @Test
    public void testCloseInputStreamWithNullStream () throws Exception {
        // Following ones should not do anything... but not crash either.
        IOUtil.close ((InputStream)null, null);
        IOUtil.close ((InputStream)null, new IOException ());
    }
    
    
    @Test
    public void testCloseInputStream () throws Exception {
        IOUtil.close (inputStream, null);
        
        verify (inputStream).close ();
    }

    
    @Test
    public void testCloseInputStream2 () throws Exception {
        IOUtil.close (inputStream, mainIOException);
        
        verify (inputStream).close ();
    }


    @Test
    public void testCloseInputStreamClosingThrows1 () throws Exception {
        //
        doThrow(closingIOException).when(inputStream).close ();
        try {
            IOUtil.close (inputStream, null);
            fail ("Should have thrown exception");
        }
        catch (final IOException e) {
            assertSame(closingIOException, e);
        }
        
        verify (inputStream).close ();
    }

    @Test
    public void testCloseInputStreamClosingThrows2 () throws Exception {
        //
        doThrow(closingIOException).when(inputStream).close ();
        try {
            IOUtil.close (inputStream, mainIOException);
            fail ("Should have thrown exception");
        }
        catch (final SuppressedIOException e) {
            assertSame (mainIOException, e.getCause());
            assertSame (closingIOException, e.getSuppressedException());
        }
        catch (final Throwable t) {
            fail ("Did not expect exception of type " + t.getClass().getCanonicalName());
        }
        
        verify (inputStream).close ();
    }
    
    
    @Test
    public void testCloseOutputStreamWithNullStream () throws IOException {
        // Following ones should not do anything... but not crash either.
        IOUtil.close ((OutputStream)null, null);
        IOUtil.close ((OutputStream)null, new IOException ());
    }
    

    @Test
    public void testCloseOutputtream () throws Exception {
        IOUtil.close (outputStream, null);
        
        verify (outputStream).close ();
    }

    
    @Test
    public void testCloseOutputtream2 () throws Exception {
        IOUtil.close (outputStream, mainIOException);
        
        verify (outputStream).close ();
    }

    
    @Test
    public void testCloseOutputStreamClosingThrows1 () throws Exception {
        //
        doThrow(closingIOException).when(outputStream).close ();
        try {
            IOUtil.close (outputStream, null);
            fail ("Should have thrown exception");
        }
        catch (final IOException e) {
            assertSame(closingIOException, e);
        }
        
        verify (outputStream).close ();
    }


    @Test
    public void testCloseOutputStreamClosingThrows2 () throws Exception {
        //
        doThrow(closingIOException).when(outputStream).close ();
        try {
            IOUtil.close (outputStream, mainIOException);
            fail ("Should have thrown exception");
        }
        catch (final SuppressedIOException e) {
            assertSame (mainIOException, e.getCause());
            assertSame (closingIOException, e.getSuppressedException());
        }
        catch (final Throwable t) {
            fail ("Did not expect exception of type " + t.getClass().getCanonicalName());
        }
        
        verify (outputStream).close ();
    }



    private void doTestStreamToString (final Charset encodeWith, final Charset decodeWith) throws IOException {
        //
        final InputStream stream = new ByteArrayInputStream (STRING.getBytes (encodeWith));
        assertEquals (STRING, streamToString (stream, decodeWith));
        stream.close ();
    }



    private Object streamToString(final InputStream stream, final Charset charset) throws IOException {
        if (charset == null) {
            return IOUtil.streamToString(stream);
        }
        else {
            return IOUtil.streamToString(stream, charset);
        }
    }
    
    
    
}
