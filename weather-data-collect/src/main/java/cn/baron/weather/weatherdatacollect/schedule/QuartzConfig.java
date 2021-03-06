package cn.baron.weather.weatherdatacollect.schedule;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;

/**
 * @author Baron
 * @date 2020/9/2 21:40
 */

public class QuartzConfig {

    @Bean
    public JobDetail testQuartzDetail() {
        return JobBuilder.
                newJob(TestQuartz.class).
                // 这个名称不能为null,内容没有任何要求，可以理解为他就是任务的key
                        withIdentity("testQuartzite1").
                        storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger() {
        // 创建 SimpleScheduleBuilder 对象
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.
                simpleSchedule().
                /* 设置时间周期单位秒,这里还有其他三种时间范围可供选择 */
                        withIntervalInSeconds(100000000).
                        repeatForever();

        return (Trigger) TriggerBuilder.newTrigger().forJob(testQuartzDetail())
                // 这个名称不能为null,内容没有任何要求，可以理解为他就是任务的key
                .withIdentity("testQuartzite1")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
