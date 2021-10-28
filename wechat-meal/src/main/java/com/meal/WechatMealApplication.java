package com.meal;

import com.meal.util.ThreadPoolExecutorForMonitor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

@SpringBootApplication
@EnableAsync
public class WechatMealApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatMealApplication.class, args);
        //初始化项目
        //SpringApplication.run(WechatMealApplication.class, args);
    }

    @Bean("asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(8);
        executor.setQueueCapacity(1000);
        executor.setThreadNamePrefix("asyncExecutor");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


    @Bean("myAsyncExecutor")
    public Executor myAsyncExecutor() {
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1000);
        ThreadPoolExecutorForMonitor executor = new ThreadPoolExecutorForMonitor(4, 8, 60L, java.util.concurrent.TimeUnit.SECONDS, workQueue, handler);
        executor.setCorePoolSize(4);
        executor.setMaximumPoolSize(8);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
