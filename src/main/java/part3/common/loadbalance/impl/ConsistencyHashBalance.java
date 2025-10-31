package part3.common.loadbalance.impl;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 11:19
 *@Description:my-RPC
 *@version:1.0
 */

import part3.common.loadbalance.LoadBalance;

import java.util.List;

public class ConsistencyHashBalance implements LoadBalance {
    @Override
    public String balance(List<String> addressList) {
        return "";
    }

    @Override
    public void addNode(String node) {

    }

    @Override
    public void removeNode(String node) {

    }
}
