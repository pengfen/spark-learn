1. 编写 WordcountMapper ---> first_yarn包

2. 编写 WordcountReducer ---> first_yarn包

3. 编写 WordcountDriver ---> first_yarn包
打包 ---> 上传至服务器 ---> 运行
hadoop jar spark-learn.jar com.hadoop.mr /wc/in /wc/out (未设置参数)
hadoop jar spark-learn.jar com.hadoop.mr
spark-learn.jar ---> pom.xml (artifactId)

4. 编写 WordcountCombiner


序列化 ---> mr_seri包
5. 编写 FlowBean

6. 编写 FlowCount


排序 ---> mr_sort包
7. 编写 FlowBean

8. 编写 FlowCountSort

9. 编写 Test (测试排序)排序


自定义切分(分区) ---> mr_part包
6. 编写 ProvincePartitioner

7. 编写 ProvinceFlowCount (修改FlowCount)


join算法 ---> mr_join
1. 编写 InfoBean

2. 编写 RJoin

3. 编写 MapSideJoin


日志预处理 ---> mr_log
编写 WebLogBean

编写 WebLogParser

编写 WeblogPreProcess


流量日志 ---> mr_flow
1. 编写 DBLoader

2. 编写 LogEnhance

3. 编写 LogEnhanceOutputFormat


好友分析 ---> mr_friend
1. 编写 SharedFriendsStepOne

2. 编写 SharedFriendsStepTwo
/home/ricky/data/hadoop/friend.txt


自定义inputFormat ---> mr_input

自定义outputFormat ---> mr_output

自定义GroupingComparator ---> mr_group

DistributedCache应用 ---> mr_distr

mr_other (计算器, )

编写 InverIndexStepOne

编写 IndexStepTwo


