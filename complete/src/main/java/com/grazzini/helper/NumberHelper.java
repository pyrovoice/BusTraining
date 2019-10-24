package com.grazzini.helper;

public class NumberHelper {
    public static boolean isStringNumeric(String string){
        try{
            Double.parseDouble(string);
        } catch(NumberFormatException | NullPointerException e){
            return false;
        }
        return true;
    }
}
