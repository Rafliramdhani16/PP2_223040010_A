package model;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory = null;

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("resource/mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                System.err.println("Error saat membaca file konfigurasi MyBatis:");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession() {
        SqlSessionFactory factory = getSqlSessionFactory();
        if (factory == null) {
            throw new RuntimeException("SqlSessionFactory tidak dapat diinisialisasi");
        }
        return factory.openSession(true);
    }
}