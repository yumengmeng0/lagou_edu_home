package com.my.dao;

import com.my.pojo.Course;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/14
 * @version: 1.0.0
 * @description: 课程模块Dao接口
 */
public interface CourseDao {

    /**
     * 查询课程列表
     *
     * @return
     */
    List<Course> findCourseList();


    /**
     * 根据条件查询课程信息
     *
     * @param courseName
     * @param status
     * @return
     */
    List<Course> findByCourseNameAndStatus(String courseName, String status);

    /**
     * 保存课程营销信息
     *
     * @param course
     * @return
     */
    int saveCourseSalesInfo(Course course);

    /**
     * 根据课程查询id
     *
     * @param id
     * @return
     */
    Course findCourseById(int id);

    /**
     * 修改营销信息
     *
     * @param course
     * @return
     */
    int updateCourseSalesInfo(Course course);


    /**
     * 修改课程状态
     * @param course
     * @return
     */
    int updateCourseStatus(Course course);


}
