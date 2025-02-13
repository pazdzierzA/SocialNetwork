package com.solvd.socialnetwork.daos.mybatisImpl;


import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.solvd.socialnetwork.daos.ICommentDAO;
import com.solvd.socialnetwork.models.Comment;
import com.solvd.socialnetwork.services.mybatisconfigs.ConnectionFactory;

public class CommentDAO implements ICommentDAO {

    @Override
    public Comment getById(Long id) {
       try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
           ICommentDAO commentDao = session.getMapper(ICommentDAO.class);
           return commentDao.getById(id);
       }
    }

    @Override
    public Comment save(Comment entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ICommentDAO commentDao = session.getMapper(ICommentDAO.class);
            return commentDao.save(entity);
        }
    }

    @Override
    public Comment update(Comment entity) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ICommentDAO commentDao = session.getMapper(ICommentDAO.class);
            return commentDao.update(entity);
        }
    }

    @Override
    public void removeById(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ICommentDAO commentDao = session.getMapper(ICommentDAO.class);
            commentDao.removeById(id);
        }
    }

    @Override
    public List<Comment> getByAuthorId(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ICommentDAO commentDao = session.getMapper(ICommentDAO.class);
            return commentDao.getByAuthorId(id);
        }
    }

    @Override
    public List<Comment> getByPostId(Long id) {
        try(SqlSession session = ConnectionFactory.getSqlSessionFactory().openSession()){
            ICommentDAO commentDao = session.getMapper(ICommentDAO.class);
            return commentDao.getByPostId(id);
        }
    }
    
}
