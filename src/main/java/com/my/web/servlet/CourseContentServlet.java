package com.my.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.my.base.BaseServlet;
import com.my.pojo.Course;
import com.my.pojo.Course_Section;
import com.my.service.CourseContentService;
import com.my.service.impl.CourseContentServiceImpl;
import com.my.utils.DruidUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author: ymm
 * @date: 2022/7/16
 * @version: 1.0.0
 * @description:
 */
@WebServlet("/courseContent")
public class CourseContentServlet extends BaseServlet {

    private CourseContentService courseContentService = new CourseContentServiceImpl();

    /**
     * 展示对应课程的章节与课时信息
     *
     * @param request
     * @param response
     */
    public void findSectionAndLessonByCourseId(HttpServletRequest request, HttpServletResponse response) {

        try {
            String course_id = request.getParameter("course_id");
            List<Course_Section> sectionList = courseContentService.findSectionAndLessonByCourseId(Integer.parseInt(course_id));

            String result = JSON.toJSONString(sectionList);

            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据id查询课程信息
     *
     * @param request
     * @param response
     */
    public void findCourseById(HttpServletRequest request, HttpServletResponse response) {
        try {
            String course_id = request.getParameter("course_id");

            Course course = courseContentService.findCourseById(Integer.parseInt(course_id));
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class, "id", "course_name");
            String result = JSON.toJSONString(course, filter);

            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存或更新章节信息
     *
     * @param request
     * @param response
     */
    public void saveOrUpdateSection(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> map = (Map) request.getAttribute("map");

            Course_Section course_section = new Course_Section();

//            BeanUtils.populate(course_section, map);

            BeanUtils.copyProperties(course_section, map.get("section"));

            String result = null;
            if (course_section.getId() == 0) {
                // 新增章节
                result = courseContentService.saveSection(course_section);

            } else {
                // 修改章节
                result = courseContentService.updateSection(course_section);
            }
            response.getWriter().print(result);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改章节状态
     *
     * @param request
     * @param response
     */
    public void updateSectionStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            String id = request.getParameter("id");
            String status = request.getParameter("status");

            String result = courseContentService.updateSectionStatus(Integer.parseInt(id), Integer.parseInt(status));

            response.getWriter().print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
