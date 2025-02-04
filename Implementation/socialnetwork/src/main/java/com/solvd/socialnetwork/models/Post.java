package com.solvd.socialnetwork.models;

import java.util.ArrayList;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)

public class Post extends BaseEntity{
	@XmlElement(name = "text")
	private String text;
	@XmlElement(name = "title")
	private String title;
	@XmlElement(name = "likeQuantity")
	private Integer likeQuantity;
	@XmlElement(name = "commentQuantity")
	private Integer commentQuantity;
	@XmlElement(name = "creatorId")
	private Long creatorId;
	
	private List<Comment> comments = new ArrayList<Comment>();
	private List<LikePost> likePosts = new ArrayList<LikePost>();

	public Post() {
		
	}
	public Post(Long id) {
		super(id);
	}

	
	public Post(Long id, String text, String title, Integer likeQuantity, Integer commentQuantity, Long creatorId) {
		super(id);
		this.text = text;
		this.title = title;
		this.likeQuantity = likeQuantity;
		this.commentQuantity = commentQuantity;
		this.creatorId =creatorId;
	}
	
	public Post(Long id, String text, String title, Integer likeQuantity, Integer commentQuantity,Long creatorId,
			List<Comment> comments, List<LikePost>likePosts) {
		super(id);
		this.text = text;
		this.title = title;
		this.likeQuantity = likeQuantity;
		this.commentQuantity = commentQuantity;
		this.creatorId =creatorId;
		this.comments = comments;
		this.likePosts = likePosts;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getLikeQuantity() {
		return likeQuantity;
	}

	public void setLikeQuantity(Integer likeQuantity) {
		this.likeQuantity = likeQuantity;
	}

	public Integer getCommentQuantity() {
		return commentQuantity;
	}

	public void setCommentQuantity(Integer commentQuantity) {
		this.commentQuantity = commentQuantity;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<LikePost> getLikes() {
		return likePosts;
	}

	public void setLikes(List<LikePost> likePosts) {
		this.likePosts = likePosts;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	@Override
	public String toString() {
		return "Post [text=" + text + ", title=" + title + ", likeQuantity=" + likeQuantity + ", commentQuantity="
				+ commentQuantity + ", creatorId=" + creatorId +  "]";
	}

	
	
	
}
