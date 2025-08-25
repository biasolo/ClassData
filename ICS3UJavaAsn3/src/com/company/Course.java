package com.company;

import java.util.*;

import static com.company.ProjectConstants.*;

public class Course {

    //fields
    int[] courseGrades = new int[MAX_STUDENTS];
    int[] courseModes = new int[MAX_MODES];
    int studentCount;
    int level_R;
    int level_1;
    int level_2;
    int level_3;
    int level_4;
    double mean;
    double median;
    int modeCount;

    String courseCode;

    //methods

    //get/set for any fields that require one

    public void setCourseCode(String courseName) {
        this.courseCode = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int addStudentGrade(int studentGrade) {
        int returnValue;
        if (studentCount < MAX_STUDENTS) {
            this.courseGrades[studentCount] = studentGrade;
            studentCount++;
            returnValue = SUCCESS;
        } else {
            System.out.println(ERR_MAX_MARK_DATA);
            returnValue = FAILURE;
        }

        return returnValue;
    }

    public void calcLevels() {
        for (int i = 0; i < studentCount; i++) {
            if (courseGrades[i] < 50) { //analyses average of student and determines whether they are failing or passing
                level_R++;
            }
            if ((courseGrades[i] >= 50) && (courseGrades[i] <= 59)) {
                level_1++;
            }
            if ((courseGrades[i] >= 60) && (courseGrades[i] <= 69)) {
                level_2++;
            }
            if ((courseGrades[i] >= 70) && (courseGrades[i] <= 79)) {
                level_3++;
            }
            if (courseGrades[i] >= 80) {
                level_4++;
            }
        }
    }

    public int getLevel_R() {
        return level_R;
    }

    public int getLevel_1() {
        return level_1;
    }

    public int getLevel_2() {
        return level_2;
    }

    public int getLevel_3() {
        return level_3;
    }

    public int getLevel_4() {
        return level_4;
    }

    public void calcMean() {
        for (int i = 0; i < studentCount; i++) {
            mean += courseGrades[i];
        }
        mean = mean / studentCount;
    }

    public double getMean() {
        return mean;
    }

    public void calcMedian() {
        for (int i = 0; i < studentCount; i++) {
            for (int j = 0; j < studentCount; j++) {
                if (courseGrades[i] < courseGrades[j]) {
                    int tempStorage = courseGrades[i];
                    courseGrades[i] = courseGrades[j];
                    courseGrades[j] = tempStorage;
                }
            }
        }
        int division = (studentCount) % 2;
        if (division == 1) {
            int middle = (studentCount / 2) + 1;
            median = courseGrades[middle];
        } else {
            int middle = studentCount / 2;
            median = courseGrades[middle] + courseGrades[middle + 1];
            median = median / 2;
        }
    }

    public double getMedian() {
        return median;
    }

    public void calcMode() {
        int[] placeholder = new int[MAX_STUDENTS];
        Arrays.fill(placeholder, INVALID);
        if (studentCount >= 0) {
            System.arraycopy(courseGrades, 0, placeholder, 0, studentCount);
        }
        for (int modeCounter = 0; modeCounter < MAX_MODES; modeCounter++) {
            int modeCountOne;
            int modeCountTwo = 0;
            for (int i = 0; i < studentCount; i++) {
                modeCountOne = 0;
                for (int j = 0; j < studentCount; j++) {
                    if (courseGrades[i] == placeholder[j]) {
                        modeCountOne++;
                    }
                    if (modeCountOne > 1) {
                        if (modeCountOne > modeCountTwo) {
                            modeCountTwo = modeCountOne;
                            courseModes[modeCounter] = courseGrades[i];
                            System.out.print(courseModes[i]);
                        }
                    }
                }
            }
            for (int i = 0; i < studentCount; i++) {
                if (courseModes[modeCounter] == placeholder[i]) {
                    placeholder[i] = INVALID;
                }
            }
        }
        setModeCount();
    }

    public void setModeCount() {
        for (int i = 0; i < MAX_MODES; i++) {
            if (courseModes[i] != INVALID) {
                modeCount++;
            }
        }
    }

    public int getModeCount() {
        return modeCount;
    }

    public void reset() {
        this.level_R = 0;
        this.level_1 = 0;
        this.level_2 = 0;
        this.level_3 = 0;
        this.level_4 = 0;
        this.modeCount = 0;
        this.studentCount = 0;
        this.median = 0;
        this.mean = 0;
        Arrays.fill(courseGrades, INVALID);
        Arrays.fill(courseModes, INVALID);
        this.courseCode = null;
    }

    public void display() {
        System.out.println("\n" + SECTION_DIV); //figure out how to round classMean for later
        System.out.println(getCourseCode().toUpperCase(Locale.ROOT));
        System.out.print("\nClass Data:\n\tAverage:");
        System.out.printf("%10.2f", getMean());
        System.out.print("%\n\tMedian: ");
        System.out.printf("%10.2f", getMedian());
        System.out.print("%\n\tMode: \n\t\tMode Count: " + getModeCount() + "\n\t\tMode(s): ");
        for (int i = 0; i < modeCount; i++) {
            System.out.print(courseModes[i]);
            if (courseModes[i + 1] == INVALID) {
                System.out.print("% ");
            } else {
                System.out.print("%, ");
            }
        }
        System.out.println("\n\n\t\tClass Level Data:\n\t\t\tLevel R: " + getLevel_R() + "\n\t\t\tLevel 1: " + getLevel_1() + "\n\t\t\tLevel 2: " + getLevel_2() + "\n\t\t\tLevel 3: " + getLevel_3() + "\n\t\t\tLevel 4: " + getLevel_4());
        System.out.println(SECTION_DIV + "\n");
    }

    public void displayAll() {
        System.out.println("Course Code: " + getCourseCode() + "\t\tStudents: " + studentCount);
        System.out.println("\nMedian: " + getMedian() + "\nMean: " + getMean() + "\nMode(s): \n\tModeCount: " + getModeCount() + "\n\tModes:");
        for (int i = 0; i < modeCount; i++) {
            System.out.print(courseModes[i]);
            if (courseModes[i + 1] == INVALID) {
                System.out.print("% ");
            } else {
                System.out.print("%, ");
            }
        }
        System.out.print("\nLevel Data: \n\tLevel 4: " + getLevel_4() + "\n\tLevel 3: " + getLevel_3() + "\n\tLevel 2:" + getLevel_2() + "\n\tLevel 1: " + getLevel_1() + "\n\tLevel R: " + getLevel_R());
    }
}
