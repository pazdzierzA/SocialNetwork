package com.solvd.socialnetwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.solvd.socialnetwork.models.Group;
import com.solvd.socialnetwork.models.GroupMember;
import com.solvd.socialnetwork.models.Like;
import com.solvd.socialnetwork.models.Post;
import com.solvd.socialnetwork.models.Story;
import com.solvd.socialnetwork.models.User;
import com.solvd.socialnetwork.services.parsers.SAXLocal;
import com.solvd.socialnetwork.models.Comment;

public class App {
	private final static Logger logger = LogManager.getLogger(App.class.getName());

	public static void main(String[] args) {

		// SAX Parser
		List<User> parsedUsers = new ArrayList<>();
		List<Post> parsedPosts = new ArrayList<>();
		List<Story> parsedStories = new ArrayList<>();
		List<Group> parsedGroups = new ArrayList<>();
		List<Like> parsedLikes = new ArrayList<>();
		List<Comment> parsedComments = new ArrayList<>();
		List<GroupMember> parsedGroupMembers = new ArrayList<>();

		InputSource inputFile = new InputSource("src/main/resources/social_networks.xml");
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXLocal saxLocal = new SAXLocal();

		try {
			SAXParser saxParser = spf.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(saxLocal);
			xmlReader.parse(inputFile);
			parsedUsers = saxLocal.getUsers();
			parsedPosts = saxLocal.getPosts();
			parsedStories = saxLocal.getStories();
			parsedGroups = saxLocal.getGroups();
			parsedLikes = saxLocal.getLikes();
			parsedComments = saxLocal.getComments();
			parsedGroupMembers = saxLocal.getGroupMembers();
			for (User user : parsedUsers) {
				logger.info(user.toString());
			}
			for (Post post : parsedPosts) {
				logger.info(post.toString());
			}

			for (Story story : parsedStories) {
				logger.info(story.toString());
			}

			for (Group group : parsedGroups) {
				logger.info(group.toString());
			}

			for (Like like : parsedLikes) {
				logger.info(like.toString());
			}
			for (Comment comment : parsedComments) {
				logger.info(comment.toString());
			}

			for (GroupMember groupMember : parsedGroupMembers) {
				logger.info(groupMember.toString());
			}
		} catch (ParserConfigurationException e) {
			logger.error("Parser configuration error: {}", e.getMessage(), e);
		} catch (SAXException e) {
			 logger.error("Error while parsing XML: {}", e.getMessage(), e);
		} catch (IOException e) {
			 logger.error("Error while parsing XML: {}", e.getMessage(), e);
		}

	}
}
