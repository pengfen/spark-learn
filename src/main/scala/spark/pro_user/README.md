根据日志统计出每个用户在站点所呆时间最长的前2个的信息

1. 编写日志产生脚本 gen_base_station.php(基站标识生成器)

2. 编写日志产生脚本 gen_bs_user.php(产生用户日志)

3. 编写定时任务每分钟产生五百条
*/1 * * * *  /usr/bin/php /home/ricky/script/spark/basic/gen_bs_user.php

4. 编写脚本每天晚十点将产生的日志移动到某目录
0 22 * * *  /home/ricky/script/spark/basic/move_bs_user.sh

5. 编写代码进行清洗 UserLocationClean

6. 入库

7. 可视化显示
