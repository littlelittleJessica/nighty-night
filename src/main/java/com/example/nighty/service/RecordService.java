package com.example.nighty.service;

import com.example.nighty.Req.PageReq;
import com.example.nighty.Req.RecordReq;
import com.example.nighty.Resp.RecordResp;
import com.example.nighty.common.ServerResponse;
import com.example.nighty.domain.Record;
import com.example.nighty.domain.RecordExample;
import com.example.nighty.mapper.RecordMapper;
import com.example.nighty.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/10/23
 */
@Service
public class RecordService {

    @Resource
    private RecordMapper recordMapper;

    /**
     * List record for recent five days
     */
    public ServerResponse<List<RecordResp>> List(PageReq pageReq, Long userId) {
        PageHelper.startPage(pageReq.getPage(), pageReq.getSize());
        RecordExample recordExample = new RecordExample();
        RecordExample.Criteria criteria = recordExample.createCriteria();
        criteria.andUserIdEqualTo(userId);

        List<Record> recordList = recordMapper.selectByExample(recordExample);
        List<RecordResp> recordRespList = CopyUtil.copyList(recordList, RecordResp.class);

        return ServerResponse.createBySuccess("List recent 5 days record succeeded", recordRespList);
    }

    /**
     * save record (insert and update)
     */
    public ServerResponse save(RecordReq recordReq) {
        RecordExample recordExample = new RecordExample();
        RecordExample.Criteria criteria = recordExample.createCriteria();
        criteria.andUserIdEqualTo(recordReq.getUserId())
                .andDateEqualTo(recordReq.getDate());
        List<Record> recordList = recordMapper.selectByExample(recordExample);

        if (!CollectionUtils.isEmpty(recordList) && recordList.size() > 0) {
            Long id = recordList.get(0).getId();
            Record recordUp = CopyUtil.copy(recordReq, Record.class);
            recordUp.setId(id);
            int update = recordMapper.updateByPrimaryKeySelective(recordUp);
            if (update > 0) {
                return ServerResponse.createBySuccess("Update record succeeded", recordUp);
            } else {
                return ServerResponse.createByError("Update record failed", recordReq);
            }

        } else {
            Record record = CopyUtil.copy(recordReq, Record.class);
            int insert = recordMapper.insert(record);
            if (insert > 0) {
                List<Record> recordNew = recordMapper.selectByExample(recordExample);
                record.setId(recordNew.get(0).getId());
                return ServerResponse.createBySuccess("Insert record succeeded", record);
            } else {
                return ServerResponse.createByError("Insert record failed", record);
            }
        }
    }

    /**
     * delete
     */
    public ServerResponse delete(Long id) {
        RecordExample recordExample = new RecordExample();
        RecordExample.Criteria criteria = recordExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<Record> recordList = recordMapper.selectByExample(recordExample);
        if (!CollectionUtils.isEmpty(recordList) && recordList.size() > 0) {
            recordMapper.deleteByExample(recordExample);
            return ServerResponse.createBySuccessMessage("Record deleted");
        } else {
            return ServerResponse.createByErrorMessage("Record does not exist");
        }
    }


}
