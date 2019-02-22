package meh.example.root.itemwall.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemCateModel {
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("cat_father")
    @Expose
    private String catFather;
    @SerializedName("cat_name")
    @Expose
    private String catName;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatFather() {
        return catFather;
    }

    public void setCatFather(String catFather) {
        this.catFather = catFather;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
