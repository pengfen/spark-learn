package hadoop.hive;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 将清洗之后的日志梳理出点击流日志
 *
 * @author
 *
 */
@Deprecated
public class ClickStream {

    static class ClickStreamMapper extends Mapper<LongWritable, Text, Text, WebLogBean> {

        Text k = new Text();
        WebLogBean v = new WebLogBean();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();

            String[] fields = line.split("\001");
            if (fields.length < 9) return;
            v.set("true".equals(fields[0]) ? true : false, fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8]);
            if(v.isValid()){
                k.set(v.getRemote_addr());
                context.write(k, v);
            }
        }
    }

    static class ClickStreamReducer extends Reducer<Text, WebLogBean, Text, Text> {

        Text v = new Text();
        TreeMap<Date, WebLogBean> treeMap = new TreeMap<Date, WebLogBean>();

        @Override
        protected void reduce(Text key, Iterable<WebLogBean> beans, Context context) throws IOException, InterruptedException {

            // 先将一个用户的所有访问记录中的时间拿出来排序
            try {
                for (WebLogBean bean : beans) {
                    treeMap.put(toDate(bean.getTime_local()), bean);
                }

                int step = 1;
                WebLogBean preBean = null;
                int count = 0;
                for (Entry<Date, WebLogBean> ent : treeMap.entrySet()) {
                    count++;
                    WebLogBean bean = ent.getValue();
                    //如果仅有一条，则直接输出
                    if (1 == treeMap.size()) {
                        // 设置默认停留市场为60s
                        v.set(bean.getRemote_user() + "\001" + bean.getTime_local() + "\001" + bean.getRequest() + "\001" + step + "\001" + (60) + "\001" + bean.getHttp_referer() + "\001" + bean.getHttp_user_agent() + "\001" + bean.getBody_bytes_sent() + "\001"
                                + bean.getStatus());
                        context.write(key, v);
                        break;
                    }


                    // 第一条直接跳过不输出
                    if (count == 1) {
                        preBean = bean;
                        continue;
                    }


                    long timeDiff = timeDiff(toDate(preBean.getTime_local()),ent.getKey());
                    // 如果本次-上次时间差<30分钟，则输出本次visit中的连续页面访问
                    if (timeDiff < 30 * 60 * 1000) {
                        v.set(preBean.getRemote_user() + "\001" + preBean.getTime_local() + "\001" + preBean.getRequest() + "\001" + step + "\001" + (timeDiff / 1000) + "\001" + preBean.getHttp_referer() + "\001" + preBean.getHttp_user_agent() + "\001"
                                + preBean.getBody_bytes_sent() + "\001" + preBean.getStatus());
                        context.write(key, v);
                        step++;
                        preBean = bean;
                    } else {
                        // 如果本次-上次时间差>30分钟，则分隔为新的visit
                        v.set(preBean.getRemote_user() + "\001" + preBean.getTime_local() + "\001" + preBean.getRequest() + "\001" + (step+1) + "\001" + (timeDiff / 1000) + "\001" + preBean.getHttp_referer() + "\001" + preBean.getHttp_user_agent() + "\001"
                                + preBean.getBody_bytes_sent() + "\001" + preBean.getStatus());
                        context.write(key, v);
                        // 输出完上一条之后，将step递增
                        step = 1;
                        preBean = bean;
                        context.write(key, v);
                    }

                    // 最后一条直接输出
                    if (count == treeMap.size()) {
                        // 设置默认停留市场为60s
                        v.set(bean.getRemote_user() + "\001" + bean.getTime_local() + "\001" + bean.getRequest() + "\001" + 1 + "\001" + (60) + "\001" + bean.getHttp_referer() + "\001" + bean.getHttp_user_agent() + "\001" + bean.getBody_bytes_sent() + "\001"
                                + bean.getStatus());
                        context.write(key, v);
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();

            }

        }

        private String toStr(Date date) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            return df.format(date);
        }

        private Date toDate(String timeStr) throws ParseException {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            return df.parse(timeStr);
        }

        private long timeDiff(String time1, String time2) throws ParseException {
            Date d1 = toDate(time1);
            Date d2 = toDate(time2);
            return d1.getTime() - d2.getTime();

        }

        private long timeDiff(Date time1, Date time2) throws ParseException {

            return time1.getTime() - time2.getTime();

        }

    }

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(ClickStream.class);

        job.setMapperClass(ClickStreamMapper.class);
        job.setReducerClass(ClickStreamReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(WebLogBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // FileInputFormat.setInputPaths(job, new Path(args[0]));
        // FileOutputFormat.setOutputPath(job, new Path(args[1]));

        FileInputFormat.setInputPaths(job, new Path("c:/weblog/clickinput"));
        FileOutputFormat.setOutputPath(job, new Path("c:/weblog/click"));

        job.waitForCompletion(true);

    }

}
