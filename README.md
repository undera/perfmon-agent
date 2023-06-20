[![Build Status](https://travis-ci.org/undera/perfmon-agent.png?branch=master)](https://travis-ci.org/undera/perfmon-agent)
# PerfMon Server Agent

Agent application to access system metrics on remote machines.

> :warning: **It's very out-of-date for modern technology**
> 
> Since it relies on [SIGAR](https://github.com/hyperic/sigar) library to gather metrics, with that library is last updated in 2010, it won't work on many modern systems.

## Download

[ServerAgent-2.2.3.zip](https://github.com/undera/perfmon-agent/releases/download/2.2.3/ServerAgent-2.2.3.zip)

(previous [ServerAgent-2.2.1.zip](https://github.com/undera/perfmon-agent/releases/download/2.2.1/ServerAgent-2.2.1.zip))

## Installation

_You do not need any root/admin privilege_. You can just unzip the the ServerAgent-X.X.X.zip somewhere on the server. Then launch the agent using `startAgent.sh` script on Unix, or `startAgent.bat` script on Windows.

The agent is written in Java, you will need JRE 1.5 or later to run it. 
Note you can create yourself the agent package which includes its own JRE 
so you don't have to install java on the server (We tested it on windows platform).
To do this, just create a JRE folder in the agent folder and copy one installed 
JRE inside. Change the java command inside the .bat file to the path to the 
java executable and it will work.

## Usage
To start the agent, simply run `startAgent.bat/sh` file. It will open UDP/TCP
server ports where clients can connect and query the metrics. Some information 
will be printed to standard output, informing you on problems gathering metrics 
(logging verbosity regulated with `--loglevel parameter`).

You can specify the listening ports as arguments (0 disables listening), default is 4444:

```
$ ./startAgent.sh --udp-port 0 --tcp-port 3450
INFO    2011-11-25 19:02:14.315 [kg.apc.p] (): Binding TCP to 3450
```

You can use the `--auto-shutdown` option when starting the agent to automatically 
stop it once the last client is disconnected. It is recommended to use this feature only with 
TCP connections:

```
$ undera@undera-HP:/tmp/serverAgent$ ./startAgent.sh --udp-port 0 --auto-shutdown
INFO    2011-11-25 19:48:59.321 [kg.apc.p] (): Agent will shutdown when all clients disconnected
INFO    2011-11-25 19:48:59.424 [kg.apc.p] (): Binding TCP to 4444
```

You can use the `--sysinfo` option to view available system objects:

```
$ ./startAgent.sh --sysinfo
INFO    2011-11-25 19:51:25.517 [kg.apc.p] (): *** Logging available processes ***
INFO    2011-11-25 19:51:25.542 [kg.apc.p] (): Process: pid=24244 name=bash args=-bash
INFO    2011-11-25 19:51:25.543 [kg.apc.p] (): Process: pid=25086 name=dash args=/bin/sh ./startAgent.sh --sysinfo
INFO    2011-11-25 19:51:25.543 [kg.apc.p] (): Process: pid=25088 name=java args=java -jar ./CMDRunner.jar --tool PerfMonAgent --sysinfo
INFO    2011-11-25 19:51:25.549 [kg.apc.p] (): *** Logging available filesystems ***
INFO    2011-11-25 19:51:25.551 [kg.apc.p] (): Filesystem: fs=/dev type=devtmpfs
INFO    2011-11-25 19:51:25.551 [kg.apc.p] (): Filesystem: fs=/ type=ext4
INFO    2011-11-25 19:51:25.551 [kg.apc.p] (): Filesystem: fs=/var/run type=tmpfs
INFO    2011-11-25 19:51:25.551 [kg.apc.p] (): Filesystem: fs=/home type=ext4
INFO    2011-11-25 19:51:25.552 [kg.apc.p] (): Filesystem: fs=/var/lock type=tmpfs
INFO    2011-11-25 19:51:25.552 [kg.apc.p] (): Filesystem: fs=/proc type=proc
INFO    2011-11-25 19:51:25.553 [kg.apc.p] (): *** Logging available network interfaces ***
INFO    2011-11-25 19:51:25.554 [kg.apc.p] (): Network interface: iface=lo addr=127.0.0.1 type=Local Loopback
INFO    2011-11-25 19:51:25.554 [kg.apc.p] (): Network interface: iface=eth0 addr=192.168.0.1 type=Ethernet
INFO    2011-11-25 19:51:25.555 [kg.apc.p] (): *** Done logging sysinfo ***
INFO    2011-11-25 19:51:25.555 [kg.apc.p] (): Binding UDP to 4444
INFO    2011-11-25 19:51:26.560 [kg.apc.p] (): Binding TCP to 4444
```

The `--interval <seconds>` argument can be used to change metrics collection frequency.

## Using Server Agent With Other Applications

Server Agent uses simple plain-text protocol, anyone can use agent's 
capabilities implementing client, based on `kg.apc.perfmon.client.Transport`
interface. If anyone's interested, start the topic on the support forums and I'll describe how to
connect third-party client app to agent.

ServerAgent has simple text protocol and can work on UDP and TCP transports. Most of cases will use TCP.

To have your first talk with the agent, start it locally. Then use telnet utility to connect to it:

```
user@ubuntu:~$ telnet localhost 4444
Trying 127.0.0.1...
Connected to localhost.
Escape character is '^]'.
```

If connection has succeeded, you should see "Accepting new TCP connection" message in ServerAgent console log. Type "test" and press Enter in telnet chat, server should answer with short "Yep":

```
test
Yep
```

Type "exit":

```
exit
Connection closed by foreign host.
```

That's it. You sending a command line, server answering. Command line consists of command, sometimes with parameters. Parameters are separated from command with a colon sign.

Possible commands are: 
  * exit - terminates current client session and closes connection to agent, no parameters
  * test - test if server is alive, no parameters
  * shutdown - terminate all client connections and shutdown agent process, no parameters
  * interval - change metrics reporting interval used in 'metrics' command, single parameter is integer value in seconds. Interval can be changed in the middle of metrics reporting. Example: `interval:5`
  * metrics - starts automatic metrics collection, parameters are metrics list to collect, described below. Example: `metrics:cpu`
  * metrics-single - calls single metric collection iteration. Example: `metrics-single:memory`

### Specifying Metrics
Metrics list consists of metric specifications, separated by TAB character. Metric collection output consists of float values, TAB separated. Example:

```
metrics-single:cpu<TAB>memory
22.05736894164194<TAB>57.52359562205553
```
Each metric specification consists of several fields, colon-separated. Short example:

```
metrics-single:cpu:idle    memory:free
80.02238388360381    57.52359562205553
```

Fields number is metric-type specific. Possible metric types are:
  * cpu
  * memory
  * swap
  * disks
  * network
  * tcp
  * tail
  * exec
  * jmx

Fields corresponding to each metric type are described below. 
Last example (Yep, ServerAgent can be shell exec vulnerability. If you have issue with this, ask me and I'll introduce 'secure' mode, disabling insecure metric types):

```
metrics-single:exec:/bin/sh:-c:free | grep Mem | awk '{print $7}'
1152488
```

## Viewing Available System Objects
Server Agent has special command-line option `--sysinfo` 
for printing available processes, filesystems and network interfaces together 
with their selectors.

# Supported Metrics

## CPU Metrics 
### Total 
Primary
  * *combined* 
  * idle 
  * system 
  * user 
  * iowait

Additional 
  * irq 
  * nice 
  * softirq 
  * stolen 

### Per Process 
  * *percent*
  * total 
  * system 
  * user

## Memory Metrics 
### Total 
Primary
  * *usedperc* - relative memory usage in percents
  * freeperc
  * used 
  * free

Additional
  * actualfree 
  * actualused  
  * ram 
  * total 

### Per Process 
Primary 
  * *resident*
  * virtual
  * shared

Additional
  * pagefaults
  * majorfaults
  * minorfaults 

## Disk I/O Metrics  
Primary
  * *queue*
  * reads 
  * writes 
  * readbytes
  * writebytes 

Additional
  * available
  * service
  * files
  * free
  * freefiles
  * total
  * useperc
  * used

## Network I/O Metrics  
Primary
  * *bytesrecv*
  * bytessent 
  * rx 
  * tx

Additional
  * used
  * speed 
  * rxdrops
  * rxerr
  * rxframe
  * rxoverruns
  * txcarrier
  * txcollisions
  * txdrops
  * txerr
  * txoverruns

## JMX Metrics 

Since version 0.5.2 Server Agent has ability to monitor some [JMX](http://docs.oracle.com/javase/6/docs/api/javax/management/package-summary.html#package_description)
values inside Java Virtual Machine. To enable JMX monitoring you must start Java 
with some special properties enabled, like described [here](http://docs.oracle.com/javase/6/docs/technotes/guides/management/agent.html).
Here's simplest (and not so secure) options to start Java with JMX enabled:

```
java -Dcom.sun.management.jmxremote.port=4711 -Dcom.sun.management.jmxremote.authenticate=false \
  -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.ssl=false <other options>
```

By default the Server Agent will try to connect to JMX server at localhost with port 4711. 
If you started JMX server at different host/port or using authentication with username/password,
please, use following additional parameters:
  * `url=<hostname>\:<port>`
  * `user=<username>`
  * `password=<password>`

Available JMX metric types:
  * gc-time - time spent in garbage collection, milliseconds ([used method](http://docs.oracle.com/javase/6/docs/api/java/lang/management/GarbageCollectorMXBean.html#getCollectionTime()))
  * memory-usage - heap memory used by VM, bytes ([method used](http://docs.oracle.com/javase/6/docs/api/java/lang/management/MemoryMXBean.html#getHeapMemoryUsage()))
  * memory-committed - heap memory committed by VM, bytes ([method used](http://docs.oracle.com/javase/6/docs/api/java/lang/management/MemoryMXBean.html#getHeapMemoryUsage()))
  * memorypool-usage - heap memory pool usage, bytes ([method used](http://docs.oracle.com/javase/6/docs/api/java/lang/management/MemoryPoolMXBean.html#getUsage())) 
  * memorypool-committed - heap memory pool committed size, bytes ([method used](http://docs.oracle.com/javase/6/docs/api/java/lang/management/MemoryPoolMXBean.html#getUsage())) 
  * class-count - loaded class count in VM ([used method](http://docs.oracle.com/javase/6/docs/api/java/lang/management/ClassLoadingMXBean.html#getLoadedClassCount()))
  * compile-time - time spent in compilation, milliseconds ([used method](http://docs.oracle.com/javase/6/docs/api/java/lang/management/CompilationMXBean.html#getTotalCompilationTime()))

Examples:

```
gc-time - monitor GC time at localhost:4711
memory-usage:url=somehost.com\:4715 - use alternative hostname/password
class-count:url=somehost.com\:4715:user=apc:password=SecurityPlease123 - some secure setup access
```

## TCP Metrics 
TCP metrics represents TCP socket state statistics (like open ports).
Primary
  * *estab* - established connections
  * time_wait
  * close_wait

Additional
  * bound
  * close
  * closing
  * fin_wait1
  * fin_wait2
  * idle
  * inbound
  * last_ack
  * listen
  * outbound
  * syn_recv 

## Swap Metrics 
  * *used*
  * pagein
  * pageout
  * free
  * total 

## Custom Metrics 
If you haven't found appropriate metric in above lists,
you can set up collecting any value with custom metrics. 

Security note: Both `exec` and `tail` metric types can be turned off by providing `--no-exec` flag to
server agent. This enables more secure service with only strict KPI fetching.

### EXEC 
This metric type interprets parameter string as path to process to start and
arguments to pass to the process. Parameters separated with colon (not space), 
see examples below. The process must print out to standard output single line
containing single numeric metric value.

In most cases you'll need to start system interpreter (cmd.exe, /bin/sh)
to run complex sequences of parameterized calls.

Example1: Monitoring Linux _cached_ memory size (unavailable via SIGAR lib in default metrics), 
used _free_ utility output:

```
/bin/sh:-c:free | grep Mem | awk '{print $7}'
```

Example2: Monitoring MySQL _select_ query count:

```
/bin/sh:-c:echo "show global status like 'Com_select'" | mysql -u root | awk ' $1 =="Com_select" {print $2}'
```

### TAIL 
Another way to collect cutom metrics is to read lines off the end of the file.
Metric parameter for tail type is path to file to read. Lines added to file must 
contain single numeric metric. Example: 

```
123
342
333
297
```
