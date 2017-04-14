package com.softgroup.task1;

/**
 * Created by Andrew on 14.04.2017.
 */
public class Main {

    //For loop

    public static boolean isPalindrom_ForLoop(String s)
    {
        for(int i = 0, j=s.length()-1; i<j; i++, j--)
            if(s.charAt(i)!=s.charAt(j)) return false;
        return true;
    }

    //Recursion

    public static boolean isPalindrom_Recursion(String s) {
        return s.length() == 0 || s.length() == 1 || s.charAt(0) == s.charAt(s.length() - 1) && isPalindrom_Recursion(s.substring(1, s.length() - 1));
    }

    //StringBuilder Reverse

    public static boolean isPalindrom_Reverse(String s)
    {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

}
