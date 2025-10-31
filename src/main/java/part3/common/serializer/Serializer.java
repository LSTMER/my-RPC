package part3.common.serializer;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 11:35
 *@Description:my-RPC
 *@version:1.0
 */

import part3.common.serializer.impl.JsonSerializer;
import part3.common.serializer.impl.ObjectSerializer;

public interface Serializer {
    byte[] serializer(Object obj);
    Object deserializer(byte[] bytes);

    int getType();
    static Serializer getSerializer(int code){
        switch(code){
            case 0:
                return new ObjectSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;
        }
    }
}
