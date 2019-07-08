package freya.component.core.util;

public class StringUtil {
    /**
     * 大小写转换
     */

//    public static String lowerCase(String str) {
//        String headStr = str.substring(0, 1);
//        String changStr = str.substring(startIndex, endIndex);
//        String endStr = str.substring(endIndex);
//        return headStr + changStr.toLowerCase() + endStr;
//    }
    public static String lowerCase(String str, int startIndex, int endIndex) {
        String frontStr = subString(str, 0, startIndex);
        String changStr = subString(str, startIndex, endIndex);
        String behindStr = subString(str, endIndex, str.length());
        return frontStr + changStr.toLowerCase() + behindStr;
    }

//    public static String upperCase(String str) {
//        String headStr = str.substring(0, startIndex);
//        String changStr = str.substring(startIndex, endIndex);
//        String endStr = str.substring(endIndex);
//        return headStr + changStr.toUpperCase() + endStr;
//    }

    public static String upperCase(String str, int startIndex, int endIndex) {
        String headStr = str.substring(0, startIndex);
        String changStr = str.substring(startIndex, endIndex);
        String endStr = str.substring(endIndex);
        return headStr + changStr.toUpperCase() + endStr;
    }

    public static String subString(String str, int startIndex, int endIndex) {
        return str != null && startIndex > -1 && startIndex <= endIndex && endIndex <= str.length() ? str.substring(startIndex, endIndex) : "";
    }

    public static String subString(String str, int startIndex) {
        return str != null ? subString(str, startIndex, str.length()) : "";
    }
}
