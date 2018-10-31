package com.springboot.blog.controller;

import com.springboot.blog.entity.SysLog;
import com.springboot.blog.entity.SysView;
import com.springboot.blog.service.SysService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//System Controller
@RestController
@RequestMapping("/admin")
public class SysController {

    @Autowired
    SysService sysService;

    //return log information
    @ApiOperation("return SysLog data")
    @GetMapping("/sys/log")
    public List<SysLog> listAllLog() {
        return sysService.listAllLog();
    }

    //return view history information
    @ApiOperation("return SysView data")
    @GetMapping("/sys/view")
    public List<SysView> listAllView() {
        return sysService.listAllView();
    }

}
