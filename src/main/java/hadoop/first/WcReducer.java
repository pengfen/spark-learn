package hadoop.first;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 *
 */
public class WcReducer extends
        Reducer<Text, IntWritable, Text, IntWritable> {

    // 生命周期　框架每传递进来一个kv组 reduce方法被调用一次
    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context) throws IOException, InterruptedException {
        // 累计计算
        int sum = 0; // 初始化总值 ---> 定义一个计数器

        // IntWritable 迭代器 ---> 遍历这一组kv的所有v　累加到count中
        for (IntWritable val : values) {
            sum += val.get();
        }
        context.write(key, new IntWritable(sum));
    }
}