package kg.apc.perfmon.metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;
import org.hyperic.sigar.SigarProxy;

public class PerfMonMetricsService {
	
	private static final Logger log = LoggingManager.getLoggerForClass();
	private static PerfMonMetricsService service;
	private ServiceLoader loader;
	private List metricExceptions = new ArrayList();
	private List triedClasses = new ArrayList();
	
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
				triedClasses.add(mCreator.getClass());
				metricExceptions.add(e);
				metric = null;
			}
		}
		
		if (metric == null) {
			metric = new InvalidPerfMonMetric();
			log.error("Couldn't get metrics from: "+ Arrays.toString(triedClasses.toArray()));
			for (int i = 0; i < metricExceptions.size(); i++) {
				log.error(((Exception) metricExceptions.get(i)).getMessage());
			}
			throw new RuntimeException("Couldn't get metrics from: "+ Arrays.toString(triedClasses.toArray()));
		}
		
		return metric;
	}

}
