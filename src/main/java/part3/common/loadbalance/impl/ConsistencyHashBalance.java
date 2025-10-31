package part3.common.loadbalance.impl;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 11:19
 *@Description:my-RPC
 *@version:1.0
 */

import part3.common.hashUtil.HashUtils;
import part3.common.loadbalance.LoadBalance;
import part3.common.loadbalance.node.Node;

import java.util.List;
import java.util.TreeMap;


public class ConsistencyHashBalance implements LoadBalance {


    private final TreeMap<Integer, Node> treeMap = new TreeMap<>();

    public void init(List<String> addressList){
        for(String ip : addressList){
            Node node = new Node(ip);
            for(Integer virHash : node.getVirtualNodeHash()){
                treeMap.put(virHash, node);
            }
        }
    }

    @Override
    public String balance(List<String> addressList) {
        String ip = getHostBasedId();
        int hash = HashUtils.hashcode(ip);
        Node node = treeMap.ceilingEntry(hash).getValue();
        return node.getIp();
    }

    public void addNode(Node node) {
        for(Integer virHash : node.getVirtualNodeHash()){
            treeMap.put(virHash, node);
        }
    }

    public void removeNode(Node node) {
        for(Integer virHash : node.getVirtualNodeHash()){
            treeMap.remove(virHash);
        }
    }

    public static String getHostBasedId() {
        try {
            java.net.InetAddress local = java.net.InetAddress.getLocalHost();
            return local.getHostName() + "@" + local.getHostAddress();
        } catch (Exception e) {
            return "unknown@127.0.0.1";
        }
    }
}


