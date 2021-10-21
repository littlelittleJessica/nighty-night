package com.example.nighty.service;

import com.example.nighty.Req.UserLoginReq;
import com.example.nighty.Req.UserRegisterReq;
import com.example.nighty.Req.UserResetPasswordReq;
import com.example.nighty.Req.UserUpdateReq;
import com.example.nighty.Resp.UserLoginResp;
import com.example.nighty.Resp.UserUpdateResp;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.User;
import com.example.nighty.domain.UserExample;
import com.example.nighty.mapper.UserMapper;
import com.example.nighty.util.CopyUtil;
import com.example.nighty.common.Const;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
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
     * Login by username and password
     */
    public ServerResponse<UserLoginResp> login(UserLoginReq req) {
        User userDB = selectByUsername(req.getUsername());
        if (ObjectUtils.isEmpty(userDB)) {
            //Username does not exist
            LOG.info("Username does not exist, {}", req.getUsername());
            return ServerResponse.createByErrorMessage("Username does not exist");
        } else {
            if (userDB.getPassword().equals(req.getPassword())) {
                //Login succeeded
                LOG.info("Login succeeded, username: {}, password: {}", req.getUsername(), req.getPassword());

                UserLoginResp resp = CopyUtil.copy(userDB, UserLoginResp.class);
                return ServerResponse.createBySuccess("Login succeeded", resp);
            } else {
                //Incorrect password
                LOG.info("Incorrect password, worng password{}, password in database: {}", req.getPassword(), userDB.getPassword());
                return ServerResponse.createByErrorMessage("Incorrect password");
            }
        }
    }


    /**
     * Register
     */
    public ServerResponse<UserLoginResp> register(UserRegisterReq user) {
        ServerResponse validResponse = this.checkValid(user.getUsername(), Const.USERNAME);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(), Const.EMIAL);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        User userNew = CopyUtil.copy(user, User.class);
        int resultCount = userMapper.insertSelective(userNew);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("Register failed");
        }

        User userDB = selectByUsername(user.getUsername());
        UserLoginResp userLoginResp = CopyUtil.copy(userDB, UserLoginResp.class);
        return ServerResponse.createBySuccess("Register succeeded", userLoginResp);
    }

    /**
     * Get the user information
     */
    public ServerResponse<User> getInformation(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            ServerResponse.createByErrorMessage("Current user not found");
        }
        return ServerResponse.createBySuccess("Get user information succeeded", user);
    }

    /**
     * verify username and email
     */
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            if (Const.USERNAME.equals(type)) {
                User userDB = selectByUsername(str);
                if (userDB != null) {
                    return ServerResponse.createByErrorMessage("The username already exists");
                }
            }
            if (Const.EMIAL.equals(type)) {
                User userDB = selectByStuNo(str);
                if (userDB != null) {
                    return ServerResponse.createByErrorMessage("The email already exists");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("wrong");
        }
        return ServerResponse.createBySuccessMessage("verify succeeded");
    }

    /**
     * Search user in database by username
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
     * Search user in database by email
     */
    public User selectByStuNo(String email) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    /**
     * Reset Password
     */
    public ServerResponse<String> resetPassword(UserResetPasswordReq req, Long id) {
        User userDB = userMapper.selectByPrimaryKey(id);
        String passwordDB = userDB.getPassword();
        if (!req.getPasswordOld().equals(passwordDB)) {
            return ServerResponse.createByErrorMessage("Current password incorrect");
        }
        userDB.setPassword(DigestUtils.md5DigestAsHex(req.getPasswordNew().getBytes()));
        int updateCount = userMapper.updateByPrimaryKeySelective(userDB);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMessage("Reset password succeeded");
        }
        return ServerResponse.createByErrorMessage("Reset Password failed");
    }

    /**
     * update user information
     */
    public ServerResponse<UserUpdateResp> updateInformation(UserUpdateReq user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(user.getEmail());
        List<User> userList = userMapper.selectByExample(userExample);
        if (!CollectionUtils.isEmpty(userList) && userList.get(0).getId() != user.getId()) {
            return ServerResponse.createByErrorMessage("Email exists");
        }

        UserExample userExample2 = new UserExample();
        UserExample.Criteria criteria2 = userExample2.createCriteria();
        criteria2.andUsernameEqualTo(user.getUsername());
        List<User> userList2 = userMapper.selectByExample(userExample2);
        if (!CollectionUtils.isEmpty(userList2) && userList2.get(0).getId() != user.getId()) {
            return ServerResponse.createByErrorMessage("Username exists");
        }

        User userNew = CopyUtil.copy(user, User.class);
        UserUpdateResp updateUser = CopyUtil.copy(user, UserUpdateResp.class);

        int updateCount = userMapper.updateByPrimaryKeySelective(userNew);
        if (updateCount > 0) {
            return ServerResponse.createBySuccess("Update information succeeded", updateUser);
        }
        return ServerResponse.createByErrorMessage("Update information failed");
    }
}
