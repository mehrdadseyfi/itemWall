package meh.example.root.itemwall.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemCatStatus {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("cate")
    @Expose
    private List<ItemCateModel> cate = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ItemCateModel> getCate() {
        return cate;
    }

    public void setCate(List<ItemCateModel> cate) {
        this.cate = cate;
    }
}
