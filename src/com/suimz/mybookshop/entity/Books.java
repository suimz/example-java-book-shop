package com.suimz.mybookshop.entity;

import java.io.Serializable;

public class Books implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8202437908910310631L;
	private int id;
	private String title;
	private String author;
	private int publisherId;
	private String publishDate;
	private String isbn;
	private int wordsCount;
	private double unitPrice;
	private String contentDescription;
	private String authorDescription;
	private String editorComment;
	private String toc;
	private int categoryId;
	private int clicks;
	private String pic;
	
	public Books(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getWordsCount() {
		return wordsCount;
	}
	public void setWordsCount(int wordsCount) {
		this.wordsCount = wordsCount;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getContentDescription() {
		return contentDescription;
	}
	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}
	public String getAuthorDescription() {
		return authorDescription;
	}
	public void setAuthorDescription(String authorDescription) {
		this.authorDescription = authorDescription;
	}
	public String getToc() {
		return toc;
	}
	public void setToc(String toc) {
		this.toc = toc;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getEditorComment() {
		return editorComment;
	}

	public void setEditorComment(String editorComment) {
		this.editorComment = editorComment;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", title=" + title + ", author=" + author + ", publisherId=" + publisherId
				+ ", publishDate=" + publishDate + ", isbn=" + isbn + ", wordsCount=" + wordsCount + ", unitPrice="
				+ unitPrice + ", contentDescription=" + contentDescription + ", authorDescription=" + authorDescription
				+ ", editorComment=" + editorComment + ", toc=" + toc + ", categoryId=" + categoryId + ", clicks="
				+ clicks + ", pic=" + pic + "]";
	}
}
