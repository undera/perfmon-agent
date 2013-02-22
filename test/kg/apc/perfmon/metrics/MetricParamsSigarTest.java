/*
 * Copyright 2013 undera.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kg.apc.perfmon.metrics;

import java.util.LinkedList;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

/**
 *
 * @author undera
 */
public class MetricParamsSigarTest extends TestCase {
    
    public MetricParamsSigarTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MetricParamsSigarTest.class);
        return suite;
    }
    
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of createFromString method, of class MetricParamsSigar.
     */
    public void testCreateFromString() {
        System.out.println("createFromString");
        String metricParamsStr = "";
        SigarProxy sigar = SigarProxyCache.newInstance(new Sigar(), 500);
        MetricParamsSigar result = MetricParamsSigar.createFromString(metricParamsStr, sigar);
        assertEquals(-1, result.PID);
    }

    /**
     * Test of populateParams method, of class MetricParamsSigar.
     */
    public void testPopulateParams() {
        System.out.println("populateParams");
        String token = "";
        List params = new LinkedList();
        SigarProxy sigar = SigarProxyCache.newInstance(new Sigar(), 500);
        MetricParamsSigar instance = MetricParamsSigar.createFromString("", sigar);
        instance.populateParams(token, params);
    }

    /**
     * Test of logAvailableProcesses method, of class MetricParamsSigar.
     */
    public void testLogAvailableProcesses() {
        System.out.println("logAvailableProcesses");
        SigarProxy sigar = SigarProxyCache.newInstance(new Sigar(), 500);
        MetricParamsSigar.logAvailableProcesses(sigar);
    }
}
