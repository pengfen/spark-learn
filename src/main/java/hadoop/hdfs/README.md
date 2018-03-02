1. 编写 ConfigurationReasearch (测试配置)

2. 编写 HdfsDemo

3. 编写 HdfsClient


4. 编写 HdfsClientDemo (hadoop dfs 操作)
cd $HADOOP_HOME
sbin/start-dfs.sh

5. 编写 HdfsStreamAccess (hadoop dfs 操作)
cd $HADOOP_HOME
sbin/start-dfs.sh

6. 编写 log4j.properties
log4j.rootLogger=INFO,testlog

log4j.appender.testlog = org.apache.log4j.RollingFileAppender
log4j.appender.testlog.layout = org.apache.log4j.PatternLayout
log4j.appender.testlog.layout.ConversionPattern = [%-5p][%-22d{yyyy/MM/dd HH:mm:ssS}][%l]%n%m%n
log4j.appender.testlog.Threshold = INFO
log4j.appender.testlog.ImmediateFlush = TRUE
log4j.appender.testlog.Append = TRUE
log4j.appender.testlog.File = /home/ricky/logs/log/access.log
log4j.appender.testlog.MaxFileSize = 10KB
log4j.appender.testlog.MaxBackupIndex = 20
#log4j.appender.testlog.Encoding = UTF-8

7. 编写 GenerateLog

rpc ---> 参看 rpc 目录
8. 编写 MyNameNode

9. 编写 PublishServiceUtil

10. 编写 ClientNamenodeProtocol

11. 编写 MyHdfsClient
运行 PublishServiceUtil ---> 运行 MyHdfsClient


12. 编写 IUserLoginService

13. 编写 UserLoginServiceImpl

14. 编写 UserLoginAction
运行 PublishServiceUtil ---> 运行 UserLoginAction

client (MyHdfsClient, UserLoginAction)

protocol (ClientNamenodeProtocol)

service (MyNameNode, IUserLoginService)

service.impl(UserLoginServiceImpl)

util (PublishServiceUtil)



