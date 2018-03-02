package hadoop.hdfs;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;

/**
 * 测试获取配置相关信息
 */
public class ConfigurationReasearch {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        conf.addResource("test.xml");
        System.out.println(conf.get("xxx.uu")); // null

		Iterator<Entry<String, String>> it = conf.iterator();

		while(it.hasNext()){

		    // 打印出所有的配置信息
		    // hadoop.security.groups.cache.secs=300
			System.out.println(it.next());

		}

    }

}
