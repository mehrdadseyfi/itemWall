package meh.example.root.itemwall.ItemDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemDetail {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Item")
    @Expose
    private List<ItemDeatailC> item = null;
    @SerializedName("item_contact")
    @Expose
    private String itemContact;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemDeatailC> getItem() {
        return item;
    }

    public void setItem(List<ItemDeatailC> item) {
        this.item = item;
    }

    public String getItemContact() {
        return itemContact;
    }

    public void setItemContact(String itemContact) {
        this.itemContact = itemContact;
    }
}
