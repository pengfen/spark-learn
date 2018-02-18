1. 编写 WordcountMapper

2. 编写 WordcountReducer

3. 编写 WordcountDriver
打包 ---> 上传至服务器 ---> 运行
hadoop jar spark-learn.jar com.hadoop.mr /wc/in /wc/out (未设置参数)
hadoop jar spark-learn.jar com.hadoop.mr
spark-learn.jar ---> pom.xml (artifactId)

4. 编写 FlowBean

5. 编写 FlowCount

自定义切分
6. 编写 ProvincePartitioner

7. 编写 ProvinceFlowCount (修改FlowCount)

排序
1. 编写 FlowCountSort

2. 编写 Test (测试排序)

3. 编写 InfoBean

4. 编写 RJoin