package kg.apc.perfmon.metrics;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

import junit.framework.TestCase;

public class PerfMonMetricsServiceTest extends TestCase {
	
	PerfMonMetricsService service = PerfMonMetricsService.getInstance();
	
	public final void testGetInstance() {
		PerfMonMetricsService instance = PerfMonMetricsService.getInstance();
		assertNotNull(instance);
		assertEquals(service, instance);
	}

	public final void testGetMetric() {
		String metricType = "cpu";
		SigarProxy sigarProxy = SigarProxyCache.newInstance(new Sigar(), 500);
		MetricParamsSigar metricParams = MetricParamsSigar.createFromString("idle", sigarProxy);
		
		AbstractPerfMonMetric metric = service.getMetric(metricType, metricParams, sigarProxy);
		
		assertNotNull(metric);
		assertEquals(CPUTotalMetric.class, metric.getClass());
		System.out.println(metric.getClass());
	}
}
