package com.solvd.socialnetwork.models;

public class LikePost extends BaseEntity{

	private Long postId;
	private Long userId;
	
	public LikePost(Long id, Long postId, Long userId) {
		super(id);
		this.postId = postId;
		this.userId = userId;
	}

	public LikePost() {
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

	public void setUserId(Long user_id) {
		this.userId = user_id;
	}

	@Override
	public String toString() {
		return "Like [postId=" + postId + ", userId=" + userId + "]";
	}
	
	
	
	
	

}
