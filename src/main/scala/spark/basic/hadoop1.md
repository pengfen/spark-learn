安装 hadoop1.2　

1. 查看当前系统版本
[root@iZ94f2imuwkZ java]# uname -a #阿里云服务器
Linux iZ94f2imuwkZ 2.6.32-431.23.3.el6.x86_64 #1 SMP Thu Jul 31 17:20:51 UTC 2014 x86_64 x86_64 x86_64 GNU/Linux

[root@peng1 bin]# uname -a #本地虚拟机 linux
Linux peng1 2.6.32-279.el6.i686 #1 SMP Fri Jun 22 10:59:55 UTC 2012 i686 i686 i386 GNU/Linux

安装 hadoop 环境

打开阿里云服务器 打开安装好的本地 linux 系统

使用 winscp 下载阿里云服务器上的 jdk 包

使用 winscp 上传 jdk 包到 192.168.209.129 服务器上

2. 安装 jdk gz 包

[root@peng1 jdk]# cd /usr/local/
[root@peng1 local]# mkdir java
[root@peng1 local]# pwd
/usr/local
[root@peng1 local]# cd /root/jdk/
[root@peng1 jdk]# ls
jdk-8u73-linux-x64.gz  jdk-8u73-linux-x64.rpm
[root@peng1 jdk]# mv jdk-8u73-linux-x64.gz /usr/local/java/
[root@peng1 jdk]# cd /usr/local/java/
[root@peng1 java]# ls
jdk-8u73-linux-x64.gz
[root@peng1 java]# tar -zxvf jdk-8u73-linux-x64.gz

[root@peng1 java]# ls
jdk1.8.0_73  jdk-8u73-linux-x64.gz
[root@peng1 java]# cd jdk1.8.0_73/

[root@peng1 jdk1.8.0_73]# vi /etc/profile

export JAVA_HOME=/usr/local/java/jdk1.8.0_73
export JRE_HOME=/usr/local/java/jdk1.8.0_73/jre
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=./:$JAVA_HOME/lib:$JAVA_HOME/jre/lib



[root@peng1 ~]# ls
anaconda-ks.cfg  hadoop  install.log  install.log.syslog  jdk
[root@peng1 ~]# cd jdk/
[root@peng1 jdk]# ls
jdk-8u73-linux-i586.gz  jdk-8u73-linux-x64.rpm
[root@peng1 jdk]#
[root@peng1 jdk]# ls /usr/local/java/
jdk-8u73-linux-x64.gz
[root@peng1 jdk]# mv jdk-8u73-linux-i586.gz /usr/local/java/
[root@peng1 jdk]# cd /usr/local/java/
[root@peng1 java]# ls
jdk-8u73-linux-i586.gz  jdk-8u73-linux-x64.gz
[root@peng1 java]# tar -zxvf jdk-8u73-linux-i586.gz

[root@peng1 java]# source /etc/profile
[root@peng1 java]# java
用法: java [-options] class [args...]


wget http://apache.fayea.com/hadoop/common/hadoop-1.2.1/hadoop-1.2.1.tar.gz 下载 hadoop 安装包
[root@peng1 hadoop]# wget http://apache.fayea.com/hadoop/common/hadoop-1.2.1/hadoop-1.2.1.tar.gz
--2016-03-07 20:49:17--  http://apache.fayea.com/hadoop/common/hadoop-1.2.1/hadoop-1.2.1.tar.gz
正在解析主机 apache.fayea.com... 119.6.242.164, 119.6.242.165
正在连接 apache.fayea.com|119.6.242.164|:80... 已连接。
已发出 HTTP 请求，正在等待回应... 200 OK
长度：63851630 (61M) [application/x-gzip]
正在保存至: “hadoop-1.2.1.tar.gz”


[root@peng1 hadoop]# tar -zxvf hadoop-1.2.1.tar.gz
[root@peng1 hadoop]# ls
hadoop-1.2.1  hadoop-1.2.1.tar.gz
[root@peng1 hadoop]# cd hadoop-1.2.1
[root@peng1 hadoop-1.2.1]# ls
bin          hadoop-ant-1.2.1.jar          ivy          sbin
build.xml    hadoop-client-1.2.1.jar       ivy.xml      share
c++          hadoop-core-1.2.1.jar         lib          src
CHANGES.txt  hadoop-examples-1.2.1.jar     libexec      webapps
conf         hadoop-minicluster-1.2.1.jar  LICENSE.txt
contrib      hadoop-test-1.2.1.jar         NOTICE.txt
docs         hadoop-tools-1.2.1.jar        README.txt

[root@peng1 hadoop-1.2.1]# ln -sf /root/hadoop/hadoop-1.2.1 /home/hadoop1.2
[root@peng1 hadoop-1.2.1]# cd /home/
[root@peng1 home]# ls
hadoop1.2
[root@peng1 home]# ll
总用量 0
lrwxrwxrwx. 1 root root 25 3月   7 21:10 hadoop1.2 -> /root/hadoop/hadoop-1.2.1
[root@peng1 home]# cd hadoop1.2/
[root@peng1 hadoop1.2]# ls
bin          hadoop-ant-1.2.1.jar          ivy          sbin
build.xml    hadoop-client-1.2.1.jar       ivy.xml      share
c++          hadoop-core-1.2.1.jar         lib          src
CHANGES.txt  hadoop-examples-1.2.1.jar     libexec      webapps
conf         hadoop-minicluster-1.2.1.jar  LICENSE.txt
contrib      hadoop-test-1.2.1.jar         NOTICE.txt
docs         hadoop-tools-1.2.1.jar        README.txt
[root@peng1 hadoop1.2]# cd conf


[root@peng1 conf]# vim core-site.xml
<configuration>
    <property>
        <name>fs.default.name</name>
        <value>hdfs://peng1:9000</value>
    </property>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/opt/hadoop-1.2</value>
    </property>
</configuration>



[root@peng1 conf]# vim hdfs-site.xml
<configuration>
    <property>
         <name>dfs.replication</name>
         <value>1</value>
    </property>
</configuration>


[root@peng1 conf]# cd ..
[root@peng1 hadoop1.2]# ./bin/hadoop nodenode -format
Error: JAVA_HOME is not set. #不使用 rpm 安装 安装 gz 包
[root@peng1 hadoop1.2]#

[root@peng1 hadoop1.2]# ./bin/hadoop namenode -format
16/03/07 23:31:53 INFO namenode.NameNode: STARTUP_MSG:

修改 windows 上的 host 主机
192.168.209.126 peng1
192.168.209.130 peng2

配置主从
peng2 主机支持 java 环境

[root@peng1 ~]# scp -r /usr/local/java/ root@peng2:/usr/local/java
[root@peng1 ~]# scp -r /etc/profile root@peng2:/etc/profile
profile                                       100% 1973     1.9KB/s   00:00


[root@peng1 conf]# vi slaves
peng2 #配置从节点的主机名

[root@peng1 conf]# vi masters
peng2 #配置 secondaryNameNode


[root@peng1 conf]# vi mapred-site.xml

<configuration>
    <property>
        <name>mapred.job.tracker</name>
        <value>peng2:9001</value>
    </property>
ttconfiguration>




[root@peng1 hadoop1.2]# jps
2192 JobTracker
2018 NameNode
2296 Jps
[root@peng1 hadoop1.2]#
[root@peng1 hadoop1.2]#
[root@peng1 hadoop1.2]# ./bin/hadoop jar hadoop-examples-1.2.1.jar pi 10 100
Number of Maps  = 10
Samples per Map = 100
Wrote input for Map #0
Wrote input for Map #1
Wrote input for Map #2
Wrote input for Map #3
Wrote input for Map #4
Wrote input for Map #5
Wrote input for Map #6
Wrote input for Map #7
Wrote input for Map #8
Wrote input for Map #9
Starting Job
16/03/08 01:23:36 INFO mapred.FileInputFormat: Total input paths to process : 10
16/03/08 01:23:36 INFO mapred.JobClient: Running job: job_201603080122_0001
16/03/08 01:23:37 INFO mapred.JobClient:  map 0% reduce 0%
16/03/08 01:23:43 INFO mapred.JobClient:  map 20% reduce 0%
16/03/08 01:23:48 INFO mapred.JobClient:  map 40% reduce 0%
16/03/08 01:23:51 INFO mapred.JobClient:  map 60% reduce 0%
16/03/08 01:23:54 INFO mapred.JobClient:  map 80% reduce 0%
16/03/08 01:23:57 INFO mapred.JobClient:  map 100% reduce 0%