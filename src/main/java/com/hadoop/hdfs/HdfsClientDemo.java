package com.hadoop.hdfs;

import java.net.URI;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * 客户端去操作hdfs时，是有一个用户身份的
 * 默认情况下，hdfs客户端api会从jvm中获取一个参数来作为自己的用户身份：-DHADOOP_USER_NAME=ricky
 *
 * 也可以在构造客户端fs对象时，通过参数传递进去
 * @author
 *
 */
public class HdfsClientDemo {
    FileSystem fs = null;
    Configuration conf = null;
    @Before
    public void init() throws Exception{

        conf = new Configuration(); // 可以设置 cone-site.xml ... 文件中的参数
        //conf.set("fs.defaultFS", "hdfs://ricky:9000");
        conf.set("dfs.replication", "5");

        //拿到一个文件系统操作的客户端实例对象
        /*fs = FileSystem.get(conf);*/
        //可以直接传入 uri和用户身份
        fs = FileSystem.get(new URI("hdfs://ricky:9000"),conf,"ricky"); //最后一个参数为用户名
    }

    /**
     * 上传文件  hadoop fs -put wel.txt /access.log.copy
     * 1. 编写 testUpload 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法 class 类 method 方法
     * 3. 启动dfs  cd $HADOOP_HOME ---> sbin/start-dfs.sh
     * 4. 运行方法
     * 5. 查看是否上传成功 hadoop fs -ls /
     * @throws Exception
     */
    @Test
    public void testUpload() throws Exception {
        Thread.sleep(2000);
        fs.copyFromLocalFile(new Path("/home/ricky/data/wel.txt"), new Path("/access.log.copy"));
        fs.close();
    }

    /**
     * 如果访问的是一个ha机制的集群
     * 则一定要把core-site.xml和hdfs-site.xml配置文件放在客户端程序的classpath下 (src/core-site.xml ...)
     * 以让客户端能够理解hdfs://ns1/中  “ns1”是一个ha机制中的namenode对——nameservice
     * 以及知道ns1下具体的namenode通信地址
     */
    @Test
    public void testUploadHA() throws Exception{

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://ns1/");

        FileSystem fs = FileSystem.get(new URI("hdfs://ns1/"),conf,"ricky");

        fs.copyFromLocalFile(new Path("g:/eclipse-jee-luna-SR1-linux-gtk.tar.gz"), new Path("hdfs://ns1/"));

        fs.close();
    }


    /**
     * 下载文件
     * 1. 编写 testDownload 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法  class 类  method 方法
     * 3. 运行方法
     * 4. 查看是否下载成功
     * @throws Exception
     */
    @Test
    public void testDownload() throws Exception {
        fs.copyToLocalFile(new Path("/access.log.copy"), new Path("/home/ricky/data/wel2.txt"));
        fs.close();
    }

    /**
     * 打印参数
     * 1. 编写 testConf 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法  class 类  method 方法
     * 3. 运行方法
     */
    @Test
    public void testConf(){
        Iterator<Entry<String, String>> iterator = conf.iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            System.out.println(entry.getValue() + "--" + entry.getValue()); // conf加载的内容
        }
    }

    /**
     * 创建目录 hadoop fs -mkdir /aaa/bbb
     * 1. 编写 mkdirTest 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法  class 类  method 方法
     * 3. 运行方法
     * 4. hadoop fs -ls /
     */
    @Test
    public void mkdirTest() throws Exception {
        boolean mkdirs = fs.mkdirs(new Path("/aaa/bbb"));
        System.out.println(mkdirs); // true
    }

    /**
     * 删除目录 hadoop fs -rmr /aaa
     * 1. 编写 deleteTest 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法  class 类  method 方法
     * 3. 运行方法
     * 4. hadoop fs -ls /
     */
    @Test
    public void deleteTest() throws Exception{
        boolean delete = fs.delete(new Path("/aaa"), true);//true， 递归删除
        System.out.println(delete); // true
    }

    /**
     * 递归列出指定目录下所有子文件夹中的文件
     * 查看dfs上所有的文件 hadoop fs -ls /
     * 1. 编写 listTest 方法
     * 2. 配置 Edit Configuration ---> Junit ---> name: 类.方法  class 类  method 方法
     * 3. 运行方法
     * 4. 查看结果
     * person.txt---hdfs://ricky:9000/person.txt
     * wel.txt---hdfs://ricky:9000/wc/wel.txt
     * @throws Exception
     */
    @Test
    public void listTest() throws Exception{

        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus) {
            System.err.println(fileStatus.getPath()+"================="+fileStatus.toString());
        }
        //会递归找到所有的文件
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while(listFiles.hasNext()){
            LocatedFileStatus next = listFiles.next();
            String name = next.getPath().getName();
            Path path = next.getPath();
            System.out.println(name + "---" + path.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://ricky:9000");
        //拿到一个文件系统操作的客户端实例对象
        FileSystem fs = FileSystem.get(conf);

        fs.copyFromLocalFile(new Path("/home/ricky/data/access.log"), new Path("/access.log.copy"));
        fs.close();
    }

}
