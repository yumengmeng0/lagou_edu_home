package com.my.service.impl;

import com.my.base.StatusCode;
import com.my.dao.CourseDao;
import com.my.dao.impl.CourseDaoImpl;
import com.my.pojo.Course;
import com.my.service.CourseService;
import com.my.utils.DateUtils;
import jdk.jshell.Snippet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ymm
 * @date: 2022/7/14
 * @version: 1.0.0
 * @description:
 */
public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao = new CourseDaoImpl();

    /**
     * 查询课程列表
     *
     * @return
     */
    @Override
    public List<Course> findCourseList() {
        return courseDao.findCourseList();
    }

    /**
     * 根据条件查询课程信息
     *
     * @param courseName
     * @param status
     * @return
     */
    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        return courseDao.findByCourseNameAndStatus(courseName, status);
    }

    /**
     * 保存课程营销信息
     *
     * @param course
     * @return
     */
    @Override
    public String saveCourseSalesInfo(Course course) {

        // 补全课程营销信息
        String dateFormat = DateUtils.getDateFormat();

        course.setCreate_time(dateFormat);
        course.setUpdate_time(dateFormat);
        course.setStatus(1);
        int raw = courseDao.saveCourseSalesInfo(course);
        String result = "";

        if (raw > 0) {
            result = StatusCode.SUCCESS.toString();
            return result;
        } else {
            result = StatusCode.FAIL.toString();
            return result;
        }

    }

    /**
     * 根据课程查询id
     *
     * @param id
     * @return
     */
    @Override
    public Course findCourseById(int id) {
        return courseDao.findCourseById(id);
    }

    /**
     * 修改营销信息
     *
     * @param course
     * @return
     */
    @Override
    public String updateCourseSalesInfo(Course course) {
        String dateFormat = DateUtils.getDateFormat();
        course.setUpdate_time(dateFormat);
        int row = courseDao.updateCourseSalesInfo(course);

        if (row > 0) {
            String result = StatusCode.SUCCESS.toString();
            return result;
        } else {
            String result = StatusCode.FAIL.toString();
            return result;
        }

    }

    /**
     * 修改课程状态
     *
     * @param course
     * @return
     */
    @Override
    public Map<String, Integer> updateCourseStatus(Course course) {

        int row = courseDao.updateCourseStatus(course);
        Map<String, Integer> map = new HashMap<>();

        if (row > 0) {

            if (course.getStatus() == 0) {
                map.put("status", 0);
            } else {
                map.put("status", 1);
            }
        }
        return map;
    }


}
