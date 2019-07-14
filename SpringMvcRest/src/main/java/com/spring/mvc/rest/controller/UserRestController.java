package com.spring.mvc.rest.controller;

import com.spring.mvc.rest.enumertion.SexEnum;
import com.spring.mvc.rest.exception.NotFoundExceptioin;
import com.spring.mvc.rest.pojo.User;
import com.spring.mvc.rest.service.UserService;
import com.spring.mvc.rest.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {
    //用户服务接口
    @Autowired
    private UserService userService = null;

    //映射 JSP 视图
    @GetMapping(value = "/restful2")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("restful");
        return modelAndView;
    }

    @PostMapping(value = "/user2/entity")
    public ResponseEntity<UserVo> insertUserEntity(@RequestBody UserVo userVo) {
        User user = this.changeToPo(userVo);
        userService.insertUser(user);
        UserVo result = changeToVo(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        String success = (result == null || result.getId() == null) ? "false" : "true";
        //设置响应头, 比较常用的方式
        httpHeaders.add("success", success);
        //下面是使用集合（List）方式，不是太常用
        //        httpHeaders.put("success", Arrays.asList(success));
        //返回创建成功的状态码
        return new ResponseEntity<UserVo>(result, httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping(value = "/user2/annotation")
    //指定状态码 201 表示资源创建成功
    @ResponseStatus(HttpStatus.CREATED)
    public UserVo insertUserAnnotation(@RequestBody UserVo userVo) {
        User user = changeToPo(userVo);
        userService.insertUser(user);
        UserVo result = changeToVo(user);
        return result;
    }

    @GetMapping(value = "/user2/exp/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserVo getUserForExp(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        //如果找不到用户，则抛出异常, 进入控制器通知
        if (user == null) {
            throw new NotFoundExceptioin(1L, "找不到用户 【" + id + "】信息");
        }

        UserVo userVo = changeToVo(user);
        return userVo;
    }

    @GetMapping(value = "/user2/{id}")
    public UserVo getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return changeToVo(user);
    }

    @GetMapping(value = "/user2/name/{id}",
            //接收任意类型的请求体
            consumes = MediaType.ALL_VALUE,
            //限定返回的媒体类型为文本
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String getUserName(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return user.getUserName();
    }

    @GetMapping("/users2/{userName}/{note}/{start}/{limit}")
    public List<UserVo> findUsers(@PathVariable("userName") String userName, @PathVariable("note") String note,
                                  @PathVariable("start") int start, @PathVariable("limit") int limit) {
        List<User> userList = userService.findUsers(userName, note, start, limit);
        return this.changeToVoes(userList);
    }

    @PutMapping("/user2/{id}")
    @ResponseBody
    public User updateUser(@PathVariable("id") Long id, @RequestBody UserVo userVo) {
        User user = this.changeToPo(userVo);
        user.setId(id);
        userService.updateUser(user);
        return user;
    }

    @PatchMapping("/user2/{id}/{userName}")
    public ResultVo changeUserName(@PathVariable("id") Long id, @PathVariable("userName") String userName) {
        int result = userService.updateUserName(id, userName);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户【" + id + "】失败。");
        return resultVo;
    }

    @PatchMapping("/user2/name")
    public ResultVo changeUserName2(Long id, String userName) {
        int result = userService.updateUserName(id, userName);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户【" + id + "】失败。");
        return resultVo;
    }

    @GetMapping("/user/name")
    public String changeUserName() {
        return "change_user_name";
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResultVo deleteUser(@PathVariable("id") Long id) {
        int result = userService.deleteUser(id);
        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户【" + id + "】失败。");
        return resultVo;
    }

    //转换 Vo 变为 PO
    private User changeToPo(UserVo userVo) {
        User user = new User();
        user.setId(userVo.getId());
        user.setUserName(userVo.getUserName());
        user.setSex(SexEnum.getSexEnum(userVo.getSexCode()));
        user.setNote(userVo.getNote());
        return user;
    }

    //转换 PO 变为 VO
    private UserVo changeToVo(User user) {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setUserName(user.getUserName());
        userVo.setSexCode(user.getSex().getCode());
        userVo.setSexName(user.getSex().getName());
        userVo.setNote(user.getNote());
        return userVo;
    }

    //将 PO 列表转换为 VO 列表
    private List<UserVo> changeToVoes(List<User> poList) {
        List<UserVo> userVos = new ArrayList<>();
        for (User user : poList) {
            UserVo userVo = changeToVo(user);
            userVos.add(userVo);
        }
        return userVos;
    }

    //结果 VO
    public class ResultVo {
        private Boolean success = null;
        private String message = null;

        public ResultVo() {

        }

        public ResultVo(Boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ResultVo{" + "success=" + success + ", message='" + message + '\'' + '}';
        }
    }
}
