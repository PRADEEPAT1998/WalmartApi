package com.wallmart.dto;

import java.util.ArrayList;
import java.util.List;

import com.wallmart.model.ImageEntities;
import com.wallmart.model.Item;

public class MaterialDto {

	private String query;
	private String sort;
	private String reponseGroup;
	private long totalResults;
	private long start;
	private long numItems;
	private List<Item> items = new ArrayList<Item>();
	private List<ImageEntities> imageEntities = new ArrayList<ImageEntities>();
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getReponseGroup() {
		return reponseGroup;
	}
	public void setReponseGroup(String reponseGroup) {
		this.reponseGroup = reponseGroup;
	}
	public long getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getNumItems() {
		return numItems;
	}
	public void setNumItems(long numItems) {
		this.numItems = numItems;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<ImageEntities> getImageEntities() {
		return imageEntities;
	}
	public void setImageEntities(List<ImageEntities> imageEntities) {
		this.imageEntities = imageEntities;
	}
}

