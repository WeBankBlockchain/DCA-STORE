#!/bin/bash

SYS_PORT=17801
JMX_PORT=`expr $SYS_PORT + 1`
HOST_IP=[@HOSTIP]
JAVA_OPTS+=" -Xmx1024m -Xms1024m -XX:MetaspaceSize=128m -XX:NewRatio=5"
JAVA_OPTS+=" -XX:+UseConcMarkSweepGC -XX:+UseParNewGC"
JAVA_OPTS+=" -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:CompressedClassSpaceSize=256m"
JAVA_OPTS+=" -XX:AutoBoxCacheMax=20000 -XX:CMSInitiatingOccupancyFraction=70 -XX:CompileThreshold=20000 -XX:MaxTenuringThreshold=10"
JAVA_OPTS+=" -XX:+ParallelRefProcEnabled -XX:+DisableExplicitGC -XX:-UseBiasedLocking -XX:+UseCMSInitiatingOccupancyOnly"
JAVA_OPTS+=" -XX:OldPLABSize=16 -XX:-OmitStackTraceInFastThrow"
JAVA_OPTS+=" -Dcom.sun.management.jmxremote.port=$JMX_PORT -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"
JAVA_OPTS+=" -XX:+PrintCommandLineFlags -XX:+PrintGCDateStamps"
JAVA_OPTS+=" -XX:+PrintGC -XX:+PrintGCApplicationConcurrentTime -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCDateStamps"
JAVA_OPTS+=" -XX:+PrintGCDetails -XX:+PrintGCID -XX:+PrintGCTaskTimeStamps"
JAVA_OPTS+=" -XX:+PrintGCTimeStamps -XX:+PrintPromotionFailure"
JAVA_OPTS+=" -XX:+PrintSafepointStatistics -XX:PrintSafepointStatisticsCount=1"
JAVA_OPTS+=" -XX:ErrorFile=/data/app/logs/dca-store/java_error_%p.log -XX:+ExplicitGCInvokesConcurrent"
JAVA_OPTS+=" -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/app/logs/dca-store"
JAVA_OPTS+=" -verbose:gc -Xloggc:/data/app/logs/dca-store/jvm_gc.log"
JAVA_OPTS+=" -Djava.rmi.server.hostname=${HOST_IP}"


echo "nohup /nemo/jdk8/bin/java -jar $JAVA_OPTS `pwd`/dca-store_1780.jar >/dev/null 2>&1 &"
nohup /nemo/jdk8/bin/java -jar $JAVA_OPTS `pwd`/dca-store_1780.jar  >/dev/null &