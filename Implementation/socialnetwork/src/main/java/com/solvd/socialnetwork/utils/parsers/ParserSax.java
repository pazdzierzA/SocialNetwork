package com.solvd.socialnetwork.utils.parsers;

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

import com.solvd.socialnetwork.models.Comment;
import com.solvd.socialnetwork.models.Group;
import com.solvd.socialnetwork.models.GroupMember;
import com.solvd.socialnetwork.models.LikePost;
import com.solvd.socialnetwork.models.Post;
import com.solvd.socialnetwork.models.Story;
import com.solvd.socialnetwork.models.User;

public class ParserSax {
    private static final String XML_FILE_PATH = "src/main/resources/social_networks.xml";
    private static final Logger logger = LogManager.getLogger(ParserSax.class);
    public static void parseSAX() {
		logger.info("SAX parser value: ");
		List<User> parsedUsers = new ArrayList<>();
		List<Post> parsedPosts = new ArrayList<>();
		List<Story> parsedStories = new ArrayList<>();
		List<Group> parsedGroups = new ArrayList<>();
		List<LikePost> parsedLikes = new ArrayList<>();
		List<Comment> parsedComments = new ArrayList<>();
		List<GroupMember> parsedGroupMembers = new ArrayList<>();

		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXLocal saxLocal = new SAXLocal();

		try {
			SAXParser saxParser = spf.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(saxLocal);
			xmlReader.parse(new InputSource(XML_FILE_PATH));

			parsedUsers = saxLocal.getUsers();
			parsedPosts = saxLocal.getPosts();
			parsedStories = saxLocal.getStories();
			parsedGroups = saxLocal.getGroups();
			parsedLikes = saxLocal.getLikes();
			parsedComments = saxLocal.getComments();
			parsedGroupMembers = saxLocal.getGroupMembers();

			logParsedData(parsedUsers, parsedPosts, parsedStories, parsedGroups, parsedLikes, parsedComments,
					parsedGroupMembers);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			logger.error("Error while parsing XML: {}", e.getMessage(), e);
		}
	}

    private static void logParsedData(List<User> users, List<Post> posts, List<Story> stories, List<Group> groups,
			List<LikePost> likes, List<Comment> comments, List<GroupMember> groupMembers) {
		users.forEach(user -> logger.info(user.toString()));
		posts.forEach(post -> logger.info(post.toString()));
		stories.forEach(story -> logger.info(story.toString()));
		groups.forEach(group -> logger.info(group.toString()));
		likes.forEach(like -> logger.info(like.toString()));
		comments.forEach(comment -> logger.info(comment.toString()));
		groupMembers.forEach(groupMember -> logger.info(groupMember.toString()));
	}
}
