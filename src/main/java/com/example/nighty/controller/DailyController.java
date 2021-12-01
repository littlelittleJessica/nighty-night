package com.example.nighty.controller;

import com.example.nighty.Req.DailyReq;
import com.example.nighty.Req.PageReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Daily;
import com.example.nighty.domain.DailyContent;
import com.example.nighty.mapper.DailyContentMapper;
import com.example.nighty.service.DailyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/21
 */
@RestController
@RequestMapping("/admin/daily-content")
public class DailyController {
    @Resource
    private DailyService dailyService;

    @PostMapping("/list")
    public ServerResponse list(@RequestBody PageReq pageReq) {
        return dailyService.list(pageReq);
    }

    @PostMapping("/save")
    public ServerResponse save(@RequestBody DailyReq daily) {
        return dailyService.save(daily);
    }

    @DeleteMapping("/delete/{id}")
    public ServerResponse delete(@PathVariable Long id) {
        return dailyService.delete(id);
    }

    @GetMapping("/show/{id}")
    public ServerResponse show(@PathVariable Long id) {
        return dailyService.show(id);
    }

    /**
     * search daily by title
     */
    @PostMapping("/search/{title}")
    public ServerResponse searchByName(@PathVariable String title, @RequestBody PageReq page) {
        PageReq pageReq = new PageReq();
        pageReq.setPage(page.getPage());
        pageReq.setSize(page.getSize());
        return ServerResponse.createBySuccess("Search daily success", dailyService.searchByTitle(pageReq, title));
    }

    @PostMapping("/save-content")
    public ServerResponse saveContent(@RequestBody DailyContent dailyContent) {
        return dailyService.saveContent(dailyContent);
    }

}
