package com.wallmart.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.wallmart.enumurl.WallmartEnumUrl;

@Entity
@Table(name = "item_details")
public class Item implements Serializable {

	
	private static final long serialVersionUID = 1541454263117387982L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long itemId;
	private Long parentItemId;
	private String name;
	private BigDecimal msrp;
	private BigDecimal salePrice;
	private String upc;
	private String categoryPath;
	private String shortDescription;
	private String longDescription;
	private String brandName;
	private String productTrackingUrl;
	private boolean ninetySevenCentShippingRate;
	private BigDecimal standardShipRate;
	private BigDecimal twoThreeDayShippingRate;
	private String thumbnailImage;
	private String mediumImage;
	private String largeImage;
	private String color;
	private boolean marketPlace;
	private boolean shipToStore;
	private boolean freeShipToStore;
	private String modelNumber;
	private String sellerInfo;
	private BigDecimal customerRating;
	private long numReviews;
	private String categoryNode;
	private String rhid;
	private boolean rollBack;
	private boolean bundle;
	private boolean clearance;
	private boolean preOrder;
	private String stock;
	private boolean freight;
	private String affiliateAddToCartUrl ;
	private boolean freeShippingOver35Dollars;
	private int maxItemsInOrder;
	private String offerType;
	private boolean isTwoDayShippingEligible;
	private boolean availableOnline;
	private String offerId;

	@Enumerated(value = EnumType.STRING)
	private WallmartEnumUrl wallmartUrlCategory;
	
	@OneToMany(mappedBy="item")
	private List<ImageEntities> imageEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public List<ImageEntities> getImageEntities() {
		return imageEntities;
	}

	public void setImageEntities(List<ImageEntities> imageEntities) {
		this.imageEntities = imageEntities;
	}


	public Long getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(Long parentItemId) {
		this.parentItemId = parentItemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMsrp() {
		return msrp;
	}

	public void setMsrp(BigDecimal msrp) {
		this.msrp = msrp;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getCategoryPath() {
		return categoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		this.categoryPath = categoryPath;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductTrackingUrl() {
		return productTrackingUrl;
	}

	public void setProductTrackingUrl(String productTrackingUrl) {
		this.productTrackingUrl = productTrackingUrl;
	}

	public boolean isNinetySevenCentShippingRate() {
		return ninetySevenCentShippingRate;
	}

	public void setNinetySevenCentShippingRate(boolean ninetySevenCentShippingRate) {
		this.ninetySevenCentShippingRate = ninetySevenCentShippingRate;
	}


	public BigDecimal getStandardShipRate() {
		return standardShipRate;
	}

	public void setStandardShipRate(BigDecimal standardShipRate) {
		this.standardShipRate = standardShipRate;
	}

	public BigDecimal getTwoThreeDayShippingRate() {
		return twoThreeDayShippingRate;
	}

	public void setTwoThreeDayShippingRate(BigDecimal twoThreeDayShippingRate) {
		this.twoThreeDayShippingRate = twoThreeDayShippingRate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

	public boolean isMarketPlace() {
		return marketPlace;
	}

	public void setMarketPlace(boolean marketPlace) {
		this.marketPlace = marketPlace;
	}

	public boolean isShipToStore() {
		return shipToStore;
	}

	public void setShipToStore(boolean shipToStore) {
		this.shipToStore = shipToStore;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public boolean isFreeShipToStore() {
		return freeShipToStore;
	}

	public void setFreeShipToStore(boolean freeShipToStore) {
		this.freeShipToStore = freeShipToStore;
	}

	public String getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(String sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public BigDecimal getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(BigDecimal customerRating) {
		this.customerRating = customerRating;
	}

	public long getNumReviews() {
		return numReviews;
	}

	public String getCategoryNode() {
		return categoryNode;
	}

	public void setCategoryNode(String categoryNode) {
		this.categoryNode = categoryNode;
	}

	public void setNumReviews(long numReviews) {
		this.numReviews = numReviews;
	}


	public String getRhid() {
		return rhid;
	}

	public void setRhid(String rhid) {
		this.rhid = rhid;
	}

	public boolean isRollBack() {
		return rollBack;
	}

	public void setRollBack(boolean rollBack) {
		this.rollBack = rollBack;
	}

	public boolean isBundle() {
		return bundle;
	}

	public void setBundle(boolean bundle) {
		this.bundle = bundle;
	}

	public boolean isClearance() {
		return clearance;
	}

	public void setClearance(boolean clearance) {
		this.clearance = clearance;
	}

	public boolean isPreOrder() {
		return preOrder;
	}

	public void setPreOrder(boolean preOrder) {
		this.preOrder = preOrder;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public boolean isFreight() {
		return freight;
	}

	public void setFreight(boolean freight) {
		this.freight = freight;
	}

	public String getAffiliateAddToCartUrl() {
		return affiliateAddToCartUrl;
	}

	public void setAffiliateAddToCartUrl(String affiliateAddToCartUrl) {
		this.affiliateAddToCartUrl = affiliateAddToCartUrl;
	}

	public boolean isFreeShippingOver35Dollars() {
		return freeShippingOver35Dollars;
	}

	public void setFreeShippingOver35Dollars(boolean freeShippingOver35Dollars) {
		this.freeShippingOver35Dollars = freeShippingOver35Dollars;
	}

	public int getMaxItemsInOrder() {
		return maxItemsInOrder;
	}

	public void setMaxItemsInOrder(int maxItemsInOrder) {
		this.maxItemsInOrder = maxItemsInOrder;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public boolean isTwoDayShippingEligible() {
		return isTwoDayShippingEligible;
	}

	public void setTwoDayShippingEligible(boolean isTwoDayShippingEligible) {
		this.isTwoDayShippingEligible = isTwoDayShippingEligible;
	}

	public boolean isAvailableOnline() {
		return availableOnline;
	}

	public void setAvailableOnline(boolean availableOnline) {
		this.availableOnline = availableOnline;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public WallmartEnumUrl getWallmartUrlCategory() {
		return wallmartUrlCategory;
	}

	public void setWallmartUrlCategory(WallmartEnumUrl wallmartUrlCategory) {
		this.wallmartUrlCategory = wallmartUrlCategory;
	}	
}