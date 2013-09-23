package kg.apc.perfmon.metrics;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import junit.framework.TestCase;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(PerfMonMetricsService.class)
public class PerfMonMetricsServiceTest extends TestCase {
	
	PerfMonMetricsService service = PerfMonMetricsService.getInstance();
	
	public final void testGetInstance() {
		PerfMonMetricsService instance = PerfMonMetricsService.getInstance();
		assertNotNull(instance);
		assertEquals(service, instance);
	}

	@Test
	public final void testGetMetric() {
		Iterator mockIterator = mock(Iterator.class);
		PowerMockito.stub(PowerMockito.method(ServiceLoader.class, "iterator")).toReturn(mockIterator);
		when(mockIterator.hasNext()).thenReturn(true);
		when(mockIterator.next()).thenReturn(new PerfMonMetricsCreatorImpl());
		
		String metricType = "cpu";
		SigarProxy sigarProxy = SigarProxyCache.newInstance(new Sigar(), 500);
		MetricParamsSigar metricParams = MetricParamsSigar.createFromString("idle", sigarProxy);
		
		AbstractPerfMonMetric metric = service.getMetric(metricType, metricParams, sigarProxy);
		
		assertNotNull(metric);
		assertEquals(CPUTotalMetric.class, metric.getClass());
		System.out.println(metric.getClass());
	}
}
