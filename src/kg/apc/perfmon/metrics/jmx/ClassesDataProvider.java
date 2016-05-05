package kg.apc.perfmon.metrics.jmx;

import javax.management.MBeanServerConnection;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;

/**
 * @author undera
 */
class ClassesDataProvider extends AbstractJMXDataProvider {

    public ClassesDataProvider(MBeanServerConnection mBeanServerConn, boolean diff) throws Exception {
        super(mBeanServerConn, diff);
    }

    protected String getMXBeanType() {
        return ManagementFactory.CLASS_LOADING_MXBEAN_NAME;
    }

    protected Class getMXBeanClass() {
        return ClassLoadingMXBean.class;
    }

    protected long getValueFromBean(Object bean) {
        return ((ClassLoadingMXBean) bean).getLoadedClassCount();
    }
}
