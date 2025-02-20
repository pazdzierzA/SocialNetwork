package com.solvd.socialnetwork.builders;

import com.solvd.socialnetwork.models.SavedPost;

public class SavedPostBuilder {
    private Long postId;
	private Long userId;
    
    public SavedPostBuilder postId(Long postId) {
        this.postId = postId;
        return this;
    }
    public SavedPostBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }
    public SavedPost build() {
        return new SavedPost(postId, userId);
    }


}
