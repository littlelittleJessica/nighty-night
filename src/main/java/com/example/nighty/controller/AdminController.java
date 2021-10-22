package com.example.nighty.controller;

import com.example.nighty.common.ServerResponse;
import com.example.nighty.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/21
 */
@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Resource
    private AdminService adminService;

    @RequestMapping("update_daily")
    @ResponseBody
    public ServerResponse updateDaily(HttpServletRequest request){
        return null;
    }

}
