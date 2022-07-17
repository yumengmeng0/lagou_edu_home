package com.my.web.servlet;

import com.my.utils.UUIDUtils;
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
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/7/14
 * @version: 1.0.0
 * @description:
 */
@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            // 1.创建磁盘文件工厂对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

            // 2.创建文件上传核心类
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

            servletFileUpload.setHeaderEncoding("utf-8");

            boolean multipartContent = servletFileUpload.isMultipartContent(request);

            if (multipartContent) {
                // 3.解析request，获取表单项集合
                List<FileItem> list = servletFileUpload.parseRequest(request);

                if (list != null) {

                    // 4.遍历表单项集合
                    for (FileItem item : list) {

                        // 5.判断普通表单项还是文件上传项
                        boolean formField = item.isFormField();
                        if (formField) {
                            // 普通表单项
                            String fieldName = item.getFieldName();
                            String filedValue = item.getString("utf-8");
                            System.out.println(fieldName + " = " + filedValue);
                        } else {
                            // 文件上传项
                            String fileName = item.getName();

                            String newFileName = UUIDUtils.getUUID() + "_" + fileName;
                            InputStream inputStream = item.getInputStream();

                            String realPath = this.getServletContext().getRealPath("/"); // C:\apache-tomcat-8.5.55\webapps\lagou_edu_home\

                            String webappsPath = realPath.substring(0, realPath.indexOf("lagou_edu_home"));

                            FileOutputStream outputStream = new FileOutputStream(webappsPath + "/upload/" + newFileName);

                            IOUtils.copy(inputStream, outputStream);

                            outputStream.close();
                            inputStream.close();
                        }

                    }

                }

            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
