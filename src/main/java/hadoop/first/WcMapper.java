package hadoop.first;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 定义四个泛型的类型
 * key_in: LongWritable  value_in Text
 * key_out: Text         value_out IntWritable
 */
public class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    /**
     * 调用 map 方法全传入 split 中一行数据 key 该行数据所有文件中的位置下标 value 这行行数据
     *
     * 方法的生命周期　框架每传一行数据就被调用一次
     * @param key 这一行的起始点在文件中的偏移量
     * @param value 这一行的内容
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        // 拿到一行数据并转换为string
        String line = value.toString();

        // 将这一行切分出各个单词
        //String[] words = line.split(" ");
        String[] words = line.split(",");

        //遍历数组 输出<单词, 1>
        for (String word:words) {
            context.write(new Text(word), new IntWritable(1));
        }
//        StringTokenizer token = new StringTokenizer(line);
//        while (token.hasMoreTokens()) {
//            String world = token.nextToken();
//            context.write(new Text(world), new IntWritable(1)); // map的输出
//        }
    }
}