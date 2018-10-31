package com.springboot.blog.interceptor;

import com.springboot.blog.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Management System Interceptor
public class BackInterceptor implements HandlerInterceptor {

    private static String username = "leejoonsung";
    private static String password = "123456";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // flag true - login successful
        boolean flag = true;

        //To-do: password encryption

        //get user from session
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //if user is null redirect to error page
            // use getRequestDispatcher to keep the address unchanged.
            request.getRequestDispatcher(request.getContextPath() + "/error.html").forward(request, response);
            flag = false;
        } else {
            // Validate the admin account and password
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }
}
