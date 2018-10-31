package com.springboot.blog.interceptor;

import com.springboot.blog.entity.SysLog;
import com.springboot.blog.entity.SysView;
import com.springboot.blog.service.SysService;
import com.springboot.blog.util.BrowserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

// user ForeInterceptor to record the traffic
public class ForeInterceptor implements HandlerInterceptor {

    @Autowired
    SysService sysService;

    private SysLog sysLog = new SysLog();
    private SysView sysView = new SysView();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // IP of the visitor
        String ip = request.getRemoteAddr();
        // Visited URL
        String url = request.getRequestURL().toString();
        // The name of browser
        String userbrowser = BrowserUtil.getOsAndBrowserInfo(request);

        // add to sysLog
        sysLog.setIp(StringUtils.isEmpty(ip) ? "0.0.0.0" : ip);
        sysLog.setOperateBy(StringUtils.isEmpty(userbrowser) ? "cannot get the name of browser" : userbrowser);
        sysLog.setOperateUrl(StringUtils.isEmpty(url) ? "cannot get the visited url" : url);

        // increase the visit
        sysView.setIp(StringUtils.isEmpty(ip) ? "0.0.0.0" : ip);
        sysService.addView(sysView);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // save the log
            sysLog.setRemark(method.getName());
            sysService.addLog(sysLog);

        } else {
            String uri = request.getRequestURI();
            sysLog.setRemark(uri);
            sysService.addLog(sysLog);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
