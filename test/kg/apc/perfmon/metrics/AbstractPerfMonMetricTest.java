package kg.apc.perfmon.metrics;

import java.util.ServiceLoader;

import junit.framework.TestCase;

import org.apache.xmlgraphics.xmp.merge.MergeRuleSet;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

/**
 *
 * @author undera
 */
public class AbstractPerfMonMetricTest extends TestCase {

    public AbstractPerfMonMetricTest() {
    }

    /**
     * Test of getValue method, of class AbstractPerfMonMetric.
     */
    public void testGetValue() throws Exception {
        System.out.println("getValue");
        StringBuffer res = null;
        AbstractPerfMonMetric instance = new AbstractPerfMonMetricImpl();
        instance.getValue(res);
    }

    public class AbstractPerfMonMetricImpl extends AbstractPerfMonMetric {

        public AbstractPerfMonMetricImpl() {
            super(SigarProxyCache.newInstance(new Sigar(), 500));

        }

        public void getValue(StringBuffer res) throws SigarException {
        }
    }

    /**
     * Test of createMetric method, of class AbstractPerfMonMetric. 
     */
    public void testCreateMetric() {
        System.out.println("createMetric");
        String metricType = "cpu";
        String metricParams = "idle";
        SigarProxy sigarProxy = SigarProxyCache.newInstance(new Sigar(), 500);
        Class expResult = CPUTotalMetric.class;
        AbstractPerfMonMetric result = AbstractPerfMonMetric.createMetric(metricType, metricParams, sigarProxy);
        assertNotNull(result);
        assertEquals(expResult, result.getClass());
    }
}
