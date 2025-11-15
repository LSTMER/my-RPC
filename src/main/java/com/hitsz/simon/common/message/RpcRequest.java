package com.hitsz.simon.common.message;/*
 *@Author:Simon
 *@Date: 2025-09-25 - 2025 09 25 9:10
 *@Description:version1
 *@version:1.0
 */

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class RpcRequest implements Serializable {
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramsType;
}
