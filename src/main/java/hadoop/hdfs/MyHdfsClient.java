package hadoop.hdfs;

import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class MyHdfsClient {

    public static void main(String[] args) throws Exception {
        ClientNamenodeProtocol namenode = RPC.getProxy(ClientNamenodeProtocol.class, 1L,
                new InetSocketAddress("localhost", 9991), new Configuration());
        String metaData = namenode.getMetaData("/dept.txt");
        System.out.println(metaData);
    }

}
