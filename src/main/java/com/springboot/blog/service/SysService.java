package com.springboot.blog.service;

import com.springboot.blog.entity.SysLog;
import com.springboot.blog.entity.SysView;

import java.util.List;

// Log/Traffic Service
public interface SysService {
    void addLog(SysLog sysLog);

    void addView(SysView sysView);

    int getLogCount();

    int getViewCount();

    List<SysLog> listAllLog();

    List<SysView> listAllView();
}
