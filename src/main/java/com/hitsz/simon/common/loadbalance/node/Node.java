package com.hitsz.simon.common.loadbalance.node;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 15:54
 *@Description:my-RPC
 *@version:1.0
 */

import com.hitsz.simon.common.hashUtil.HashUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Getter
public class Node{
    static final int VIRTUAL_NODE_NUM = 3;
    private final List<Integer> virtualNodeHash = new ArrayList<>(VIRTUAL_NODE_NUM);
    private final String ip;
    public Node(String ip){
        Objects.requireNonNull(ip);
        this.ip = ip;
        initVirNode();
    }

    private void initVirNode() {
        String virNodeName;
        for(int i=0;i<VIRTUAL_NODE_NUM;i++){
            virNodeName = ip + "##VM" + i;
            virtualNodeHash.add(HashUtils.hashcode(virNodeName));
        }
    }

}