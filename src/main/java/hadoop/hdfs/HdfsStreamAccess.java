package hadoop.hdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;


/**
 * 用流的方式来操作hdfs上的文件
 * 可以实现读取指定偏移量范围的数据
 *
 * stream
 * 相对那些封装好的方法而言的更底层一些的操作方式
 * 上层那些mapreduce   spark等运算框架，去hdfs中获取数据的时候，就是调的这种底层的api
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
     * 注意 IOUtils 与 testUpload方法中的不一样
     * 1. 编写 testUploadByStream 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法 class 类 method 方法
     * 3. 运行方法
     * 4. 查看结果
     * @throws Exception
     */
    @Test
    public void testUploadByStream() throws Exception{

        //hdfs文件的输出流
        FSDataOutputStream fsout = fs.create(new Path("/dept.txt"));

        //本地文件的输入流
        FileInputStream fsin = new FileInputStream("/home/ricky/data/dept.txt");

        org.apache.hadoop.io.IOUtils.copyBytes(fsin, fsout,4096);

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
        //先获取一个文件的输入流----针对hdfs上的
        FSDataInputStream inputStream = fs.open(new Path("/dept.txt"));

        //再构造一个文件的输出流----针对本地的
        FileOutputStream outputStream = new FileOutputStream("/home/ricky/data/dept1.txt");

        //再将输入流中数据传输到输出流
        IOUtils.copy(inputStream, outputStream);

    }

    /**
     * 注意 IOUtils 与 testDownLoad方法中的不一样
     * 1. 编写 testDownLoadFileToLocal 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法 class 类 method 方法
     * 3. 运行方法
     * 4. 查看结果
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @Test
    public void testDownLoadFileToLocal() throws IllegalArgumentException, IOException{
        //先获取一个文件的输入流----针对hdfs上的
        FSDataInputStream in = fs.open(new Path("/jdk-7u65-linux-i586.tar.gz"));

        //再构造一个文件的输出流----针对本地的
        FileOutputStream out = new FileOutputStream(new File("/home/ricky/data/jdk.tar.gz"));

        //再将输入流中数据传输到输出流
        org.apache.hadoop.io.IOUtils.copyBytes(in, out, 4096);

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
     * hdfs支持随机定位进行文件读取，而且可以方便地读取指定长度
     * 用于上层分布式运算框架并发处理数据
     * @throws IllegalArgumentException
     * @throws IOException
     */
    @Test
    public void testRandomAccessStream() throws IllegalArgumentException, IOException{

        FSDataInputStream in = fs.open(new Path("/dept.txt"));//先获取一个文件的输入流----针对hdfs上的
        in.seek(22);	//可以将流的起始偏移量进行自定义
        FileOutputStream out = new FileOutputStream(new File("/home/ricky/data/dept.txt"));//再构造一个文件的输出流----针对本地的
        org.apache.hadoop.io.IOUtils.copyBytes(in,out,19L,true);	//apache的工具类， 3.缓冲大小，4.是否关闭流

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

    /**
     * 读取指定的block
     * @throws IOException
     * @throws IllegalArgumentException
     */
    @Test
    public void testCatStream() throws IllegalArgumentException, IOException{
        FSDataInputStream in = fs.open(new Path("/input/dept.txt"));
        //拿到文件信息
        FileStatus[] listStatus = fs.listStatus(new Path("/input/dept.txt"));
        //获取这个文件的所有block的信息
        BlockLocation[] fileBlockLocations = fs.getFileBlockLocations(listStatus[0], 0L, listStatus[0].getLen());


        //第一个block的长度
        long length = fileBlockLocations[0].getLength();
        //第一个block的起始偏移量
        long offset = fileBlockLocations[0].getOffset();

        System.out.println(length);
        System.out.println(offset);

        //获取第一个block写入输出流
//		IOUtils.copyBytes(in, System.out, (int)length);
        byte[] b = new byte[4096];

        FileOutputStream os = new FileOutputStream(new File("d:/block0"));
        while(in.read(offset, b, 0, 4096)!=-1){
            os.write(b);
            offset += 4096;
            if(offset>length) return;
        };

        os.flush();
        os.close();
        in.close();

    }
}

