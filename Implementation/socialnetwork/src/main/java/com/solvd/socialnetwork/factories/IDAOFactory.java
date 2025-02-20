package com.solvd.socialnetwork.factories;

import com.solvd.socialnetwork.daos.*;


public interface IDAOFactory {

    ICommentDAO getCommentDAO();

    IUserDAO getUserDAO();

    IStoryDAO getStoryDAO();

    ISavedPostDAO getSavedPostDAO();

    IPostDAO getPostDAO();

    INotificationDAO getNotificationDAO();

    IMessageDAO getMessageDAO();

    ILikePostDAO getLikePostDAO();

    IGroupDAO getGroupDAO();

    IGroupMemberDAO getGroupMemberDAO();

    IFriendshipDAO getFriendshipDAO();

    IEventDAO getEventDAO();

    IEventParticipantDAO getEventParticipantDAO();


}
