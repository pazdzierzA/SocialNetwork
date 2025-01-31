package com.solvd.socialnetwork.models;

public class Comment extends BaseEntity {
	private String text;
	private Long authorId;
	private Long postId;
	

	public Comment(Long id, String text, Long authorId, Long postId) {
		super(id);
		this.text = text;
		this.authorId = authorId;
		this.postId = postId;
	}

	public Comment(Long id) {
		super(id);
	}
	
	public Comment() {};

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	

}
