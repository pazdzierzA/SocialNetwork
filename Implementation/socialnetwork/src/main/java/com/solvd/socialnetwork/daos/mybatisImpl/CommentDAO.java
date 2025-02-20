package com.solvd.socialnetwork.daos.mybatisImpl;

import java.util.List;                       
import com.solvd.socialnetwork.daos.ICommentDAO;
import com.solvd.socialnetwork.models.Comment;

public class CommentDAO extends AbstractMyBatisDAO implements ICommentDAO {
    private ICommentDAO commentDAO;

    @Override
    public Comment getById(Long id) {
        return execute(session -> {
            commentDAO = getMapper(ICommentDAO.class, session);
            return commentDAO.getById(id);
        });
    }

    @Override
    public Integer save(Comment entity) {
        return execute(session -> {
            commentDAO = getMapper(ICommentDAO.class, session);
            return commentDAO.save(entity);
        });
    }

    @Override
    public Integer update(Comment entity) {
        return execute(session -> {
            commentDAO = getMapper(ICommentDAO.class, session);
            return commentDAO.update(entity);
        });
    }

    @Override
    public void removeById(Long id) {
            executeVoid(session -> {
            commentDAO = getMapper(ICommentDAO.class, session);
            commentDAO.removeById(id);
        });

    }

    @Override
    public List<Comment> getByAuthorId(Long id) {
        return execute(session -> {
            commentDAO = getMapper(ICommentDAO.class, session);
            return commentDAO.getByAuthorId(id);
        });
    }

    @Override
    public List<Comment> getByPostId(Long id) {
        return execute(session -> {
            commentDAO = getMapper(ICommentDAO.class, session);
            return commentDAO.getByPostId(id);
        });
    }

}
