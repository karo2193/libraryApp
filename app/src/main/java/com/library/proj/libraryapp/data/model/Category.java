package com.library.proj.libraryapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Karo2 on 2018-01-06.
 */

public class Category implements Parcelable {

    @SerializedName("id_kategorii")
    @Expose
    private String categoryId;
    @SerializedName("kategoria")
    @Expose
    private String name;

    private boolean isChecked = false;
    private boolean isExpanded;
    private List<Category> subcategories;

    public Category(String categoryId, String name, List<Category> subcategories) {
        this.categoryId = categoryId;
        this.name = name;
        this.subcategories = subcategories;
    }

    public Category(String name, List<Category> subcategories) {
        this.name = name;
        this.subcategories = subcategories;
    }

    protected Category(Parcel in) {
        categoryId = in.readString();
        name = in.readString();
        isChecked = in.readByte() != 0;
        isExpanded = in.readByte() != 0;
        subcategories = in.createTypedArrayList(Category.CREATOR);
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

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

    public void setSubcategories(List<Category> subcategories) {
        this.subcategories = subcategories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(categoryId);
        parcel.writeString(name);
        parcel.writeByte((byte) (isChecked ? 1 : 0));
        parcel.writeByte((byte) (isExpanded ? 1 : 0));
        parcel.writeTypedList(subcategories);
    }
}
