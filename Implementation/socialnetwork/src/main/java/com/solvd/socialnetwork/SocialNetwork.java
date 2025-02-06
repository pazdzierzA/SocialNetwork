package com.solvd.socialnetwork;

import java.util.List;

import com.solvd.socialnetwork.models.Comment;
import com.solvd.socialnetwork.models.Group;
import com.solvd.socialnetwork.models.GroupMember;
import com.solvd.socialnetwork.models.LikePost;
import com.solvd.socialnetwork.models.Post;
import com.solvd.socialnetwork.models.Story;
import com.solvd.socialnetwork.models.User;

public class SocialNetwork {

	private List<User> users;
	private List<Post> posts;
	private List<Comment> comments;
	private List<Group> groups;
	private List<GroupMember> groupMembers;
	private List<LikePost> likes;
	private List<Story> stories;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<GroupMember> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(List<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

	public List<LikePost> getLikes() {
		return likes;
	}

	public void setLikes(List<LikePost> likes) {
		this.likes = likes;
	}

	public List<Story> getStories() {
		return stories;
	}

	public void setStories(List<Story> stories) {
		this.stories = stories;
	}

}
