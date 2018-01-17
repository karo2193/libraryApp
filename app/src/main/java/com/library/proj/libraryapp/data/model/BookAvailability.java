package com.library.proj.libraryapp.data.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Karo2 on 2018-01-14.
 */

public enum BookAvailability {
    AVAILABLE,
    BORROW,
    READING_ROOM;

    public static final String AVAILABLE_BOOK = "dostępna";
    public static final String BORROW_BOOK = "wypożyczona";
    public static final String READING_ROOM_BOOK = "czytelnia";

    @Override
    public String toString() {
        String name = name();
        if (name.equals(AVAILABLE.name()))
            return AVAILABLE_BOOK;
        else if (name.equals(BORROW.name()))
            return BORROW_BOOK;
        else if (name.equals(READING_ROOM.name()))
            return READING_ROOM_BOOK;
        else
            return name;
    }

    public static BookAvailability getType(String name) {
        if (StringUtils.isBlank(name))
            return null;
        name = name.trim();
        switch (name) {
            case AVAILABLE_BOOK:
                return AVAILABLE;
            case BORROW_BOOK:
                return BORROW;
            case READING_ROOM_BOOK:
                return READING_ROOM;
            default:
                return null;
        }
    }
}
