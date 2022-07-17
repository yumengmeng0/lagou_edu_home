package com.my.base;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author: ymm
 * @date: 2022/7/13
 * @version: 1.0.0
 * @description:
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * doPost方法作为一个调度器，根据请求功能的不同，调用不同的方法
     * 规定必须传递一个参数，必须传递一个参数
     * methodName = 功能名
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String methodName = null;

        // 1. 获取POST请求的Content-Type

        String contentType = request.getHeader("Content-Type");

        // 2. 判断传递的数据是否是JSON格式
        if ("application/json;charset=utf-8".equals(contentType)) {
            String postJSON = getPostJSON(request);
            Map<String, Object> map = JSON.parseObject(postJSON, Map.class);

            methodName = (String) map.get("methodName");

            // 把JSON转化为map
            request.setAttribute("map", map);

        } else {
            methodName = request.getParameter("methodName");
        }


//        String methodName = request.getParameter("methodName");

//        if ("addCourse".equals(methodName)) {
//            addCourse(request, response);
//        } else if ("findByName".equals(methodName)) {
//            findByName(request, response);
//        } else if ("findByStatus".equals(methodName)) {
//            findByStatus(request, response);
//        } else {
//            System.out.println("请求的功能不存在！");
//        }

        if (methodName != null) {
            Class c = this.getClass();

            try {
                Method method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                method.invoke(this, request, response);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("请求的功能不存在！");
        }

    }


    public String getPostJSON(HttpServletRequest request) {

        try {
            BufferedReader reader = request.getReader();
            StringBuffer stringBuffer = new StringBuffer();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }

            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
