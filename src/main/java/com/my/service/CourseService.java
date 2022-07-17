package com.my.service;

import com.my.pojo.Course;
import com.my.pojo.Course_Section;

import java.util.List;
import java.util.Map;

/**
 * @author: ymm
 * @date: 2022/7/14
 * @version: 1.0.0
 * @description: 课程管理模块Service层接口
 */
public interface CourseService {


    /**
     * 查询课程列表
     *
     * @return
     */
    public List<Course> findCourseList();

    /**
     * 根据条件查询课程信息
     *
     * @param courseName
     * @param status
     * @return
     */
    public List<Course> findByCourseNameAndStatus(String courseName, String status);

    /**
     * 保存课程营销信息
     *
     * @param course
     * @return
     */
    public String saveCourseSalesInfo(Course course);


    /**
     * 根据课程查询id
     *
     * @param id
     * @return
     */
    public Course findCourseById(int id);

    /**
     * 修改营销信息
     *
     * @param course
     * @return
     */
    public String updateCourseSalesInfo(Course course);


    /**
     * 修改课程状态
     * @param course
     * @return
     */
    public Map<String,Integer> updateCourseStatus(Course course);

}
