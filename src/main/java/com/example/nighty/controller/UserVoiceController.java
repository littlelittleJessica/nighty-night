package com.example.nighty.controller;

import com.example.nighty.Req.PageReq;
import com.example.nighty.common.Const;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.User;
import com.example.nighty.domain.UserVoice;
import com.example.nighty.domain.Voice;
import com.example.nighty.service.UserVoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @PostMapping("list_favorite/{category}")
    public ServerResponse listFavorite(HttpServletRequest request, @PathVariable String category, @RequestBody PageReq page) {
        Long userId = Long.valueOf(request.getHeader("userId"));
        LOG.info("****************************" + userId);
        PageReq pageReq = new PageReq();
        pageReq.setPage(page.getPage());
        pageReq.setSize(page.getSize());
        return ServerResponse.createBySuccess("Query user favorite list success", userVoiceService.listFavorite(userId, pageReq, category));
    }

    /**
     * search voice by name
     */
    @GetMapping("search/{name}")
    public ServerResponse searchByName(HttpServletRequest request, @PathVariable String name) {
        User user = (User) request.getSession().getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("The user has not logged in");
        }
        PageReq pageReq = new PageReq();
        pageReq.setPage(1);
        pageReq.setSize(8);
        return ServerResponse.createBySuccess("Search voice list success", userVoiceService.searchByName(user, pageReq, name));
    }

    /**
     * Favorite voice
     */
    @PostMapping(value = "favorite")
    public ServerResponse favorite(@RequestBody UserVoice userVoice) {
        return userVoiceService.favorite(userVoice);
    }

    /**
     * Unfavorite voice
     */
    @PostMapping(value = "unfavorite")
    public ServerResponse unfavorite(@RequestBody UserVoice userVoice) {
        return userVoiceService.unfavorite(userVoice);
    }
}
