根据日志统计出每天站点所呆时间最长的前十个用户


2. 编写日志产生脚本 gen_bs_user.php(产生用户日志)

3. 编写定时任务每分钟产生五百条
*/1 * * * *  /usr/bin/php /home/ricky/script/spark/basic/gen_bs_user.php

4. 编写脚本每天晚零点将产生的日志移动到某目录
59 23 * * *  /home/ricky/script/spark/basic/move_bs_user.sh

5. 编写代码进行清洗 UserLocationClean
参考
[UrlCount](https://github.com/pengfen/spark-learn/blob/master/src/main/scala/spark/rdd/UrlCount.scala)
[AdvUrlCount](https://github.com/pengfen/spark-learn/blob/master/src/main/scala/spark/rdd/AdvUrlCount.scala)
[UrlCountPartition](https://github.com/pengfen/spark-learn/blob/master/src/main/scala/spark/rdd/UrlCountPartition.scala)

6. 编写实体类 User

7. 编写dao UserDao

6. 入库 DataLoad