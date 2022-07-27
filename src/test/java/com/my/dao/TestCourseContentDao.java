package com.my.dao;

import com.my.dao.impl.CourseContentDaoImpl;
import com.my.pojo.Course;
import com.my.pojo.Course_Lesson;
import com.my.pojo.Course_Section;
import com.my.utils.DateUtils;
import org.junit.Test;

import javax.servlet.annotation.WebServlet;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/16
 * @version: 1.0.0
 * @description:
 */
public class TestCourseContentDao {


    private CourseContentDao courseContentDao = new CourseContentDaoImpl();

    @Test
    public void testFindSectionAndLessonByCourseId() {
        List<Course_Section> sectionList = courseContentDao.findSectionAndLessonByCourseId(1);
        for (Course_Section course_section : sectionList) {
            int id = course_section.getId();
            System.out.println("id = " + id);

            List<Course_Lesson> lessonList = course_section.getLessonList();
            for (Course_Lesson course_lesson : lessonList) {
                int id1 = course_lesson.getId();
                String theme = course_lesson.getTheme();
                int section_id = course_lesson.getSection_id();

                System.out.println("id = " + course_lesson.getId() + "，theme = " + course_lesson.getTheme() + ",section_id = " + section_id);
            }
        }
    }

    @Test
    public void testFindCourseByCourseId() {
        Course course = courseContentDao.findCourseById(59);
        System.out.println("course = " + course);
    }

    @Test
    public void testSaveSection() {
        Course_Section course_section = new Course_Section();
        course_section.setSection_name("Vue高级2");
        course_section.setDescription("Vue高级2描述");
        course_section.setCourse_id(59);
        course_section.setOrder_num(8);
        String dateFormat = DateUtils.getDateFormat();
        course_section.setCreate_time(dateFormat);
        course_section.setUpdate_time(dateFormat);
        courseContentDao.saveSection(course_section);
    }

    @Test
    public void testUpdateSection() {
        Course_Section course_section = new Course_Section();
        course_section.setId(44);
        course_section.setSection_name("Vue高级2222222222");
        course_section.setDescription("Vue高级2描述2222222");
        course_section.setCourse_id(59);
        course_section.setOrder_num(8);
        String dateFormat = DateUtils.getDateFormat();
        course_section.setCreate_time(dateFormat);
        course_section.setUpdate_time(dateFormat);
        courseContentDao.updateSection(course_section);
    }

    @Test
    public void testUpdateSectionStatus() {
        int i = courseContentDao.updateSectionStatus(1, 0);
        System.out.println("i = " + i);
    }
}
