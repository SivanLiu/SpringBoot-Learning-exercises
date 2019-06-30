package com.spring.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = "com.spring.demo.redis")
public class SpringRedisDemoApplication {

    @Autowired
    private RedisTemplate redisTemplate = null;

    //Redis 连接工厂
    @Autowired
    private RedisConnectionFactory connectionFactory = null;

    //Redis 消息监听器
    @Autowired
    private MessageListener redisMsgListener = null;

    //任务池
    private ThreadPoolTaskScheduler taskScheduler = null;

    /**
     * 创建任务池，运行线程等待处理 Redis 地消息
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler initTaskScheduler() {
        if (taskScheduler != null) {
            return taskScheduler;
        }

        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    @Bean
    public RedisMessageListenerContainer initRedisContainer() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //Redis 连接工厂
        container.setConnectionFactory(connectionFactory);
        //设置运行任务池
        container.setTaskExecutor(initTaskScheduler());
        //定义监听渠道，名称为 topic1
        Topic topic = new ChannelTopic("topic1");
        //使用监听器监听 Redis 的消息
        container.addMessageListener(redisMsgListener, topic);
        return container;
    }

    @PostConstruct
    public void init() {
        initRedisTemplate();
    }

    public void initRedisTemplate() {
        RedisSerializer redisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisDemoApplication.class, args);
//        ApplicationContext context = new AnnotationConfigApplicationContext(RedisConfig.class);
//        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
//        redisTemplate.opsForValue().set("key1", "value1");
//        redisTemplate.opsForHash().put("hash", "field", "havlue");
//        redisTemplate.boundSetOps("set").add("sss", "xxx");
    }

    //需要处理底层的转换规则，如果不考虑改写底层，尽量不要使用该接口
    private void userRedisCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.set("key1".getBytes(), "value1".getBytes());
            connection.hSet("hash".getBytes(), "field".getBytes(), "hvalue".getBytes());
            return connection;
        });
    }

    //高级接口，比较友好，一般情况下，使用该接口
    private static void useSessionCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.opsForValue().set("key2", "value1");
                operations.opsForHash().put("hash1", "filed", "havlue");
                return null;
            }
        });
    }
}
