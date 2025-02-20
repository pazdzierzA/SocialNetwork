package com.solvd.socialnetwork;


import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import com.solvd.socialnetwork.models.GroupMember;

import com.solvd.socialnetwork.models.Story;
import com.solvd.socialnetwork.models.User;
import com.solvd.socialnetwork.services.impl.CommentService;
import com.solvd.socialnetwork.services.impl.StoryService;
import com.solvd.socialnetwork.services.impl.UserService;
import com.solvd.socialnetwork.enums.UserRole;
import com.solvd.socialnetwork.factories.GroupMemberFactory;



public class Main {
	private final static Logger logger = LogManager.getLogger(Main.class.getName());

	public static void main(String[] args) {

		LocalDate userDate = LocalDate.of(1990, 05, 15);
		//builder pattern
		User userAdam = User.builder().login("adam123").email("adam_oak@example.com").password("password123")
				.firstName("Adam").lastName("Oak").birthDate(userDate).build();

		// lombok builder
		Story firstStory = Story.builder().text("This is my first story").pictureStoryUrl("url").storyCreatorId(1L).build();
		
		// service pattern with factory pattern
		StoryService storyService = new StoryService();
		storyService.save(firstStory);
		

		CommentService commentService = new CommentService();
		commentService.getById((long) 1);

		// factory pattern
		GroupMember admin = GroupMemberFactory.createGroupMember(UserRole.ADMIN);
		GroupMember member = GroupMemberFactory.createGroupMember(UserRole.MEMBER);
		logger.info(admin.toString());
		logger.info(member.toString());


		UserService userService = new UserService();
		User updatedJohn = new User((long) 29, "Adam_doe", "Adam.doe@example.com", "password123", "Adam", "Doe",
				userDate);

		userService.save(userAdam);
		userService.update(updatedJohn);
		userService.removeById((long) 31);
		userService.removeById((long) 33);
	}
}
