package com.example.nighty.service;


import com.example.nighty.Req.PageReq;
import com.example.nighty.Resp.UpdateResp;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Article;



public interface ArticleService {
    PageReq list(PageReq pageReq, Long uid);

    ServerResponse<UpdateResp> saveOrUpdate(Article article);

    ServerResponse deleteById(Integer id);
}
