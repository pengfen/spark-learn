package hadoop.first;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * mapreduce是hadoop中的分布式运算编程框架，只要按照其编程规范，只需要编写少量的业务逻辑代码即可实现一个强大的海量数据并发处理程序
 *
 * 开发 ---> wordcount
 *
 * 1. 需求  从大量(比如T级别)文本文件中 统计出每一个单词出现的总次数
 *
 * 2. mapreduce实现
 * Map阶段
 * 从HDFS的源数据文件中逐行读取数据
 * 将每一行数据切分单词
 * 为每一个单词构造一个键值对(单词, 1)
 * 将键值对发送给reduce
 *
 * Reduce阶段
 * 接收map阶段输出的单词键值对
 * 将相同单词的键值对汇聚成一组
 * 对每一组 遍历组中的所有"值" 累加求和 即得到每个单词的总次数
 * 将(单词,总次数)输出到HDFS的文件中
 *
 * 3. 定义一个mapper类 ---> WcMapper
 *
 * 4. 定义一个reducer类 ---> WcReducer
 *
 * 5. 定义一个主类 用来描述job并提交job
 *
 * 6. 打包 mvn clean package -DskipTests
 *
 * 7. 准备数据 cat wc.txt
 * 在hdfs上创建输入数据文件夹 ---> hadoop   fs  mkdir  -p  /wordcount/input
 * 将words.txt上传到hdfs上 ---> hadoop  fs  –put  /home/hadoop/words.txt  /wordcount/input
 *
 * 8. 将程序jar包上传到集群的任意一台服务器上
 *
 * 9. 使用命令启动执行wordcount程序jar包
 * hadoop jar spark-learn-1.0.jar hadoop.first.JobRun /wordcount/input /wordcount/out
 *
 * 10. 查看执行结果
 * hadoop fs –cat /wordcount/out/part*
 */
public class JobRun {

    //把业务逻辑相关的信息（哪个是mapper，哪个是reducer，要处理的数据在哪里，输出的结果放哪里。。。。。。）描述成一个job对象
    //把这个描述好的job提交给集群去运行
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("mapred.job.tracker", "ricky:9000");
        try {
            Job job = new Job(conf);
            // job.setJar("/home/ricky/wordcount.jar"); //指定我这个job所在的jar包

            job.setJarByClass(JobRun.class);
            job.setMapperClass(WcMapper.class);
            job.setReducerClass(WcReducer.class);

            // 设置我们的业务逻辑Mapper类的输出key和value的数据类型
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            // 设置我们的业务逻辑Reducer类的输出key和value的数据类型
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            // job.setNumReduceTasks(1); // 设置 reduce 任务的个数

            // mapreduce 输入数据所在目录或者文件 ---> 指定要处理的数据所在的位置
            FileInputFormat.addInputPath(job, new Path("/usr/input/wc/"));
            // mapreduce 执行之后的输出数据的目录 ---> 指定处理完成之后的结果所保存的位置
            FileOutputFormat.setOutputPath(job, new Path("/usr/output/wc/"));

            //向yarn集群提交这个job
            boolean res = job.waitForCompletion(true);
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
