package com.bright.quartz.jobs;

import com.bright.quartz.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class MySampleJob extends QuartzJobBean {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss");
    private MyService myService;

    @Autowired
    public void setMyService(MyService myService) {
        this.myService = myService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) {
        LocalDateTime now = LocalDateTime.now();
        log.info(now.format(DTF) + ": 执行了定时任务");
        myService.syncHandler();
    }

}
