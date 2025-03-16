package com.merabills.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utils class.
 */
public class Utils {

    /**
     * Get formated amount.
     *
     * @param amount amount to format.
     * @return formated amount.
     */
    public static String getFormatedAmount(double amount) {
        NumberFormat format = NumberFormat
                .getCurrencyInstance(new Locale("en", "IN"));
        return format.format(amount).replaceAll("Rs.|₹", "").trim();
    }
}
