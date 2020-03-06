package com.leaves.smalltiger.common.config;

import com.leaves.smalltiger.common.po.Consumer;
import com.leaves.smalltiger.consumer.mapper.ConsumerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
@Configuration
@EnableScheduling
public class TimeConfig {
    @Resource
    private ConsumerMapper consumerMapper;

    /**
     * 月初清除所有用户预算
     */
    @Transactional
    @Scheduled(cron = "0 0 0 1 * *")
    public void clear(){
        List<Consumer> consumers = consumerMapper.selectAll();
        for (Consumer consumer : consumers) {
            int i = consumerMapper.deleteBudget(consumer.getConId());
            log.info("==已经在月初清除用户"+consumer.getConId()+"预算=="+i);
        }
    }

}
