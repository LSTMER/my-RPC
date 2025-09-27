package part3.Client.client;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 14:15
 *@Description:version1
 *@version:1.0
 */

import part3.common.message.RpcRequest;
import part3.common.message.RpcResponse;

public interface RpcClient {
    RpcResponse sendRequest(RpcRequest request);
}
