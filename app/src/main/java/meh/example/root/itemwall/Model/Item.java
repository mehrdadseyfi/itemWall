package meh.example.root.itemwall.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("item_topic")
    @Expose
    private String itemTopic;
    @SerializedName("item_cat_id")
    @Expose
    private String itemCatId;
    @SerializedName("item_location")
    @Expose
    private String itemLocation;
    @SerializedName("item_price")
    @Expose
    private String itemPrice;
    @SerializedName("item_peresent")
    @Expose
    private String itemPeresent;
    @SerializedName("item_status")
    @Expose
    private int itemStatus;
    @SerializedName("image_url1")
    @Expose
    private String imageUrl1;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("image_url2")
    @Expose
    private String imageUrl2;
    @SerializedName("reason")
    @Expose
    private String reason;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemTopic() {
        return itemTopic;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setItemTopic(String itemTopic) {
        this.itemTopic = itemTopic;
    }

    public String getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(String itemCatId) {
        this.itemCatId = itemCatId;
    }

    public String getItemLocation() {
        return itemLocation;
    }

    public void setItemLocation(String itemLocation) {
        this.itemLocation = itemLocation;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemPeresent() {
        return itemPeresent;
    }

    public void setItemPeresent(String itemPeresent) {
        this.itemPeresent = itemPeresent;
    }

    public int getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(int itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getImageUrl1() {
        return imageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        this.imageUrl1 = imageUrl1;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public Item(String itemId, String itemTopic, String itemCatId, String itemLocation, String itemPrice, String itemPeresent, int itemStatus, String imageUrl1, String userId, String imageUrl2) {
        this.itemId = itemId;
        this.itemTopic = itemTopic;
        this.itemCatId = itemCatId;
        this.itemLocation = itemLocation;
        this.itemPrice = itemPrice;
        this.itemPeresent = itemPeresent;
        this.itemStatus = itemStatus;
        this.imageUrl1 = imageUrl1;
        this.userId = userId;
        this.imageUrl2 = imageUrl2;
    }
}
