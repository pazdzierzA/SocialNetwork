package com.solvd.socialnetwork.models;

public class SavedPost extends BaseEntity{
	
	private Long postId;
	private Long userId;
	
	public SavedPost(Long id, Long postId, Long userId) {
		super(id);
		this.postId = postId;
		this.userId = userId;
	}

	public SavedPost() {

	}
	public SavedPost(Long postId, Long userId) {
		this.postId = postId;
		this.userId = userId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SavedPost [postId=" + postId + ", userId=" + userId + "]";
	}
	
	
	
}
