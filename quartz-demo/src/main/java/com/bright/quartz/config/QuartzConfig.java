package com.bright.quartz.config;

import com.bright.quartz.jobs.MyJob;
import com.bright.quartz.jobs.MySampleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class QuartzConfig {
    private Scheduler scheduler;

    @Autowired
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Bean
    public JobDetail mySampleJobDetail() {
        return JobBuilder.newJob(MySampleJob.class).withIdentity("mySampleJob", "mySample")
            .storeDurably().build();
    }

    @Bean
    public CronTrigger myCronTrigger() {
        return TriggerBuilder.newTrigger().forJob("mySampleJob", "mySample")
            .withIdentity("mySampleCronTrigger", "mySample")
            .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?")).build();
    }

    @PostConstruct
    public void addJobs() throws SchedulerException {
        JobDetail jd = JobBuilder.newJob(MyJob.class).withIdentity("MyJob", "mySample").storeDurably().build();
        CronTrigger ct = TriggerBuilder.newTrigger().forJob(jd)
            .withIdentity("myJobCronTrigger", "mySample")
            .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();

        // scheduler.scheduleJob(jd, Collections.singleton(ct), true);
        if (!scheduler.checkExists(jd.getKey())) {
            scheduler.scheduleJob(jd, ct);
        }
    }

}
