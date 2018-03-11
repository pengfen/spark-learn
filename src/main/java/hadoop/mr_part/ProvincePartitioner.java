package hadoop.mr_part;

import java.util.HashMap;

import hadoop.mr_seri.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 定义自己的从map到reduce之间的数据(分组)分发规则
 * 按照手机号所属的省份来分发(分组)ProvincePartitioner
 *
 * 默认的分组组件是HashPartitioner
 *
 * K2  V2  对应的是map输出kv的类型
 */
public class ProvincePartitioner extends Partitioner<Text, FlowBean>{

    public static HashMap<String, Integer> proviceDict = new HashMap<String, Integer>();
    static{
        proviceDict.put("136", 0);
        proviceDict.put("137", 1);
        proviceDict.put("138", 2);
        proviceDict.put("139", 3);
    }

    @Override
    public int getPartition(Text key, FlowBean value, int numPartitions) {
        String prefix = key.toString().substring(0, 3);
        Integer provinceId = proviceDict.get(prefix);

        return provinceId==null?4:provinceId;
    }

}
