package com.library.proj.libraryapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Karo2 on 2018-01-14.
 */

public class BookRequestFilters implements Parcelable {

    @SerializedName("syg_ms__contains")
    @Expose
    private String facultySignature;
    @SerializedName("syg_bg__contains")
    @Expose
    private String mainSignature;
    @SerializedName("ozn_opdow__contains")
    @Expose
    private String responsibility;
    @SerializedName("tytul__contains")
    @Expose
    private String title;
    @SerializedName("tom__contains")
    @Expose
    private String volume;
    @SerializedName("rok__contains")
    @Expose
    private Integer year;
    @SerializedName("isbn_issn__contains")
    @Expose
    private String isbnWithIssn;
    @SerializedName("typ__contains")
    @Expose
    private String type;
    @SerializedName("dostepnosc__contains")
    @Expose
    private String availability;

    public BookRequestFilters() {

    }

    protected BookRequestFilters(Parcel in) {
        facultySignature = in.readString();
        mainSignature = in.readString();
        responsibility = in.readString();
        title = in.readString();
        volume = in.readString();
        if (in.readByte() == 0) {
            year = null;
        } else {
            year = in.readInt();
        }
        isbnWithIssn = in.readString();
        type = in.readString();
        availability = in.readString();
    }

    public static final Creator<BookRequestFilters> CREATOR = new Creator<BookRequestFilters>() {
        @Override
        public BookRequestFilters createFromParcel(Parcel in) {
            return new BookRequestFilters(in);
        }

        @Override
        public BookRequestFilters[] newArray(int size) {
            return new BookRequestFilters[size];
        }
    };

    public String getFacultySignature() {
        return facultySignature;
    }

    public void setFacultySignature(String facultySignature) {
        this.facultySignature = facultySignature;
    }

    public String getMainSignature() {
        return mainSignature;
    }

    public void setMainSignature(String mainSignature) {
        this.mainSignature = mainSignature;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getIsbnWithIssn() {
        return isbnWithIssn;
    }

    public void setIsbnWithIssn(String isbnWithIssn) {
        this.isbnWithIssn = isbnWithIssn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(facultySignature);
        parcel.writeString(mainSignature);
        parcel.writeString(responsibility);
        parcel.writeString(title);
        parcel.writeString(volume);
        if (year == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(year);
        }
        parcel.writeString(isbnWithIssn);
        parcel.writeString(type);
        parcel.writeString(availability);
    }
}
