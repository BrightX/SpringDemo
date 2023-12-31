package com.bright.quartz.first;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @author Bright Xu
 * @since 2023/2/10
 */
@Slf4j
public class HelloJob implements Job {
    /**
     * <p>
     * Called by the <code>{@link org.quartz.Scheduler}</code> when a <code>{@link org.quartz.Trigger}</code>
     * fires that is associated with the <code>Job</code>.
     * </p>
     *
     * <p>
     * The implementation may wish to set a
     * {@link JobExecutionContext#setResult(Object) result} object on the
     * {@link JobExecutionContext} before this method exits.  The result itself
     * is meaningless to Quartz, but may be informative to
     * <code>{@link org.quartz.JobListener}s</code> or
     * <code>{@link org.quartz.TriggerListener}s</code> that are watching the job's
     * execution.
     * </p>
     *
     * @param context 任务上下文
     */
    @Override
    public void execute(JobExecutionContext context) {
        log.info("Quartz 任务 开始调度了...");
    }
}
