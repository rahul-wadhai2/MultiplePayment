package com.bills.utils;

import java.text.DecimalFormat;
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
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        String formattedAmount = decimalFormat.format(amount);
        return currencyFormat.format(Double.parseDouble(formattedAmount))
                .replaceAll("Rs.|₹", "").trim();
    }
}
