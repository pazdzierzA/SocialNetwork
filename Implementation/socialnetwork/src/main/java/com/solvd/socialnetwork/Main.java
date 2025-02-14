package com.solvd.socialnetwork;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
import com.solvd.socialnetwork.models.LikePost;
import com.solvd.socialnetwork.models.Post;
import com.solvd.socialnetwork.models.Story;
import com.solvd.socialnetwork.models.User;
import com.solvd.socialnetwork.services.impl.StoryService;
import com.solvd.socialnetwork.services.impl.UserService;
import com.solvd.socialnetwork.services.parsers.SAXLocal;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.socialnetwork.models.Comment;

public class Main {
	private final static Logger logger = LogManager.getLogger(Main.class.getName());
	private static final String XML_FILE_PATH = "src/main/resources/xmls/social_networks.xml";
	private static final String POSTS_XML_FILE_PATH = "src/main/resources/xmls/posts.xml";
	private static final String JSON_FILE_PATH = "src/main/resources/jsons/social_network.json";
	private static final String OUTPUT_JSON_FILE_PATH = "src/main/resources/jsons/social_networks.json";
	private static final String POSTS_JSON_FILE_PATH = "src/main/resources/jsons/posts.json";

	public static void main(String[] args) {

		LocalDate userDate = LocalDate.of(1990, 05, 15);
		//builder pattern
		User userAdam = User.builder().login("adam123").email("adam_oak@example.com").password("password123")
				.firstName("Adam").lastName("Oak").birthDate(userDate).build();

		// lombok builder
		Story firstStory = Story.builder().text("This is my first story").pictureStoryUrl("url").storyCreatorId(1L).build();
		
		// service pattern
		StoryService storyService = new StoryService();
		storyService.save(firstStory);
		
		// User userJohn = new
		// User("john_doe","john.doe@example.com","password123","John","Doe",userDate);
		UserService userService = new UserService();
		User updatedJohn = new User((long) 29, "Adam_doe", "Adam.doe@example.com", "password123", "Adam", "Doe",
				userDate);

		userService.save(userAdam);
		userService.update(updatedJohn);
		userService.removeById((long) 31);
		userService.removeById((long) 33);

		parseSAX();
		parseJAXB();
		parseJackson();
	}

	private static void parseSAX() {
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

	private static void parseJAXB() {
		logger.info("JAXB parser value: ");
		try {
			JAXBContext context = JAXBContext.newInstance(PostWrapper.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			PostWrapper result = (PostWrapper) unmarshaller.unmarshal(new InputSource(POSTS_XML_FILE_PATH));

			for (Post post : result.getPosts()) {
				logger.info("Title: {}", post.getTitle());
				logger.info("Text: {}", post.getText());
				logger.info("Likes: {}", post.getLikeQuantity());
				logger.info("Comments: {}", post.getCommentQuantity());
				logger.info("--------------------------------");
			}
		} catch (JAXBException e) {
			logger.error("Error while parsing JAXB: {}", e.getMessage(), e);
		}
	}

	private static void parseJackson() {
		logger.info("JACKSON parser value: ");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			SocialNetwork socialNetworkData = objectMapper.readValue(new File(JSON_FILE_PATH), SocialNetwork.class);
			objectMapper.writeValue(new File(OUTPUT_JSON_FILE_PATH), socialNetworkData);

			Post post = new Post(1L, "This is my first post!", "Introduction", 20, 5, 1L);
			Post secondPost = new Post(2L, "This is my second post!", "Second day", 10, 6, 2L);
			Comment firstComment = new Comment(1L, "Fantastic", 1L, 2L);
			Comment secondComment = new Comment(2L, "Bad", 2L, 2L);
			LikePost like = new LikePost(1L, 2L, 1L);
			LikePost secondLike = new LikePost(2L, 2L, 2L);

			List<Comment> comments = new ArrayList<>();
			comments.add(firstComment);
			comments.add(secondComment);

			List<LikePost> likes = new ArrayList<>();
			likes.add(like);
			likes.add(secondLike);

			List<Post> allPosts = new ArrayList<>();
			allPosts.add(post);
			allPosts.add(secondPost);

			secondPost.setComments(comments);
			secondPost.setLikes(likes);

			objectMapper.writeValue(new File(POSTS_JSON_FILE_PATH), allPosts);
		} catch (IOException e) {
			logger.error("Error while parsing JSON: {}", e.getMessage(), e);
		}
	}

}
