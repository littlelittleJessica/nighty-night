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
     * Login
     */
    @PostMapping(value = "login")
    public ServerResponse<UserLoginResp> login(HttpSession session, @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        ServerResponse<UserLoginResp> resp = userService.login(req);
        if (resp.isSuccess()) {
            Long token = snowFlake.nextId();
            LOG.info("Generate a token:{}, and put it in redis", token);
            resp.getData().setToken(token.toString());
            redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(resp), 3600 * 24, TimeUnit.SECONDS);
            //verify if the token has been put in redis
            LOG.info("key:{},value:{}", token, redisTemplate.opsForValue().get(token.toString()));
            User user = CopyUtil.copy(resp.getData(), User.class);
            session.setAttribute(Const.CURRENT_USER, user);
        }
        if (resp.getData().getRole().equals("admin")) {
            session.setAttribute(Const.ROLE, "admin");
        }
        return resp;

    }

    /**
     * Logout
     */
    @GetMapping("logout/{token}")
    public ServerResponse<String> logout(@PathVariable String token) {
        if (token == null || token.isEmpty()) {
            return ServerResponse.createByErrorMessage("Logout failed because of empty token");
        }
        Object object = redisTemplate.opsForValue().get(token);
        if (object == null) {
            return ServerResponse.createByErrorMessage("Logout failed because of invalid token");
        } else {
            redisTemplate.delete(token);
            LOG.info("Logout success!");
            return ServerResponse.createBySuccessMessage("Logout success");
        }
    }

    /**
     * Register
     */
    @PostMapping(value = "register")
    public ServerResponse<UserLoginResp> register(@RequestBody UserRegisterReq user) {

        //verify email verification code
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
     * get the information of current user
     */
    @GetMapping(value = "get_user_info/{id}")
    public ServerResponse getUserInfo(@PathVariable Long id ) {
        return userService.getInformation(id);
    }

    /**
     * Reset Password
     */
    @PostMapping(value = "reset_password")
    public ServerResponse resetPassword(@RequestBody UserResetPasswordReq req, HttpServletRequest request) {
        User currentUser = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "The user has not logged in");
        }
        Long id = ((User) request.getSession().getAttribute(Const.CURRENT_USER)).getId();
        req.setPasswordOld(DigestUtils.md5DigestAsHex(req.getPasswordOld().getBytes()));
        return userService.resetPassword(req, id);

    }

    /**
     * Update user information
     */
    @PostMapping(value = "update_information")
    public ServerResponse update_information(HttpServletRequest request, @RequestBody UserUpdateReq user) {
        User currentUser = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "The user has not logged in");
        }

        ServerResponse<UserUpdateResp> response = userService.updateInformation(user);
        if (response.isSuccess()) {
            request.getSession().setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * List all users for admin
     */
    @GetMapping(value="admin/list-user")
    public ServerResponse listAllUsers(){
        PageReq pageReq = new PageReq();
        pageReq.setPage(1);
        pageReq.setSize(8);
        return ServerResponse.createBySuccess("Query user list success", userService.listAllUsers(pageReq));
    }

    /**
     * admin delete a user
     */
    @DeleteMapping(value = "admin/delete-user/{id}")
    public ServerResponse deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ServerResponse.createBySuccessMessage("Delete user succeeded");
    }
}
