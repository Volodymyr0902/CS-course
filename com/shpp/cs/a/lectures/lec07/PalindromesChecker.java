package com.shpp.cs.a.lectures.lec07;

import com.shpp.cs.a.console.TextProgram;

public class PalindromesChecker extends TextProgram {

    @Override
    public void run() {

        String userInput = readLine("Type some text: ");

        if (isPalindrome(userInput)) {
            println("Palindrome");
        } else {
            println("Not palindrome");
        }
    }

    private boolean isPalindrome(String str) {
        str = unifyString(str);
        return str.equals(reverseString(str));
    }

    private String unifyString(String str) {
        String unifiedString = "";
        for (int i = 0; i < str.length(); i++) {
            char l = str.charAt(i);
            unifiedString += Character.isLetter(l) ? Character.toLowerCase(l) : "";
        }

        return unifiedString;
    }

    private String reverseString(String str) {
        String strRev = new String();
        for (int i = 1; i <= str.length(); i++) {
            strRev += str.charAt(str.length() - i);
        }
        return strRev;
    }

    //        String str = "Hello!";
//        String strRev = new String();
//        for (int i = 1; i <= str.length(); i++) {
//            strRev += str.charAt(str.length() - i);
//        }
//        System.out.println(strRev);
//
//        String str2 = "Vova";
//        String strRev2 = "";
//        for (int i = 0; i < str2.length(); i++) {
//            char l = str2.charAt(i);
//            strRev2 = l + strRev2;
//        }
//        System.out.println(strRev2);

}
