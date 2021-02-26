package io.github.jacobpassam.kits.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Utility class for time operations.
 */
public class UtilTime {

    /**
     * Default method to convert time to a string, rounding to 1DP.
     *
     * @param time The time to convert, in milliseconds.
     * @return A formatted string.
     */
    public static String timeToString(long time) {
        return convertString(time);
    }

    /**
     * Converts a time to a string.
     *
     * @param time The time to convert, in milliseconds.
     * @return A formatted string.
     */
    private static String convertString(long time) {
        if (time == -1) return "Permanent";

        TimeUnit timeUnit;

        if (time < 60000) timeUnit = TimeUnit.SECONDS;
        else if (time < 3600000) timeUnit = TimeUnit.MINUTES;
        else if (time < 86400000) timeUnit = TimeUnit.HOURS;
        else timeUnit = TimeUnit.DAYS;

        String fullText;
        double numToDisplay;

        if (timeUnit == TimeUnit.DAYS) {
            numToDisplay = trimDecimal(time / 86400000D);
            fullText = numToDisplay + " day";
        } else if (timeUnit == TimeUnit.HOURS) {
            numToDisplay = trimDecimal(time / 3600000D);
            fullText = numToDisplay + " hour";
        } else if (timeUnit == TimeUnit.MINUTES) {
            numToDisplay = trimDecimal(time / 60000D);
            fullText = numToDisplay + " minute";
        } else {
            numToDisplay = trimDecimal(time / 1000D);
            fullText = numToDisplay + " second";
        }

        if (numToDisplay != 1)
            fullText += "s";

        return fullText;
    }

    /**
     * Trims a decimal by a degree of places.
     *
     * @param d      The decimal to trim.
     * @return The trimmed decimal.
     */
    private static double trimDecimal(double d) {

        DecimalFormatSymbols symb = new DecimalFormatSymbols(Locale.ENGLISH);
        DecimalFormat twoDForm = new DecimalFormat("#.#", symb);
        return Double.parseDouble(twoDForm.format(d));
    }

    /**
     * Represents a unit of time.
     */
    public enum TimeUnit {
        DAYS,
        HOURS,
        MINUTES,
        SECONDS
    }
}
