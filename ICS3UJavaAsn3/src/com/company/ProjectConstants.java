package com.company;

public class    ProjectConstants {

    // values
    public final static int MAX_MARKS = 10;
    public final static int MAX_STUDENTS = 28;
    public final static int MAX_MODES = 5;
    public static final int INVALID = -1;

    public static final int SUCCESS = -1000;
    public static final int FAILURE = -200;

    //Errors

    public final static String ERR_MAX_MARK_DATA = "Error: Max Data Amount";
    public static final String ERR_EXPECTED_NUM = "Error: Incorrect data type entered. Please enter a positive integer.";
    public static final String ERR_MAX_STUDENTS = "No more students allowed.";

    //strings

    public static final String DATA_DIV = "\n-------------------------------------------------\n";
    public static final String SECTION_DIV = "\n====================================================================\n";
    public static final String YES_NO = "Please input either 'yes' or 'no.'";

    private ProjectConstants() {
        //prevents even the class itself from creating objects within it
        throw new AssertionError();
    }
}
