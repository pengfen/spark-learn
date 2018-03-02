package hadoop.first;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JobRun {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("mapred.job.tracker", "ricky:9000");
        try {
            Job job = new Job(conf);
            job.setJarByClass(JobRun.class);
            job.setMapperClass(WcMapper.class);
            job.setReducerClass(WcReducer.class);
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            // job.setNumReduceTasks(1); // 设置 reduce 任务的个数

            // mapreduce 输入数据所在目录或者文件
            FileInputFormat.addInputPath(job, new Path("/usr/input/wc/"));
            // mapreduce 执行之后的输出数据的目录
            FileOutputFormat.setOutputPath(job, new Path("/usr/output/wc/"));

            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
