package com.company;

import java.util.*;

import static com.company.ProjectConstants.*;

public class Main {

    public static void main(String[] args) {

        Student student = new Student();
        Course course = new Course();

        String answer;
        String garbage;

        int studentNum;
        double answerNum;
        int gradeCount;

        boolean validInfo;
        boolean allDone = false;
        boolean done = false;
        boolean courseDone = false;

        Scanner uIn = new Scanner(System.in);

        do {
            studentNum = 0;
            course.reset();
            System.out.print(SECTION_DIV + "Enter Course Code: ");
            if (uIn.hasNext()) {
                course.setCourseCode(uIn.next());
            }
            do {
                studentNum++;
                student.reset();
                do {
                    System.out.println(SECTION_DIV);
                    System.out.println("STUDENT INFORMATION: Student " + studentNum);
                    System.out.print("\tFirst Name: ");
                    if (uIn.hasNext()) { //try .hasNextLine next time and see if you can get it to work
                        student.setFName(uIn.next());
                    }
                    System.out.print("\tLast Name: ");
                    if (uIn.hasNext()) {
                        student.setLName(uIn.next());
                    }
                    do {
                        System.out.print("\tStudent ID: ");
                        if (uIn.hasNextInt()) {
                            student.setStID(uIn.nextInt());
                            if (student.getStID() > 0) {
                                validInfo = true;
                            } else {
                                System.out.println(ERR_EXPECTED_NUM);
                                validInfo = false;
                            }
                        } else {
                            garbage = uIn.next();
                            System.out.println(ERR_EXPECTED_NUM + "Value '" + garbage + "' is not valid.");
                            validInfo = false;
                        }
                    } while (!validInfo);
                    do {
                        student.displayBasic();
                        System.out.println("\nIs all the above information correct?");
                        if (uIn.hasNext()) {
                            answer = uIn.next();
                            if ((answer.contains("y")) && (answer.contains("n"))) {
                                System.out.println(YES_NO);
                                validInfo = false;
                                done = false;
                            } else if (answer.contains("y")) {
                                done = true;
                                validInfo = true;
                            } else if (answer.contains("n")) {
                                done = false;
                                validInfo = true;
                                System.out.println("Please re-input information for Student " + studentNum);
                            } else {
                                System.out.println(YES_NO);
                                done = false;
                                validInfo = false;
                            }
                        }
                    } while (!validInfo);
                } while (!done);

                System.out.println(DATA_DIV);
                System.out.println("STUDENT GRADES");
                gradeCount = 1;
                do {
                    System.out.print("\tGrade " + gradeCount + ": ");
                    if (uIn.hasNextDouble()) {
                        answerNum = uIn.nextDouble();
                        if (answerNum > 100 || answerNum < 0) { //Forces the user to input a number between 0 and 100.
                            System.out.println("Please input a valid value (between 0 and 100).");
                            done = false;
                        } else if (gradeCount <= MAX_MARKS) {
                            student.addMark(answerNum);
                            do {
                                System.out.println();
                                if (gradeCount < MAX_MARKS) {
                                    System.out.println("Are there any more marks?"); //asks the user if there are any more grades, if yes repeats code if no moves on
                                    if (uIn.hasNext()) {
                                        answer = uIn.next();
                                        if ((answer.contains("y")) && (answer.contains("n"))) {
                                            System.out.println(YES_NO);
                                            validInfo = false;
                                            done = false;
                                        } else if (answer.contains("y")) {
                                            done = false;
                                            gradeCount++;
                                            validInfo = true;
                                        } else if (answer.contains("n")) {
                                            done = true;
                                            validInfo = true;
                                        } else {
                                            System.out.println(YES_NO);
                                            validInfo = false;
                                        }
                                    }
                                } else {
                                    System.out.println(ERR_MAX_MARK_DATA);
                                    validInfo = true;
                                    done = true;
                                }
                            } while (!validInfo);
                        } else {
                            System.out.println("No more inputs allowed.");
                            done = true;
                        }
                    } else {
                        garbage = uIn.next();
                        System.out.println(ERR_EXPECTED_NUM + "Value '" + garbage + "' is not valid.");
                        done = false;
                    }
                } while (!done);

                student.calcMean();
                course.addStudentGrade(student.getMean());
                System.out.println();
                student.display();

                System.out.println(SECTION_DIV);

                if (studentNum < MAX_STUDENTS) {
                    do {
                        System.out.println("\nAre there any more students?");
                        if (uIn.hasNext()) {
                            answer = uIn.next();
                            if ((answer.contains("y")) && (answer.contains("n"))) {
                                System.out.println(YES_NO);
                                validInfo = false;
                                allDone = false;
                            } else if (answer.contains("y")) {
                                validInfo = true;
                                allDone = false;
                            } else if (answer.contains("n")) {
                                allDone = true;
                                validInfo = true;
                            } else {
                                System.out.println(YES_NO);
                                validInfo = false;
                                allDone = false;
                            }
                        }
                    } while (!validInfo);
                } else {
                    System.out.println(ERR_MAX_STUDENTS);
                    allDone = true;
                }
            } while (!allDone);

            course.calcLevels();
            course.calcMean();
            course.calcMedian();
            course.calcMode();

            course.display();

            System.out.println(SECTION_DIV + "Is there another course?");
            do {
                if (uIn.hasNext()) {
                    answer = uIn.next();
                    if ((answer.contains("y")) && (answer.contains("n"))) {
                        System.out.println(YES_NO);
                        validInfo = false;
                    } else if (answer.contains("y")) {
                        validInfo = true;
                        courseDone = false;
                    } else if (answer.contains("n")) {
                        validInfo = true;
                        courseDone = true;
                    } else {
                        System.out.println(YES_NO);
                        validInfo = false;
                    }
                }
            } while (!validInfo);

        } while (!courseDone);
    }
}
