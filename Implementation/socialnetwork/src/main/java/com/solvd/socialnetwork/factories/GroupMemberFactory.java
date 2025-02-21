package com.solvd.socialnetwork.factories;

import com.solvd.socialnetwork.enums.UserRole;
import com.solvd.socialnetwork.models.GroupMember;

public class GroupMemberFactory {

    public static GroupMember createGroupMember(UserRole userRole, Long userId, Long groupId) {
        if (userId == null || groupId == null) {
            throw new IllegalArgumentException("User ID and Group ID cannot be null");
        } else {
            switch (userRole) {
                case ADMIN:
                    GroupMember admin = GroupMember.builder().userRole(UserRole.ADMIN).userId(userId).groupId(groupId)
                            .build();
                    return admin;
                case MEMBER:
                    GroupMember member = GroupMember.builder().userRole(UserRole.MEMBER).userId(userId).groupId(groupId)
                            .build();
                    return member;
                default:
                    throw new IllegalArgumentException("Invalid user role");
            }

        }
    }

} 
