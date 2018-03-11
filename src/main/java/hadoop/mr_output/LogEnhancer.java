package hadoop.mr_output;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.HashMap;

/**
 * 这个程序是对每个小时不断产生的用户上网记录日志进行增强(将日志中的url所指向的网页内容分析结果信息追加到每一行原始日志后面)
 *
 * @author
 *
 */
public class LogEnhancer {

    static class LogEnhancerMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

        HashMap<String, String> knowledgeMap = new HashMap<String, String>();

        /**
         * maptask在初始化时会先调用setup方法一次 利用这个机制，将外部的知识库加载到maptask执行的机器内存中
         */
        @Override
        protected void setup(org.apache.hadoop.mapreduce.Mapper.Context context) throws IOException, InterruptedException {

            DBLoader.dbLoader(knowledgeMap);

        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();

            String[] fields = StringUtils.split(line, "\t");

            try {
                String url = fields[26];

                // 对这一行日志中的url去知识库中查找内容分析信息
                String content = knowledgeMap.get(url);

                // 根据内容信息匹配的结果，来构造两种输出结果
                String result = "";
                if (null == content) {
                    // 输往待爬清单的内容
                    result = url + "\t" + "tocrawl\n";
                } else {
                    // 输往增强日志的内容
                    result = line + "\t" + content + "\n";
                }

                context.write(new Text(result), NullWritable.get());
            } catch (Exception e) {

            }
        }

    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        Job job = Job.getInstance(conf);

        job.setJarByClass(LogEnhancer.class);

        job.setMapperClass(LogEnhancerMapper.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        // 要将自定义的输出格式组件设置到job中
        job.setOutputFormatClass(LogEnhancerOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));

        // 虽然我们自定义了outputformat，但是因为我们的outputformat继承自fileoutputformat
        // 而fileoutputformat要输出一个_SUCCESS文件，所以，在这还得指定一个输出目录
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
        System.exit(0);

    }

}
