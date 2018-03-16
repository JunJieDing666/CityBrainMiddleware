package com.djj.middleware.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * @author   丁俊杰
 * @name     属性工具类
 * @time     2018/3/2
 * */
public class PropertiesUtil {
    private static final String PROPERTIES = "/com/djj/middleware/config/config.properties";

    /**
     * 根据key读取value
     *
     * @Title: getProperties
     * @Description:
     *                    相对路径， properties文件需在classpath目录下，
     *                  比如：config.properties在包com.test.config下，
     *                  路径就是/com/test/config/config.properties
     * @param keyWord
     * @return String
     * @throws
     */
    public static String getProperties(String keyWord){
        Properties prop = new Properties();
        String value = null;
        try {
            InputStream inputStream = PropertiesUtil.class.getResourceAsStream(PROPERTIES);
            prop.load(inputStream);
            value = prop.getProperty(keyWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
