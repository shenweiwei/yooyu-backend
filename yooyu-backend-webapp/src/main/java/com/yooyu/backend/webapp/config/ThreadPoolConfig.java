package com.yooyu.backend.webapp.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

	private int corePoolSize = 10;// 线程池维护线程的最少数量

	private int maxPoolSize = 30;// 线程池维护线程的最大数量

	private int queueCapacity = 8; // 缓存队列

	private int keepAlive = 60;// 允许的空闲时间

	/**
	 * 线程池
	 * rejection-policy：当pool已经达到max size的时候，如何处理新任务  
     * CALLER_RUNS:不在新线程中执行任务，而是由调用者所在的线程来执行
	 * @return
	 */
	@Bean
    public Executor threadPoolExecuter() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("mqExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //对拒绝task的处理策略
        executor.setKeepAliveSeconds(keepAlive);
        executor.initialize();
        return executor;
    }
}
