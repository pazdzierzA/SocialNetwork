package com.solvd.socialnetwork.models;

public class Like extends BaseEntity{

	private Long post_id;
	private Long user_id;
	
	public Like(Long id, Long post_id, Long user_id) {
		super(id);
		this.post_id = post_id;
		this.user_id = user_id;
	}

	public Long getPost_id() {
		return post_id;
	}

	public void setPost_id(Long post_id) {
		this.post_id = post_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	
	
	
	

}
