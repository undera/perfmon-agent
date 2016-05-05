package kg.apc.perfmon.client;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import kg.apc.emulators.DatagramChannelEmul;

import java.io.IOException;

public class NIOTransportTest extends TestCase {

    private NIOTransport instance;

    public NIOTransportTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(NIOTransportTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        instance = new NIOTransport();
        DatagramChannelEmul channel = (DatagramChannelEmul) DatagramChannelEmul.open();
        instance.setChannels(channel, channel);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of writeln method, of class AbstractTransport.
     */
    public void testWriteln() throws Exception {
        System.out.println("writeln");
        String line = "";
        instance.writeln(line);
    }

    /**
     * Test of readln method, of class AbstractTransport.
     */
    public void testReadln_0args() {
        System.out.println("readln");
        String expResult = "";
        String result = instance.readln();
        assertEquals(expResult, result);
    }

    /**
     * Test of setChannels method, of class NIOTransport.
     */
    public void testSetChannels() throws IOException {
        System.out.println("setChannels");
        instance.setChannels(null, null);
    }

    /**
     * Test of readln method, of class NIOTransport.
     */
    public void testReadln() {
        System.out.println("readln");
        String expResult = "";
        String result = instance.readln();
        assertEquals(expResult, result);
    }

    /**
     * Test of disconnect method, of class NIOTransport.
     */
    public void testDisconnect() {
        System.out.println("disconnect");
        instance.disconnect();
    }
}
