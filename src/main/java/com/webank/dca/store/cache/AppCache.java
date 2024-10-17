package com.webank.dca.store.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

public class AppCache {

    private static Cache<String,Integer> appInfoCache = CacheBuilder.newBuilder()
            .initialCapacity(10)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();

    public static Cache<String,Integer> getAppCache(){
        return appInfoCache;
    }
}
