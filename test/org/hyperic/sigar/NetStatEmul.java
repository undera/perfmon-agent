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

import java.util.Random;

/**
 * @author undera
 */
public class NetStatEmul extends NetStat {

    public NetStatEmul() {
    }

    public int getTcpBound() {
        return new Random().nextInt();
    }

    public int getTcpClose() {
        return new Random().nextInt();
    }

    public int getAllInboundTotal() {
        return new Random().nextInt();
    }

    public int getAllOutboundTotal() {
        return new Random().nextInt();
    }

    public int getTcpCloseWait() {
        return new Random().nextInt();
    }

    public int getTcpClosing() {
        return new Random().nextInt();
    }

    public int getTcpEstablished() {
        return new Random().nextInt();
    }

    public int getTcpFinWait1() {
        return new Random().nextInt();
    }

    public int getTcpFinWait2() {
        return new Random().nextInt();
    }

    public int getTcpIdle() {
        return new Random().nextInt();
    }

    public int getTcpInboundTotal() {
        return new Random().nextInt();
    }

    public int getTcpLastAck() {
        return new Random().nextInt();
    }

    public int getTcpListen() {
        return new Random().nextInt();
    }

    public int getTcpOutboundTotal() {
        return new Random().nextInt();
    }

    public int[] getTcpStates() {
        return new int[0];
    }

    public int getTcpSynRecv() {
        return new Random().nextInt();
    }

    public int getTcpSynSent() {
        return new Random().nextInt();
    }

    public int getTcpTimeWait() {
        return new Random().nextInt();
    }
}
