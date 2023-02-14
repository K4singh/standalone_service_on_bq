package com.google;

public class hello_world {

    public static String generateString( String input) {
//        System.out.println("Generating string");

        return input+"_test";
    }

    public static void main(String[] args){
        String sample = "this_is_a_string";
        String encoded = generateString(sample);
        System.out.println(encoded);

    }
}
