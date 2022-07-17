package com.my.service.impl;

import com.my.base.StatusCode;
import com.my.dao.CourseContentDao;
import com.my.dao.impl.CourseContentDaoImpl;
import com.my.pojo.Course;
import com.my.pojo.Course_Section;
import com.my.service.CourseContentService;
import com.my.utils.DateUtils;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/16
 * @version: 1.0.0
 * @description:
 */
public class CourseContentServiceImpl implements CourseContentService {

    private CourseContentDao courseContentDao = new CourseContentDaoImpl();

    /**
     * 根据课程id查询课程相关信息
     *
     * @param courseId
     * @return
     */
    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        return courseContentDao.findSectionAndLessonByCourseId(courseId);
    }

    /**
     * 根据课程id查询课程信息
     *
     * @param courseId
     * @return
     */
    @Override
    public Course findCourseById(int courseId) {
        return courseContentDao.findCourseById(courseId);
    }

    /**
     * 保存章节信息
     *
     * @param courseSection
     * @return
     */
    @Override
    public String saveSection(Course_Section courseSection) {
        courseSection.setStatus(2); // 0 隐藏，1 待更新， 2 已发布
        String dateFormat = DateUtils.getDateFormat();
        courseSection.setCreate_time(dateFormat);
        courseSection.setUpdate_time(dateFormat);
        int row = courseContentDao.saveSection(courseSection);

        String result = null;

        if (row > 0) {
            result = StatusCode.SUCCESS.toString();
        } else {
            result = StatusCode.FAIL.toString();
        }

        return result;

    }


    /**
     * 修改章节信息
     *
     * @param courseSection
     * @return
     */
    @Override
    public String updateSection(Course_Section courseSection) {
        String dateFormat = DateUtils.getDateFormat();
        courseSection.setUpdate_time(dateFormat);

        int row = courseContentDao.updateSection(courseSection);
        String result = null;

        if (row > 0) {
            result = StatusCode.SUCCESS.toString();
        } else {
            result = StatusCode.FAIL.toString();
        }

        return result;
    }

    /**
     * 修改课程章节状态
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public String updateSectionStatus(int id, int status) {
        int row = courseContentDao.updateSectionStatus(id, status);
        String result = null;

        if (row > 0) {
            result = StatusCode.SUCCESS.toString();
        } else {
            result = StatusCode.FAIL.toString();
        }

        return result;
    }
}
