package com.hadoop.rpc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.RPC.Server;

public class publish {

    public static void main(String[] args) throws Exception {

        Builder builder = new RPC.Builder(new Configuration());
        builder.setBindAddress("localhost").setPort(13144).setProtocol(LoginServiceInterface.class)
                .setInstance(new LoginServiceImpl());
        Server server1 = builder.build();
        System.out.println("server1启动了.....");
        server1.start();

        Builder builder2 = new RPC.Builder(new Configuration());
        builder2.setBindAddress("master").setPort(1314)
                .setProtocol(ClientNameNodeProtocol.class)
                .setInstance(new NameNodeNameSystem());
        Server server2 = builder2.build();
        System.out.println("server2启动了.....");
        server2.start();

    }


}