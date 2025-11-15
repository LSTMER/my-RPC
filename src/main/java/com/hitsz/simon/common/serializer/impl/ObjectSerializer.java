package com.hitsz.simon.common.serializer.impl;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 11:37
 *@Description:my-RPC
 *@version:1.0
 */

import com.hitsz.simon.common.serializer.Serializer;

import java.io.*;

public class ObjectSerializer implements Serializer {

    @Override
    public byte[] serializer(Object obj) {
        byte[] bytes = null;
        try (
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
             ){
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public <T> T deserializer(byte[] bytes, Class<T> clazz) {
        Object object = null;
        try(ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis)){
            object = ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return (T)object;
    }

    @Override
    public int getType() {
        return 0;
    }
}
