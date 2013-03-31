package kg.apc.perfmon.metrics;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.hyperic.sigar.SigarProxyEmul;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

/**
 *
 * @author undera
 */
public class MemTotalMetricTest extends TestCase {

    public MemTotalMetricTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MemTotalMetricTest.class);
        return suite;
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getValue method, of class MemTotalMetric.
     */
    public void testGetValue() throws Exception {
        System.out.println("getValue");
        StringBuffer res = new StringBuffer();
        SigarProxy sigar = new SigarProxyEmul();
        MemTotalMetric instance = new MemTotalMetric(sigar, MetricParamsSigar.createFromString("", sigar));
        instance.getValue(res);
    }

    public void testGetValue_all() throws Exception {
        System.out.println("getValue");
        SigarProxy sigar = new SigarProxyEmul();
        for (int n = 0; n < MemTotalMetric.types.length; n++) {
            MetricParamsSigar params = MetricParamsSigar.createFromString(MemTotalMetric.types[n], sigar);
            MemTotalMetric instance = new MemTotalMetric(sigar, params);
            StringBuffer res = new StringBuffer();
            instance.getValue(res);
            System.out.println(MemTotalMetric.types[n] + "=" + res.toString());
        }
    }
}
