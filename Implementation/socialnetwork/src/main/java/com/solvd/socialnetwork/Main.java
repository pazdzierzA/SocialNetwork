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

	public static void main(String[] args) {
		
		LocalDate userDate = LocalDate.of(1990, 05, 15);
		//User userJohn = new User("john_doe","john.doe@example.com","password123","John","Doe",userDate);
		UserService userService = new UserService();
		User updatedJohn = new User((long)29,"Adam_doe","Adam.doe@example.com","password123","Adam","Doe",userDate);
       	//userJohn = userService.save(userJohn);

       //	List<User> withNameJohn = userService.getByFirstName("John");
       	//List<User> withNameAdam = userService.getByFirstName("Adam");
       	
       	/*for (User john: withNameJohn) {
       	logger.info("Users from db with name John: {}", john.toString());
       	}*/
       	
       	userService.update(updatedJohn);
       	userService.removeById((long) 31);
		userService.removeById((long) 33);
       	/*for (User john: withNameJohn) {
           	logger.info("Users from db with name John after changes : {}", john.toString());
           	}*/
     	/*for (User adam: withNameAdam) {
           	logger.info("Users from db with name Adam after changes : {}", adam.toString());
           	}*/

		/*// SAX Parser
    	logger.info("SAX parser value: ");
		List<User> parsedUsers = new ArrayList<>();
		List<Post> parsedPosts = new ArrayList<>();
		List<Story> parsedStories = new ArrayList<>();
		List<Group> parsedGroups = new ArrayList<>();
		List<LikePost> parsedLikes = new ArrayList<>();
		List<Comment> parsedComments = new ArrayList<>();
		List<GroupMember> parsedGroupMembers = new ArrayList<>();

		InputSource inputFile = new InputSource("src/main/resources/xmls/social_networks.xml");
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

			for (LikePost likePost : parsedLikes) {
				logger.info(likePost.toString());
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
		
		
		// JAXB 
		logger.info("JAXB parser value: ");
		PostWrapper result = null;
		
        try {
        	InputSource inputFilePost = new InputSource("src/main/resources/xmls/posts.xml");
        	JAXBContext context = JAXBContext.newInstance(PostWrapper.class);
			Marshaller marshaller = context.createMarshaller();
			Unmarshaller unmarshaller = context.createUnmarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			result = (PostWrapper) unmarshaller.unmarshal( inputFilePost);
			for (Post post : result.getPosts()) {
				logger.info("Title: {}", post.getTitle());
                logger.info("Text: {}", post.getText());
                logger.info("Likes: {}", post.getLikeQuantity());
                logger.info("Comments: {}", post.getCommentQuantity());
                logger.info("--------------------------------");
            }
			
		} catch (JAXBException e) {
			
			logger.error(e);
		}
        
        
        //JACKSON
        
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.registerModule(new JavaTimeModule());
			SocialNetwork socialNetworkData = objectMapper.readValue(new File("src/main/resources/jsons/social_network.json"), SocialNetwork.class);
			objectMapper.writeValue(new File("src/main/resources/jsons/social_networks.json"), socialNetworkData);
			
			
			Post post = new Post((long)1,"This is my first post!","Introduction",20, 5,(long)1 );
			Post secondPost = new Post((long)2,"This is my second post!","Second day",10, 6,(long)2 );
			Comment firstComment = new Comment((long) 1,"Fantastic",(long)1,(long)2);
			Comment secondComment = new Comment((long) 2,"Bad",(long)2,(long)2);
			LikePost like = new LikePost((long)1, (long)2,(long)1);
			LikePost secondLike = new LikePost((long)2, (long)2,(long)2);
			List<Comment> comments = new ArrayList<>();
			comments.add(firstComment);
			comments.add(secondComment);
			List<LikePost> likes = new ArrayList<>();
			likes.add(like);
			likes.add(secondLike);
			List <Post> allPosts = new ArrayList<>();
			allPosts.add(post);
			allPosts.add(secondPost);
			secondPost.setComments(comments);
			secondPost.setLikes(likes);
			
			//ObjectMapper objectMapper1 = new ObjectMapper();
			objectMapper.writeValue(new File("src/main/resources/jsons/posts.json"), allPosts);
		} catch (IOException e) {
			e.printStackTrace();
		}
        */
        

	}
}
