package com.my.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.my.base.BaseServlet;
import com.my.pojo.Course;
import com.my.service.CourseService;
import com.my.service.impl.CourseServiceImpl;
import com.my.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: ymm
 * @date: 2022/7/14
 * @version: 1.0.0
 * @description:
 */
@WebServlet("/course")
public class CourseServlet extends BaseServlet {

    private CourseService courseService = new CourseServiceImpl();

    /**
     * @param request
     * @param response
     */
    public void findCourseList(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Course> courseList = courseService.findCourseList();

            // 响应查询结果
            // SimplePropertyPreFilter指定要转换的JSON字段
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class, "id", "course_name", "price", "sort_num", "status");

            String result = JSON.toJSONString(courseList, filter);
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * @param request
     * @param response
     */
    public void findByCourseNameAndStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            String course_name = request.getParameter("course_name");
            String status = request.getParameter("status");

            List<Course> courseList = courseService.findByCourseNameAndStatus(course_name, status);

            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class, "id", "course_name", "price", "sort_num", "status");

            String result = JSON.toJSONString(courseList, filter);
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 根据课程查询id
     *
     * @param request
     * @param response
     * @return
     */
    public void findCourseById(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            Course course = courseService.findCourseById(Integer.parseInt(id));
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter("id", "course_name", "brief", "teacher_name",
                    "teacher_info", "preview_first_field", "preview_second_field", "discounts", "price", "price_tag",
                    "share_image_title", "share_title", "share_description", "course_description", "course_img_url");

            String result = JSON.toJSONString(course, filter);
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改课程状态
     *
     * @param request
     * @param response
     */
    public void updateCourseStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            Course course = courseService.findCourseById(Integer.parseInt(id));

            int status = course.getStatus();
            if (status == 0) {
                course.setStatus(1);
            } else {
                course.setStatus(0);
            }

            String dateFormat = DateUtils.getDateFormat();
            course.setUpdate_time(dateFormat);
            Map<String, Integer> map = courseService.updateCourseStatus(course);
            String result = JSON.toJSONString(map);
            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
