package com.example.nighty.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.nighty.Req.*;
import com.example.nighty.Resp.UserLoginResp;
import com.example.nighty.Resp.UserUpdateResp;
import com.example.nighty.common.ResponseCode;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.User;
import com.example.nighty.service.UserService;
import com.example.nighty.service.VerificationCodeService;
import com.example.nighty.util.CopyUtil;
import com.example.nighty.common.Const;
import com.example.nighty.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private VerificationCodeService verificationCodeService;

    /**
     * 用户名登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserLoginResp> login(HttpSession session, UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        ServerResponse<UserLoginResp> resp = userService.login(req);
        if (resp.isSuccess()) {
            Long token = snowFlake.nextId();
            LOG.info("生成单点登录token:{}，并放入redis中", token);
            resp.getData().setToken(token.toString());
            redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(resp), 3600 * 24, TimeUnit.SECONDS);
            //验证token是否已存放到redis中
            LOG.info("key:{},value:{}", token, redisTemplate.opsForValue().get(token.toString()));
            User user = CopyUtil.copy(resp.getData(), User.class);
            session.setAttribute(Const.CURRENT_USER, user);
        }
        return resp;

    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            return ServerResponse.createByErrorMessage("token为空，无法退出登录");
        }
        Object object = redisTemplate.opsForValue().get(token);
        if (object == null) {
            return ServerResponse.createByErrorMessage("token无效，无法退出登录");
        } else {
            redisTemplate.delete(token);
            LOG.info("退出登录成功");
            return ServerResponse.createBySuccessMessage("退出登录成功");
        }
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserLoginResp> register(UserRegisterReq user) {

        //校验邮件验证码
        CodeValidReq code = new CodeValidReq();
        code.setCode(user.getCode());
        code.setEmail(user.getEmail());
        ServerResponse response = verificationCodeService.validCode(code);
        if (response.isSuccess()) {
            return userService.register(user);
        } else {
            return response;
        }


    }

    /**
     * 获取当前用户信息
     */
    @RequestMapping(value = "get_user_info", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getUserInfo(HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            LOG.info("token失效或不正确");
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        return userService.getInformation(currentUser.getId());
    }

    /**
     * 登录状态的重置密码
     */
    @PostMapping(value = "reset_password")
    @ResponseBody
    public ServerResponse resetPassword(UserResetPasswordReq req, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        Long id = ((User) request.getSession().getAttribute(Const.CURRENT_USER)).getId();
        req.setPasswordOld(DigestUtils.md5DigestAsHex(req.getPasswordOld().getBytes()));
        return userService.resetPassword(req, id);

    }

    /**
     * 更新用户信息
     */
    @RequestMapping(value = "update_information", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse update_information(HttpServletRequest request, UserUpdateReq user) {
        User currentUser = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }

        ServerResponse<UserUpdateResp> response = userService.updateInformation(user);
        if (response.isSuccess()) {
            request.getSession().setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

}
