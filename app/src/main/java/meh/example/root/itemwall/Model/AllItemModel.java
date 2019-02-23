package meh.example.root.itemwall.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllItemModel {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Item")
    @Expose
    private List<Item> item = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }
}
