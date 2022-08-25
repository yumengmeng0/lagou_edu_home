package com.my.dao;

import com.my.pojo.Course;
import com.my.pojo.Course_Lesson;
import com.my.pojo.Course_Section;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/16
 * @version: 1.0.0
 * @description: 课程内容管理
 */
public interface CourseContentDao {

    /**
     * 根据课程id查询课程相关信息
     *
     * @param courseId
     * @return
     */
    List<Course_Section> findSectionAndLessonByCourseId(int courseId);

    /**
     * 根据章节id查询章节相关的课时信息
     *
     * @return
     */
    List<Course_Lesson> findLessonBySectionId(int sessionId);

    /**
     * 根据课程id查询课程信息
     *
     * @param courseId
     * @return
     */
    Course findCourseById(int courseId);

    /**
     * 保存章节信息
     *
     * @param courseSection
     * @return
     */
    int saveSection(Course_Section courseSection);

    /**
     * 修改章节信息
     *
     * @param courseSection
     * @return
     */
    int updateSection(Course_Section courseSection);


    /**
     * 修改章节状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateSectionStatus(int id, int status);

    /**
     * 添加课时信息
     *
     * @param courseLesson
     * @return
     */
    int saveLesson(Course_Lesson courseLesson);

    /**
     * 更新课时信息
     *
     * @param courseLesson
     * @return
     */
    int updateLesson(Course_Lesson courseLesson);

    /**
     * 更新课时状态
     *
     * @param id
     * @param status
     * @return
     */
    int updateLessonStatus(int id, int status);


}
