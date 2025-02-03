package com.solvd.socialnetwork.services.parsers;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.solvd.socialnetwork.models.Comment;
import com.solvd.socialnetwork.models.Group;
import com.solvd.socialnetwork.models.GroupMember;
import com.solvd.socialnetwork.models.Like;
import com.solvd.socialnetwork.models.Post;
import com.solvd.socialnetwork.models.Story;
import com.solvd.socialnetwork.models.User;

public class SAXLocal extends DefaultHandler {
	private User user;
	private Group group;
	private Comment comment;
	private Like like;
	private Story story;
	private Post post;
	private GroupMember groupMember;

	List<User> users = new ArrayList<>();
	List<Post> posts = new ArrayList<>();
	List<Group> groups = new ArrayList<>();
	List<Comment> comments = new ArrayList<>();
	List<Like> likes = new ArrayList<>();
	List<Story> stories = new ArrayList<>();
	List<GroupMember> groupMembers = new ArrayList<>();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
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
			like = new Like();
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "user":
			if (user != null)
				users.add(user);
			break;
		case "post":
			if (post != null)
				posts.add(post);
			break;
		case "group":
			if (group != null)
				groups.add(group);
			break;
		case "groupsMember":
			if (groupMember != null)
				groupMembers.add(groupMember);
			break;
		case "story":
			if (story != null)
				stories.add(story);
			break;
		case "comment":
			if (comment != null)
				comments.add(comment);
			break;
		case "like":
			if (like != null)
				likes.add(like);
			break;
		}

	}

	// Metoda wywoływana dla tekstu w elemencie
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length).trim();

	}

}
