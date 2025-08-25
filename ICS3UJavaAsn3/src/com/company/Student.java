package com.company;

import static com.company.ProjectConstants.*;

public class    Student {

    //fields

    private String fName;
    private String lName;

    private int stID;
    private double[] testMarks = new double[MAX_MARKS];
    private int markCount;
    private int mean;

    //methods

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getFName() {
        return fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getLName() {
        return lName;
    }

    public void setStID(int stID) {
        this.stID = stID;
    }

    public int getStID() {
        return stID;
    }

    public int getMarkCount() {
        return markCount;
    }

    public int addMark(double newMark) {
        int returnValue;
        if (markCount < MAX_MARKS) {
            this.testMarks[markCount] = newMark;
            markCount++;
            returnValue = SUCCESS;
        } else {
            System.out.println(ERR_MAX_MARK_DATA);
            returnValue = FAILURE;
        }

        return returnValue;
    }

    public void calcMean() {
        double doubleStAvg = 0;
        for (int i = 0; i < markCount; i++) {
            doubleStAvg += testMarks[i];
        }
        doubleStAvg = doubleStAvg / markCount;
        mean = (int) (doubleStAvg + 0.5); //rounding
    }

    public int getMean() {
        return mean;
    }

    public void displayAll() {
        System.out.print("First Name: " + getFName() + " ");
        System.out.print("Last Name: " + getLName() + ", ");
        System.out.print("Student ID: " + getStID() + " ");
        System.out.print("Grade #: " + getMarkCount() + " ");
        System.out.print("Grades: ");
        for (int i = 0; i < markCount; i++) {
            System.out.print(testMarks[i] + " ");
        }
        System.out.print("Student Average: " + mean + "\n");
    }

    public void displayBasic() {
        System.out.print(getLName() + ", " + getFName() + ", " + getStID());
    }

    public void display() {
        System.out.print(getLName() + ", ");
        System.out.print(getFName() + ", ");
        System.out.print(getStID() + "\t\t");
        System.out.print("Mark: " + mean + "%\n");
    }

    public void reset() {
        this.fName = null;
        this.lName = null;
        this.markCount = 0;
        this.stID = INVALID;
        this.mean = 0;

        for (int i = 0; i < MAX_MARKS; i++) {
            this.testMarks[i] = 0;
        }
    }

}
