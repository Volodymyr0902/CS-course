package com.shpp.cs.a.lectures.lec15;

import java.util.Date;

public class Hacker {
    private static final String PASSWORD = "zzzzzz";

    public static void main(String[] args) {
        Character[] form = {'!', '!', '!', '!', '!', '!'};

        Date start = new Date();
        System.out.println(start);

        for (int k = 33; k < 126; k++) {
            String key = check5Identique(form, k);
            if (key.equals(PASSWORD)) {
                System.out.println("Password cracked!");
                break;
            }
            form = allSymbolsUp(form);
        }

        Date finish = new Date();
        System.out.println(finish);
    }

    private static Character[] allSymbolsUp(Character[] form) {
        for (int i = 0; i < form.length; i++) {
            form[i]++;
        }
        return form;
    }

    private static String check5Identique(Character[] form, int k) {
        String key = arrToString(form);
        for (int i = 0; i < form.length; i++) {
            for (int j = form[i]; j < 126; j++) {
                System.out.println(key);
                if (key.equals(PASSWORD)) {
                    return key;
                }
                form[i]++;
                key = arrToString(form);
            }
            form[i] = (char) k;
        }
        return key;
    }

    private static String arrToString(Character[] form) {
        String key = "";
        for (int i = 0; i < form.length; i++)
            key += form[i];
        return key;
    }
}
