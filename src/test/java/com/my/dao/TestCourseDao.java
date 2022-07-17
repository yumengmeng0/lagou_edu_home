package com.my.dao;

import com.my.dao.impl.CourseDaoImpl;
import com.my.pojo.Course;
import com.my.utils.DateUtils;
import org.junit.Test;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/14
 * @version: 1.0.0
 * @description:
 */
public class TestCourseDao {

    CourseDao courseDao = new CourseDaoImpl();

    @Test
    public void TestFindCourseList() {
        List<Course> courseList = courseDao.findCourseList();
        System.out.println("courseList.size() = " + courseList.size());
    }

    @Test
    public void testFindByCourseNameAndStatus() {
        List<Course> courseList = courseDao.findByCourseNameAndStatus("微服务", null);

        for (Course course : courseList) {

            System.out.println("course.getCourse_name() = " + course.getCourse_name());
        }
    }

    @Test
    public void testSaveCourseSalesInfo() {
        Course course = new Course();
        course.setCourse_name("课程名");
        course.setBrief("简介");
        course.setTeacher_name("教师名");
        course.setTeacher_info("教师信息");
        course.setPreview_first_field("共10讲");
        course.setPreview_second_field("每周更新");
        course.setDiscounts(88.88);
        course.setPrice(55.00);
        course.setPrice_tag("价格标签");
        course.setShare_image_title("图片标题");
        course.setShare_title("分享标题");
        course.setShare_description("分享介绍");
        course.setCourse_description("课程介绍");
        course.setCourse_img_url("图片地址");
        course.setStatus(0);
        String dateFormat = DateUtils.getDateFormat();
        course.setCreate_time(dateFormat);
        course.setUpdate_time(dateFormat);

        int i = courseDao.saveCourseSalesInfo(course);
        System.out.println("i = " + i);
    }

    @Test
    public void testFindCourseById(){
        Course course = courseDao.findCourseById(1);
        System.out.println("course = " + course);

    }


    @Test
    public void testUpdateCourseSalesInfo(){

        Course course = courseDao.findCourseById(1);
        course.setCourse_name(course.getCourse_name() + "name");
        int i = courseDao.updateCourseSalesInfo(course);
        System.out.println("i = " + i);

    }
}
