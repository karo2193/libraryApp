package com.library.proj.libraryapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Karo2 on 2018-01-14.
 */

public class BookRequestQuery {

    @SerializedName("filters")
    @Expose
    private BookRequestFilters filters;
    @SerializedName("categories")
    @Expose
    private String[] categories;
    @SerializedName("pagination")
    @Expose
    private BookRequestPagination pagination;

    public BookRequestFilters getFilters() {
        return filters;
    }

    public void setFilters(BookRequestFilters filters) {
        this.filters = filters;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public BookRequestPagination getPagination() {
        return pagination;
    }

    public void setPagination(BookRequestPagination pagination) {
        this.pagination = pagination;
    }
}