package com.my;

/**
 * @author: ymm
 * @date: 2022/7/16
 * @version: 1.0.0
 * @description:
 */
public class TestCatch {
    public static void main(String[] args) {

        try {
//            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("end"); // 会继续执行
    }
}
