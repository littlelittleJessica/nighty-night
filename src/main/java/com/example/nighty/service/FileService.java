package com.example.nighty.service;

import com.example.nighty.Req.FileReq;
import com.example.nighty.domain.File;
import com.example.nighty.domain.FileExample;
import com.example.nighty.mapper.FileMapper;
import com.example.nighty.util.CopyUtil;
import com.example.nighty.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FileService {

    @Resource
    private FileMapper fileMapper;
    

    /**
     * save
     * update if id has a value, or insert
     */
    public void save(FileReq fileReq) {
        File file = CopyUtil.copy(fileReq, File.class);
        File fileDb = fileMapper.selectByPrimaryKey(fileReq.getId());
        if (fileDb == null) {
            this.insert(file);
        } else {
            this.update(fileDb);
        }
    }

    /**
     * insert
     */
    private void insert(File file) {

        Date now = new Date();
        file.setCreatedAt(now);
        file.setUpdatedAt(now);
        file.setId(UuidUtil.getShortUuid());
        fileMapper.insert(file);
    }

    /**
     * update
     */
    private void update(File file) {
        file.setUpdatedAt(new Date());
        fileMapper.updateByPrimaryKey(file);
    }

    /**
     * delete
     */
    public void delete(String id) {
        fileMapper.deleteByPrimaryKey(id);
    }
}