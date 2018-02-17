package com.hadoop.hdfs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;


/**
 * 用流的方式来操作hdfs上的文件
 * 可以实现读取指定偏移量范围的数据
 * @author
 *
 */
public class HdfsStreamAccess {

    FileSystem fs = null;
    Configuration conf = null;

    @Before
    public void init() throws Exception{

        conf = new Configuration();
        //拿到一个文件系统操作的客户端实例对象
//		fs = FileSystem.get(conf);
        //可以直接传入 uri和用户身份
        fs = FileSystem.get(new URI("hdfs://ricky:9000"),conf,"ricky");
    }

    /**
     * 通过流的方式上传文件到hdfs
     * 1. 编写 testUpload 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法 class 类 method 方法
     * 3. 运行方法
     * 4. 查看结果 hadoop fs -ls /
     * @throws Exception
     */
    @Test
    public void testUpload() throws Exception {

        FSDataOutputStream outputStream = fs.create(new Path("/dept.txt"), true);
        FileInputStream inputStream = new FileInputStream("/home/ricky/data/dept.txt");

        IOUtils.copy(inputStream, outputStream);
    }

    /**
     * 通过流的方式获取hdfs上数据
     * 1. 编写 testDownload 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法 class 类 method 方法
     * 3. 运行方法
     * 4. 查看结果
     * @throws Exception
     */
    @Test
    public void testDownload() throws Exception {

        FSDataInputStream inputStream = fs.open(new Path("/dept.txt"));

        FileOutputStream outputStream = new FileOutputStream("/home/ricky/data/dept1.txt");

        IOUtils.copy(inputStream, outputStream);

    }

    /**
     * 1. 编写 testRandomAccess 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法 class 类 method 方法
     * 3. 运行方法
     * 4. 查看结果 hadoop fs -ls /
     * @throws Exception
     */
    @Test
    public void testRandomAccess() throws Exception{

        FSDataInputStream inputStream = fs.open(new Path("/dept1.txt"));

        inputStream.seek(12);

        FileOutputStream outputStream = new FileOutputStream("/home/ricky/data/dept.txt");

        IOUtils.copy(inputStream, outputStream);
    }

    /**
     * 显示hdfs上文件的内容 hadoop fs -cat /dept.txt
     * 1. 编写 testCat 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法 class 类 method 方法
     * 3. 运行方法 (会输出文件内容)
     * 10	ACCOUNTING	NEW YORK
     *
     * @throws IOException
     * @throws IllegalArgumentException
     */
    @Test
    public void testCat() throws IllegalArgumentException, IOException{

        FSDataInputStream in = fs.open(new Path("/dept.txt"));

        IOUtils.copy(in, System.out);

//		IOUtils.copyBytes(in, System.out, 1024);
    }
}

