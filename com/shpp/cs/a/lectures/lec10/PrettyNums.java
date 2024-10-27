package com.shpp.cs.a.lectures.lec10;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;

public class PrettyNums extends TextProgram {

    @Override
    public void run() {
        while (true) {
            String digits = readLine("Enter a numeric string: "); // 533,976
            if (digits.isEmpty())
                break;
            println(addCommasToNumericString(digits));
        }
    }

    private String addCommasToNumericString(String digits) {
//        ArrayList<String> prettyDigitsArr = new ArrayList<String>();
//        for (int i = digits.length() - 1; i >= 0; i--) {
//            prettyDigitsArr.addFirst(String.valueOf(digits.charAt(i)));
//            if ((digits.length() - i) % 3 == 0 && i > 0) {
//                prettyDigitsArr.addFirst(",");
//            }
//        }
//        String prettyDigits = String.join("", prettyDigitsArr);


        String revPrettyDigits = "";
//        String prettyDigits = "";

        // Reverse and add commas
        for (int i = digits.length() - 1; i >= 0; i--) {
            revPrettyDigits = digits.charAt(i) + revPrettyDigits;
            if ((digits.length() - i) % 3 == 0 && i > 0) {
                revPrettyDigits = "," + revPrettyDigits;
            }
        }

        // Alternative
//        for (int i = digits.length() - 1; i >= 0; ) {
//            for (int j = 0; j < 3 && !(i < 0); j++, i--) {
//                revPrettyDigits += digits.charAt(i);
//            }
//            if (i >= 0) {
//                revPrettyDigits += ",";
//            }
//        }

        // Reverse back the one with commas
//        for (int i = 0; i < revPrettyDigits.length(); i++) {
//            prettyDigits = revPrettyDigits.charAt(i) + prettyDigits;
//        }

        return revPrettyDigits;
    }


}
