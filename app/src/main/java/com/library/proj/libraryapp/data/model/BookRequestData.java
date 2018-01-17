package com.library.proj.libraryapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Karo2 on 2018-01-14.
 */

public class BookRequestData {

    @SerializedName("query")
    @Expose
    private BookRequestQuery query;

    public BookRequestData() {
        query = new BookRequestQuery();
    }

    public BookRequestQuery getQuery() {
        return query;
    }

    public void setQuery(BookRequestQuery query) {
        this.query = query;
    }
}
