package com.hadoop.hdfs;

import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class MyHdfsClient {

    public static void main(String[] args) throws Exception {
        ClientNamenodeProtocol namenode = RPC.getProxy(ClientNamenodeProtocol.class, 1L,
                new InetSocketAddress("localhost", 8888), new Configuration());
        String metaData = namenode.getMetaData("/angela.mygirl");
        System.out.println(metaData);
    }

}
