package com.my.service.impl;

import com.my.base.StatusCode;
import com.my.dao.CourseContentDao;
import com.my.dao.impl.CourseContentDaoImpl;
import com.my.pojo.Course;
import com.my.pojo.Course_Lesson;
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
        String createTime = DateUtils.getDateFormat();
        courseSection.setCreate_time(createTime);
        courseSection.setUpdate_time(createTime);
        int row = courseContentDao.saveSection(courseSection);

        return getResultByRow(row);

    }


    /**
     * 修改章节信息
     *
     * @param courseSection
     * @return
     */
    @Override
    public String updateSection(Course_Section courseSection) {
        String updateTime = DateUtils.getDateFormat();
        courseSection.setUpdate_time(updateTime);

        int row = courseContentDao.updateSection(courseSection);
        return getResultByRow(row);
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
        return getResultByRow(row);
    }

    /**
     * 添加课时信息
     *
     * @param courseLesson
     * @return
     */
    @Override
    public String saveLesson(Course_Lesson courseLesson) {
        String createTime = DateUtils.getDateFormat();
        courseLesson.setCreate_time(createTime);
        courseLesson.setUpdate_time(createTime);
        int row = courseContentDao.saveLesson(courseLesson);
        return getResultByRow(row);
    }

    /**
     * 更新课时信息
     *
     * @param courseLesson
     * @return
     */
    @Override
    public String updateLesson(Course_Lesson courseLesson) {
        String updateTime = DateUtils.getDateFormat();
        courseLesson.setUpdate_time(updateTime);
        int row = courseContentDao.updateLesson(courseLesson);
        return getResultByRow(row);
    }

    /**
     * 更新课时状态
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public String updateLessonStatus(int id, int status) {
        int row = courseContentDao.updateLessonStatus(id, status);
        return getResultByRow(row);
    }

    /**
     * 根据dao层执行结果返回成功与否的字符串
     *
     * @param row dao层执行是受影响的行数
     * @return
     */
    private String getResultByRow(int row) {
//        String result = null;
//
//        if (row > 0) {
//            result = StatusCode.SUCCESS.toString();
//        } else {
//            result = StatusCode.FAIL.toString();
//        }

        return row > 0 ? StatusCode.SUCCESS.toString() : StatusCode.FAIL.toString();
    }
}
