package com.hitsz.simon.common.loadbalance.impl;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 11:20
 *@Description:my-RPC
 *@version:1.0
 */

import com.hitsz.simon.common.loadbalance.LoadBalance;

import java.util.List;
import java.util.Random;

public class RandomBalance implements LoadBalance {
    @Override
    public String balance(List<String> addressList) {
        Random random = new Random();
        int size = addressList.size();
        int num = random.nextInt(size);
        return addressList.get(num);
    }
}
