package com.bright.quartz.service.impl;

import com.bright.quartz.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Bright Xu
 * @since 2023/8/5
 */
@Service
@Slf4j
public class MyServiceImpl implements MyService {
    private static final AtomicInteger handleCount = new AtomicInteger(0);

    @Override
    public void syncHandler() {
        log.info("执行同步程序...: " + handleCount.incrementAndGet());
    }
}
