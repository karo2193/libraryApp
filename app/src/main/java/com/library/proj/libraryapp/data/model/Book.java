package com.library.proj.libraryapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Karo2 on 2018-01-14.
 */

public class Book {

    @SerializedName("syg_ms")
    @Expose
    private String facultySignature;
    @SerializedName("syg_bg")
    @Expose
    private String mainSignature;
    @SerializedName("ozn_opdow")
    @Expose
    private String responsibility;
    @SerializedName("tytul")
    @Expose
    private String title;
    @SerializedName("tom")
    @Expose
    private String volume;
    @SerializedName("rok")
    @Expose
    private Integer year;
    @SerializedName("isbn_issn")
    @Expose
    private String isbnWithIssn;
    @SerializedName("typ")
    @Expose
    private String type;
    @SerializedName("dostepnosc")
    @Expose
    private String availability;
    @SerializedName("kategoria")
    @Expose
    private List<Category> categories;

    public String getFacultySignature() {
        return facultySignature;
    }

    public String getMainSignature() {
        return mainSignature;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public String getTitle() {
        return title;
    }

    public String getVolume() {
        return volume;
    }

    public Integer getYear() {
        return year;
    }

    public String getIsbnWithIssn() {
        return isbnWithIssn;
    }

    public String getType() {
        return type;
    }

    public String getAvailability() {
        return availability;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
