package com.spring.webfluex.validator;

import com.spring.webfluex.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    // 确定支持的验证类型
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, org.springframework.validation.Errors errors) {
        User user = (User) target;
        // 监测用户名是否为空
        if (StringUtils.isEmpty(user.getUserName())) {
            errors.rejectValue("userName", null, "用户名不能为空");
        }
    }
}
