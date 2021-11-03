package com.example.nighty.controller;

import com.example.nighty.Req.PageReq;
import com.example.nighty.Req.RecordReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.service.RecordService;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("list/{userId}")
    public ServerResponse List(@PathVariable Long userId) {
        PageReq pageReq = new PageReq();
        pageReq.setPage(1);
        pageReq.setSize(5);
        return recordService.List(pageReq, userId);
    }


    /**
     * save (insert and update)
     */
    @RequestMapping("save")
    public ServerResponse save(@RequestBody RecordReq recordReq) {
        return recordService.save(recordReq);
    }

    /**
     * delete
     */
    @RequestMapping("delete/{recordId}")
    public ServerResponse delete(@PathVariable Long recordId) {
        return recordService.delete(recordId);
    }
}
