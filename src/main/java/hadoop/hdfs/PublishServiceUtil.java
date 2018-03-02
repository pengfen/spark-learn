package hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.RPC.Server;

public class PublishServiceUtil {

    public static void main(String[] args) throws Exception {
        Builder builder = new RPC.Builder(new Configuration());
        builder.setBindAddress("localhost")
                .setPort(9991)
                .setProtocol(ClientNamenodeProtocol.class)
                .setInstance(new MyNameNode());

        Server server = builder.build();
        server.start();


        Builder builder2 = new RPC.Builder(new Configuration());
        builder2.setBindAddress("localhost")
                .setPort(9999)
                .setProtocol(IUserLoginService.class)
                .setInstance(new UserLoginServiceImpl());

        Server server2 = builder2.build();
        server2.start();

    }

}
