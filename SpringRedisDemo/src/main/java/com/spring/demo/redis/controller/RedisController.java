package com.spring.demo.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

@Controller
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private StringRedisTemplate stringRedisTemplate = null;

    @RequestMapping("/stringAndHash")
    @ResponseBody
    public Map<String, Object> testStringAndHash() {
        redisTemplate.opsForValue().set("key1", "value1");
        //注意这里使用了 JDK 的序列化器，所以 redis 保存时不是整数，不能进行运算
        redisTemplate.opsForValue().set("int_key", "1");
        stringRedisTemplate.opsForValue().set("int", "1");
        //使用运算
        stringRedisTemplate.opsForValue().increment("int", 1);
        //获取底层 Jedis 连接
        Jedis jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        jedis.decr("int");
        Map<String, String> hash = new HashMap<>();
        hash.put("field1", "value1");
        hash.put("field2", "value2");
        //存入一个散列数据类型
        stringRedisTemplate.opsForHash().putAll("hash", hash);
        //新增一个字段
        stringRedisTemplate.opsForHash().put("hash", "field3", "value3");
        //绑定散列操作的 key, 这样可以连续对同一个散列数据类型进行操作
        BoundHashOperations hashOperations = stringRedisTemplate.boundHashOps("hash");
        //删除两个字段
        hashOperations.delete("field1", "field2");
        //新增一个字段
        hashOperations.put("field4", "value4");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> testList() {
        //链表从左到右
        stringRedisTemplate.opsForList().leftPushAll("list1", "v2", "v4", "v6", "v8", "v10");
        stringRedisTemplate.opsForList().rightPushAll("list2", "v1", "v2", "v3", "v4", "v5", "v6");
        //绑定 list2 链表操作
        BoundListOperations listOperations = stringRedisTemplate.boundListOps("list2");
        //从右边弹出一个成员
        Object result1 = listOperations.rightPop();
        System.out.println("sivan result1 = " + result1);
        Object result2 = listOperations.index(1);
        System.out.println("sivan result2 = " + result2);
        listOperations.leftPush("v0");
        Long size = listOperations.size();
        System.out.println("sivan size = " + size);
        List<String> elements = listOperations.range(0, size - 2);
        for (String string : elements) {
            System.out.println("s = " + string);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/zset")
    @ResponseBody
    public Map<String, Object> testZset() {
        Set<ZSetOperations.TypedTuple<String>> typedTupleSet = new HashSet<>();
        for (int i = 1; i <= 9; ++i) {
            double score = i * 0.1;
            //创建 TypedTuple 对象， 存入值和分数
            ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>("value" + i, score);
            typedTupleSet.add(typedTuple);
        }

        //往有序集合插入元素
        stringRedisTemplate.opsForZSet().add("zset1", typedTupleSet);
        //绑定 zset 有序集合操作
        BoundZSetOperations<String, String> zsetOps = stringRedisTemplate.boundZSetOps("zset1");
        //增加一个元素
        zsetOps.add("value10", 0.26);
        Set<String> setRange = zsetOps.range(1, 6);
        System.out.println("-------------------增加一个元素-------------------");
        for (String string : setRange) {
            System.out.println("s : " + string);
        }
        //按分数排序获取有序集合
        Set<String> setScore = zsetOps.rangeByScore(0.2, 0.6);
        System.out.println("-------------------按分数排序获取有序集合-------------------");
        for (String string : setScore) {
            System.out.println("score : " + string);
        }
        //定义取值范围
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.gt("value3");
//        range.gte("value3");
//        range.lt("value8");
        //按值排序，请注意这个排序是按字符串排序
        Set<String> setLex = zsetOps.rangeByLex(range);
        System.out.println("-------------------按值排序-------------------");
        for (String string : setLex) {
            System.out.println("lex : " + string);
        }

        //删除元素
        zsetOps.remove("value9", "value2");
        //求分数
        Double score = zsetOps.score("value8");
        System.out.println("-------------------求分数-------------------");
        System.out.println("score value8 = " + score);
        Set<ZSetOperations.TypedTuple<String>> rangeSet = zsetOps.rangeWithScores(1, 6);
        System.out.println("-------------------rangeSet-------------------");
        for (ZSetOperations.TypedTuple<String> string : rangeSet) {
            System.out.println("value : " + string.getValue() + " score = " + string.getScore());
        }

        //在分数区间下，按分数排序，同时返回 value 和 score
        Set<ZSetOperations.TypedTuple<String>> scoreSet = zsetOps.rangeByScoreWithScores(1, 6);
        System.out.println("-------------------scoreSet-------------------");
        for (ZSetOperations.TypedTuple<String> string : scoreSet) {
            System.out.println("value : " + string.getValue() + " score = " + string.getScore());
        }
        //按从大到小排序
        Set<String> reverseSet = zsetOps.reverseRange(2, 8);
        System.out.println("-------------------按从大到小排序-------------------");
        for (String string : reverseSet) {
            System.out.println("reverseSet s : " + string);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/multi")
    @ResponseBody
    public Map<String, Object> testMulti() {
        redisTemplate.opsForValue().set("key1", "value1");
        List list = (List) redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                //设置需要监控的 key1
                operations.watch("key1");
                //开启事务， 在 exec 命令执行前， 全部都只是进入队列
                operations.multi();
                operations.opsForValue().set("key2", "value2");
//                operations.opsForValue().increment("key1", 1);
                Object value2 = operations.opsForValue().get("key2");
                System.out.println("命令在队列, 所以 value 为 null [ " + value2 + "]");
                operations.opsForValue().set("key3", "value3");
                Object value3 = operations.opsForValue().get("key3");
                System.out.println("命令在队列, 所以 value 为 null [ " + value3 + "]");
                //执行 exec 命令，将先判别 key1 是否在监控后被修改过，如果是则不执行事务，否则就执行事务
                return operations.exec();
            }
        });

        System.out.println(list);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/pipeline")
    @ResponseBody
    public Map<String, Object> testPipeline() {
        Long start = System.currentTimeMillis();
        List list = (List) redisTemplate.executePipelined(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for (int i = 1; i <= 10000; ++i) {
                    operations.opsForValue().set("pipeline_" + i, "value_" + i);
                    String value = (String) operations.opsForValue().get("pipeline_" + i);
                    if (i == 100000) {
                        System.out.println("命令只是进入队列, 所以值为空 [" + value + "]");
                    }
                }
                return null;
            }
        });
        Long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "毫秒");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/publish")
    @ResponseBody
    public Map<String, Object> publishTopic() {
        redisTemplate.convertAndSend("topic1", "msg");
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/lua")
    @ResponseBody
    public Map<String, Object> testLua() {
        DefaultRedisScript<String> redisScript = new DefaultRedisScript<>();
        //设置脚本
        redisScript.setScriptText("return 'Hello Redis'");

        //定义返回类型，注意：如果没有该定义，Spring 不会返回结果
        redisScript.setResultType(String.class);
        RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
        //执行 Lua 脚本
        String str = (String) redisTemplate.execute(redisScript, stringRedisSerializer,
                stringRedisSerializer, null);
        System.out.println("ssss lua == " + str);
        Map<String, Object> map = new HashMap<>();
        map.put("str", str);
        return map;
    }

    //http://localhost:8080/redis/lua2?key1=s&key2=g&value1=3&value2=5
    @RequestMapping("/lua2")
    @ResponseBody
    public Map<String, Object> testLua2(String key1, String key2, String value1, String value2) {
        // 定义Lua脚本
        String lua = " redis.call('set', KEYS[1], ARGV[1]) \n"
                + " redis.call('set', KEYS[2], ARGV[2]) \n"
                + " local str1 = redis.call('get', KEYS[1]) \n"
                + " local str2 = redis.call('get', KEYS[2]) \n"
                + " if str1 == str2 then  \n" + "return 1 \n"
                + " end \n"
                + " return 0 \n";
        System.out.println(lua);
        // 结果返回为Long
        DefaultRedisScript<Long> rs = new DefaultRedisScript<Long>();
        rs.setScriptText(lua);
        rs.setResultType(Long.class);
        // 采用字符串序列化器
        RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
        // 定义key参数
        List<String> keyList = new ArrayList<>();
        keyList.add(key1);
        keyList.add(key2);
        // 传递两个参数值，其中第一个序列化器是key的序列化器，第二个序列化器是参数的序列化器
        Long result = (Long) redisTemplate.execute(rs, stringSerializer, stringSerializer, keyList, value1, value2);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", result);
        return map;
    }
}
