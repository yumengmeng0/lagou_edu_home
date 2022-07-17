package com.my.web.servlet;

import com.my.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ymm
 * @date: 2022/7/13
 * @version: 1.0.0
 * @description:
 */
@WebServlet("/test")
public class TestServlet extends BaseServlet {

    public void addCourse(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("新建课程");
    }

    public void findByName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("根据课程名查询课程");
    }

    public void findByStatus(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("根据课程状态查询课程");
    }
}
