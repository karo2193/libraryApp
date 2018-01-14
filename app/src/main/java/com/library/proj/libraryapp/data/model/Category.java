package com.library.proj.libraryapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Karo2 on 2018-01-06.
 */

public class Category implements Serializable {

    @SerializedName("id_kategorii")
    @Expose
    private String categoryId;
    @SerializedName("kategoria")
    @Expose
    private String name;

    private boolean isChecked = false;
    private boolean isExpanded;
    private List<Category> subcategories;

    public Category(String name, List<Category> subcategories) {
        this.name = name;
        this.subcategories = subcategories;
    }

    public String getName() {
        return name;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }
}
