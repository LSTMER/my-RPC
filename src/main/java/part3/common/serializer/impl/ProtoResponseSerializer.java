package part3.common.serializer.impl;/*
 *@Author:Simon
 *@Date: 2025-10-28 - 2025 10 28 18:55
 *@Description:my-RPC
 *@version:1.0
 */

import part3.common.message.RpcResponse;
import part3.common.serializer.ResponseSerializer;

public class ProtoResponseSerializer implements ResponseSerializer {
    @Override
    public byte[] serialize(RpcResponse obj) {

        return new byte[0];
    }

    @Override
    public RpcResponse deserialize(byte[] bytes) {
        return null;
    }
}
