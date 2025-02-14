package com.solvd.socialnetwork.services.parsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.socialnetwork.SocialNetwork;
import com.solvd.socialnetwork.models.Comment;
import com.solvd.socialnetwork.models.LikePost;
import com.solvd.socialnetwork.models.Post;

public class ParserJackson {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(ParserJackson.class);
    private static final String JSON_FILE_PATH = "src/main/resources/jsons/social_network.json";
    private static final String OUTPUT_JSON_FILE_PATH = "src/main/resources/jsons/social_networks.json";
    private static final String POSTS_JSON_FILE_PATH = "src/main/resources/jsons/posts.json";
    public static void parseJackson() {
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
