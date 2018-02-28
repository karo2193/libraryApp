package com.library.proj.libraryapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Karo2 on 2018-01-16.
 */

public class CategoryResponse {

    @SerializedName("main_category")
    @Expose
    private Category category;
    @SerializedName("subcategories")
    @Expose
    private List<Category> subcategories;

    public void setCategorySubcategories() {
        category.setSubcategories(subcategories);
    }

    public Category getCategory() {
        return category;
    }
}
