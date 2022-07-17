package com.my.dao.impl;

import com.my.dao.CourseContentDao;
import com.my.pojo.Course;
import com.my.pojo.Course_Lesson;
import com.my.pojo.Course_Section;
import com.my.utils.DateUtils;
import com.my.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/16
 * @version: 1.0.0
 * @description:
 */
public class CourseContentDaoImpl implements CourseContentDao {

    private QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    /**
     * 根据课程id查询课程相关信息
     *
     * @param courseId
     * @return
     */
    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        try {
            String sql = "SELECT\n" +
                    "  id,\n" +
                    "  course_id,\n" +
                    "  section_name,\n" +
                    "  description,\n" +
                    "  order_num,\n" +
                    "  STATUS\n" +
                    "FROM\n" +
                    "  course_section\n" +
                    "WHERE course_id = ?;";

            List<Course_Section> courseSectionList = queryRunner.query(sql, new BeanListHandler<Course_Section>(Course_Section.class), courseId);


            // 根据章节id查询课时信息
            for (Course_Section courseSection : courseSectionList) {
                // 章节对应课时
                List<Course_Lesson> courseLessonList = findLessonBySectionId(courseSection.getId());
                courseSection.setCourseLessonList(courseLessonList);
            }

            return courseSectionList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据章节id查询章节相关的课时信息
     *
     * @param sessionId
     * @return
     */
    @Override
    public List<Course_Lesson> findLessonBySectionId(int sessionId) {
        try {
            String sql = "SELECT\n" +
                    "  id,\n" +
                    "  course_id,\n" +
                    "  section_id,\n" +
                    "  theme,\n" +
                    "  duration,\n" +
                    "  is_free,\n" +
                    "  order_num,\n" +
                    "  STATUS\n" +
                    "FROM\n" +
                    "  course_lesson\n" +
                    "WHERE section_id = ?;";
            List<Course_Lesson> courseLessonList = queryRunner.query(sql, new BeanListHandler<Course_Lesson>(Course_Lesson.class), sessionId);
            return courseLessonList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据课程id查询课程信息
     *
     * @param courseId
     * @return
     */
    @Override
    public Course findCourseById(int courseId) {

        try {
            String sql = "SELECT\n" +
                    "  id,\n" +
                    "  course_name\n" +
                    "FROM\n" +
                    "  course\n" +
                    "WHERE id = ?;";

            Course course = queryRunner.query(sql, new BeanHandler<>(Course.class), courseId);
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 保存章节信息
     *
     * @param courseSection
     * @return
     */
    @Override
    public int saveSection(Course_Section courseSection) {

        try {
            String sql = "INSERT INTO course_section (\n" +
                    "  course_id,\n" +
                    "  section_name,\n" +
                    "  description,\n" +
                    "  order_num,\n" +
                    "  STATUS,\n" +
                    "  create_time,\n" +
                    "  update_time\n" +
                    ")\n" +
                    "VALUES\n" +
                    "  (?, ?, ?, ?, ?, ?, ?);";

            Object[] param = {
                    courseSection.getCourse_id(),
                    courseSection.getSection_name(),
                    courseSection.getDescription(),
                    courseSection.getOrder_num(),
                    courseSection.getStatus(),
                    courseSection.getCreate_time(),
                    courseSection.getUpdate_time()
            };

            int row = queryRunner.update(sql, param);

            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * 修改章节信息
     *
     * @param courseSection
     * @return
     */
    @Override
    public int updateSection(Course_Section courseSection) {

        try {
            String sql = "UPDATE\n" +
                    "  course_section\n" +
                    "SET\n" +
                    "  section_name = ?,\n" +
                    "  description = ?,\n" +
                    "  order_num = ?,\n" +
                    "  update_time = ?\n" +
                    "WHERE id = ?;";

            Object[] param = {
                    courseSection.getSection_name(),
                    courseSection.getDescription(),
                    courseSection.getOrder_num(),
                    courseSection.getUpdate_time(),
                    courseSection.getId()
            };
            int row = queryRunner.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * 修改章节状态
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public int updateSectionStatus(int id, int status) {
        try {
            String sql = "UPDATE\n" +
                    "  course_section\n" +
                    "  \n" +
                    "SET\n" +
                    "  STATUS = ?,\n" +
                    "  update_time = ?\n" +
                    "WHERE id = ?;";

            Object[] param = {
                    status, DateUtils.getDateFormat(), id
            };
            int row = queryRunner.update(sql, param);

            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }
}
