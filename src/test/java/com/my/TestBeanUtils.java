package com.my;

import com.my.pojo.Course;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ymm
 * @date: 2022/7/14
 * @version: 1.0.0
 * @description:
 */
public class TestBeanUtils {

    @Test
    public void testBeanUtils() {


        try {
            Course course = new Course();
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("course_name", "大数据");
            map.put("brief", "大数据");
            map.put("teacher_name", "周老师");
            map.put("teacher_info", "特级教师");

            BeanUtils.populate(course, map);
            System.out.println("course = " + course);

            // 设置属性
            BeanUtils.setProperty(course, "price", 99);
            String price = BeanUtils.getProperty(course, "price");
            System.out.println("price = " + price);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
