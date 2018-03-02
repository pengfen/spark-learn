1. 编写 log4j.properties
# Configure logging for testing: optionally with log file
#可以设置级别：debug>info>error
#debug:可以显式debug,info,error
#info:可以显式info,error
#error:可以显式error

log4j.rootLogger=debug,appender1
#log4j.rootLogger=info,appender1
#log4j.rootLogger=error,appender1

#输出到控制台
log4j.appender.appender1=org.apache.log4j.ConsoleAppender
#样式为TTCCLayout
log4j.appender.appender1.layout=org.apache.log4j.TTCCLayout

2. 编写 NameNodeNameSystem

3. 编写 LoginServiceInterface

4. 编写 LoginServiceImpl

5. 编写 ClientNameNodeProtocol

6. 编写 publish

7. 编写 UserLoginAction
运行publish ---> 运行UserLoginAction