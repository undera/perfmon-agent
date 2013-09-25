package kg.apc.perfmon.metrics;

import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

/**
 *
 * @author undera
 */
public class InvalidPerfMonMetric extends AbstractPerfMonMetric {

    private static final Logger log = LoggingManager.getLoggerForClass();

    public InvalidPerfMonMetric() {
        super(null);
    }

    public void getValue(StringBuffer res) {
        log.debug("Invalid metric stub hit");
        res.append("");
    }
}
