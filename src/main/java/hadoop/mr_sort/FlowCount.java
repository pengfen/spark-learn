package hadoop.mr_sort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 统计每一个用户(手机号)所耗费的总上行流量 下行流量 总流量
 * map 读一行 切分字段
 * 抽取手机号 上行流量 下行流量
 * context.write(手机号, bean)
 *
 * 1. 编写代码
 *
 * 2. 上传输入文件
 * flow.log文件内容 (数据源 /home/ricky/data/hadoop/flow.log)
 * 1363157985066 	13726230503	00-FD-07-A4-72-B8:CMCC	120.196.100.82	i02.c.aliimg.com		24	27	2481	24681	200
 * hadoop fs -mkdir -p /flow/in
 * hadoop fs -put flow.log /flow/in/
 * hadoop fs -cat /flow/in/flow.log
 *
 * 3. 打包
 * mvn clean package -DskipTests
 *
 * 4. 运行jar至服务器
 * cp target/spark-learn-1.0.jar /home/ricky/spark-jar/
 *
 * 5. 运行
 * hadoop jar spark-learn-1.0.jar hadoop.mr_seri.FlowCount /flow/in /flow/out
 *
 * 6. 查看结果
 * hadoop fs -cat /flow/out/part*
 */
public class FlowCount {

    static class FlowCountMapper extends Mapper<LongWritable, Text, FlowBean,Text > {

        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();
            String[] fields = line.split("\t");
            try {
                String phonenbr = fields[0];

                long upflow = Long.parseLong(fields[1]);
                long dflow = Long.parseLong(fields[2]);

                FlowBean flowBean = new FlowBean(upflow, dflow);

                context.write(flowBean,new Text(phonenbr));
            } catch (Exception e) {

                e.printStackTrace();
            }

        }

    }

    static class FlowCountReducer extends Reducer<FlowBean,Text,Text, FlowBean> {

        @Override
        protected void reduce(FlowBean bean, Iterable<Text> phonenbr, Context context) throws IOException, InterruptedException {

            Text phoneNbr = phonenbr.iterator().next();

            context.write(phoneNbr, bean);

        }

    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(FlowCount.class);

        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);

        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        // job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean res = job.waitForCompletion(true);
        System.exit(res?0:1);
    }

}