package com.hitsz.simon.Client.client;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 14:15
 *@Description:version1
 *@version:1.0
 */

import com.hitsz.simon.common.message.RpcRequest;
import com.hitsz.simon.common.message.RpcResponse;

public interface RpcClient {
    RpcResponse sendRequest(RpcRequest request);
}
