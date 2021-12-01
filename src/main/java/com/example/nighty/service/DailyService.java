package com.example.nighty.service;

import com.example.nighty.Req.DailyReq;
import com.example.nighty.Req.PageReq;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.*;
import com.example.nighty.mapper.DailyContentMapper;
import com.example.nighty.mapper.DailyMapper;
import com.example.nighty.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/21
 */
@Service
public class DailyService {
    @Resource
    private DailyMapper dailyMapper;

    @Resource
    private DailyContentMapper dailyContentMapper;

    /**
     * 列表查询
     */
    public ServerResponse list(PageReq pageReq) {
        DailyExample dailyExample = new DailyExample();
        dailyExample.createCriteria();
        PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        List<Daily> dailies = dailyMapper.selectByExample(dailyExample);
        PageInfo<Daily> pageInfo = new PageInfo<>(dailies);
        pageReq.setTotal(pageInfo.getTotal());
        pageReq.setList(dailies);
        return ServerResponse.createBySuccess("List daily succeeded", pageReq);
    }

    /**
     * search daily by title
     */
    public PageReq searchByTitle(PageReq pageReq, String title) {
        PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        DailyExample dailyExample = new DailyExample();
        dailyExample.createCriteria().andTitleLike("%" + title + "%");
        List<Daily> dailyList = dailyMapper.selectByExample(dailyExample);
        PageInfo<Daily> pageInfo = new PageInfo<>(dailyList);
        pageReq.setTotal(pageInfo.getTotal());
        pageReq.setList(dailyList);
        return pageReq;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public ServerResponse save(DailyReq daily) {
        Daily dailyNew = CopyUtil.copy(daily, Daily.class);
        if (StringUtils.isEmpty(daily.getId())) {
            this.insert(dailyNew);
        } else {
            this.update(dailyNew);
        }
        return ServerResponse.createBySuccess("Save daily succeeded!");
    }

    /**
     * 新增
     */
    private void insert(Daily daily) {
        dailyMapper.insert(daily);
    }

    /**
     * 更新
     */
    private void update(Daily daily) {
        dailyMapper.updateByPrimaryKey(daily);
    }

    /**
     * 删除
     */
    public ServerResponse delete(Long id) {
        dailyMapper.deleteByPrimaryKey(id);
        return ServerResponse.createBySuccess("Delete daily succeeded!");
    }

    /**
     * 展示内容
     */
    public ServerResponse show(Long id) {
        DailyContentExample example = new DailyContentExample();
        example.createCriteria().andIdEqualTo(id);
        List<DailyContent> dailyContents = dailyContentMapper.selectByExampleWithBLOBs(example);
        if (!CollectionUtils.isEmpty(dailyContents) && dailyContents.size() != 0) {
            DailyContent dailyContent = dailyContents.get(0);
            return ServerResponse.createBySuccess("Show content", dailyContent);
        } else {
            return ServerResponse.createBySuccess("No content yet", null);
        }
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public ServerResponse saveContent(DailyContent dailyContent) {
        DailyContentExample example = new DailyContentExample();
        example.createCriteria().andIdEqualTo(dailyContent.getId());
        List<DailyContent> list = dailyContentMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            this.insertContent(dailyContent);
        } else {
            this.updateContent(dailyContent);
        }
        return ServerResponse.createBySuccess("Save daily content succeeded!");
    }

    /**
     * 新增
     */
    private void insertContent(DailyContent daily) {
        dailyContentMapper.insert(daily);
    }

    /**
     * 更新
     */
    private void updateContent(DailyContent daily) {
        dailyContentMapper.updateByPrimaryKeyWithBLOBs(daily);
    }
}
