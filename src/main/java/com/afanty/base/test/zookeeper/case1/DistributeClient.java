package com.afanty.base.test.zookeeper.case1;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务器动态上下线--客户端监听
 * </p>
 *
 * @author yejx
 * @date 2021/12/2
 */
public class DistributeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributeClient.class);

    private String cluster = "192.168.30.101:2181,192.168.30.102:2181,192.168.30.103:2181";

    private int timeout = 4000;

    private ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeClient distributeClient = new DistributeClient();
        // 1. 获取zk连接
        distributeClient.getConnect();

        // 2. 监听/servers限免子节点的增加和删除
        distributeClient.getServerList();

        // 3. 业务逻辑
        distributeClient.doBusiness();
    }

    /**
     * 建立zookeeper连接
     *
     * @throws IOException
     */
    private void getConnect() throws IOException {
        zooKeeper = new ZooKeeper(cluster, timeout, watchedEvent -> {
            try {
                getServerList();
            } catch (KeeperException e) {
                LOGGER.error("获取zk连接异常KeeperException：{}", e.getMessage());
            } catch (InterruptedException e) {
                LOGGER.error("获取zk连接异常InterruptedException：{}", e.getMessage());
            }
        });

    }

    /**
     * 获取在线服务列表
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void getServerList() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/servers", true);
        List<String> servers = new ArrayList<>();
        for (String child : children) {
            byte[] data = zooKeeper.getData("/servers/" + child, false, null);
            servers.add(new String(data));
        }
        LOGGER.info("当前在线服务：{}", servers);
    }

    /**
     * 业务逻辑
     */
    private void doBusiness() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }
}
