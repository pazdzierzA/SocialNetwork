package com.solvd.socialnetwork.utils.parsers;

import org.xml.sax.InputSource;

import com.solvd.socialnetwork.PostWrapper;
import com.solvd.socialnetwork.models.Post;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class ParserJAXB {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(ParserJAXB.class);
    private static final String POSTS_XML_FILE_PATH = "src/main/resources/xmls/posts.xml";

    public static void parseJAXB() {
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

}
