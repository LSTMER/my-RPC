package part3.common.message;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:10
 *@Description:version1
 *@version:1.0
 */

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
@Data
@Builder
@Slf4j
public class RpcResponse implements Serializable {
    private int code;
    private String message;

    private Object data;

    public static RpcResponse success(Object data){
        log.info("success response");
        return RpcResponse.builder()
                .code(200)
                .message("success")
                .data(data)
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
