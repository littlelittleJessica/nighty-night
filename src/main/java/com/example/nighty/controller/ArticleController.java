package com.example.nighty.controller;

import com.example.nighty.Req.PageReq;
import com.example.nighty.Resp.UpdateResp;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Article;
import com.example.nighty.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/article/")
public class ArticleController {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Resource
    private ArticleService articleService;

    @PostMapping(value = "list/{uid}")
    public ServerResponse list(@RequestBody PageReq page, @PathVariable Long uid) {
        PageReq pageReq = new PageReq();
        pageReq.setPage(page.getPage());
        pageReq.setSize(page.getSize());
        return ServerResponse.createBySuccess("Query diary success", articleService.list(pageReq, uid));
    }

    //异步发布文章
    @PostMapping(value = "saveOrUpdate/{uid}")
    public ServerResponse<UpdateResp> saveOrUpdate(@RequestBody Article article, @PathVariable long uid) {
        article.setUid((int) uid);
        //LOG.info(article.toString());
        ServerResponse<UpdateResp> resp = articleService.saveOrUpdate(article);
        return resp;
    }

    //异步删除文章
    @GetMapping(value = "deleteById/{id}")
    public ServerResponse deleteById(@PathVariable Integer id) {
        ServerResponse resp = articleService.deleteById(id);
        return resp;
    }
}
