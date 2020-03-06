package com.leaves.smalltiger;

import com.leaves.smalltiger.common.po.Consumer;
import com.leaves.smalltiger.common.po.Detail;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.consumer.mapper.ConsumerMapper;
import com.leaves.smalltiger.consumer.vo.RegConsumer;
import com.leaves.smalltiger.detail.mapper.DetailMapper;
import com.leaves.smalltiger.detail.service.DetailService;
import com.leaves.smalltiger.detail.vo.DetailInsert;
import com.leaves.smalltiger.detail.vo.DetailParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmalltigerApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private DetailMapper detailMapper;
    @Autowired
    private ConsumerMapper consumerMapper;
    @Test
    public void testConsumer() {
        RegConsumer regConsumer = new RegConsumer();
        regConsumer.setAccount("15596531513");
        Consumer consumer = consumerMapper.selectByPhone(regConsumer.getAccount());
        if (consumer != null) {
            log.info(consumer.toString()+"********");
        }else {
            log.info("手机号不存在" );
        }

    }

    @Test
    public void testxx() {
//        List<Consumer> consumers = consumerMapper.selectAll();
        List<Detail> details = detailMapper.selectAll();
        for (Detail detail : details){
            log.info(detail.toString()+"***********");
        }
    }

    @Test
    public void redisTest() {
        Consumer consumer1 = new Consumer();
        Consumer consumer2 = new Consumer();
        Consumer consumer3 = new Consumer();
        Consumer consumer4 = new Consumer();
        consumer1.setConId(123);
        consumer2.setConId(456);
        consumer3.setConId(789);
        consumer4.setConId(7890);
        consumer1.setConName("AAA");
        consumer2.setConName("BBB");
        consumer3.setConName("CCC");
        consumer4.setConName("DDD");
        List<Consumer> consumers = new ArrayList<>();
        consumers.add(consumer1);
        consumers.add(consumer2);
        consumers.add(consumer3);
        redisTemplate.opsForValue().set("cons",consumers);
    }
    @Autowired
    private DetailService detailService;

    @Test
    public void LS(){
        DetailInsert detailInsert = new DetailInsert(520131417,1,1,35.9,"小五","2019-12-14");
        MsgResult result = detailService.insertDetail(detailInsert);
        log.info("==="+result.toString());
    }

    @Test
    public void setDetailServ(){
        MsgResult result = detailService.queryAllHome(new DetailParam(520131417, 2019, 12));
        log.info(result.toString()+"=======");
    }
}
