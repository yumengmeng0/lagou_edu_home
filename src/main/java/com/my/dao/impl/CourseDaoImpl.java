package com.my.dao.impl;

import com.my.dao.CourseDao;
import com.my.pojo.Course;
import com.my.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/14
 * @version: 1.0.0
 * @description: 课程模块Dao实现类
 */
public class CourseDaoImpl implements CourseDao {

    private QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    /**
     * 查询课程列表
     *
     * @return
     */
    @Override
    public List<Course> findCourseList() {

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            // is_del 为 0 代表未删除数据
            String sql = "SELECT\n" +
                    "  id,\n" +
                    "  course_name,\n" +
                    "  price,\n" +
                    "  sort_num,\n" +
                    "  STATUS\n" +
                    "FROM\n" +
                    "  course\n" +
                    "WHERE is_del = ?;";

            List<Course> courseList = queryRunner.query(sql, new BeanListHandler<Course>(Course.class), 0);

            return courseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

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

        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            StringBuffer sql = new StringBuffer("SELECT id, course_name, price, sort_num, STATUS FROM course WHERE 1=1 AND is_del = ? ");


            // 保存参数的集合
            List<Object> paramList = new ArrayList<>();
            paramList.add(0);

            if (courseName != null && courseName != "") {
                sql.append(" AND course_name LIKE ? ");
                courseName = "%" + courseName + "%";
                paramList.add(courseName);
            }

            if (status != null && status != "") {
                sql.append(" AND status = ?");
                int i = Integer.parseInt(status);
                paramList.add(i);
            }

            List<Course> courseList = queryRunner.query(sql.toString(), new BeanListHandler<Course>(Course.class), paramList.toArray());
            return courseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 保存课程营销信息
     *
     * @param course
     * @return
     */
    @Override
    public int saveCourseSalesInfo(Course course) {
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = " INSERT INTO course (\n" +
                    "  course_name,\n" +
                    "  brief,\n" +
                    "  teacher_name,\n" +
                    "  teacher_info,\n" +
                    "  preview_first_field,\n" +
                    "  preview_second_field,\n" +
                    "  discounts,\n" +
                    "  price,\n" +
                    "  price_tag,\n" +
                    "  share_image_title,\n" +
                    "  share_title,\n" +
                    "  share_description,\n" +
                    "  course_description,\n" +
                    "  course_img_url,\n" +
                    "  STATUS,\n" +
                    "  create_time,\n" +
                    "  update_time\n" +
                    ")\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            Object[] param = {
                    course.getCourse_name(),
                    course.getBrief(),
                    course.getTeacher_name(),
                    course.getTeacher_info(),
                    course.getPreview_first_field(),
                    course.getPreview_second_field(),
                    course.getDiscounts(),
                    course.getPrice(),
                    course.getPrice_tag(),
                    course.getShare_image_title(),
                    course.getShare_title(),
                    course.getShare_description(),
                    course.getCourse_description(),
                    course.getCourse_img_url(),
                    course.getStatus(),
                    course.getCreate_time(),
                    course.getUpdate_time()
            };


            System.out.println("sql = " + sql);
            System.out.println("param = " + Arrays.toString(param));


            int row = queryRunner.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
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
        try {
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

            String sql = " SELECT\n" +
                    "  id,\n" +
                    "  course_name,\n" +
                    "  brief,\n" +
                    "  teacher_name,\n" +
                    "  teacher_info,\n" +
                    "  preview_first_field,\n" +
                    "  preview_second_field,\n" +
                    "  discounts,\n" +
                    "  price,\n" +
                    "  price_tag,\n" +
                    "  share_image_title,\n" +
                    "  share_title,\n" +
                    "  share_description,\n" +
                    "  course_description,\n" +
                    "  course_img_url,\n" +
                    "  STATUS\n" +
                    "FROM\n" +
                    "  course\n" +
                    "WHERE id = ?;";

            Course course = queryRunner.query(sql, new BeanHandler<Course>(Course.class), id);
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改营销信息
     *
     * @param course
     * @return
     */
    @Override
    public int updateCourseSalesInfo(Course course) {
        try {
            String sql = "UPDATE course SET\n" +
                    "  course_name = ?,\n" +
                    "  brief = ?,\n" +
                    "  teacher_name = ?,\n" +
                    "  teacher_info = ?,\n" +
                    "  preview_first_field = ?,\n" +
                    "  preview_second_field = ?,\n" +
                    "  discounts = ?,\n" +
                    "  price = ?,\n" +
                    "  price_tag = ?,\n" +
                    "  share_image_title = ?,\n" +
                    "  share_title = ?,\n" +
                    "  share_description = ?,\n" +
                    "  course_description = ?,\n" +
                    "  course_img_url = ?,\n" +
                    "  update_time = ?\n" +
                    "  WHERE id = ?\n";
            Object[] param = {
                    course.getCourse_name(),
                    course.getBrief(),
                    course.getTeacher_name(),
                    course.getTeacher_info(),
                    course.getPreview_first_field(),
                    course.getPreview_second_field(),
                    course.getDiscounts(),
                    course.getPrice(),
                    course.getPrice_tag(),
                    course.getShare_image_title(),
                    course.getShare_title(),
                    course.getShare_description(),
                    course.getCourse_description(),
                    course.getCourse_img_url(),
                    course.getUpdate_time(),
                    course.getId()

            };
            int row = queryRunner.update(sql, param);
            return row;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * 修改课程状态
     *
     * @param course
     * @return
     */
    @Override
    public int updateCourseStatus(Course course) {
        try {
            String sql = "UPDATE course SET STATUS = ?, update_time = ? WHERE id = ?";
            Object[] param = {
                    course.getStatus(),
                    course.getUpdate_time(),
                    course.getId()
            };
            int row = queryRunner.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
