package com.example.nighty.service.Impl;

import com.example.nighty.Req.PageReq;
import com.example.nighty.Resp.UpdateResp;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Article;
import com.example.nighty.domain.ArticleExample;
import com.example.nighty.mapper.ArticleMapper;
import com.example.nighty.service.ArticleService;
import com.example.nighty.util.CopyUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final Logger LOG = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Resource
    private ArticleMapper articleMapper;


    @Override
    public PageReq list(PageReq pageReq, Long uid) {

        //查询当前登录用户的文章
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        criteria.andUidEqualTo(Math.toIntExact(uid));
        List<Article> articles = articleMapper.selectByExampleWithBLOBs(articleExample);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        pageReq.setTotal(pageInfo.getTotal());
        pageReq.setList(articles);
        return pageReq;
    }


    @Override
    public ServerResponse<UpdateResp> saveOrUpdate(Article article) {
        //LOG.info(article.getContent());
        if (article.getAid() == null) {
            int count = articleMapper.insert(article);
            if (count == 0) {
                //throw new BlogException(BlogEnum.ARTICLE_PUNISH);
                return ServerResponse.createByErrorMessage("Publish failed");
            } else {
                ArticleExample articleExample = new ArticleExample();
                ArticleExample.Criteria criteria = articleExample.createCriteria();
                criteria.andUidEqualTo(article.getUid());
                List<Article> articles = articleMapper.selectByExampleWithBLOBs(articleExample);
                //LOG.info(articles.toString());
                Article newArticle = articles.get(articles.size() - 1);
                UpdateResp resp = CopyUtil.copy(newArticle, UpdateResp.class);
                //LOG.info(resp.getContent());
                return ServerResponse.createBySuccess("Published successfully!", resp);
            }
        } else {
            int count1 = articleMapper.updateByPrimaryKeySelective(article);
            if (count1 == 0) {
                return ServerResponse.createByErrorMessage("Edit failed");
            } else {
                ArticleExample articleExample = new ArticleExample();
                ArticleExample.Criteria criteria = articleExample.createCriteria();
                criteria.andUidEqualTo(article.getUid());
                List<Article> articles = articleMapper.selectByExampleWithBLOBs(articleExample);
                //LOG.info(articles.toString());
                Article newArticle = articles.get(articles.size() - 1);
                UpdateResp resp = CopyUtil.copy(newArticle, UpdateResp.class);
                //LOG.info(resp.getContent());
                return ServerResponse.createBySuccess("Edit successfully!", resp);
            }
        }
        //return article;

    }

    @Override
    public ServerResponse deleteById(Integer id) {
        int count = articleMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            return ServerResponse.createByErrorMessage("Failed!");
        } else {
            return ServerResponse.createBySuccessMessage("Deleted!");
        }
    }

}
