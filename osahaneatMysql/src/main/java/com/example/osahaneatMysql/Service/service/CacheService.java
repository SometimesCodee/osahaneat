package com.example.osahaneatMysql.Service.service;

import com.example.osahaneatMysql.Service.imp.CacheServiceImp;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService implements CacheServiceImp {
    private final CacheManager cacheManager;

    @Autowired
    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    @Override
    public void clearAllCaches() {
        System.out.println("Clearing all caches");
        cacheManager.getCacheNames().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
}
