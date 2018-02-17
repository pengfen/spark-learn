package zk;

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

/**
 * java 操作 zookeeper API
 *
 * connect - 连接到zookeeper集合
 * create - 创建znode
 * exists - 检查znode是否存在及其信息
 * getData - 从特定的znode获取数据
 * setData - 在特定的znode中设置数据
 * getChildren - 获取特定znode中的所有子节点
 * delete - 删除特定的znode及其所有子项
 * close - 关闭连接
 */
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
     */

    /**
     * 1. 编写 testCreate 方法
     * 2. 修改配置 Edit Configuration ---> Junit ---> Name:类.方法 class 类 method 方法 (注 方法需要@Test标注,否则不显示)
     * 3. 运行方法
     * 4. 查看节点是否存在 终端 cd $ZK_HOME ---> bin/zkCli.sh -server ricky ---> get /eclipse
     * 5. 再次运行会 KeeperErrorCode = NodeExists for /eclipse
     * @throws KeeperException
     * @throws InterruptedException
     */
    // 创建数据节点到zk中
    @Test
    public void testCreate() throws KeeperException, InterruptedException {
        // 参数1 要创建的节点的路径
        // 参数2 节点大数据
        // 参数3 节点的权限
        // 参数4 节点的类型
        String nodeCreated = zkClient.create("/eclipse", "welcome_to_zookeeper_world".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 1. 编写 testExist 方法
     * 2. 修改配置 Edit Configuration ---> Junit ---> Name: 类.方法 class 类 method 方法 (注 方法需要@Test标注,否则不显示)
     * 3. 运行方法
     * @throws Exception
     */
    // 判断znode是否存在
    @Test
    public void testExist() throws Exception{
        Stat stat = zkClient.exists("/eclipse", false);
        System.out.println(stat==null?"not exist":"exist"); // exist
    }

    /**
     * 1. 编写 getChildren 方法
     * 2. 修改配置 Edit Configuration ---> Junit ---> Name: 类.方法 class 类 method 方法 (注 方法需要@Test标注,否则不显示)
     * 3. 运行方法
     * 4. 结束所有子节点 controller_epoch,brokers,zookeeper,admin,isr_change_notification,consumers,config,hbase,eclipse
     * @throws Exception
     */
    // 获取子节点
    @Test
    public void getChildren() throws Exception {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 1. 编写 getData 方法
     * 2. 修改配置 Edit Configuration ---> Junit ---> Name: 类.方法 class 类 method 方法 (注 方法需要@Test标注,否则不显示)
     * 3. 运行方法
     * 4. 结果 welcome_to_zookeeper_world
     * @throws Exception
     */
    // 获取znode的数据
    @Test
    public void getData() throws Exception {
        byte[] data = zkClient.getData("/eclipse", false, null);
        System.out.println(new String(data));
    }

    /**
     * 1. 编写 deleteZnode 方法
     * 2. 修改配置 Edit Configuration ---> Junit ---> Name: 类.方法 class 类 method 方法 (注 方法需要@Test标注,否则不显示)
     * 3. 运行方法
     * @throws Exception
     */
    // 删除znode
    @Test
    public void deleteZnode() throws Exception {
        zkClient.delete("/eclipse", -1);
    }

    /**
     * 1. 编写 setData 方法
     * 2. 修改配置 Edit Configuration ---> Junit ---> Name: 类.方法 class 类 method 方法 (注 方法需要@Test标注,否则不显示)
     * 3. 运行方法
     * 4. 节点不存在 KeeperErrorCode = NoNode for /eclipse
     * @throws Exception
     */
    // 修改数据
    @Test
    public void setData() throws Exception {
        zkClient.setData("/eclipse", "welcome_zookeeper".getBytes(), -1);

        byte[] data = zkClient.getData("/eclipse", false, null);
        System.out.println(new String(data));

    }


}