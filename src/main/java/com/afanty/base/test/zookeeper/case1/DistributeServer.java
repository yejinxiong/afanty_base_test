package com.afanty.base.test.zookeeper.case1;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <p>
 * 服务器动态上下线--服务端注册
 * </p>
 *
 * @author yejx
 * @date 2021/12/2
 */
public class DistributeServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributeServer.class);

    private String cluster = "192.168.30.101:2181,192.168.30.102:2181,192.168.30.103:2181";

    private int timeout = 4000;

    private ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeServer distributeServer = new DistributeServer();
        // 1. 获取zk连接
        distributeServer.getConnect();

        // 2. 注册服务器到zk集群
        distributeServer.regist(args[0]);

        // 3. 启动业务逻辑
        distributeServer.doBusiness();

    }

    /**
     * 建立zookeeper连接
     *
     * @throws IOException
     */
    public void getConnect() throws IOException {
        zooKeeper = new ZooKeeper(cluster, timeout, watchedEvent -> {

        });
    }

    /**
     * 注册服务器到zk集群
     *
     * @param hostName
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void regist(String hostName) throws KeeperException, InterruptedException {
        String createMode = zooKeeper.create("/servers/" + hostName, hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("注册节点：" + createMode);
        System.out.println("服务[" + hostName + "]已上线");
    }

    /**
     * 启动业务逻辑
     */
    private void doBusiness() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }
}
