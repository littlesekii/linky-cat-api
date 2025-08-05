package cat.linky.linkycat_api.core.util;

public class Utils {
    
    public static int randomInt(int startRange, int endRange)
    {
        int number = (int) (Math.random() * (1 + endRange - startRange));
        number += startRange;
        return number;
    }

    public static boolean startsWithAlphanumeric(String str) {
        return Character.isLetterOrDigit(str.charAt(0));
    }
}
