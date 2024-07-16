package com.example.demo.application.common.request;

import com.example.demo.application.common.flash.NotificationManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CustomInterceptor implements HandlerInterceptor {
    private final NotificationManager notificationManager;

    CustomInterceptor(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Do nothing before handling the request
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        if (modelAndView != null) {
            modelAndView.addObject("currentUrlPath", request.getRequestURI());
            modelAndView.addObject("flash", notificationManager);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // Do nothing after completing the request
    }
}
