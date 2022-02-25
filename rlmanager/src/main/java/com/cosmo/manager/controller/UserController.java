package com.cosmo.manager.controller;

import com.cosmo.manager.entity.User;
import com.cosmo.manager.service.UserService;
import com.cosmo.manager.utils.JWTUtil;
import com.cosmo.manager.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String,Object> params){
        User user = userService.queryByUserCode((String) params.get("loginName"));
        if (String.valueOf(params.get("psw")).equals(user.getPsw())) {
            String token = JWTUtil.sign(user.getUserCode());
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            return Result.success(data, "登陆成功");
        } else {
            return Result.failure("账号或密码错误！");
        }
    }

}
