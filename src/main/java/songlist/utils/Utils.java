package songlist.utils;

public class Utils {

    private Utils(){}

    public static boolean isNotNullOrEmpty(String s) {
        return s != null && !s.isBlank();
    }
}
