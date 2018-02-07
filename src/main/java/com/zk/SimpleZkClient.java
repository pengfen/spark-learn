package com.zk;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SimpleZkClient {

    private static final String connectString = "ricky:2181";
    private static final int sessionTimeout = 2000;

    ZooKeeper zkClient = null;

    @Before
    public void init() throws Exception {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            public void process(WatchedEvent event) {
                // 收到事件通知后的回调函数(应该是我们自己的事件处理逻辑)
                System.out.println(event.getType() + "---" + event.getPath());
                try {
                    zkClient.getChildren("/", true);
                } catch (Exception e) {
                }
            }
        });

    }

    /**
     * 数据的增删改查
     *
     * @throws InterruptedException
     * @throws KeeperException
     */

    // 创建数据节点到zk中
    public void testCreate() throws KeeperException, InterruptedException {
        // 参数1 要创建的节点的路径
        // 参数2 节点大数据
        // 参数3 节点的权限
        // 参数4 节点的类型
        String nodeCreated = zkClient.create("/eclipse", "hellozk".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    // 判断znode是否存在
    @Test
    public void testExist() throws Exception{
        Stat stat = zkClient.exists("/eclipse", false);
        System.out.println(stat==null?"not exist":"exist");


    }

    // 获取子节点
    @Test
    public void getChildren() throws Exception {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    // 获取znode的数据
    @Test
    public void getData() throws Exception {

        byte[] data = zkClient.getData("/eclipse", false, null);
        System.out.println(new String(data));

    }

    // 删除znode
    @Test
    public void deleteZnode() throws Exception {

        //
        zkClient.delete("/eclipse", -1);


    }

    // 修改数据
    @Test
    public void setData() throws Exception {

        zkClient.setData("/app1", "imissyou angelababy".getBytes(), -1);

        byte[] data = zkClient.getData("/app1", false, null);
        System.out.println(new String(data));

    }


}