package com.wallmart.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ImageEntities")
public class ImageEntities implements Serializable{

	private static final long serialVersionUID = 2440188195942748309L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String thumbnailImage;
	private String mediumImage;
	private String largeImage;
	private String entityType;
    @JsonIgnore
	@ManyToOne 
	@JoinColumn(name = "itemId")
	private Item item;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	public String getMediumImage() {
		return mediumImage;
	}

	public void setMediumImage(String mediumImage) {
		this.mediumImage = mediumImage;
	}

	public String getLargeImage() {
		return largeImage;
	}

	public void setLargeImage(String largeImage) {
		this.largeImage = largeImage;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	
}
