package com.solvd.socialnetwork.factories;

import com.solvd.socialnetwork.enums.UserRole;
import com.solvd.socialnetwork.models.GroupMember;

public class GroupMemberFactory {

    public static GroupMember createGroupMember( UserRole userRole) {
       switch(userRole){
        case ADMIN:
            GroupMember admin = GroupMember.builder().userRole(UserRole.ADMIN).userId(1L).groupId(2L).build();
            return admin;
        case MEMBER:
            GroupMember member = GroupMember.builder().userRole(UserRole.MEMBER).userId(2L).groupId(2L).build();
            return member;
        default: throw new IllegalArgumentException("Invalid user role");
       }
    }

}
