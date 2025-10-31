package part3.common.loadbalance;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 11:17
 *@Description:my-RPC
 *@version:1.0
 */

import java.util.List;

public interface LoadBalance {
    String balance(List<String> addressList);
    void addNode(String node);
    void removeNode(String node);
}
