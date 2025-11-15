package com.hitsz.simon.common.message;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:10
 *@Description:version1
 *@version:1.0
 */

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
@Data
@Builder
@Slf4j
public class RpcResponse implements Serializable {
    private int code;
    private String message;
    private Class<?> dataType;
    private Object data;

    public static RpcResponse success(Object data){
        log.info("success response");
        return RpcResponse.builder()
                .code(200)
                .message("success")
                .data(data)
                .dataType(data.getClass())
                .build();
    }

    public static RpcResponse fail(){
        log.info("fail...");
        return RpcResponse.builder()
                .code(500)
                .message("server error")
                .build();
    }
}
