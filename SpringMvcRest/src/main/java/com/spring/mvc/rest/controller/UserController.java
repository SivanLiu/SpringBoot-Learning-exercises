//package com.spring.mvc.rest.controller;
//
//import com.spring.mvc.rest.enumertion.SexEnum;
//import com.spring.mvc.rest.exception.NotFoundExceptioin;
//import com.spring.mvc.rest.pojo.User;
//import com.spring.mvc.rest.service.UserService;
//import com.spring.mvc.rest.vo.UserVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class UserController {
//    //用户服务接口
//    @Autowired
//    private UserService userService = null;
//
//    //映射 JSP 视图
//    @GetMapping("/restful")
//    public String index() {
//        return "restful";
//    }
//
//    @PostMapping("/user")
//    @ResponseBody
//    public User insertUser(@RequestBody UserVo userVo) {
//        User user = this.changeToPo(userVo);
//        return userService.insertUser(user);
//    }
//
//    @GetMapping(value = "/user/{id}")
//    @ResponseBody
//    public UserVo getUser(@PathVariable("id") Long id) {
//        User user = userService.getUser(id);
//        return changeToVo(user);
//    }
//
//    @GetMapping("/users/{userName}/{note}/{start}/{limit}")
//    @ResponseBody
//    public List<UserVo> findUsers(@PathVariable("userName") String userName, @PathVariable("note") String note,
//                                  @PathVariable("start") int start, @PathVariable("limit") int limit) {
//        List<User> userList = userService.findUsers(userName, note, start, limit);
//        return this.changeToVoes(userList);
//    }
//
//    @PutMapping("/user/{id}")
//    @ResponseBody
//    public User updateUser(@PathVariable("id") Long id, @RequestBody UserVo userVo) {
//        User user = this.changeToPo(userVo);
//        user.setId(id);
//        userService.updateUser(user);
//        return user;
//    }
//
//    @PatchMapping("/user/{id}/{userName}")
//    @ResponseBody
//    public ResultVo changeUserName(@PathVariable("id") Long id, @PathVariable("userName") String userName) {
//        int result = userService.updateUserName(id, userName);
//        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户【" + id + "】失败。");
//        return resultVo;
//    }
//
//    @PatchMapping("/user/name")
//    @ResponseBody
//    public ResultVo changeUserName2(Long id, String userName) {
//        int result = userService.updateUserName(id, userName);
//        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户【" + id + "】失败。");
//        return resultVo;
//    }
//
//    @GetMapping("/user/name")
//    public String changeUserName() {
//        return "change_user_name";
//    }
//
//    @DeleteMapping("/user/{id}")
//    @ResponseBody
//    public ResultVo deleteUser(@PathVariable("id") Long id) {
//        int result = userService.deleteUser(id);
//        ResultVo resultVo = new ResultVo(result > 0, result > 0 ? "更新成功" : "更新用户【" + id + "】失败。");
//        return resultVo;
//    }
//
//    //转换 Vo 变为 PO
//    private User changeToPo(UserVo userVo) {
//        User user = new User();
//        user.setId(userVo.getId());
//        user.setUserName(userVo.getUserName());
//        user.setSex(SexEnum.getSexEnum(userVo.getSexCode()));
//        user.setNote(userVo.getNote());
//        return user;
//    }
//
//    //转换 PO 变为 VO
//    private UserVo changeToVo(User user) {
//        UserVo userVo = new UserVo();
//        userVo.setId(user.getId());
//        userVo.setUserName(user.getUserName());
//        userVo.setSexCode(user.getSex().getCode());
//        userVo.setSexName(user.getSex().getName());
//        userVo.setNote(user.getNote());
//        return userVo;
//    }
//
//    //将 PO 列表转换为 VO 列表
//    private List<UserVo> changeToVoes(List<User> poList) {
//        List<UserVo> userVos = new ArrayList<>();
//        for (User user : poList) {
//            UserVo userVo = changeToVo(user);
//            userVos.add(userVo);
//        }
//        return userVos;
//    }
//
//    //结果 VO
//    public class ResultVo {
//        private Boolean success = null;
//        private String message = null;
//
//        public ResultVo() {
//
//        }
//
//        public ResultVo(Boolean success, String message) {
//            this.success = success;
//            this.message = message;
//        }
//
//        public Boolean getSuccess() {
//            return success;
//        }
//
//        public void setSuccess(Boolean success) {
//            this.success = success;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//
//        @Override
//        public String toString() {
//            return "ResultVo{" + "success=" + success + ", message='" + message + '\'' + '}';
//        }
//    }
//}
