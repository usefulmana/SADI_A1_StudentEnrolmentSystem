/*
 * Author: Nguyen Le Bao Anh
 * Created on: 27/02/2019
 * Last updated on: 14/03/2019
 * GitHub Repo: https://github.com/usefulmana/SADI_A1_EnrolmentApp
 * This is the submission file for assignment I of COSC2440- sadi.
 * This is a console application written in Java 11. This application will allow staffs to enroll students in courses
 * available in the corresponding semesters. It also allows staffs to update, delete, and show enrollments of all
 * students. Additionally, 11 Java design patterns were used. For more details, please refer to the README.md file. */

package sadi;

import sadi.builder.CourseBuilder;
import sadi.command.Command;
import sadi.cor.Chain;
import sadi.course.*;
import sadi.enrollment.StudentEnrollment;
import sadi.enrollment.StudentEnrollmentVisitor;
import sadi.person.Student;
import sadi.person.StudentFactory;
import sadi.person.StudentList;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /* Building courses */
        Course webProgramming = new CourseBuilder("COSC2430","Web Programming",12,"AC").build();
        Course softwareArchitecture = new CourseBuilder("COSC2440","Software Architecture: Design &" +
                " Implementation",12,"A").build();
        Course cppProgramming = new CourseBuilder("COSC2131","Programming Using C++",12,"A").build();
        Course database = new CourseBuilder("ISYS2077","Database Concepts",12,"A").build();
        Course softEnProcess = new CourseBuilder("COSC2101","Software Engineering: Process &" +
                " Tools",12,"B").build();
        Course buildingITSys = new CourseBuilder("COSC2634","Building IT Systems", 12,"C").build();
        Course security = new CourseBuilder("COSC2539","Security in Computing and Information Technology",
                12,"C").build();
        Course softENFundamental = new CourseBuilder("ISYS2089","Software Engineering Fundamentals",
                12,"C").build();
        Course professionalComp = new CourseBuilder("COSC2130","Professional Computing Practice",
                12,"C").build();
        Course introToIT = new CourseBuilder("COSC2083","Intro to Information Technology",12, "AC").build();
        Course introToProgramming = new CourseBuilder("COSC2429","Intro to Programming",12, "AC").build();
        Course programmingI = new CourseBuilder("COSC2081","Programming I",12,"AC").build();
        Course math = new CourseBuilder("MATH2081","Mathematics for Computing",12,"ABC").build();

        /* Assigning courses to semesters */
        Chain courseListA = CourseListA.getINSTANCE();
        Chain courseListB = CourseListB.getINSTANCE();
        Chain courseListC = CourseListC.getINSTANCE();
        courseListA.setNextChain(courseListB);
        courseListB.setNextChain(courseListC);
        courseListA.addCourse(Course.getCourseList());


        /* Creating students */
        Student student1 = StudentFactory.createStudent("s1234567","Emily Nguyen");
        student1.setBirthday("18-04-1999");
        student1.subscribe();
        Student student2 = StudentFactory.createStudent("s1234568", "Bob Le");
        student2.setBirthday("23-11-2000");
        student2.subscribe();
        Student student3 = StudentFactory.createStudent("s1234569","Hannah Tran");
        student3.setBirthday("11-07-1996");
        student3.subscribe();
        Student student4 = StudentFactory.createStudent("s9876543","Vincent Tran");
        student4.setBirthday("07-12-1999");
        student4.subscribe();
        Student student5 = StudentFactory.createStudent("s9876542","Alexandra Scott");
        student5.setBirthday("12-12-1997");
        student5.subscribe();

        viewMainMenu();

    }
    public static void viewMainMenu() {
            /* Creating StudentEnrollment objects for every students*/
            List<Command> studentEnrolmentList = new ArrayList<>();
            StudentList studentList = StudentList.getINSTANCE();
            /* Iterating through the StudentList object*/
            while (studentList.hasNext())
            {
                studentEnrolmentList.add(new StudentEnrollment(studentList.next(),48));
            }
            while (true) {
                StudentList.printStudentInfo();
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter student ID: ");
                String studentID = scanner.nextLine().trim();
                Student student = StudentList.searchStudent(studentID);

                if (student != null) {
                    try {
                        StudentEnrollment studentEnrollment = StudentEnrollment.getStudentEnrolment(studentID);
                        System.out.print("**************************************\n"
                                +"RMIT Student Enrollment System\n"+
                                "1. Enroll student in a course\n" +
                                "2. Drop a course\n" +
                                "3. Update enrollments\n" +
                                "4. View all current enrollments of the current student\n" +
                                "5. View enrollments of all students in a particular semester\n" +
                                "6. Drop student from all courses \n" +
                                "7. Quit\n"+
                                "Your choice: ");
                        Scanner scanner1 = new Scanner(System.in);
                        int choice = scanner1.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println("Please choose a semester");
                                System.out.print("1. A\n" + "2. B\n" + "3. C\n" + "4. Return\n" + "5. Quit program\n" + "Your choice: ");
                                Scanner scanner2 = new Scanner(System.in);
                                int choiceEnrolA = scanner2.nextInt();
                                switch (choiceEnrolA) {
                                    case 1:
                                        while (true) {
                                            CourseListA.printCourseList();
                                            System.out.print("Enter course ID: ");
                                            Scanner scanner3 = new Scanner(System.in);
                                            String courseID = scanner3.nextLine();
                                            Course course3 = CourseListA.courseSearch(courseID.toUpperCase());
                                            if (course3 != null) {
                                                studentEnrollment.executeEnrol(course3, "A");
                                                if (StudentEnrollment.continuePrompt("Undo previous action (Y/n)?: ")) {
                                                    studentEnrollment.undoEnrol(course3, "A");
                                                }
                                                if (!StudentEnrollment.continuePrompt("Enrolling in another course (Y/n)?: ")) {
                                                    break;
                                                }
                                            } else {
                                                System.out.println("Wrong Course ID.");
                                            }
                                        }
                                        break;
                                    case 2:
                                        while (true) {
                                            CourseListB.printCourseList();
                                            System.out.print("Enter course ID: ");
                                            Scanner scanner4 = new Scanner(System.in);
                                            String courseID1 = scanner4.nextLine();
                                            Course course4 = CourseListB.courseSearch(courseID1.toUpperCase());
                                            if (course4 != null) {
                                                studentEnrollment.executeEnrol(course4, "B");
                                                if (StudentEnrollment.continuePrompt("Undo previous action (Y/n)?: ")) {
                                                    studentEnrollment.undoEnrol(course4, "B");
                                                }
                                                if (!StudentEnrollment.continuePrompt("Enrolling in another course (Y/n)?: ")) {
                                                    break;
                                                }
                                            } else {
                                                System.out.println("Wrong Course ID.");
                                            }

                                        }
                                        break;
                                    case 3:
                                        while (true) {
                                            CourseListC.printCourseList();
                                            System.out.print("Enter course ID: ");
                                            Scanner scanner5 = new Scanner(System.in);
                                            String courseID5 = scanner5.nextLine();
                                            Course course5 = CourseListC.courseSearch(courseID5.toUpperCase());
                                            if (course5 != null) {
                                                studentEnrollment.executeEnrol(course5, "C");

                                                if (StudentEnrollment.continuePrompt("Undo previous action (Y/n)?: ")) {
                                                    studentEnrollment.undoEnrol(course5, "C");
                                                }
                                                if (!StudentEnrollment.continuePrompt("Enrolling in another course (Y/n)?: ")) {
                                                    break;
                                                }
                                            } else {
                                                System.out.println("Wrong Course ID.");
                                            }
                                        }
                                        break;
                                    case 4:
                                        break;
                                    case 5:
                                        System.out.println("Program exits. Have a good day!");
                                        System.exit(0);
                                        break;
                                    default:
                                        System.out.println("No such choice available. Please try again!");
                                }
                                break;
                            case 2:
                                System.out.println("Please choose a semester");
                                System.out.print("1. A\n" + "2. B\n" + "3. C\n" + "4. Return\n" + "5. Quit program\n" + "Your choice: ");
                                Scanner scanner6 = new Scanner(System.in);
                                int choiceDropA = scanner6.nextInt();
                                switch (choiceDropA) {
                                    case 1:
                                        while (true) {
                                            if (studentEnrollment.viewASemesterCourses("A")) {
                                                System.out.print("Enter course ID: ");
                                                Scanner scanner7 = new Scanner(System.in);
                                                String courseID = scanner7.nextLine();
                                                Course course7 = CourseListA.courseSearch(courseID.toUpperCase());
                                                if (course7 != null) {
                                                    studentEnrollment.executeDrop(course7, "A");
                                                    if (StudentEnrollment.continuePrompt("Undo previous action (Y/n)?: ")) {
                                                        studentEnrollment.undoDrop(course7, "A");
                                                    }
                                                    if (!StudentEnrollment.continuePrompt("Dropping another course (Y/n)?: ")) {
                                                        break;
                                                    }
                                                } else {
                                                    System.out.println("Wrong Course ID.");
                                                }

                                            } else {
                                                System.out.println("Student is not currently enroled in any course this semester");
                                                System.out.println("Returning to main menu");
                                                break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        while (true) {
                                            if (studentEnrollment.viewASemesterCourses("B")) {
                                                System.out.print("Enter course ID: ");
                                                Scanner scanner8 = new Scanner(System.in);
                                                String courseID8 = scanner8.nextLine();
                                                Course course8 = CourseListB.courseSearch(courseID8.toUpperCase());
                                                if (course8 != null) {
                                                    studentEnrollment.executeDrop(course8, "B");
                                                    if (StudentEnrollment.continuePrompt("Undo previous action (Y/n)?: ")) {
                                                        studentEnrollment.undoEnrol(course8, "B");
                                                    }
                                                    if (!StudentEnrollment.continuePrompt("Dropping another course (Y/n)?: ")) {
                                                        break;
                                                    }
                                                } else {
                                                    System.out.println("Wrong Course ID.");
                                                }

                                            } else {
                                                System.out.println("Student is not currently enroled in any course this semester");
                                                System.out.println("Returning to main menu");
                                                break;
                                            }
                                        }
                                        break;
                                    case 3:
                                        while (true) {
                                            if (studentEnrollment.viewASemesterCourses("C")) {
                                                System.out.print("Enter course ID: ");
                                                Scanner scanner5 = new Scanner(System.in);
                                                String courseID5 = scanner5.nextLine();
                                                Course course5 = CourseListC.courseSearch(courseID5.toUpperCase());
                                                if (course5 != null) {
                                                    studentEnrollment.executeDrop(course5, "C");
                                                    if (StudentEnrollment.continuePrompt("Undo previous action (Y/n)?: ")) {
                                                        studentEnrollment.undoEnrol(course5, "C");
                                                    }
                                                    if (!StudentEnrollment.continuePrompt("Enrolling in another course (Y/n)?: ")) {
                                                        break;
                                                    }
                                                } else {
                                                    System.out.println("Wrong Course ID.");
                                                }

                                            } else {
                                                System.out.println("Student is not currently enroled in any course this semester");
                                                System.out.println("Returning to main menu");
                                                break;
                                            }
                                        }
                                        break;
                                    case 4:
                                        break;
                                    case 5:
                                        System.out.println("Program exits. Have a good day!");
                                        System.exit(0);
                                        break;
                                    default:
                                        System.out.println("No such choice available. Please try again!");
                                }
                                break;
                            case 3:
                                System.out.println("Please choose a semester");
                                System.out.print("1. A\n" + "2. B\n" + "3. C\n" + "4. Return\n" + "5. Quit program\n" + "Your choice: ");
                                Scanner scanner9 = new Scanner(System.in);
                                int choiceUpdateA = scanner9.nextInt();
                                switch (choiceUpdateA) {
                                    case 1:
                                        while (true) {
                                            if (studentEnrollment.viewASemesterCourses("A")) {
                                                System.out.print("Enter the ID of the course you wish to replace: ");
                                                Scanner updateAScanner = new Scanner(System.in);
                                                String oldCourseIDA = updateAScanner.nextLine().toUpperCase();
                                                CourseListA.printCourseList();
                                                System.out.print("Enter the ID of the new course: ");
                                                Scanner updateAScanner1 = new Scanner(System.in);
                                                String newCourseIDA = updateAScanner1.nextLine().toUpperCase();
                                                Course oldCourseA = studentEnrollment.studentCourseSearch(oldCourseIDA, "A");
                                                Course newCourseA = CourseListA.courseSearch(newCourseIDA);
                                                if (oldCourseA != null && newCourseA != null) {
                                                    studentEnrollment.update(oldCourseA, newCourseA, "A");
                                                    break;
                                                }
                                            } else {
                                                System.out.println("Student is not currently enroled in any course this semester");
                                                System.out.println("Returning to main menu");
                                                break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        while (true) {
                                            if (studentEnrollment.viewASemesterCourses("B")) {
                                                System.out.print("Enter the ID of the course you wish to replace: ");
                                                Scanner updateBScanner = new Scanner(System.in);
                                                String oldCourseIDB = updateBScanner.nextLine().toUpperCase();
                                                CourseListB.printCourseList();
                                                System.out.print("Enter the ID of the new course: ");
                                                Scanner updateBScanner1 = new Scanner(System.in);
                                                String newCourseIDB = updateBScanner1.nextLine().toUpperCase();
                                                Course oldCourseB = studentEnrollment.studentCourseSearch(oldCourseIDB, "B");
                                                Course newCourseB = CourseListA.courseSearch(newCourseIDB);
                                                if (oldCourseB != null && newCourseB != null) {
                                                    studentEnrollment.update(oldCourseB, newCourseB, "B");
                                                    break;
                                                }
                                            } else {
                                                System.out.println("Student is not currently enrolled in any course this semester");
                                                System.out.println("Returning to main menu");
                                                break;
                                            }
                                        }
                                        break;
                                    case 3:
                                        while (true) {
                                            if (studentEnrollment.viewASemesterCourses("C")) {
                                                System.out.print("Enter the ID of the course you wish to replace: ");
                                                Scanner updateCScanner = new Scanner(System.in);
                                                String oldCourseIDC = updateCScanner.nextLine().toUpperCase();
                                                CourseListC.printCourseList();
                                                System.out.print("Enter the ID of the new course: ");
                                                Scanner updateCScanner1 = new Scanner(System.in);
                                                String newCourseIDC = updateCScanner1.nextLine().toUpperCase();
                                                Course oldCourseC = studentEnrollment.studentCourseSearch(oldCourseIDC, "C");
                                                Course newCourseC = CourseListA.courseSearch(newCourseIDC);
                                                if (oldCourseC != null && newCourseC != null) {
                                                    studentEnrollment.update(oldCourseC, newCourseC, "C");
                                                    break;
                                                }
                                            } else {
                                                System.out.println("Student is not currently enrolled in any course this semester");
                                                System.out.println("Returning to main menu");
                                                break;
                                            }
                                        }
                                        break;
                                    case 4:
                                        break;
                                    case 5:
                                        System.out.println("Program exits. Have a good day!");
                                        System.exit(0);
                                        break;
                                    default:
                                        System.out.println("No such choice available");
                                        break;
                                }
                                break;
                            case 4:
                                while (true) {
                                    studentEnrollment.viewAllCourses();
                                    if (StudentEnrollment.continuePrompt("Return to main menu (Y/n)?: ")) {
                                        break;
                                    }
                                }
                                break;
                            case 5:
                                while (true) {
                                    System.out.println("Please choose a semester");
                                    System.out.print("1. A\n" + "2. B\n" + "3. C\n" + "4. Return\n" + "5. Quit program\n" + "Your choice: ");
                                    Scanner scanner3 = new Scanner(System.in);
                                    int choiceViewAll = scanner3.nextInt();
                                    switch (choiceViewAll) {
                                        case 1:
                                            studentEnrollment.printAllEnrolments("A");
                                            break;
                                        case 2:
                                            studentEnrollment.printAllEnrolments("B");
                                            break;
                                        case 3:
                                            studentEnrollment.printAllEnrolments("C");
                                            break;
                                        case 4:
                                            break;
                                        case 5:
                                            System.out.println("Program exits. Have a good day!");
                                            System.exit(0);
                                            break;
                                        default:
                                            System.out.println("No such choice available. Please try again");
                                            break;

                                    }
                                    if (!StudentEnrollment.continuePrompt("View another semester (Y/n)?: ")) {
                                        break;
                                    }
                                }
                                break;
                            case 6:
                                if(StudentEnrollment.continuePrompt("Are you sure you want to drop all courses(Y/n)? "))
                                {
                                    StudentEnrollmentVisitor studentEnrolmentDeleter = new StudentEnrollmentVisitor(studentID);
                                    studentEnrolmentDeleter.visit(studentEnrollment);
                                }

                                break;
                            case 7:
                                System.out.println("Program exits. Have a good day!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("No such choice available. Please try again");
                                break;
                        }
                    }
                    catch (InputMismatchException e)
                    {
                        System.out.println("Integer Input only. Please enter a number associated with the desired option!");
                    }
                }
                else {
                    System.out.println("Invalid student ID. Please try again!");
                }
            }

        }
}

