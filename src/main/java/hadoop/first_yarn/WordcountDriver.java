package hadoop.first_yarn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 相当于一个yarn集群的客户端
 * 需要在此封装我们的mr程序的相关运行参数，指定jar包
 * 最后提交给yarn
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
 * 3. 定义一个mapper类 ---> WordcountMapper
 *
 * 4. 定义一个reducer类 ---> WordcountReducer
 *
 * 5. 定义一个主类 用来描述job并提交job
 *
 * 6. 打包 mvn clean package -DskipTests
 *
 * 7. 准备数据 cat wc.txt (/home/ricky/data/spark/basic/wx.txt)
 * 在hdfs上创建输入数据文件夹 ---> hadoop fs -mkdir -p /wc/input
 * 将wc.txt上传到hdfs上 ---> hadoop fs –put wc.txt /wc/input
 *
 * 8. 将程序jar包上传到集群的任意一台服务器上
 * cp target/spark-learn-1.0.jar ~/spark-jar/
 *
 * 9. 使用命令启动执行wordcount程序jar包
 * hadoop jar spark-learn-1.0.jar hadoop.first_yarn.WordcountDriver hdfs://ricky:9000/wc/input/wc.txt hdfs://ricky:9000/wc/out
 *
 * 10. 查看执行结果
 * hadoop fs –cat /wc/out/part*
 */
public class WordcountDriver {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            // hdfs://ricky:9000/wc/input/wc.txt hdfs://ricky:9000/wc/out
            System.err.println("Usage: input_path output_path ");
            System.exit(1);
        }

//        if (args == null || args.length == 0) {
//            args = new String[2];
//            args[0] = "hdfs://ricky:9000/wc/in/wc.txt";
//            args[1] = "hdfs://ricky:9000/wc/out";
//        }

        Configuration conf = new Configuration();

        //设置的没有用!  ??????
//		conf.set("HADOOP_USER_NAME", "hadoop");
//		conf.set("dfs.permissions.enabled", "false");


		/*conf.set("mapreduce.framework.name", "yarn");
		conf.set("yarn.resoucemanager.hostname", "mini1");*/
        Job job = Job.getInstance(conf);

        /*job.setJar("/home/hadoop/wc.jar");*/
        //指定本程序的jar包所在的本地路径
        job.setJarByClass(WordcountDriver.class);

        //指定本业务job要使用的mapper/Reducer业务类
        job.setMapperClass(WordcountMapper.class);
        job.setReducerClass(WordcountReducer.class);

        //指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //指定最终输出的数据的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //指定job的输入原始文件所在目录
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        //指定job的输出结果所在目录
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //将job中配置的相关参数，以及job所用的java类所在的jar包，提交给yarn去运行
        /*job.submit();*/
        boolean res = job.waitForCompletion(true);
        System.exit(res?0:1);

    }


}
