package com.springboot.blog.service.impl;

import com.springboot.blog.dao.SysLogMapper;
import com.springboot.blog.dao.SysViewMapper;
import com.springboot.blog.entity.SysLog;
import com.springboot.blog.entity.SysLogExample;
import com.springboot.blog.entity.SysView;
import com.springboot.blog.entity.SysViewExample;
import com.springboot.blog.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// System Service implementation
@Component
public class SysServiceImpl implements SysService {

    @Autowired
    SysLogMapper sysLogMapper;
    @Autowired
    SysViewMapper sysViewMapper;


    // Add a log information
    @Override
    public void addLog(SysLog sysLog) {
        sysLogMapper.insertSelective(sysLog);
    }

    // Increase traffic
    @Override
    public void addView(SysView sysView) {
        sysViewMapper.insertSelective(sysView);
    }

    // Get the total number of log
    @Override
    public int getLogCount() {
        SysLogExample example = new SysLogExample();
        return sysLogMapper.selectByExample(example).size();
    }

    // return number of view
    @Override
    public int getViewCount() {
        SysViewExample example = new SysViewExample();
        return sysViewMapper.selectByExample(example).size();
    }

    // return log data
    @Override
    public List<SysLog> listAllLog() {
        SysLogExample example = new SysLogExample();
        return sysLogMapper.selectByExample(example);
    }

    // return visit data
    @Override
    public List<SysView> listAllView() {
        SysViewExample example = new SysViewExample();
        return sysViewMapper.selectByExample(example);
    }
}
