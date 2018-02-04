package com.library.proj.libraryapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Karo2 on 2018-01-16.
 */

public class Dictionary {

    @SerializedName("types")
    @Expose
    private String[] bookTypes;
    @SerializedName("availability_types")
    @Expose
    private String[] bookAvailabilities;

    public List<String> getBookTypesList() {
        return Arrays.asList(bookTypes);
    }

    public List<String> getBookAvailabilitiesList() {
        return Arrays.asList(bookAvailabilities);
    }
}
