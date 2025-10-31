package part3.common.serializer;/*
 *@Author:Simon
 *@Date: 2025-10-28 - 2025 10 28 18:54
 *@Description:my-RPC
 *@version:1.0
 */

import part3.common.message.RpcResponse;

public interface ResponseSerializer {
    byte[] serialize(RpcResponse obj);
    RpcResponse deserialize(byte[] bytes);
}
