package com.solvd.socialnetwork.services.parsers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.solvd.socialnetwork.enums.GroupType;
import com.solvd.socialnetwork.enums.UserRole;
import com.solvd.socialnetwork.models.Comment;
import com.solvd.socialnetwork.models.Group;
import com.solvd.socialnetwork.models.GroupMember;
import com.solvd.socialnetwork.models.LikePost;
import com.solvd.socialnetwork.models.Post;
import com.solvd.socialnetwork.models.Story;
import com.solvd.socialnetwork.models.User;

public class SAXLocal extends DefaultHandler {
	private User user;
	private Group group;
	private Comment comment;
	private LikePost likePost;
	private Story story;
	private Post post;
	private GroupMember groupMember;
	private String currentElement;
	private List<User> users;
	private List<Post> posts;
	private List<Group> groups;
	private List<Comment> comments;
	private List<LikePost> likePosts;
	private List<Story> stories;
	private List<GroupMember> groupMembers;

	@Override
	public void startDocument() throws SAXException {
		users = new ArrayList<>();
		posts = new ArrayList<>();
		groups = new ArrayList<>();
		groupMembers = new ArrayList<>();
		stories = new ArrayList<>();
		comments = new ArrayList<>();
		likePosts = new ArrayList<>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentElement = qName;

		switch (qName) {
		case "user":
			user = new User();
			break;
		case "post":
			post = new Post();
			break;

		case "group":
			group = new Group();
			break;

		case "groupsMember":
			groupMember = new GroupMember();
			break;

		case "story":
			story = new Story();
			break;

		case "comment":
			comment = new Comment();
			break;

		case "like":
			likePost = new LikePost();
			break;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "user":
			users.add(user);
			user = null;
			break;

		case "post":
			posts.add(post);
			post = null;
			break;

		case "group":
			groups.add(group);
			group = null;
			break;

		case "groupsMember":
			groupMembers.add(groupMember);
			groupMember = null;
			break;

		case "story":
			stories.add(story);
			story = null;
			break;

		case "comment":
			comments.add(comment);
			comment = null;
			break;

		case "like":
			likePosts.add(likePost);
			likePost = null;
			break;
		}

		currentElement = "";
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch, start, length).trim();
		if (user != null) {
			switch (currentElement) {
			case "id":
				user.setId(Long.parseLong(content));
				break;
			case "login":
				user.setLogin(content);
				break;
			case "email":
				user.setEmail(content);
				break;
			case "firstName":
				user.setFirstName(content);
				break;
			case "lastName":
				user.setLastName(content);
				break;
			case "birthDate":
				user.setBirthDate(LocalDate.parse(content));
				break;
			}
		}
		if (post != null) {
			switch (currentElement) {
			case "id":
				post.setId(Long.parseLong(content));
				break;
			case "text":
				post.setText(content);
				break;
			case "title":
				post.setTitle(content);
				break;
			case "likeQuantity":
				post.setLikeQuantity(Integer.parseInt(content));
				break;
			case "commentQuantity":
				post.setCommentQuantity(Integer.parseInt(content));
				break;
			case "creatorId":
				post.setCreatorId(Long.parseLong(content));
				break;

			}
		}

		if (group != null) {
			switch (currentElement) {
			case "id":
				group.setId(Long.parseLong(content));
				break;
			case "text":
				group.setGroupName(content);
				break;
			case "groupType":
				group.setGroupType(GroupType.valueOf(content.toUpperCase()));
				break;
			case "groupCreatorId":
				group.setGroupCreatorId(Long.parseLong(content));
				break;

			}
		}

		if (groupMember != null) {
			switch (currentElement) {
			case "id":
				groupMember.setId(Long.parseLong(content));
				break;
			case "userRole":
				groupMember.setUserRole(UserRole.valueOf(content.toUpperCase()));
				break;
			case "groupId":
				groupMember.setId(Long.parseLong(content));
				break;
			case "userId":
				groupMember.setId(Long.parseLong(content));
				break;

			}
		}
		if (story != null) {
			switch (currentElement) {
			case "id":
				story.setId(Long.parseLong(content));
				break;
			case "text":
				story.setText(content);
				break;
			case "pictureStoryUrl":
				story.setPictureStoryUrl(content);
				break;
			case "stroyCreatorId":
				story.setId(Long.parseLong(content));
				break;
			}
		}

		if (comment != null) {
			switch (currentElement) {
			case "id":
				comment.setId(Long.parseLong(content));
				break;
			case "text":
				comment.setText(content);
				break;
			case "authorId":
				comment.setAuthorId(Long.parseLong(content));
				break;
			case "postId":
				comment.setPostId(Long.parseLong(content));
				break;
			}

		}
		if (likePost != null) {
			switch (currentElement) {
			case "id":
				likePost.setId(Long.parseLong(content));
				break;
			case "postId":
				likePost.setPostId(Long.parseLong(content));
				break;
			case "userId":
				likePost.setUserId(Long.parseLong(content));
				break;
			}
		}

	}

	public List<User> getUsers() {
		return users;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public List<Story> getStories() {
		return stories;
	}

	public List<LikePost> getLikes() {
		return likePosts;
	}

	public List<GroupMember> getGroupMembers() {
		return groupMembers;
	}
}
