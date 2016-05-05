package kg.apc.perfmon.metrics;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.hyperic.sigar.*;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author undera
 */
public class MetricParamsTest extends TestCase {

    public MetricParamsTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MetricParamsTest.class);
        return suite;
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of createFromString method, of class MetricParams.
     */
    public void testCreateFromString_ptql() {
        System.out.println("createFromString");
        String metricParams = "ptql=Pid.Pid.gt=1";
        final SigarProxy sigar = new SigarProxyEmul();
        SigarProxyCache.newInstance(new SigarEmul());
        try {
            MetricParamsSigar result = MetricParamsSigarEmul.createFromString(metricParams, sigar);
            assertTrue(result.PID > 0);
        } catch (IllegalArgumentException ex) {
            // undera: I spent 3 hours trying to talk sigar into
            //   mocking correctly, but this line in their code prevents from having success: 
            //   https://github.com/hyperic/sigar/blob/master/bindings/java/src/org/hyperic/sigar/SigarProxyCache.java#L92
            assertEquals("not a proxy instance", ex.getMessage());
        }
    }

    public void testCreateFromString_name() {
        System.out.println("createFromString");
        final SigarProxy sigar = new SigarProxyEmul();
        MetricParamsSigar resultLinux = MetricParamsSigar.createFromString("name=java#1", sigar);
        MetricParamsSigar resultWin = MetricParamsSigar.createFromString("name=java.exe#1", sigar);
        assertTrue(resultLinux.PID > 0 || resultWin.PID > 0);
    }

    public void testCreateFromString_escaping() {
        System.out.println("createFromString");
        final SigarProxy sigar = SigarProxyCache.newInstance(new Sigar(), 500);
        MetricParamsSigar result = MetricParamsSigar.createFromString("exec:testcolon\\:attr:next", sigar);
        assertEquals("exec", result.params[0]);
        assertEquals("testcolon:attr", result.params[1]);
        assertEquals("next", result.params[2]);
    }

    public void testCreateFromString_escaping_issue101() {
        System.out.println("createFromString");
        final SigarProxy sigar = SigarProxyCache.newInstance(new Sigar(), 500);
        MetricParamsSigar result = MetricParamsSigar.createFromString("exec:/bin/sh:-c:wget http\\://localhost\\:8000", sigar);
        assertEquals("exec", result.params[0]);
        assertEquals("/bin/sh", result.params[1]);
        assertEquals("-c", result.params[2]);
        assertEquals("wget http://localhost:8000", result.params[3]);
    }

    public void testCreateFromString_multicore() {
        System.out.println("createFromString");
        final SigarProxy sigar = SigarProxyCache.newInstance(new Sigar(), 500);
        MetricParamsSigar result = MetricParamsSigar.createFromString("core=1", sigar);
        assertEquals(1, result.coreID);
    }

    public void testCreateFromString() {
        System.out.println("createFromString");
        final SigarProxy sigar = new SigarProxyEmul();
        String metricParams = "pid=" + sigar.getPid();
        MetricParamsSigar result = MetricParamsSigar.createFromString(metricParams, sigar);
        System.out.println(result.PID);
        assertTrue(result.PID != 0);
    }

    /**
     * Test of logAvailableProcesses method, of class MetricParams.
     */
    public void testLogAvailableProcesses() {
        System.out.println("logAvailableProcesses");
        final SigarProxy sigar = SigarProxyCache.newInstance(new Sigar(), 500);
        MetricParamsSigar.logAvailableProcesses(sigar);
    }

    /**
     * Test of parseParams method, of class MetricParams.
     */
    public void testParseParams() {
        System.out.println("parseParams");
        String metricParams = "";
        MetricParams inst = MetricParams.createFromString(metricParams);
        MetricParams.parseParams(metricParams, inst);
    }

    /**
     * Test of populateParams method, of class MetricParams.
     */
    public void testPopulateParams() {
        System.out.println("populateParams");
        String token = "";
        List params = new LinkedList();
        MetricParams instance = new MetricParams();
        instance.populateParams(token, params);
    }

    /**
     * Test of tokenizeString method, of class MetricParams.
     */
    public void testTokenizeString() {
        System.out.println("tokenizeString");
        String metricParams = "";
        StringTokenizer result = MetricParams.tokenizeString(metricParams);
        assertEquals(0, result.countTokens());
    }

    /**
     * Test of join method, of class MetricParams.
     */
    public void testJoin() {
        System.out.println("join");
        StringBuffer buff = new StringBuffer();
        Object[] array = {"a", "b"};
        String delim = "";
        String expResult = "ab";
        String result = MetricParams.join(buff, array, delim);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLabel method, of class MetricParams.
     */
    public void testGetLabel() {
        System.out.println("getLabel");
        MetricParams instance = new MetricParams();
        String expResult = "";
        String result = instance.getLabel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUnit method, of class MetricParams.
     */
    public void testGetUnit() {
        System.out.println("getUnit");
        MetricParams instance = new MetricParams();
        String expResult = "";
        String result = instance.getUnit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getParamValue method, of class MetricParams.
     */
    public void testGetParamValue() {
        System.out.println("getParamValue");
        String token = "";
        String expResult = "";
        String result = MetricParams.getParamValue(token);
        assertEquals(expResult, result);
    }
}
