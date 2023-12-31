package com.bright.config;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author Bright Xu
 * @since 2023/12/31
 */
@EnableCaching
@Configuration
@ConditionalOnClass(Caffeine.class)
public class CommonCacheConfig {
    @Bean
    public CacheManager cacheManager(ObjectProvider<CacheLoader<Object, Object>> cacheLoader) {
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .initialCapacity(64);

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);
        cacheLoader.ifAvailable(cacheManager::setCacheLoader);
        return cacheManager;
    }
}
