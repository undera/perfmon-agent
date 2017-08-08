package kg.apc.perfmon.metrics.jmx;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.management.MBeanServerConnection;

/**
 * @author undera
 */
public class JMXConnectorHelperTest extends TestCase {

    public JMXConnectorHelperTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(JMXConnectorHelperTest.class);
        return suite;
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getServerConnection method, of class JMXConnectorHelper.
     */
//    public void testGetServerConnection() {
//        System.out.println("getServerConnection");
//        String url = "";
//        String user = "";
//        String pwd = "";
//        JMXConnectorHelper instance = new JMXConnectorHelper();
//        try {
//            MBeanServerConnection result = instance.getServerConnection(url, user, pwd);
//            fail();
//        } catch (RuntimeException e) {
//        }
//    }

    public void testGetServerConnection2() {
        System.out.println("getServerConnection");
        String url = "somehost";
        String user = "someuser";
        String pwd = "somepass";
        JMXConnectorHelper instance = new JMXConnectorHelper();
        try {
            MBeanServerConnection result = instance.getServerConnection(url, user, pwd);
            fail();
        } catch (RuntimeException e) {
        }
    }
}
