package com.hadoop.hive;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 对日志文件进行清洗
 * @author: 张政
 * @date: 2016年4月22日-上午8:54:55
 * @package_name: web_click_mr_hive
 * @package_name: cn.itcast.bigdata.hive.mr.pre
 */
public class WeblogPreProcess {

    static class WeblogPreProcessMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        //用来存储网站url分类数据
        Set<String> pages = new HashSet<String>();
        Text k = new Text();
        NullWritable v = NullWritable.get();

        /**
         * 从外部加载网站url分类数据
         */
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            pages.add("/about");
            pages.add("/black-ip-list/");
            pages.add("/cassandra-clustor/");
            pages.add("/finance-rhive-repurchase/");
            pages.add("/hadoop-family-roadmap/");
            pages.add("/hadoop-hive-intro/");
            pages.add("/hadoop-zookeeper-intro/");
            pages.add("/hadoop-mahout-roadmap/");

        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();
            WebLogBean webLogBean = WebLogParser.parser(line);//对资源进行解析
            // 过滤js/图片/css等静态资源
            WebLogParser.filtStaticResource(webLogBean, pages);
            /* if (!webLogBean.isValid()) return; */
            k.set(webLogBean.toString());
            context.write(k, v);
        }

    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(WeblogPreProcess.class);

        job.setMapperClass(WeblogPreProcessMapper.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

//		 FileInputFormat.setInputPaths(job, new Path(args[0]));
//		 FileOutputFormat.setOutputPath(job, new Path(args[1]));
        FileInputFormat.setInputPaths(job, new Path("D:/srcdata/webloginput"));
        FileOutputFormat.setOutputPath(job, new Path("D:/temp/temp"));

        job.setNumReduceTasks(0);

        job.waitForCompletion(true);

    }

}
