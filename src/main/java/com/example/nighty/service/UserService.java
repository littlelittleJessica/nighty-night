package com.example.nighty.service;

import com.example.nighty.Req.UserLoginReq;
import com.example.nighty.Resp.UserLoginResp;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.User;
import com.example.nighty.domain.UserExample;
import com.example.nighty.mapper.UserMapper;
import com.example.nighty.util.CopyUtil;
import com.example.nighty.util.MD5Util;
import com.example.nighty.common.Const;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/7
 */
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    /**
     * 用户名登录
     */
    public ServerResponse<User> loginByName(String username, String password) {
        User userDB = selectByUsername(username);
        if (ObjectUtils.isEmpty(userDB)) {
            //用户名不存在
            LOG.info("用户名不存在，{}", username);
            return ServerResponse.createByErrorMessage("用户名不存在");
        } else {
            String md5Password = MD5Util.MD5EncodeUtf8(password);
            if (userDB.getPassword().equals(md5Password)) {
                //登录成功
                LOG.info("登录成功，用户名：{}，密码：{}", username, md5Password);
                return ServerResponse.createBySuccess("登录成功", userDB);
            } else {
                //密码不正确
                LOG.info("密码不正确，输入密码：{}，数据库密码：{}", password, userDB.getPassword());
                return ServerResponse.createByErrorMessage("密码错误");
            }
        }
    }


    /**
     * 用户注册
     */
    public ServerResponse<UserLoginResp> register(UserLoginReq user) {
        ServerResponse validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        validResponse = this.checkValid(user.getMobile(), Const.MOBILE);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        User userNew = CopyUtil.copy(user, User.class);
        int resultCount = userMapper.insertSelective(userNew);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        UserLoginResp userLoginResp = CopyUtil.copy(user, UserLoginResp.class);
        return ServerResponse.createBySuccess("注册成功", userLoginResp);
    }

    /**
     * 校验用户名和学号
     */
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            //开始校验
            if (Const.USERNAME.equals(type)) {
                User userDB = selectByUsername(str);
                if (userDB != null) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Const.MOBILE.equals(type)) {
                User userDB = selectByStuNo(str);
                if (userDB != null) {
                    return ServerResponse.createByErrorMessage("学号已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    /**
     * 通过用户名在数据库中查找用户
     */
    public User selectByUsername(String Username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(Username);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    /**
     * 通过手机号在数据库中查找用户
     */
    public User selectByStuNo(String mobile) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andMobileEqualTo(mobile);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    /**
     * 登录状态修改密码
     */
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {


        //防止横向越权，要校验一下这个用户的旧密码，一定要指定是这个用户，因为我们回查询出一个count(1)，如果不指定id，那么结果就是true（count>0）
        String md5Password = MD5Util.MD5EncodeUtf8(passwordOld);
        if (!user.getPassword().equals(md5Password)) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("更新密码成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

}
