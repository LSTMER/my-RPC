package com.hitsz.simon.common.loadbalance.impl;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 11:20
 *@Description:my-RPC
 *@version:1.0
 */

import com.hitsz.simon.common.loadbalance.LoadBalance;

import java.util.List;

public class RoundBalance implements LoadBalance {

    private int count = 0;
    @Override
    public String balance(List<String> addressList) {
        int next = count % addressList.size();
        count++;
        return addressList.get(next);
    }
}
