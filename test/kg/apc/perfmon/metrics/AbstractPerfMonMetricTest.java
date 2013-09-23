package kg.apc.perfmon.metrics;

import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ServiceLoader;

import junit.framework.TestCase;

import org.apache.xmlgraphics.xmp.merge.MergeRuleSet;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author undera
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PerfMonMetricsService.class)
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
		//PerfMonMetricsService mockService = PowerMockito.mock(PerfMonMetricsService.class);
		//PowerMockito.when(mockService.getMetric(Mockito.anyString(), Mockito.any(MetricParamsSigar.class), Mockito.any(SigarProxy.class))).thenReturn(null);
        System.out.println("createMetric");
        String metricType = "cpu";
        String metricParams = "idle";
        SigarProxy sigarProxy = SigarProxyCache.newInstance(new Sigar(), 500);
        Class expResult = CPUTotalMetric.class;
        PowerMockito.stub(PowerMockito.method(PerfMonMetricsService.class, "getMetric")).toReturn(new CPUTotalMetric(sigarProxy, new MetricParamsSigar(sigarProxy)));
        AbstractPerfMonMetric result = AbstractPerfMonMetric.createMetric(metricType, metricParams, sigarProxy);
        assertNotNull(result);
        assertEquals(expResult, result.getClass());
    }
}
