package com.homework.bookcontacts.utils;


import java.util.Random;

public class Constants {

    public static String MESSAGE_NOT_FOUND_CONTACT = "Not found contact by this ID";
    private static final Random random = new Random();

    public static Long getId() {
        long id = random.nextLong(1111, 9999);
        return id;
    }
}
