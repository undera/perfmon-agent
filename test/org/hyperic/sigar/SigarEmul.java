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
package org.hyperic.sigar;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author undera
 */
public class SigarEmul extends Sigar {

    public long getPid() {
        return new Random().nextLong();
    }

    public long getServicePid(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Mem getMem() throws SigarException {
        return new Mem();
    }

    public Swap getSwap() throws SigarException {
        return new Swap();
    }

    public Cpu getCpu() throws SigarException {
        return new Cpu();
    }

    public CpuPerc getCpuPerc() throws SigarException {
        return CpuPercEmul.getInstance();
    }

    public Uptime getUptime() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResourceLimit getResourceLimit() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double[] getLoadAverage() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long[] getProcList() throws SigarException {
        long[] list = new long[2];
        list[0] = 1;
        list[1] = 2;
        return list;
    }

    public ProcStat getProcStat() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcMem getProcMem(long l) throws SigarException {
        return new ProcMem();
    }

    public ProcMem getProcMem(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcMem getMultiProcMem(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcState getProcState(long l) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcState getProcState(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcTime getProcTime(long l) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcTime getProcTime(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcCpu getProcCpu(long l) throws SigarException {
        return new ProcCpu();
    }

    public ProcCpu getProcCpu(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MultiProcCpu getMultiProcCpu(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcCred getProcCred(long l) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcCred getProcCred(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcCredName getProcCredName(long l) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcCredName getProcCredName(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcFd getProcFd(long l) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcFd getProcFd(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProcExe getProcExe(long l) throws SigarException {
        final ProcExe procExe = new ProcExeEmul();
        return procExe;
    }

    public ProcExe getProcExe(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String[] getProcArgs(long l) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String[] getProcArgs(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Map getProcEnv(long l) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Map getProcEnv(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getProcEnv(long l, String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getProcEnv(String string, String string1) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List getProcModules(long l) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List getProcModules(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getProcPort(int i, long l) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getProcPort(String string, String string1) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FileSystem[] getFileSystemList() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FileSystemMap getFileSystemMap() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FileSystemUsage getMountedFileSystemUsage(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FileSystemUsage getFileSystemUsage(String string) throws SigarException {
        return new FileSystemUsage();
    }

    public DiskUsage getDiskUsage(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FileInfo getFileInfo(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FileInfo getLinkInfo(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DirStat getDirStat(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DirUsage getDirUsage(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CpuInfo[] getCpuInfoList() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cpu[] getCpuList() throws SigarException {
        Cpu[] res = new Cpu[1];
        res[0] = new Cpu();
        return res;
    }

    public CpuPerc[] getCpuPercList() throws SigarException {
        CpuPerc[] res = new CpuPerc[1];
        res[0] = CpuPercEmul.getInstance();
        return res;
    }

    public NetRoute[] getNetRouteList() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NetInterfaceConfig getNetInterfaceConfig(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NetInterfaceConfig getNetInterfaceConfig() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NetInterfaceStat getNetInterfaceStat(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String[] getNetInterfaceList() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NetConnection[] getNetConnectionList(int i) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNetListenAddress(long l) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNetListenAddress(String string) throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NetStat getNetStat() throws SigarException {
        final NetStat netStat = new NetStatEmul();
        return netStat;
    }

    public String getNetServicesName(int i, long l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Who[] getWhoList() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Tcp getTcp() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NfsClientV2 getNfsClientV2() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NfsServerV2 getNfsServerV2() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NfsClientV3 getNfsClientV3() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NfsServerV3 getNfsServerV3() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public NetInfo getNetInfo() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getFQDN() throws SigarException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
