package kg.apc.perfmon;

import kg.apc.perfmon.metrics.AbstractPerfMonMetric;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;
import org.hyperic.sigar.SigarProxy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class PerfMonMetricGetter {

    public static final String TAB = "\t";
    public static final String DVOETOCHIE = ":";
    public static final String NEWLINE = "\n";
    private final PerfMonWorker controller;
    private static final Logger log = LoggingManager.getLoggerForClass();
    private String commandString = "";
    private final SelectableChannel channel;
    private AbstractPerfMonMetric[] metrics = new AbstractPerfMonMetric[0];
    private final SigarProxy sigarProxy;
    private SocketAddress udpPeer;

    public PerfMonMetricGetter(SigarProxy aproxy, PerfMonWorker aController, SelectableChannel aChannel) throws IOException {
        controller = aController;
        channel = aChannel;
        sigarProxy = aproxy;
    }

    PerfMonMetricGetter(SigarProxy aproxy, PerfMonWorker aThis, DatagramChannel udpServer, SocketAddress remoteAddr) throws IOException {
        this(aproxy, aThis, udpServer);
        udpPeer = remoteAddr;
    }

    private void processCommand(String command) throws IOException {
        log.debug("Got command line: " + command);

        String cmdType = command.trim();
        String params = "";
        if (command.contains(DVOETOCHIE)) {
            cmdType = command.substring(0, command.indexOf(DVOETOCHIE)).trim();
            params = command.substring(command.indexOf(DVOETOCHIE) + 1).trim();
        }

        if (cmdType.equals("interval")) {
            controller.setInterval(Integer.parseInt(params));
        } else if (cmdType.equals("shutdown")) {
            controller.shutdownConnections();
        } else if (cmdType.equals("metrics-single")) {
            setUpMetrics(params.split(TAB));
            ByteBuffer buf = getMetricsLine();
            controller.sendToClient(channel, buf);
        } else if (cmdType.equals("metrics")) {
            log.info("Starting measures: " + params);
            setUpMetrics(params.split(TAB));
            // this will make it sending channel
            controller.registerWritingChannel(channel, this);
        } else if (cmdType.equals("exit")) {
            log.info("Client disconnected");
            synchronized (channel) {
                metrics = new AbstractPerfMonMetric[0];
                if (channel instanceof SocketChannel) {
                    channel.close();
                } else {
                    log.debug("UDP channel left open to receive data");
                }
            }
            controller.notifyDisonnected();
        } else if (cmdType.equals("test")) {
            log.info("Yep, we received the 'test' command");
            if (channel instanceof DatagramChannel) {
                DatagramChannel udpChannel = ((DatagramChannel) channel);
                udpChannel.send(ByteBuffer.wrap("Yep\n".getBytes()), udpPeer);
            } else {
                ((WritableByteChannel) channel).write(ByteBuffer.wrap("Yep\n".getBytes()));
            }
        } else if (cmdType.equals("udp-transmitter")) {
            setUpTransmitter(params);
        } else {
            throw new UnsupportedOperationException("Unknown command [" + cmdType.length() + "]: '" + cmdType + "'");
        }
    }

    public void addCommandString(String byteBufferToString) {
        commandString += byteBufferToString;
    }

    public boolean processNextCommand() throws IOException {
        log.debug("Command line is: " + commandString);
        if (commandString.contains(NEWLINE)) {
            int pos = commandString.indexOf(NEWLINE);
            String cmd = commandString.substring(0, pos);
            commandString = commandString.substring(pos + 1);
            processCommand(cmd);
            return true;
        } else {
            return false;
        }
    }

    public ByteBuffer getMetricsLine() throws IOException {
        log.debug("Building metrics");
        StringBuffer res = new StringBuffer();
        synchronized (channel) {
            for (AbstractPerfMonMetric metric : metrics) {
                try {
                    metric.getValue(res);
                } catch (Exception ex) {
                    log.error("Error getting metric", ex);
                }
                res.append(TAB);
            }
        }
        res.append(NEWLINE);

        log.debug("Metrics line: " + res.toString());
        return (ByteBuffer.wrap(res.toString().getBytes()));
    }

    private void setUpMetrics(String[] params) throws IOException {
        synchronized (channel) {
            metrics = new AbstractPerfMonMetric[params.length];
            for (int n = 0; n < params.length; n++) {
                String metricParams = "";
                String metricType = params[n];
                if (DVOETOCHIE.contains(metricType)) {
                    metricParams = metricType.substring(metricType.indexOf(DVOETOCHIE) + 1).trim();
                    metricType = metricType.substring(0, metricType.indexOf(DVOETOCHIE)).trim();
                }

                metrics[n] = AbstractPerfMonMetric.createMetric(metricType, metricParams, sigarProxy);
            }
        }
    }

    public boolean isStarted() {
        return metrics.length > 0;
    }

    // FIXME: some kind of tokenizer would go better
    private void setUpTransmitter(String params) throws IOException {
        log.info("Starting UDP transmitter for: " + params);
        if (!params.contains(DVOETOCHIE)) {
            throw new IllegalArgumentException("Wrong syntax for udp-transmitter command: " + params);
        }
        String transmitToAddr = params.substring(0, params.indexOf(DVOETOCHIE)).trim();
        params = params.substring(params.indexOf(DVOETOCHIE) + 1).trim();

        if (!params.contains(DVOETOCHIE)) {
            throw new IllegalArgumentException("Wrong syntax for udp-transmitter command: " + params);
        }
        int transmitToPort = Integer.parseInt(params.substring(0, params.indexOf(DVOETOCHIE)).trim());
        params = params.substring(params.indexOf(DVOETOCHIE) + 1).trim();

        setUpMetrics(params.split(TAB));
        DatagramChannel transmitter = DatagramChannel.open();
        SocketAddress addr = new InetSocketAddress(transmitToAddr, transmitToPort);
        transmitter.connect(addr);
        transmitter.configureBlocking(false);
        controller.registerWritingChannel(transmitter, this);
        controller.connectUDPClient(addr, transmitter, this);
    }
}
