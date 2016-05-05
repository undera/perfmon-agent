package kg.apc.perfmon.metrics;

import org.hyperic.sigar.SigarProxy;

public interface PerfMonMetricsCreator {

    public AbstractPerfMonMetric getMetricProvider(String metricType, MetricParamsSigar metricParams, SigarProxy sigarProxy);

}
