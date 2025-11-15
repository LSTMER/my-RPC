package com.hitsz.simon.Client.ZKWatcher;/*
 *@Author:Simon
 *@Date: 2025-10-31 - 2025 10 31 12:50
 *@Description:my-RPC
 *@version:1.0
 */

import com.hitsz.simon.Client.cache.ServiceCache;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;

import java.util.Collections;

public class Watcher {
    private final CuratorFramework client;
    private final ServiceCache cache;

    public Watcher(ServiceCache cache, CuratorFramework client) {
        this.client = client;
        this.cache = cache;
    }

    public void startWatching() {
        CuratorCache curatorCache = CuratorCache.builder(client, "/")
                        .build();

        curatorCache.listenable().addListener(new CuratorCacheListener() {
            @Override
            public void event(Type type, ChildData oldChildData, ChildData newChildData) {
                String[] newPathList = parsePath(newChildData);
                String[] oldPathList = parsePath(oldChildData);
                switch (type) {
                    case NODE_CHANGED:
                        cache.replaceService(newPathList[1], oldPathList[2], newPathList[2]);
                        break;
                    case NODE_CREATED:
                        cache.addService(newPathList[1], Collections.singletonList(newPathList[2]));
                        break;
                    case NODE_DELETED:
                        cache.delete(oldPathList[1], oldPathList[2]);
                        break;
                    default:
                        break;
                }
            }
        });
        curatorCache.start();
    }
    private String[] parsePath(ChildData childData){
        String path = childData.getPath();
        return path.split("/");
    }
}
