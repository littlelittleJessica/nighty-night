package com.example.nighty.controller;

import com.example.nighty.Req.PageReq;
import com.example.nighty.common.Const;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.User;
import com.example.nighty.domain.UserVoice;
import com.example.nighty.service.UserVoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/18
 */
@RestController
@RequestMapping("/user-voice/")
public class UserVoiceController {

    private static final Logger LOG = LoggerFactory.getLogger(UserVoiceController.class);

    @Resource
    private UserVoiceService userVoiceService;

    /**
     * List the favorite voice of the user by category
     */
    @PostMapping("list_favorite")
    @ResponseBody
    public ServerResponse listFavorite(HttpServletRequest request, PageReq pageReq, String category) {
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if(user==null){
            return ServerResponse.createByErrorMessage("The user has not logged in");
        }
        return ServerResponse.createBySuccess("Query user favorite list success", userVoiceService.listFavorite(user, pageReq, category));
    }

    /**
     * Favorite voice
     */
    @RequestMapping(value = "favorite", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse favorite(UserVoice userVoice) {
        return userVoiceService.favorite(userVoice);
    }

    /**
     * Unfavorite voice
     */
    @RequestMapping(value = "unfavorite", method = RequestMethod.DELETE)
    @ResponseBody
    public ServerResponse unfavorite(UserVoice userVoice) {
        return userVoiceService.unfavorite(userVoice);
    }
}
