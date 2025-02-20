package com.solvd.socialnetwork.factories;

import com.solvd.socialnetwork.daos.ICommentDAO;
import com.solvd.socialnetwork.daos.IEventDAO;
import com.solvd.socialnetwork.daos.IEventParticipantDAO;
import com.solvd.socialnetwork.daos.IFriendshipDAO;
import com.solvd.socialnetwork.daos.IGroupDAO;
import com.solvd.socialnetwork.daos.IGroupMemberDAO;
import com.solvd.socialnetwork.daos.ILikePostDAO;
import com.solvd.socialnetwork.daos.IMessageDAO;
import com.solvd.socialnetwork.daos.INotificationDAO;
import com.solvd.socialnetwork.daos.IPostDAO;
import com.solvd.socialnetwork.daos.ISavedPostDAO;
import com.solvd.socialnetwork.daos.IStoryDAO;
import com.solvd.socialnetwork.daos.IUserDAO;
import com.solvd.socialnetwork.daos.mybatisImpl.*;

public class MyBatisDAOFactory implements IDAOFactory {

    @Override
    public ICommentDAO getCommentDAO() {
        return new CommentDAO();

    }

    @Override
    public IUserDAO getUserDAO() {
       return new UserDAO();
    }

    @Override
    public IStoryDAO getStoryDAO() {
        return new StoryDAO();
    }

    @Override
    public ISavedPostDAO getSavedPostDAO() {
        return new SavedPostDAO();
    }

    @Override
    public IPostDAO getPostDAO() {
        return new PostDAO();
    }

    @Override
    public INotificationDAO getNotificationDAO() {
        return new NotificationDAO();
    }

    @Override
    public IMessageDAO getMessageDAO() {
        return new MessageDAO();
    }

    @Override
    public ILikePostDAO getLikePostDAO() {
        return new LikePostDAO();
    }

    @Override
    public IGroupDAO getGroupDAO() {
        return new GroupDAO();
    }

    @Override
    public IGroupMemberDAO getGroupMemberDAO() {
        return new GroupMemberDAO();
    }

    @Override
    public IFriendshipDAO getFriendshipDAO() {
        return new FriendshipDAO();
    }

    @Override
    public IEventDAO getEventDAO() {
        return new EventDAO();
    }

    @Override
    public IEventParticipantDAO getEventParticipantDAO() {
        return new EventParticipantDAO();
    }


}
