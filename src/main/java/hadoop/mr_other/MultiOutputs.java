package hadoop.mr_other;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 计算器
 *
 * 在实际生产代码中，常常需要将数据处理过程中遇到的不合规数据行进行全局计数，类似这种需求可以借助mapreduce框架中提供的全局计数器来实现
 */
public class MultiOutputs {
    //通过枚举形式定义自定义计数器
    enum MyCounter {
        MALFORORMED, NORMAL
    }

    static class CommaMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String[] words = value.toString().split(",");

            for (String word : words) {
                context.write(new Text(word), new LongWritable(1));
            }
            //对枚举定义的自定义计数器加1
            context.getCounter(MyCounter.MALFORORMED).increment(1);
            //通过动态设置自定义计数器加1
            context.getCounter("counterGroupa", "countera").increment(1);
        }

    }
}