package com.bright.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Bright Xu
 * @since 2023/12/31
 */
@Component("CacheKeyGenerator")
public class CacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return generateKey(target, method, params);
    }

    /**
     * Generate a key based on the specified parameters.
     */
    public static Object generateKey(Object target, Method method, Object... params) {
        String targetClassName = target.getClass().getName();
        String methodName = method.getName();
        String key = targetClassName + "#" + methodName;
        if (params.length == 0) {
            return new SimpleKey(key);
        }
        if (params.length == 1) {
            Object param = params[0];
            if (param != null && !param.getClass().isArray()) {
                return new SimpleKey(key, param);
            }
        }
        return new SimpleKey(key, params);
    }

}
