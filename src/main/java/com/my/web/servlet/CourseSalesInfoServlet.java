package com.my.web.servlet;

import com.my.base.Constants;
import com.my.pojo.Course;
import com.my.service.CourseService;
import com.my.service.impl.CourseServiceImpl;
import com.my.utils.DateUtils;
import com.my.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ymm
 * @date: 2022/7/14
 * @version: 1.0.0
 * @description:
 */
@WebServlet("/courseSalesInfo")
public class CourseSalesInfoServlet extends HttpServlet {

    /**
     * 保存课程营销信息：
     * 1.收集表单数据封装到Course对象实体
     * 2.将图片上传到tomcat服务器
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Course course = new Course();
            Map<String, Object> map = new HashMap<>();


            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            List<FileItem> list = servletFileUpload.parseRequest(request);

            if (list != null) {
                for (FileItem item : list) {

                    boolean formField = item.isFormField();
                    if (formField) {
                        // 普通表单项
                        String fieldName = item.getFieldName();
                        String value = item.getString("UTF-8");

                        System.out.println(fieldName + " = " + value);
                        map.put(fieldName, value);
                    } else {
                        // 文件上传项
                        String fileName = item.getName();
                        String newFileName = UUIDUtils.getUUID() + "_" + fileName;

                        InputStream inputStream = item.getInputStream();

                        String realPath = this.getServletContext().getRealPath("/");
                        String webappsPath = realPath.substring(0, realPath.indexOf("lagou_edu_home"));
                        String imgUrl = webappsPath + "/upload/" + newFileName;
                        FileOutputStream outputStream = new FileOutputStream(imgUrl);

                        IOUtils.copy(inputStream, outputStream);
                        outputStream.close();
                        inputStream.close();

                        // 保存图片路径
                        map.put("course_img_url", Constants.LOCAL_URL + "/upload/" + newFileName);
                    }
                }

                BeanUtils.populate(course, map);
            }

            String dateFormat = DateUtils.getDateFormat();
            // 业务处理
            CourseService courseService = new CourseServiceImpl();
            String result = "";

            if (map.get("id") != null) {
                // 修改课程
                course.setUpdate_time(dateFormat);
                result = courseService.updateCourseSalesInfo(course);

            } else {
                // 新增课程
                // 补全信息
                course.setCreate_time(dateFormat);
                course.setUpdate_time(dateFormat);
                course.setStatus(1);
                result = courseService.saveCourseSalesInfo(course);

            }

            response.getWriter().print(result);

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
