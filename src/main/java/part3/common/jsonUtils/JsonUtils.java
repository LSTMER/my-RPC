package part3.common.jsonUtils;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 18:07
 *@Description:my-RPC
 *@version:1.0
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper getInstance(){
        return mapper;
    }

    public static byte[] toBytes(Object object) throws JsonProcessingException {
        return mapper.writeValueAsBytes(object);
    }

    public static Object bytesToObject(byte[] bytes, Class<?> clazz) throws IOException {
        return mapper.readValue(bytes, clazz);
    }

    public static <T> T convertValue(Object object, Class<T> clazz){
        return mapper.convertValue(object, clazz);
    }
}
