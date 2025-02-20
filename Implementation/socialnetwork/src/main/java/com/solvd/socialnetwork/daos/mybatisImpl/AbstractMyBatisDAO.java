package com.solvd.socialnetwork.daos.mybatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.socialnetwork.utils.mybatisconfigs.ConnectionFactory;

public abstract class AbstractMyBatisDAO {
    private static final Logger logger = LogManager.getLogger(AbstractMyBatisDAO.class);

    protected <T> T getMapper(Class<T> clazz, SqlSession session) {
        return session.getMapper(clazz);
    }

    protected <T> T execute(SqlSessionFunction<T> function) {
        try (SqlSession session = getSqlSession()) {
            return function.apply(session);
        } catch (Exception e) {
            logger.error("Error executing SQL session function", e);
            return null;
        }
    }

    protected void executeVoid(SqlSessionVoidFunction function) {
        try (SqlSession session = getSqlSession()) {
            function.apply(session);
        } catch (Exception e) {
            logger.error("Error executing SQL session function", e);
        }
    }

    @FunctionalInterface
    protected interface SqlSessionFunction<T> {
        T apply(SqlSession session) throws Exception;
    }

    @FunctionalInterface
    protected interface SqlSessionVoidFunction {
        void apply(SqlSession session) throws Exception;
    }

    private SqlSession getSqlSession() {
        return ConnectionFactory.getSqlSessionFactory().openSession();
    }

}
