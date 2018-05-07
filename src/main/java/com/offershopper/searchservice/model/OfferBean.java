package com.offershopper.searchservice.model;

import java.time.LocalDateTime;

public class OfferBean {

  String offerId;
  String userId;
  String offerTitle;
  LocalDateTime offerValidity;
  LocalDateTime dateOfAnnouncement;
  AddressBean addressBean;
  String offerDescription;
  Float originalPrice;
  Float discount;
  Float offerRating;
  String offerCategory;
  String offerTerms;
  
  
  
  @Override
  public String toString() {
    return "OfferBean [offerId=" + offerId + ", userId=" + userId + ", offerTitle=" + offerTitle + ", offerValidity="
        + offerValidity + ", dateOfAnnouncement=" + dateOfAnnouncement + ", addressBean=" + addressBean
        + ", offerDescription=" + offerDescription + ", originalPrice=" + originalPrice + ", discount=" + discount
        + ", offerRating=" + offerRating + ", offerCategory=" + offerCategory + ", offerTerms=" + offerTerms + "]";
  }

  public OfferBean() {
    super();
  }

  public OfferBean(String offerId, String userId, String offerTitle, LocalDateTime offerValidity,
      LocalDateTime dateOfAnnouncement, AddressBean addressBean, String offerDescription, Float originalPrice,
      Float discount, Float offerRating, String offerCategory, String offerTerms) {
    super();
    this.offerId = offerId;
    this.userId = userId;
    this.offerTitle = offerTitle;
    this.offerValidity = offerValidity;
    this.dateOfAnnouncement = dateOfAnnouncement;
    this.addressBean = addressBean;
    this.offerDescription = offerDescription;
    this.originalPrice = originalPrice;
    this.discount = discount;
    this.offerRating = offerRating;
    this.offerCategory = offerCategory;
    this.offerTerms = offerTerms;
  }

  public String getOfferId() {
    return offerId;
  }

  public void setOfferId(String offerId) {
    this.offerId = offerId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getOfferTitle() {
    return offerTitle;
  }

  public void setOfferTitle(String offerTitle) {
    this.offerTitle = offerTitle;
  }

  public LocalDateTime getOfferValidity() {
    return offerValidity;
  }

  public void setOfferValidity(LocalDateTime offerValidity) {
    this.offerValidity = offerValidity;
  }

  public LocalDateTime getDateOfAnnouncement() {
    return dateOfAnnouncement;
  }

  public void setDateOfAnnouncement(LocalDateTime dateOfAnnouncement) {
    this.dateOfAnnouncement = dateOfAnnouncement;
  }

  public AddressBean getAddress() {
    return addressBean;
  }

  public void setAddress(AddressBean addressBean) {
    this.addressBean = addressBean;
  }

  public String getOfferDescription() {
    return offerDescription;
  }

  public void setOfferDescription(String offerDescription) {
    this.offerDescription = offerDescription;
  }

  public Float getOriginalPrice() {
    return originalPrice;
  }

  public void setOriginalPrice(Float originalPrice) {
    this.originalPrice = originalPrice;
  }

  public Float getDiscount() {
    return discount;
  }

  public void setDiscount(Float discount) {
    this.discount = discount;
  }

  public Float getOfferRating() {
    return offerRating;
  }

  public void setOfferRating(Float offerRating) {
    this.offerRating = offerRating;
  }

  public String getOfferCategory() {
    return offerCategory;
  }

  public void setOfferCategory(String offerCategory) {
    this.offerCategory = offerCategory;
  }

  public String getOfferTerms() {
    return offerTerms;
  }

  public void setOfferTerms(String offerTerms) {
    this.offerTerms = offerTerms;
  }
  
}
