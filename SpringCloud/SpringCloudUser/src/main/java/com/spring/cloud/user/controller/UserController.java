package com.spring.cloud.user.controller;

import com.spring.cloud.user.pojo.UserPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private DiscoveryClient discoveryClient = null;

    @GetMapping("/user/id")
    public UserPo getUserPo(@PathVariable("id") Long id) {
        ServiceInstance serviceInstance = discoveryClient.getInstances("USER").get(0);
        logger.info(
                "【" + serviceInstance.getServiceId() + "" + "】:" + serviceInstance.getHost() + " : " + serviceInstance
                        .getPort());
        UserPo userPo = new UserPo();
        userPo.setId(id);
        int level = (int) (id % 3 + 1);
        userPo.setLevel(level);
        userPo.setUserName("user_name_" + id);
        userPo.setNote("note_" + id);
        return userPo;
    }

    @PostMapping("/insert")
    public Map<String, Object> insertUser(@RequestBody UserPo userPo){
        Map<String, Object> map = new HashMap<>();
        map.put("success",true);
        map.put("message", "插入用户信息【"+userPo.getUserName()+"】成功");
        return map;
    }

    @PostMapping("/update/{userName}")
    public Map<String, Object> updateUsername(@PathVariable("userName")String userName,
                                              @RequestHeader("id")Long id){
        Map<String, Object> map = new HashMap<>();
        map.put("success",true);
        map.put("message","更新用户【"+id+"】名称【"+userName+"】成功");
        return map;
    }

    @GetMapping("/timeout")
    public String timeout() {
        // 生成一个3000之内的随机数
        long ms = (long)(3000L*Math.random());
        try {
            // 程序延迟，有一定的概率超过2000毫秒
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "熔断测试";
    }
}
