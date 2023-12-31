package com.bright.quartz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Bright Xu
 * @since 2023/2/10
 */
@SpringBootTest
@Slf4j
public class QuartzApplicationTests {
    @Test
    void contextLoads() {
        log.info("应用上下文加载...");
    }
}
