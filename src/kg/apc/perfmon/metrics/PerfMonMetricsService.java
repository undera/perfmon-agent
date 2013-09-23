package kg.apc.perfmon.metrics;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.hyperic.sigar.SigarProxy;

public class PerfMonMetricsService {
	
	private static PerfMonMetricsService service;
	private ServiceLoader loader;
	
	private PerfMonMetricsService() {
		loader = ServiceLoader.load(PerfMonMetricsCreator.class);
	}
	
	public static synchronized PerfMonMetricsService getInstance() {
		if (service==null) {
			service = new PerfMonMetricsService();
		}
		return service;
	}
	
	public AbstractPerfMonMetric getMetric(String metricType, MetricParamsSigar metricParams, SigarProxy sigarProxy) throws IllegalArgumentException, RuntimeException {		
		AbstractPerfMonMetric metric = null;

		Iterator mCreators = loader.iterator();
		while (metric == null && mCreators.hasNext()) {
			PerfMonMetricsCreator mCreator = (PerfMonMetricsCreator) mCreators.next();
			metric = mCreator.getMetricProvider(metricType, metricParams, sigarProxy);
		}
		
		return metric;
	}

}
