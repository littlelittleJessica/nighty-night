package com.example.nighty.controller;

import com.example.nighty.Req.PageReq;
import com.example.nighty.Req.RecordReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.service.RecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/23
 */
@RestController
@RequestMapping("/record/")
public class RecordController {

    @Resource
    private RecordService recordService;

    /**
     * List the record for recent five days
     */
    @RequestMapping("list")
    @ResponseBody
    public ServerResponse List(Long userId) {
        PageReq pageReq = new PageReq();
        pageReq.setPage(1);
        pageReq.setSize(5);
        return recordService.List(pageReq, userId);
    }


    /**
     * save (insert and update)
     */
    @RequestMapping("save")
    @ResponseBody
    public ServerResponse save(RecordReq recordReq) {
        return recordService.save(recordReq);
    }

    /**
     * delete
     */
    @RequestMapping("delete")
    @ResponseBody
    public ServerResponse delete(Long id) {
        return recordService.delete(id);
    }
}
