package com.solvd.socialnetwork.utils.mybatisconfigs;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class ConnectionFactory {
    private static SqlSessionFactory sqlSessionFactory;
    private static final String CONFIG_FILE = "mybatis-config.xml";

    static {
        try {
            Reader reader = Resources.getResourceAsReader(CONFIG_FILE);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader,"Stag");
        } catch (IOException e) {
            throw new RuntimeException("mybatis-config.xml file not found.", e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
