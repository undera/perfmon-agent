package kg.apc.perfmon.metrics;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;
import org.hyperic.sigar.SigarProxy;

public class PerfMonMetricsService {
	
	private static final Logger log = LoggingManager.getLoggerForClass();
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
			try {
				metric = mCreator.getMetricProvider(metricType, metricParams, sigarProxy);
			} catch (Exception e) {
				log.debug("Error when getting metrics from: "+mCreator.getClass());
				log.debug(e.getMessage());
				metric = null;
			}
		}
		
		if (metric == null) {
			metric = new InvalidPerfMonMetric();
			throw new RuntimeException();
		}
		
		return metric;
	}

}
