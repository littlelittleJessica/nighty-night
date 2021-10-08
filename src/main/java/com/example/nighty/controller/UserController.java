package com.example.nighty.controller;

import com.example.nighty.Req.UserLoginReq;
import com.example.nighty.Req.UserUpdateReq;
import com.example.nighty.Resp.UserLoginResp;
import com.example.nighty.Resp.UserUpdateResp;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.User;
import com.example.nighty.service.UserService;
import com.example.nighty.util.CopyUtil;
import com.example.nighty.common.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/7
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * 用户名登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = userService.loginByName(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;

    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        LOG.info("退出登录成功");
        return ServerResponse.createBySuccessMessage("退出登录成功");
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserLoginResp> register(UserLoginReq user) {
        return userService.register(user);
    }

    /**
     * 获取当前用户信息
     */
    @RequestMapping(value = "get_user_info", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<UserLoginResp> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            UserLoginResp userLoginResp = CopyUtil.copy(user, UserLoginResp.class);
            return ServerResponse.createBySuccess("获取用户信息成功", userLoginResp);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }

    /**
     * 登录状态的重置密码
     */
    @RequestMapping(value = "reset_password", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return userService.resetPassword(passwordOld, passwordNew, user);

    }

    /**
     * 更新用户信息
     */
    @RequestMapping(value = "update_information", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserUpdateResp> update_information(HttpSession session, UserUpdateReq user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        ServerResponse<UserUpdateResp> response = userService.updateInformation(user);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

}
