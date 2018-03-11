1. 编写 WordcountMapper ---> first_yarn包

2. 编写 WordcountReducer ---> first_yarn包

3. 编写 WordcountDriver ---> first_yarn包
打包 ---> 上传至服务器 ---> 运行
hadoop jar spark-learn.jar com.hadoop.mr /wc/in /wc/out (未设置参数)
hadoop jar spark-learn.jar com.hadoop.mr
spark-learn.jar ---> pom.xml (artifactId)


序列化 ---> mr_seri包
4. 编写 FlowBean

5. 编写 FlowCount


自定义切分
6. 编写 ProvincePartitioner

7. 编写 ProvinceFlowCount (修改FlowCount)

编写 WordcountCombiner

排序
1. 编写 FlowCountSort

2. 编写 Test (测试排序)

3. 编写 InfoBean

4. 编写 RJoin

编写 MapSideJoin


编写 InverIndexStepOne

编写 IndexStepTwo


编写 SharedFriendsStepOne

编写 SharedFriendsStepTwo
/home/ricky/data/friend.txt


编写 WebLogBean

编写 WebLogParser

编写 WeblogPreProcess


流量日志
1. 编写 DBLoader

2. 编写 LogEnhance

3. 编写 LogEnhanceOutputFormat