package main.capone.pojo;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
private String productId;
private String productDisplayName;
private String applyNowLink;
private String productType;
private List<Image> images;
private List<String> marketingCopy;
private APR purchaseApr;
//purchaseAprValue":"13.99% - 23.99%","purchaseAprType":"Variable"}"
		
private String imageUrl;
public String getProductId() {
	return productId;
}
public void setProductId(String productId) {
	this.productId = productId;
}
public String getProductDisplayName() {
	return productDisplayName;
}
public void setProductDisplayName(String productDisplayName) {
	this.productDisplayName = productDisplayName;
}
public String getApplyNowLink() {
	return applyNowLink;
}
public void setApplyNowLink(String applyNowLink) {
	this.applyNowLink = applyNowLink;
}
public String getProductType() {
	return productType;
}
public void setProductType(String productType) {
	this.productType = productType;
}
public List<Image> getImages() {
	return images;
}
public void setImages(List<Image> images) {
	this.images = images;
}
public String getImageUrl() {
	
	return images.get(0).getUrl();
}
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}
public String getMarketingCop() {
	return marketingCopy.get(0) + "<br><br>" +marketingCopy.get(1);
}
public List<String> getMarketingCopy() {
	return marketingCopy;
}
public void setMarketingCopy(List<String> marketingCopy) {
	this.marketingCopy = marketingCopy;
}



public APR getPurchaseApr() {
	return purchaseApr;
}
public void setPurchaseApr(APR purchaseApr) {
	this.purchaseApr = purchaseApr;
}
public String getAprDesc() {
	
	return purchaseApr.getPurchaseAprValue();
}



}
