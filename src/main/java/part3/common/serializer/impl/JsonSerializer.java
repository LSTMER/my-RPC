package part3.common.serializer.impl;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 11:37
 *@Description:my-RPC
 *@version:1.0
 */

import part3.common.serializer.Serializer;

public class JsonSerializer implements Serializer {
    @Override
    public byte[] serializer(Object obj) {
        return new byte[0];
    }

    @Override
    public Object deserializer(byte[] bytes) {
        return null;
    }

    @Override
    public int getType() {
        return 0;
    }
}
