package org.maxkey.client.utils;

public class StringUtils {

    public static boolean isBlank(String str) {
        if(str ==null || str.trim().equals("")) {
            return true;
        }
        return false;
    }
    
    public static boolean isNotBlank(String str) {
        if(str!=null && !str.trim().equals("")) {
            return true;
        }
        return false;
    }
}
