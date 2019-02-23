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
    private String itemStatus;
    @SerializedName("image_url1")
    @Expose
    private String imageUrl1;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("image_url2")
    @Expose
    private String imageUrl2;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemTopic() {
        return itemTopic;
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

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
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

}
