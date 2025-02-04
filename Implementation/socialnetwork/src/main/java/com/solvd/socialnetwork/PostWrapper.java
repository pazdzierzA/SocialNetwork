package com.solvd.socialnetwork;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

import com.solvd.socialnetwork.models.Post;


@XmlRootElement(name = "posts") // This matches the root element in your XML
@XmlAccessorType(XmlAccessType.FIELD)

public class PostWrapper {
	 @XmlElement(name = "post") // Maps each <post> inside <posts>
    private List<Post> posts;

   
    
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
