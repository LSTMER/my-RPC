package part3.common.serializer.impl;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 11:37
 *@Description:my-RPC
 *@version:1.0
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import part3.common.jsonUtils.JsonUtils;
import part3.common.message.RpcRequest;
import part3.common.message.RpcResponse;
import part3.common.serializer.Serializer;

import java.io.IOException;

public class JsonSerializer implements Serializer {
    @Override
    public byte[] serializer(Object obj) {
        try {
            return JsonUtils.toBytes(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserializer(byte[] bytes, Class<T> clazz) {
        try {
            Object o = JsonUtils.bytesToObject(bytes, clazz);
            if(clazz.equals(RpcResponse.class)){
                RpcResponse response = (RpcResponse) o;
                Class<?> dataType = response.getDataType();
                if(!dataType.isAssignableFrom(response.getData().getClass())){
                    response.setData(JsonUtils.convertValue(response.getData(), dataType));
                }
                return (T)response;
            }else{
                RpcRequest request = (RpcRequest) o;
                Object[] objects = new Object[request.getParams().length];
                for(int i=0;i<objects.length;i++){
                    Class<?> paramType = request.getParamsType()[i];
                    if(!paramType.isAssignableFrom(request.getParams()[i].getClass())){
                        objects[i] = JsonUtils.convertValue(request.getParams()[i], paramType);
                    }else {
                        objects[i] = request.getParams()[i];
                    }
                }
                request.setParams(objects);
                return (T)request;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getType() {
        return 0;
    }
}
