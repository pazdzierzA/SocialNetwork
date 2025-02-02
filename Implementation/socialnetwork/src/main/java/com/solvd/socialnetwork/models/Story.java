package com.solvd.socialnetwork.models;

public class Story extends BaseEntity {

	private String text;
	private String pictureStoryUrl;
	private Long storyCreatorId;

	public Story(Long id, String text, String pictureStoryUrl, Long storyCreatorId) {
		super(id);
		this.text = text;
		this.pictureStoryUrl = pictureStoryUrl;
		this.storyCreatorId = storyCreatorId;
	}

	public Story() {

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPictureStoryUrl() {
		return pictureStoryUrl;
	}

	public void setPictureStoryUrl(String pictureStoryUrl) {
		this.pictureStoryUrl = pictureStoryUrl;
	}

	public Long getStoryCreatorId() {
		return storyCreatorId;
	}

	public void setStoryCreatorId(Long storyCreatorId) {
		this.storyCreatorId = storyCreatorId;
	}

}
