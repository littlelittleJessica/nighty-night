package com.example.nighty.controller;

import com.example.nighty.common.ServerResponse;
import com.example.nighty.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/9/8
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ServerResponse businessExceptionHandler(BusinessException e) {
        LOG.error("业务异常：{}", e.getCode().getDesc());
        return ServerResponse.createByErrorMessage(e.getMessage());
    }
}
