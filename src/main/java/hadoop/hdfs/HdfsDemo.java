package hadoop.hdfs;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsDemo {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
//		conf.set("fs.defaultFS","hdfs://ricky:9000");
//		FileSystem fs = FileSystem.get(conf);

        FileSystem fs = FileSystem.get(new URI("hdfs://ricky:9000"), conf, "ricky");

//		fs.copyFromLocalFile(new Path("/home/ricky/data/111.txt"), new Path("/111.txt"));

//		fs.copyToLocalFile(new Path("/111.txt"), new Path("/home/ricky/data/222.txt"));

        //参数4：是否使用原生的java操作本地文件系统;如果为false，则使用winutils；如果为true，则用java操作
        fs.copyToLocalFile(false, new Path("/111.txt"), new Path("/home/ricky/data/233.txt"), true);

        fs.close();

    }

}
