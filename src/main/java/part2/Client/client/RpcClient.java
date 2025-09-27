package part2.Client.client;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 14:15
 *@Description:version1
 *@version:1.0
 */

import part2.common.message.RpcResponse;
import part2.common.message.RpcRequest;

public interface RpcClient {
    RpcResponse sendRequest(RpcRequest request);
}
